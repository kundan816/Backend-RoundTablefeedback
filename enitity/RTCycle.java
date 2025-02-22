package com.assignm4.RTFeedbackkkkk.enitity;

import lombok.*;
import jakarta.persistence.*;
import java.time.YearMonth;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RTCycle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private YearMonth startMonth;
    private YearMonth endMonth;
    private boolean active;

    @OneToMany(mappedBy = "rtCycle", cascade = CascadeType.ALL)
    private List<RTFeedbackSubmission> submissions;
}
