package vn.bookstore.backend.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendEmail(String from, String to, String subject, String body) {
        //MimeMailMessage => đính kèm media, tập tin, hình ảnh
        //SimpleMailMessage => nội dung thông thường
        //SimpleMailMessage message = new SimpleMailMessage();

        //message.setFrom(from);
        //message.setTo(to);
        //message.setSubject(subject);
        //message.setText(body);

        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);
//            helper.addAttachment();  dinh kem file

            // send email
            mailSender.send(message);
        } catch (MessagingException e) {
            // handle exception
            throw new RuntimeException(e);
        }

        // send email
        mailSender.send(message);
    }
}
