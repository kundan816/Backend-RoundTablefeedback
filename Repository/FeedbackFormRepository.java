package com.assignm4.RTFeedbackkkkk.Repository;

import com.assignm4.RTFeedbackkkkk.enitity.FeedbackForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FeedbackFormRepository extends JpaRepository<FeedbackForm, Long> {
    List<FeedbackForm> findByUserIdAndFeedbackMonthBetween(
            Long userId, LocalDate startDate, LocalDate endDate);

    boolean existsByUserIdAndFeedbackMonth(Long userId, LocalDate feedbackMonth);
}
