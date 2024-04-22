/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common;

import entity.Account;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author admin
 */
public class SendMail {
    static String Link = "http://localhost:9999/swp/confirmation";
    
    public static void sendConfirmationEmail(Account account) {
        // Thông tin cấu hình mail server
        String host = "smtp.gmail.com";
        String port = "587"; // Port sử dụng, thường là 587 cho TLS
        String username = "pvk2kpro1@gmail.com";
        String password = "Lcra mjyv cxim xvgi";

        // Địa chỉ email người nhận
        String toAddress = account.getEmail();

        // Nội dung email
        String subject = "Confirmation Email";
        String message = "Thank you for registering. Please confirm your email by clicking the link: " + Link + "?user="+account.getUser()+"&token="+account.getToken();

        // Tạo Properties để cấu hình phiên làm việc với mail server
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");

        // Tạo Session để nhận diện thông tin xác thực
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(username, password);
            }
        });

        try {
            // Tạo đối tượng MimeMessage
            MimeMessage mimeMessage = new MimeMessage(session);

            // Đặt thông tin người gửi, người nhận và nội dung
            mimeMessage.setFrom(new InternetAddress(username));
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(message);

            // Gửi email
            Transport.send(mimeMessage);

            System.out.println("Confirmation email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
