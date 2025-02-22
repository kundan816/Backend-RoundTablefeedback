package com.assignm4.RTFeedbackkkkk.Repository;

import com.assignm4.RTFeedbackkkkk.enitity.Employee;
import com.assignm4.RTFeedbackkkkk.enitity.FeedbackForm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

public interface FeedbackFormRepository extends JpaRepository<FeedbackForm, Long> {
    List<FeedbackForm> findByEmployeeAndFeedbackMonthBetweenOrderByFeedbackMonthAsc(
            Employee employee,
            YearMonth startMonth,
            YearMonth endMonth
    );
    Optional<FeedbackForm> findByEmployeeAndFeedbackMonth(
            Employee employee, YearMonth feedbackMonth);
}
