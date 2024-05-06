package vn.bookstore.backend.service;

public interface IEmailService {

    public void sendEmail(String from, String to, String subject, String body);
}
