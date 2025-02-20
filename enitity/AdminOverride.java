package com.assignm4.RTFeedbackkkkk.enitity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "admin_overrides")
public class AdminOverride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "rtSubmission_id", nullable = false)
    private RTSubmission rtSubmission;

    private double finalRating;
    private String reason;
}
