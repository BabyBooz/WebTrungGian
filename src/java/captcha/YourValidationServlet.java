package captcha;

import common.MD5;
import dao.DAO;
import entity.Account;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author linhnghiem
 */
@WebServlet(name = "YourValidationServlet", urlPatterns = {"/validation"})
public class YourValidationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));
        DAO dao = new DAO();
        Account a = dao.getAccountID(id);
        session.setAttribute("acc", a);
        request.setAttribute("wallet", a.getWallet());
        request.setAttribute("name", a.getUser());
        request.getRequestDispatcher("homePage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy mã CAPTCHA người dùng nhập vào từ trường captcha
        String userInput = request.getParameter("captcha");
        String user = request.getParameter("username");
        String password = request.getParameter("password");
        DAO dao = new DAO();
        MD5 md5 = new MD5();
        Account a = dao.login(user, md5.MD5(password));
        // Lấy mã CAPTCHA hiện tại từ session
        String actualCaptcha = (String) request.getSession().getAttribute("captcha");
        if (!isCaptchaExpired(request)) {
            // So sánh với mã CAPTCHA hiện tại
            if (userInput.equals(actualCaptcha)) {
                if (a == null) {
                    request.setAttribute("Mess", "Wrong Account");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                } else {
                    if (a.getStatus() == 1) {
                        HttpSession session = request.getSession();
                        session.setAttribute("acc", a);
                        request.setAttribute("wallet", a.getWallet());
                        request.setAttribute("name", a.getUser());
                        if (a.getRole() == 1) { //Role is ADMIN
                            String contextPath = request.getContextPath();
                            response.sendRedirect(contextPath + "/admin");
                        } else {
                            request.getRequestDispatcher("homePage.jsp").forward(request, response);
                        }
                    } else {

                        request.setAttribute("Mess", "Account is not Active");
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    }

                }
            } else {
                request.setAttribute("Mess", "Captcha not match");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("Mess", "Captcha is expired");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    private boolean isCaptchaExpired(HttpServletRequest request) {
        Long captchaTime = (Long) request.getSession().getAttribute("captchaTime");

        // Kiểm tra xem captchaTime có tồn tại và đã hết hạn hay không
        return captchaTime == null || (System.currentTimeMillis() - captchaTime) > 30 * 1000; // 50 giây
    }
}
