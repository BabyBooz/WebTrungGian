package utils;

import java.security.SecureRandom;

public class PasswordGenerator {
    public static void main(String[] args) {
        String generatedPassword = generatePassword();
        System.out.println("Generated Password: " + generatedPassword);
    }

    public static String generatePassword() {
        String allowedCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";
        int passwordLength = 8;

        StringBuilder password = new StringBuilder();

        SecureRandom random = new SecureRandom();
        for (int i = 0; i < passwordLength; i++) {
            int randomIndex = random.nextInt(allowedCharacters.length());
            char randomChar = allowedCharacters.charAt(randomIndex);
            password.append(randomChar);
        }

        return password.toString();
    }
}