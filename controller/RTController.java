package com.assignm4.RTFeedbackkkkk.controller;

import com.assignm4.RTFeedbackkkkk.DTO.RTCycleDTO;
import com.assignm4.RTFeedbackkkkk.DTO.RTFeedbackSubmissionDTO;
import com.assignm4.RTFeedbackkkkk.Repository.EmployeeRepository;
import com.assignm4.RTFeedbackkkkk.enitity.Employee;
import com.assignm4.RTFeedbackkkkk.enitity.RTCycle;
import com.assignm4.RTFeedbackkkkk.enitity.RTFeedbackSubmission;
import com.assignm4.RTFeedbackkkkk.service.RTService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rt")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RTController {
    private final RTService rtService;
    private final EmployeeRepository employeeRepository;


    @PostMapping("/cycle")
    public ResponseEntity<String> startRTCycle(@RequestBody  RTCycleDTO cycleDTO, @RequestParam String email) {
        Optional<Employee> employeeOptional = employeeRepository.findByEmail(email);

        if (employeeOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
        }

        Employee employee = employeeOptional.get(); // Unwrap the Optional safely

        if (!employee.isAdmin()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only admins can start RT cycle.");
        }

        RTCycle rtCycle = rtService.startRTCycle(cycleDTO);
        return ResponseEntity.ok("RT cycle started successfully: " + rtCycle.getId());
    }

    @GetMapping("/cycle")
    public ResponseEntity<String> getActiveRTCycle() {
        Optional<RTCycle> activeCycleOptional = rtService.getActiveCycle();

        if (activeCycleOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No active RT cycle found.");
        }

        RTCycle activeCycle = activeCycleOptional.get();
        return ResponseEntity.ok("Active RT cycle found with ID: " + activeCycle.getId());
    }


    @PostMapping("/feedback")
    public ResponseEntity<RTFeedbackSubmission> submitRTFeedback(
            @RequestBody RTFeedbackSubmissionDTO submissionDTO) {
        return ResponseEntity.ok(rtService.submitRTFeedback(submissionDTO));
    }

    @PutMapping("/feedback/{id}/admin-update")
    public ResponseEntity<RTFeedbackSubmission> adminUpdateRTFeedback(
            @RequestHeader("adminEmail") String adminEmail,  // Get admin email from request header
            @PathVariable Long id,
            @RequestParam Double overrideRating,
            @RequestParam String reason) {

        RTFeedbackSubmission updatedFeedback = rtService.adminUpdateRTFeedback(adminEmail, id, overrideRating, reason);
        return ResponseEntity.ok(updatedFeedback);
    }
    @GetMapping("/feedback")
    public ResponseEntity<List<RTFeedbackSubmission>> getAllRTFeedbacks() {
        return ResponseEntity.ok(rtService.getAllRTFeedbacks());
    }

    @GetMapping("/feedback/{id}")
    public ResponseEntity<RTFeedbackSubmission> getRTFeedback(@PathVariable Long id) {
        return ResponseEntity.ok(rtService.getRTFeedback(id));
    }
}