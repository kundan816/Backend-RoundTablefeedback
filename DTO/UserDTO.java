package com.assignm4.RTFeedbackkkkk.DTO;

import com.assignm4.RTFeedbackkkkk.enitity.BandLevel;
import com.assignm4.RTFeedbackkkkk.enitity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String email;
    private UserRole role;
    private BandLevel bandLevel;
}
