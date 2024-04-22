/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import common.MD5;
import common.SendMail;
import common.Token;
import static common.Token.generateTokenInfo;
import dao.DAO;
import entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author admin
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

    static final int DEFAULT_STATUS = 0;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegisterServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = request.getParameter("user");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String userInput = request.getParameter("captcha");
        DAO dao = new DAO();
        String actualCaptcha = (String) request.getSession().getAttribute("captcha");

        boolean check = true;
        if (userInput.equals(actualCaptcha)) {
            if (!validatePassword(password)) {
                request.setAttribute("Mess", "Wrong password format");
                check = false;
            } else {
                if (!password.equals(confirmPassword)) {
                    request.setAttribute("Mess", "Confirm password not match password");
                    check = false;
                } else {
                    if (dao.isUsernameTaken(user)) {
                        request.setAttribute("Mess", "Username already taken");
                        check = false;
                    } else {
                        if (dao.isEmailTaken(email)) {
                            request.setAttribute("Mess", "Email already taken");
                            check = false;
                        }

                    }

                }
            }

        } else {
            request.setAttribute("Mess", "Captcha not match");
            check = false;
        }

        request.setAttribute("user", user);
        request.setAttribute("email", email);
        request.setAttribute("password", password);
        request.setAttribute("confirmPassword", confirmPassword);

        int status = DEFAULT_STATUS;
        MD5 md5 = new MD5();
        Token.TokenInfo tokenInfo = generateTokenInfo();
        // Kiểm tra các điều kiện và chuyển hướng dựa trên kết quả
        if (check) {
            
            request.setAttribute("Mess", "Check email to register");
            request.getRequestDispatcher("confirmation.jsp").forward(request, response);
            
            Account account = new Account(user, md5.MD5(password), email, tokenInfo.getToken(), tokenInfo.getCreationTime(), status);
            
            if (dao.register(account)) {
                // Nếu đăng ký thành công, chuyển hướng đến trang đăng nhập
                SendMail.sendConfirmationEmail(account);
                return;
            }
        }
        // Nếu có lỗi, chuyển hướng về trang register.jsp và hiển thị lỗi
        request.getRequestDispatcher("signup.jsp").forward(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public boolean validatePassword(String password) {
        String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
