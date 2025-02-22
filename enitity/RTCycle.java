package com.assignm4.RTFeedbackkkkk.enitity;
import com.assignm4.RTFeedbackkkkk.utils.YearMonthConverter;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import jakarta.persistence.*;
import java.time.YearMonth;
import java.util.ArrayList;
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

    @Convert(converter = YearMonthConverter.class)
    private YearMonth startMonth;

    @Convert(converter = YearMonthConverter.class)
    private YearMonth endMonth;

    private boolean active;

    @OneToMany(mappedBy = "rtCycle", cascade = CascadeType.ALL)
    @JsonManagedReference  // Prevent infinite recursion
    private List<RTFeedbackSubmission> submissions;
}