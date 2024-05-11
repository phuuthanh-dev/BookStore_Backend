package vn.bookstore.backend.service.email;

public interface IEmailService {

    public void sendEmail(String from, String to, String subject, String body);
}
