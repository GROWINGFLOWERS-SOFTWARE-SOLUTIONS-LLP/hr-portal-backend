package com.gfss.hr_portal_backend.service;

import java.util.List;

public interface EmailService {
    void sendInterviewNotification(List<String> candidateNames, List<String> candidateEmails, String title, String description, String interviewDate, String hrName);
}
