package com.assignm4.RTFeedbackkkkk.enitity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonBackReference  // Prevents recursion
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "rt_cycle_id")
    @JsonIgnore  // This prevents recursion by not including rtCycle in JSON response
    private RTCycle rtCycle;


    private String additionalComments;
    private Double calculatedRating;
    private String calculatedGrade;

    private Double adminOverrideRating;
    private String adminOverrideGrade;
    private String adminReason;

    @OneToMany(mappedBy = "rtFeedbackSubmission", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<FeedbackForm> includedFeedbacks;


}
