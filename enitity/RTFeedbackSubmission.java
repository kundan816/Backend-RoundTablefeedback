package com.assignm4.RTFeedbackkkkk.enitity;

import lombok.*;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RTFeedbackSubmission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "rt_cycle_id")
    private RTCycle rtCycle;

    private String additionalComments;
    private Double calculatedRating;
    private String calculatedGrade;

    private Double adminOverrideRating;
    private String adminOverrideGrade;
    private String adminReason;

    @OneToMany
    @JoinColumn(name = "rt_feedback_submission_id")
    private List<FeedbackForm> includedFeedbacks;
}
