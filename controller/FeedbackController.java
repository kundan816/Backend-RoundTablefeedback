package com.assignm4.RTFeedbackkkkk.controller;

import com.assignm4.RTFeedbackkkkk.DTO.FeedbackFormDTO;
import com.assignm4.RTFeedbackkkkk.enitity.FeedbackForm;
import com.assignm4.RTFeedbackkkkk.service.FeedbackService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequestMapping("/api/feedback")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FeedbackController {
    private final FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<FeedbackForm> submitFeedback(@RequestBody FeedbackFormDTO feedbackDTO) {
        return ResponseEntity.ok(feedbackService.submitFeedback(feedbackDTO));
    }

    @GetMapping("/employee/{email}")
    public ResponseEntity<List<FeedbackForm>> getEmployeeFeedbacks(@PathVariable String email) {
        return ResponseEntity.ok(feedbackService.getEmployeeFeedbacks(email));
    }
}
