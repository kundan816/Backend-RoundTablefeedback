package com.assignm4.RTFeedbackkkkk.controller;

import com.assignm4.RTFeedbackkkkk.DTO.RTCycleDTO;
import com.assignm4.RTFeedbackkkkk.DTO.RTFeedbackSubmissionDTO;
import com.assignm4.RTFeedbackkkkk.enitity.RTCycle;
import com.assignm4.RTFeedbackkkkk.enitity.RTFeedbackSubmission;
import com.assignm4.RTFeedbackkkkk.service.RTService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequestMapping("/api/rt")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RTController {
    private final RTService rtService;

    @PostMapping("/cycle")
    public ResponseEntity<RTCycle> startRTCycle(@RequestBody RTCycleDTO cycleDTO) {
        return ResponseEntity.ok(rtService.startRTCycle(cycleDTO));
    }

    @PostMapping("/feedback")
    public ResponseEntity<RTFeedbackSubmission> submitRTFeedback(
            @RequestBody RTFeedbackSubmissionDTO submissionDTO) {
        return ResponseEntity.ok(rtService.submitRTFeedback(submissionDTO));
    }

    @PutMapping("/feedback/{id}/admin-update")
    public ResponseEntity<RTFeedbackSubmission> adminUpdateRTFeedback(
            @PathVariable Long id,
            @RequestParam Double overrideRating,
            @RequestParam String reason) {
        return ResponseEntity.ok(rtService.adminUpdateRTFeedback(id, overrideRating, reason));
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