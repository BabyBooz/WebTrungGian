/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.account;

import common.MD5;
import dao.AccountDAO;
import entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.Validator;

/**
 *
 * @author Fangl
 */
@WebServlet(name = "UpdatePassword", urlPatterns = {"/updatePassword"})
public class UpdatePassword extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdatePassword</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdatePassword at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "update-password.jsp";
        String message = "";
        try {
            String accountId = request.getParameter("accountId");
            String token = request.getParameter("token");
            AccountDAO accountDAO = new AccountDAO();
            Account account = accountDAO.getById(Integer.parseInt(accountId));
            if (account.getToken().equals(token)) {
                url = "update-password.jsp";
                request.setAttribute("account", account);
            } else {
                url = "forgotPass.jsp";
                message = "The token is invalid !";
            }
            request.setAttribute("mess", message);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "update-password.jsp";
        String message = "";
        try {
            String accountId = request.getParameter("accountId");
            String password = request.getParameter("password");
            String rePassword = request.getParameter("rePassword");

            MD5 md5 = new MD5();
            AccountDAO accountDAO = new AccountDAO();
            Account account = accountDAO.getById(Integer.parseInt(accountId));

            if (!Validator.validatePassword(password)) {
                message = "Password need to format [0-8][a-Z], 1 special char";
                url = "update-password.jsp";
                request.setAttribute("account", account);
            } else {
                if (password.equals(rePassword)) { //Thoa man dieu kien 2 password trung nhau
                    account.setPassword(md5.MD5(password)); //Ma hoa password
                    accountDAO.updateAccountPassword(account);
                    accountDAO.deleteUserToken(account.getId());
                    url = "login.jsp";
                    message = "update sucessful";
                } else {
                    url = "update-password.jsp";
                    message = "invalid password";
                    request.setAttribute("account", account);
                }
            }
            request.setAttribute("mess", message);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
