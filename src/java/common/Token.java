/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author admin
 */
public class Token {

    // Đối tượng chứa thông tin token và thời gian tạo token
    public static class TokenInfo {

        private String token;
        private Timestamp creationTime;

        public TokenInfo(String token, Timestamp creationTime) {
            this.token = token;
            this.creationTime = creationTime;
        }

        public String getToken() {
            return token;
        }

        public Timestamp getCreationTime() {
            return creationTime;
        }
    }

    // Hàm tạo chuỗi ngẫu nhiên
    private static String generateRandomString() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] randomBytes = new byte[20];
        secureRandom.nextBytes(randomBytes);
        return bytesToHex(randomBytes);
    }

    // Hàm chuyển đổi mảng byte thành chuỗi hex
    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexStringBuilder = new StringBuilder();
        for (byte b : bytes) {
            hexStringBuilder.append(String.format("%02x", b));
        }
        return hexStringBuilder.toString();
    }

    // Hàm tạo token và trả về đối tượng TokenInfo
    public static TokenInfo generateTokenInfo() {
        try {
            // Kết hợp thời gian hiện tại và chuỗi ngẫu nhiên để tạo token
            // Lấy thời gian hiện tại
            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

            // Chuyển Timestamp thành Calendar
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currentTimestamp);

            // Thêm một giờ
            calendar.add(Calendar.HOUR_OF_DAY, 1);

            // Lấy thời điểm sau khi thêm một giờ
            Timestamp newTimestamp = new Timestamp(calendar.getTimeInMillis());
            String randomString = generateRandomString();
            String input = currentTimestamp.toString() + randomString;

            // Sử dụng SHA-256 để băm chuỗi
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(input.getBytes());

            // Chuyển đổi kết quả băm thành chuỗi hex
            String token = bytesToHex(hashedBytes);

            // Trả về đối tượng TokenInfo
            return new TokenInfo(token, newTimestamp);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        // Sử dụng hàm generateTokenInfo để tạo token và lấy thông tin
        TokenInfo tokenInfo = generateTokenInfo();
        System.out.println("Generated Token: " + tokenInfo.getToken());
        System.out.println("Token Creation Time: " + tokenInfo.getCreationTime());
    }
}
