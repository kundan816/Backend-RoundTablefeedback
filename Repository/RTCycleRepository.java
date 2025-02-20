package com.assignm4.RTFeedbackkkkk.Repository;

import com.assignm4.RTFeedbackkkkk.enitity.RTCycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RTCycleRepository extends JpaRepository<RTCycle, Long> {
    Optional<RTCycle> findByActive(boolean active);
}
