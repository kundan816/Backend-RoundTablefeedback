package com.assignm4.RTFeedbackkkkk.DTO;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RTCycleDTO {
    private Long id;
    private String startMonth;
    private int startYear;
    private String endMonth;
    private int endYear;
}
