package com.assignm4.RTFeedbackkkkk.Repository;

import com.assignm4.RTFeedbackkkkk.enitity.Employee;
import com.assignm4.RTFeedbackkkkk.enitity.RTCycle;
import com.assignm4.RTFeedbackkkkk.enitity.RTFeedbackSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RTFeedbackSubmissionRepository extends JpaRepository<RTFeedbackSubmission, Long> {
    Optional<RTFeedbackSubmission> findByEmployeeIdAndRtCycleId(Long employeeId, Long rtCycleId);
    // Add a query to fetch feedback by employee email
    @Query("SELECT r FROM RTFeedbackSubmission r WHERE r.employee.email = :email")
    List<RTFeedbackSubmission> findByEmployeeEmail(@Param("email") String email);

}