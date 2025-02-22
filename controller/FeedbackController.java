package com.assignm4.RTFeedbackkkkk.controller;

import com.assignm4.RTFeedbackkkkk.DTO.FeedbackFormDTO;
import com.assignm4.RTFeedbackkkkk.Repository.EmployeeRepository;
import com.assignm4.RTFeedbackkkkk.enitity.Employee;
import com.assignm4.RTFeedbackkkkk.enitity.FeedbackForm;
import com.assignm4.RTFeedbackkkkk.enitity.RTCycle;
import com.assignm4.RTFeedbackkkkk.service.FeedbackService;
import com.assignm4.RTFeedbackkkkk.service.RTService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/feedback")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FeedbackController {
    private final FeedbackService feedbackService;
    private final EmployeeRepository employeeRepository;
    private final RTService rtService; // Renamed from RTCycleService to match your RTService

    @PostMapping
    public ResponseEntity<String> submitFeedback(@RequestBody FeedbackFormDTO feedbackDTO) {
        //  Fetch the active RT cycle safely
        Optional<RTCycle> activeCycleOptional = rtService.getActiveCycle();
        if (activeCycleOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("RT cycle is not active. Cannot submit feedback.");
        }
        RTCycle activeCycle = activeCycleOptional.get();

        //  Validate if the employee exists
        Optional<Employee> employeeOptional = employeeRepository.findByEmail(feedbackDTO.getEmployeeEmail());
        if (employeeOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found.");
        }

        //  Submit feedback
        FeedbackForm feedback = feedbackService.submitFeedback(feedbackDTO);
        return ResponseEntity.ok("Feedback submitted successfully with ID: " + feedback.getId());
    }

    @GetMapping("/employee/{email}")
    public ResponseEntity<List<FeedbackForm>> getEmployeeFeedbacks(@PathVariable String email) {
        return ResponseEntity.ok(feedbackService.getEmployeeFeedbacks(email));
    }
}
