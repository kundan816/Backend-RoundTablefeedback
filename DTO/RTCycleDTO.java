package com.assignm4.RTFeedbackkkkk.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RTCycleDTO {
    private Long id;
    private String startDate;
    private String endDate;
    private boolean active;
}
