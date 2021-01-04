package com.luxoft.rcalculator.service.mail;

public interface MailService {

    void sendCanRetirementYearMessage(
            String nameFrom,
            String emailFrom,
            String emailTo,
            String message);

}
