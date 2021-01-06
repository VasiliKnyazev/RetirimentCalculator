package com.luxoft.rcalculator.service.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class MailServiceImpl implements MailService {

    private JavaMailSender emailSender;
    private String emailSubject;

    public MailServiceImpl(JavaMailSender emailSender,
                           @Value("${sending.mail.email.subject}")String emailSubject) {
        this.emailSender = emailSender;
        this.emailSubject = emailSubject;
    }

    @Async
    @Override
    public void sendCanRetirementYearMessage(String nameFrom, String emailFrom, String emailTo, String message) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(emailTo);
            messageHelper.setSubject(emailSubject);
            messageHelper.setText(message, true);
        };
        try {
            emailSender.send(messagePreparator);
            System.out.println("Sending retirement result to " + emailTo + " was successful");
        } catch (MailException e) {
            System.out.println("Sending retirement result to " + emailTo + " failed");
            e.printStackTrace();
        }
    }
}
