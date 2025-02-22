package com.assignm4.RTFeedbackkkkk.DTO;

import lombok.Data;
import java.time.YearMonth;

@Data
public class RTCycleDTO {
    private Long id;
    private YearMonth startMonth;
    private YearMonth endMonth;
    private boolean active;
}
