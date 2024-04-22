/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import common.MD5;
import dao.DAO;
import entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet(name = "loginControl", urlPatterns = {"/login"})
public class loginControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet loginControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet loginControl at " + request.getContextPath() + "</h1>");
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
        String pass = request.getParameter("pass");
        String captcha = request.getParameter("captchaValue");
        DAO dao = new DAO();
        MD5 md5 = new MD5();
        Account a = dao.login(user, md5.MD5(pass));
        if (a == null) {
            request.setAttribute("mess2", captcha);
            request.setAttribute("mess", "Wrong User or Pass");

            if (a.getRole() == 1) { //Role is ADMIN
                String contextPath = request.getContextPath();
                String url = String.format("%s/admin", contextPath);
                request.getRequestDispatcher(url).forward(request, response);
            } else {
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

        } else {
            HttpSession session = request.getSession();
            session.setAttribute("acc", a);
            request.getRequestDispatcher("index.html").forward(request, response);
        }
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
