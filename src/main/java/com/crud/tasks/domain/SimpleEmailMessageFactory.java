package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.mail.SimpleMailMessage;

@AllArgsConstructor
@NoArgsConstructor
public class SimpleEmailMessageFactory {
    SimpleMailMessage mailMessage = new SimpleMailMessage();

    public SimpleMailMessage createMailMessage(final Mail mail) {
        mailMessage.setTo(mail.getMailTo());
        if(mail.getToCc() != null) {
            mailMessage.setCc(mail.getToCc());
        }
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        return mailMessage;
    }
}
