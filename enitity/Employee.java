package com.assignm4.RTFeedbackkkkk.enitity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String name;

    @Enumerated(EnumType.STRING)
    private BandLevel bandLevel;

    private boolean isAdmin;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<FeedbackForm> feedbackForms;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<RTFeedbackSubmission> rtFeedbackSubmissions;
}
