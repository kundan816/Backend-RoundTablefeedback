package com.assignm4.RTFeedbackkkkk.Repository;

import com.assignm4.RTFeedbackkkkk.enitity.RTCycle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RTCycleRepository extends JpaRepository<RTCycle, Long> {
    Optional<RTCycle> findByActiveTrue();
}
