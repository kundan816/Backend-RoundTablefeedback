package com.assignm4.RTFeedbackkkkk.DTO;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminOverrideDTO {
    private Long id;
    private Long rtSubmissionId;
    private double finalRating;
    private String reason;
}
