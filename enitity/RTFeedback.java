package com.assignm4.RTFeedbackkkkk.enitity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "rt_feedbacks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RTFeedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "rt_cycle_id")
    private RTCycle rtCycle;

    private String additionalComments;
    private Double rating;
    private String grade;
    private boolean approved;
    private String adminComments;
}
