package com.luxoft.rcalculator.service.mail;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

@Component
public class MailServiceImpl implements MailService {

    private JavaMailSender emailSender;
    private String emailSubject = "Calculate Retirement Result";

    public MailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

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
            System.out.println("Sending invitation to " + emailTo + " was successful");
        } catch (MailException e) {
            System.out.println("Sending invitation to " + emailTo + " failed");
            e.printStackTrace();
        }
    }
}
