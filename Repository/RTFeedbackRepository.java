package com.assignm4.RTFeedbackkkkk.Repository;


import com.assignm4.RTFeedbackkkkk.enitity.RTFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RTFeedbackRepository extends JpaRepository<RTFeedback, Long> {
    List<RTFeedback> findByUserId(Long userId);
    Optional<RTFeedback> findByUserIdAndRtCycleId(Long userId, Long rtCycleId);
}