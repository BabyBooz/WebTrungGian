/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin.withdraw;

import dao.AccountDAO;
import dao.khanh.WithdrawDAO;
import entity.Account;
import entity.khanh.WithdrawDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Fangl
 */
@WebServlet(name = "UpdateWithdrawStatus", urlPatterns = {"/UpdateWithdrawStatus"})
public class UpdateWithdrawStatus extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateWithdrawStatus</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateWithdrawStatus at " + request.getContextPath() + "</h1>");
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
        String url = "/ManagerWithdraw?";
        String message = "";
        try {
            WithdrawDAO withdrawDAO = new WithdrawDAO();
            AccountDAO accountDAO = new AccountDAO();
            Integer withdrawID = Integer.parseInt(request.getParameter("wdsID"));
            Integer status = Integer.parseInt(request.getParameter("status"));
            if (withdrawDAO.updateWithdrawStatusById(withdrawID, status)) {
                if (status == 1) {
                    message = "Chấp nhận thành công";
                } else {
                    WithdrawDTO withdrawDTO = withdrawDAO.getWithdrawById(withdrawID);
                    Account account = accountDAO.getById(withdrawDTO.getUserId());
                    Float result = account.getWallet() + withdrawDTO.getPrice();
                    account.setWallet(result);
                    accountDAO.updateAccountWallet(account.getId(), result);
                    message = "Từ chối thành công";
                }
            } else {
                message = "Không thể xử lý";
            }
            request.setAttribute("mess", message);
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
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
        processRequest(request, response);
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
