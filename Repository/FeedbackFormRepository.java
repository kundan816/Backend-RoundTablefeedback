package com.assignm4.RTFeedbackkkkk.Repository;

import com.assignm4.RTFeedbackkkkk.enitity.FeedbackForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackFormRepository extends JpaRepository<FeedbackForm, Long> {}