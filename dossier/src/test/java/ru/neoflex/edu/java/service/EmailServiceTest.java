package ru.neoflex.edu.java.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmailServiceTest {
    @Mock
    private JavaMailSender javaMailSender;

    @InjectMocks
    private EmailService emailService;

    @Test
    void sendMessage() {
        String to = "to@mail.ru";
        String subject = "Subject";
        String text = "Content";

        emailService.sendMessage(to, subject, text);

        SimpleMailMessage expectedMessage = new SimpleMailMessage();
        expectedMessage.setTo(to);
        expectedMessage.setSubject(subject);
        expectedMessage.setText(text);

        verify(javaMailSender, times(1)).send(expectedMessage);
    }
}