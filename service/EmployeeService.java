package com.assignm4.RTFeedbackkkkk.service;


import com.assignm4.RTFeedbackkkkk.DTO.LoginDTO;
import com.assignm4.RTFeedbackkkkk.Repository.EmployeeRepository;
import com.assignm4.RTFeedbackkkkk.enitity.Employee;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public Employee login(LoginDTO loginDTO) {
        return employeeRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }
}
