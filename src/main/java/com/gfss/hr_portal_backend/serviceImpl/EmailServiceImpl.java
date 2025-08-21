package com.gfss.hr_portal_backend.serviceImpl;

import com.gfss.hr_portal_backend.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendInterviewNotification(List<String> candidateNames, List<String> candidateEmails,
                                          String title, String description, String interviewDate, String hrName) {
        try {
            for (int i = 0; i < candidateEmails.size(); i++) {
                String email = candidateEmails.get(i);
                String name = candidateNames.size() > i ? candidateNames.get(i) : "Candidate";

                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true);

                helper.setTo(email);
                helper.setSubject("Interview Scheduled: " + title);

                String content = """
                        Dear %s,<br><br>
                        You are invited for an interview.<br><br>
                        <b>Title:</b> %s <br>
                        <b>Description:</b> %s <br>
                        <b>Date:</b> %s <br>
                        <b>HR:</b> %s <br><br>
                        Regards,<br>
                        HR Team
                        """.formatted(name, title, description, interviewDate, hrName);

                helper.setText(content, true);

                mailSender.send(message);
            }
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send interview notification emails", e);
        }
    }
}
