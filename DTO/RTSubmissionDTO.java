package com.assignm4.RTFeedbackkkkk.DTO;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RTSubmissionDTO {
    private Long id;
    private Long employeeId;
    private Long rtCycleId;
    private double rating;
    private String comments;
    private boolean submitted;
}