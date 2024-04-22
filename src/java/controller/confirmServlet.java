/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DAO;
import entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author admin
 */
@WebServlet(name = "confirmServlet", urlPatterns = {"/confirmation"})
public class confirmServlet extends HttpServlet {

    static final int ACTIVE_STATUS = 1;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet confirmServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet confirmServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String token = request.getParameter("token");
        String user = request.getParameter("user");
        DAO dao = new DAO();
        Account account = dao.getAccountByUser(user);
        if (account != null) {
            if (account.getToken()==null || !account.getToken().equals(token)) {
                request.setAttribute("Mess", "Invalid token");
            } else {
                Date now = new Date();
                Timestamp currentTimestamp = new Timestamp(now.getTime());
                if (currentTimestamp.after(account.getExpiration_time())) {
                    request.setAttribute("Mess", "token has expired");
                } else {
                    dao.changeAccountStatus(account.getId(), ACTIVE_STATUS);
                    dao.deleteUserToken(account.getId());
                    request.setAttribute("Mess", "Account has been active, now you can log in to the system");                    
                }
            }

        } else {
            request.setAttribute("Mess", "Cannot find user");
        }
        
        request.getRequestDispatcher("confirmation.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    // Phương thức kiểm tra token
    private boolean isValidToken(String token) {
        // Thực hiện kiểm tra token, ví dụ: so sánh với danh sách token đã tạo
        // Nếu token hợp lệ, trả về true; ngược lại, trả về false
        // Bạn có thể thực hiện kiểm tra trong cơ sở dữ liệu hoặc lưu trữ trạng thái token ở đây
        // ...

        return true; // Ví dụ: luôn trả về true để kiểm tra
    }

    // Phương thức xác nhận tài khoản
    private void confirmAccount(String token) {
        // Thực hiện xác nhận tài khoản, ví dụ: đổi trạng thái tài khoản thành active
        // Bạn có thể thực hiện các thao tác cần thiết để xác nhận tài khoản ở đây
        // ...
    }
}
