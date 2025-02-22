package com.assignm4.RTFeedbackkkkk.Repository;

import com.assignm4.RTFeedbackkkkk.enitity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);
}