package com.assignm4.RTFeedbackkkkk.enitity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import jakarta.persistence.*;
import java.time.YearMonth;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedbackForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonBackReference
    private Employee employee;

    private YearMonth feedbackMonth;
    private String managerEmail;

    // Common fields
    private String comments;

    // B6 specific fields
    private Integer leadership;
    private Integer orgContribution;
    private Integer assistingPresales;

    // B7 specific fields
    private Integer timelyDelivery;
    private Integer codeQuality;
    private Integer clientCommunication;

    // B8 specific fields
    private Integer improvement;
}