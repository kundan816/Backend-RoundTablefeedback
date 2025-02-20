package com.assignm4.RTFeedbackkkkk.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackFormDTO {
    private Long id;
    private Long userId;
    private String feedbackMonth;
    private String managerEmail;
    private String comments;

    // Band specific fields
    private Integer leadership;
    private Integer orgContribution;
    private Integer preSalesAssistance;
    private Integer timelyDelivery;
    private Integer codeQuality;
    private Integer clientCommunication;
    private Integer improvement;
}
