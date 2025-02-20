package com.assignm4.RTFeedbackkkkk.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RTFeedbackDTO {
    private Long id;
    private Long userId;
    private Long rtCycleId;
    private String additionalComments;
    private Double rating;
    private String grade;
    private boolean approved;
    private String adminComments;
    private List<FeedbackFormDTO> collatedFeedbacks;
}
