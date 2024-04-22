package utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {

    public static final String SENDER_EMAIL = "pvk2kpro1@gmail.com";
    public static final String SENDER_PASSWORD = "Lcra mjyv cxim xvgi";

    public static void main(String[] args) {
        // Provide your email credentials and mail server details
        String receiverEmail = "khanhlbhe130965@fpt.edu.vn";
        String emailTitle = "Test Email";
        String emailContent = "Hello, this is a test email.";
        sendEmail(receiverEmail, emailTitle, emailContent);
    }

    public static void sendEmail(String receiverEmail, String emailTitle, String emailContent) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com"); // Change this to your mail server
        properties.put("mail.smtp.port", "587"); // Change this to your mail server's port
        properties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER_EMAIL, SENDER_PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SENDER_EMAIL));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiverEmail));
            message.setSubject(emailTitle);
            message.setText(emailContent);

            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("Failed to send email. Error: " + e.getMessage());
        }
    }
}
//import java.util.Properties;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//
//public class EmailSender {
//    public static void main(String[] args) {
//        // Cấu hình thuộc tính
//        Properties properties = new Properties();
//        properties.setProperty("mail.smtp.host", "smtp.gmail.com");
//        properties.setProperty("mail.smtp.port", "587");
//        properties.setProperty("mail.smtp.auth", "true");
//        properties.setProperty("mail.smtp.starttls.enable", "true");
//        properties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
//
//        // Tạo phiên gửi thư
//        Session session = Session.getDefaultInstance(properties);
//
//        try {
//            // Tạo đối tượng MimeMessage
//            MimeMessage message = new MimeMessage(session);
//            // Thiết lập thông tin người gửi, người nhận, tiêu đề, nội dung thư, vv.
//            // ...
//
//            // Gửi thư
//            Transport.send(message);
//
//            System.out.println("Thư đã được gửi thành công!");
//        } catch (Exception e) {
//            e.printStackTrace();
//              System.err.println("Failed to send email. Error: " + e.getMessage());
//        }
//    }
//}