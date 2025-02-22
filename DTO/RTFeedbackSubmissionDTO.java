package com.assignm4.RTFeedbackkkkk.DTO;

import lombok.Data;
import java.util.List;

@Data
public class RTFeedbackSubmissionDTO {
    private Long id;
    private String employeeEmail;
    private Long rtCycleId;
    private String additionalComments;
    private Double calculatedRating;
    private String calculatedGrade;
    private Double adminOverrideRating;
    private String adminOverrideGrade;
    private String adminReason;
    private List<FeedbackFormDTO> includedFeedbacks;
}
