package com.assignm4.RTFeedbackkkkk.DTO;

import lombok.Data;
import java.time.YearMonth;

@Data
public class FeedbackFormDTO {
    private Long  id;
    private String  employeeEmail;
    private YearMonth feedbackMonth;
    private String managerEmail;
    private String comments;

    // B6 fields
    private Integer leadership;
    private Integer orgContribution;
    private Integer assistingPresales;

    // B7 fields
    private Integer timelyDelivery;
    private Integer codeQuality;
    private Integer clientCommunication;

    // B8 fields
    private Integer improvement;
}
