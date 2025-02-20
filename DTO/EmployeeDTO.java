package com.assignm4.RTFeedbackkkkk.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDTO {
    private Long id;
    private String name;
    private String email;
    private String bandLevel;
}