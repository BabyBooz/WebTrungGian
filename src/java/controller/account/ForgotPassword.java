package controller.account;

import common.MD5;
import common.Token;
import dao.AccountDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import utils.EmailSender;
import utils.PasswordGenerator;

@WebServlet(name = "ForgotPassword", urlPatterns = {"/forgotPassword"})
public class ForgotPassword extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "forgotPass.jsp";
        response.sendRedirect(url);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = "";

        AccountDAO accountDAO = new AccountDAO();
        String email = request.getParameter("email");
        String captcha = request.getParameter("captcha");
        String captcha2 = (String) request.getSession().getAttribute("captcha");
        MD5 md5 = new MD5();
        entity.Account account = accountDAO.getByEmail(email);
        if (!captcha.equals(captcha2)) {
            message = "Captcha not correct";
        } else {
            if (accountDAO.getByEmail(email) == null) {
                message = "Not found email";
            } else {
                //UPDATE DATABASE
//                String password = PasswordGenerator.generatePassword();
//                account.setPassword(md5.MD5(password));
//                account = accountDAO.updateAccountPassword(account);
//                
                account.setToken(Token.generateTokenInfo().getToken());
                accountDAO.updateUserToken(account);
                String host = "http://localhost:9999";
                String contextPath = request.getServletContext().getContextPath();
                String url = String.format("%s%s/updatePassword?accountId=%s&token=%s"
                            , host, contextPath, account.getId(), account.getToken());
                
                //SEND MAIL
                message = "Your request to change password approved";
                String title = "YOUR PASSWORD UPDATED!";
                 String content = "Dear User,\n\n"
                            + "Your password for forgot updated. Click the link below to set your new password:\n"
                            + url + "\n\n"
                            + "If you did not initiate this change or have any concerns regarding your account security, please contact our support team immediately.\n\n"
                            + "Thank you for choosing us.\n\n"
                            + "Best Regards,\n"
                            + "Your Name\n"
                            + "Your Position/Title\n"
                            + "Your Company Name";
                EmailSender.sendEmail(account.getEmail(), title, content);
            }
        }
        request.setAttribute("mess", message);
        request.getRequestDispatcher("forgotPass.jsp").forward(request, response);
    }
}
