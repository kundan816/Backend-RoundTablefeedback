package com.assignm4.RTFeedbackkkkk.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;

    @Value("${hr.email}")
    private String hrEmail;

    public void sendFeedbackNotification(String managerEmail, String employeeEmail, String month) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(managerEmail);
        message.setCc(hrEmail);
        message.setSubject("New Feedback Submission - " + month);
        message.setText("A new feedback has been submitted by " + employeeEmail + " for " + month);
        mailSender.send(message);
    }
}
