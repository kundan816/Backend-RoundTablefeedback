package com.assignm4.RTFeedbackkkkk.Repository;

import com.assignm4.RTFeedbackkkkk.enitity.Employee;
import com.assignm4.RTFeedbackkkkk.enitity.RTCycle;
import com.assignm4.RTFeedbackkkkk.enitity.RTFeedbackSubmission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RTFeedbackSubmissionRepository extends JpaRepository<RTFeedbackSubmission, Long> {
    Optional<RTFeedbackSubmission> findByEmployeeAndRtCycle(
            Employee employee, RTCycle rtCycle);
}