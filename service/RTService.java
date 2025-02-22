package com.assignm4.RTFeedbackkkkk.service;

import com.assignm4.RTFeedbackkkkk.DTO.RTCycleDTO;
import com.assignm4.RTFeedbackkkkk.DTO.RTFeedbackSubmissionDTO;
import com.assignm4.RTFeedbackkkkk.Repository.EmployeeRepository;
import com.assignm4.RTFeedbackkkkk.Repository.FeedbackFormRepository;
import com.assignm4.RTFeedbackkkkk.Repository.RTCycleRepository;
import com.assignm4.RTFeedbackkkkk.Repository.RTFeedbackSubmissionRepository;
import com.assignm4.RTFeedbackkkkk.enitity.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class RTService {
    private final RTCycleRepository rtCycleRepository;
    private final RTFeedbackSubmissionRepository rtFeedbackSubmissionRepository;
    private final FeedbackFormRepository feedbackFormRepository;
    private final EmployeeRepository employeeRepository;

    /**
     * Starts a new RT cycle and deactivates any existing active cycle
     */
    public RTCycle startRTCycle(RTCycleDTO dto) {
        // Validate the date range
        if (dto.getStartMonth().isAfter(dto.getEndMonth())) {
            throw new RuntimeException("Start month cannot be after end month");
        }

        // Deactivate any active cycle
        rtCycleRepository.findByActiveTrue()
                .ifPresent(cycle -> {
                    cycle.setActive(false);
                    rtCycleRepository.save(cycle);
                });

        RTCycle newCycle = RTCycle.builder()
                .startMonth(dto.getStartMonth())
                .endMonth(dto.getEndMonth())
                .active(true)
                .build();

        return rtCycleRepository.save(newCycle);
    }

    /**
     * Gets currently active RT cycle
     */
    public Optional<RTCycle> getActiveCycle() {
        return rtCycleRepository.findByActiveTrue();
    }

    /**
     * Calculates the performance rating based on band level and feedback parameters
     */
    private double calculateRating(List<FeedbackForm> feedbacks, BandLevel bandLevel) {
        if (feedbacks.isEmpty()) return 0.0;

        double totalRating = 0.0;
        for (FeedbackForm feedback : feedbacks) {
            double monthlyRating = switch (bandLevel) {
                case B6 -> {
                    if (feedback.getLeadership() == null ||
                            feedback.getOrgContribution() == null ||
                            feedback.getAssistingPresales() == null) {
                        throw new RuntimeException("Missing required parameters for B6 rating calculation");
                    }
                    yield (feedback.getLeadership() +
                            feedback.getOrgContribution() +
                            feedback.getAssistingPresales()) / 3.0;
                }
                case B7 -> {
                    if (feedback.getTimelyDelivery() == null ||
                            feedback.getCodeQuality() == null ||
                            feedback.getClientCommunication() == null) {
                        throw new RuntimeException("Missing required parameters for B7 rating calculation");
                    }
                    yield (feedback.getTimelyDelivery() +
                            feedback.getCodeQuality() +
                            feedback.getClientCommunication()) / 3.0;
                }
                case B8 -> {
                    if (feedback.getTimelyDelivery() == null ||
                            feedback.getCodeQuality() == null ||
                            feedback.getImprovement() == null) {
                        throw new RuntimeException("Missing required parameters for B8 rating calculation");
                    }
                    yield (feedback.getTimelyDelivery() +
                            feedback.getCodeQuality() +
                            feedback.getImprovement()) / 3.0;
                }
            };
            totalRating += monthlyRating;
        }

        // Calculate final rating (out of 100)
        return (totalRating / feedbacks.size()) * 10;
    }

    /**
     * Determines grade based on the calculated rating
     */
    private String calculateGrade(double rating) {
        if (rating >= 90) return "Above";
        if (rating >= 80) return "Meets-Above";
        if (rating >= 70) return "Meets";
        if (rating >= 60) return "Meets-Below";
        return "Below";
    }


    public RTFeedbackSubmission submitRTFeedback(RTFeedbackSubmissionDTO dto) {
        Employee employee = employeeRepository.findByEmail(dto.getEmployeeEmail())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        RTCycle cycle = rtCycleRepository.findById(dto.getRtCycleId())
                .orElseThrow(() -> new RuntimeException("RT Cycle not found"));

        if (!cycle.isActive()) {
            throw new RuntimeException("Cannot submit feedback for inactive RT cycle");
        }

        if (rtFeedbackSubmissionRepository.findByEmployeeIdAndRtCycleId(
                employee.getId(), cycle.getId()).isPresent()) {
            throw new RuntimeException("RT Feedback already submitted for this cycle");
        }

        List<FeedbackForm> cycleFeedbacks = feedbackFormRepository
                .findByEmployeeAndFeedbackMonthBetweenOrderByFeedbackMonthAsc(
                        employee,
                        cycle.getStartMonth(),
                        cycle.getEndMonth()
                );

        // Fixed monthsBetween calculation
        long monthsBetween = ChronoUnit.MONTHS.between(
                cycle.getStartMonth(),
                cycle.getEndMonth().plusMonths(1)  // Add 1 to include the end month
        );

        long requiredFeedbacks = (long) Math.ceil(monthsBetween * 0.75);

        if (cycleFeedbacks.size() < requiredFeedbacks) {
            throw new RuntimeException(
                    String.format("Insufficient feedback forms. Have %d, need at least %d forms to submit RT feedback",
                            cycleFeedbacks.size(), requiredFeedbacks)
            );
        }

        double rating = calculateRating(cycleFeedbacks, employee.getBandLevel());
        String grade = calculateGrade(rating);

        RTFeedbackSubmission submission = RTFeedbackSubmission.builder()
                .employee(employee)
                .rtCycle(cycle)
                .additionalComments(dto.getAdditionalComments())
                .calculatedRating(rating)
                .calculatedGrade(grade)
                .includedFeedbacks(cycleFeedbacks)
                .build();

        return rtFeedbackSubmissionRepository.save(submission);
    }
    public List<FeedbackForm> getEligibleFeedbackForms(String email, Long cycleId) {
        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        RTCycle cycle = rtCycleRepository.findById(cycleId)
                .orElseThrow(() -> new RuntimeException("RT Cycle not found"));

        // Fixed line 242
        return feedbackFormRepository.findByEmployeeAndFeedbackMonthBetweenOrderByFeedbackMonthAsc(
                employee,
                cycle.getStartMonth(),
                cycle.getEndMonth()
        );
    }

    /**
     * Checks if employee has sufficient feedback forms for RT submission
     */
    public boolean hasRequiredFeedbackCount(String email, Long cycleId) {
        List<FeedbackForm> eligibleForms = getEligibleFeedbackForms(email, cycleId);
        RTCycle cycle = rtCycleRepository.findById(cycleId)
                .orElseThrow(() -> new RuntimeException("RT Cycle not found"));

        // Fixed monthsBetween calculation
        long monthsBetween = ChronoUnit.MONTHS.between(
                cycle.getStartMonth(),
                cycle.getEndMonth().plusMonths(1)  // Add 1 to include the end month
        );

        long requiredFeedbacks = (long) Math.ceil(monthsBetween * 0.75);

        return eligibleForms.size() >= requiredFeedbacks;
    }

    public RTFeedbackSubmission adminUpdateRTFeedback(Long submissionId, Double overrideRating, String reason) {
        RTFeedbackSubmission submission = rtFeedbackSubmissionRepository.findById(submissionId)
                .orElseThrow(() -> new RuntimeException("RT Feedback submission not found"));

        submission.setAdminOverrideRating(overrideRating);
        submission.setAdminOverrideGrade(calculateGrade(overrideRating));
        submission.setAdminReason(reason);

        return rtFeedbackSubmissionRepository.save(submission);
    }

    public List<RTFeedbackSubmission> getAllRTFeedbacks() {
        return rtFeedbackSubmissionRepository.findAll();
    }

    public RTFeedbackSubmission getRTFeedback(Long id) {
        return rtFeedbackSubmissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("RT Feedback submission not found"));
    }

}
