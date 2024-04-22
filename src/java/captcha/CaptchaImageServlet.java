
package captcha;

import java.awt.BasicStroke;
import java.awt.RenderingHints;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CaptchaImageServlet
 */
@WebServlet(name = "CaptchaImageServlet", urlPatterns = {"/CaptchaImage"})
public class CaptchaImageServlet extends HttpServlet {

    private static final String CAPTCHA_TIME_ATTRIBUTE = "captchaTime";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // Tạo mã CAPTCHA số ngẫu nhiên
        String captcha = generateRandomNumberCaptcha(4);

        // Lưu mã CAPTCHA vào session để xác minh sau này
        request.getSession().setAttribute("captcha", captcha);

        // Lưu thời gian tạo CAPTCHA vào session
        request.getSession().setAttribute(CAPTCHA_TIME_ATTRIBUTE, System.currentTimeMillis());

        // Tạo hình ảnh từ mã CAPTCHA và gửi về trình duyệt
        response.setContentType("image/jpeg");
        try ( ServletOutputStream outputStream = response.getOutputStream()) {
            generateCaptchaImage(captcha, outputStream);
        }
    }

    private void generateCaptchaImage(String captcha, ServletOutputStream outputStream) throws IOException {
        // Kích thước của hình ảnh
        int width = 150; // Điều chỉnh kích thước theo ý muốn
        int height = 50; // Điều chỉnh kích thước theo ý muốn

        // Tạo hình ảnh
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();

        // Đặt màu nền cho hình ảnh
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        // Thêm hiệu ứng nhiễu sọc
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setStroke(new BasicStroke(2.0f));

        // Vẽ nhiễu sọc ngẫu nhiên
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int x2 = random.nextInt(width);
            int y2 = random.nextInt(height);

            g2d.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
            g2d.drawLine(x1, y1, x2, y2);
        }

        // Đặt font và màu cho văn bản
        Font font = new Font("Arial", Font.BOLD, 20); // Điều chỉnh kích thước và font theo ý muốn
        g2d.setFont(font);
        g2d.setColor(Color.darkGray);

        // Xác định vị trí cho văn bản (mã CAPTCHA)
        FontMetrics fontMetrics = g2d.getFontMetrics();
        int textWidth = fontMetrics.stringWidth(captcha);
        int x = (width - textWidth) / 2;
        int y = height / 2 + fontMetrics.getAscent() / 2;

        // Vẽ văn bản (mã CAPTCHA) vào hình ảnh
        g2d.drawString(captcha, x, y);

        // Ghi hình ảnh vào OutputStream
        ImageIO.write(bufferedImage, "jpeg", outputStream);
    }

    private String generateRandomNumberCaptcha(int length) {
        // Tạo một số ngẫu nhiên với độ dài cho trước
        Random random = new Random();
        int min = (int) Math.pow(10, length - 1);
        int max = (int) Math.pow(10, length) - 1;
        int randomNumber = min + random.nextInt(max - min + 1);
        return String.valueOf(randomNumber);
    }
}