package com.assignm4.RTFeedbackkkkk.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedbackFormDTO {
    private Long id;
    private Long employeeId;
    private String month;
    private int year;
    private int param1;
    private int param2;
    private int param3;
    private String comments;
}