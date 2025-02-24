package com.assignm4.RTFeedbackkkkk.service;


import com.assignm4.RTFeedbackkkkk.DTO.FeedbackFormDTO;
import com.assignm4.RTFeedbackkkkk.Repository.EmployeeRepository;
import com.assignm4.RTFeedbackkkkk.Repository.FeedbackFormRepository;
import com.assignm4.RTFeedbackkkkk.enitity.Employee;
import com.assignm4.RTFeedbackkkkk.enitity.FeedbackForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    private final FeedbackFormRepository feedbackRepository;
    private final EmployeeRepository employeeRepository;
    private final EmailService emailService;

    public FeedbackForm submitFeedback(FeedbackFormDTO dto) {
        Employee employee = employeeRepository.findByEmail(dto.getEmployeeEmail())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // Check if feedback already exists for the month
        if (feedbackRepository.findByEmployeeAndFeedbackMonth(
                employee, dto.getFeedbackMonth()).isPresent()) {
            throw new RuntimeException("Feedback already exists for this month");
        }

        FeedbackForm feedback = FeedbackForm.builder()
                .employee(employee)
                .feedbackMonth(dto.getFeedbackMonth())
                .managerEmail(dto.getManagerEmail())
                .comments(dto.getComments())
                .build();

        // Set fields based on band level
        switch (employee.getBandLevel()) {
            case B6:
                feedback.setLeadership(dto.getLeadership());
                feedback.setOrgContribution(dto.getOrgContribution());
                feedback.setAssistingPresales(dto.getAssistingPresales());
                break;
            case B7:
                feedback.setTimelyDelivery(dto.getTimelyDelivery());
                feedback.setCodeQuality(dto.getCodeQuality());
                feedback.setClientCommunication(dto.getClientCommunication());
                break;
            case B8:
                feedback.setTimelyDelivery(dto.getTimelyDelivery());
                feedback.setCodeQuality(dto.getCodeQuality());
                feedback.setImprovement(dto.getImprovement());
                break;
        }

        FeedbackForm savedFeedback = feedbackRepository.save(feedback);

        // Send email notification
        emailService.sendFeedbackNotification(
                dto.getManagerEmail(),
                dto.getEmployeeEmail(),
                dto.getFeedbackMonth().toString()
        );

        return savedFeedback;
    }

    public List<FeedbackForm> getEmployeeFeedbacks(String email) {
        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return feedbackRepository.findAll().stream()
                .filter(f -> f.getEmployee().equals(employee))
                .toList();
    }
}
