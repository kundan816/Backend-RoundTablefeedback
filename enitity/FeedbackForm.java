package com.assignm4.RTFeedbackkkkk.enitity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "feedback_forms")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDate feedbackMonth;
    private String managerEmail;

    // Common fields for all band levels
    private String comments;

    // B6 specific fields
    private Integer leadership;
    private Integer orgContribution;
    private Integer preSalesAssistance;

    // B7 specific fields
    private Integer timelyDelivery;
    private Integer codeQuality;
    private Integer clientCommunication;

    // B8 specific fields
    private Integer improvement;
}