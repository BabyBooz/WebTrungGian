/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin.orderhistory;

import dao.AccountDAO;
import dao.DAO;
import dao.khanh.OrderHistoryDAO;
import dao.khanh.ProductDAO;
import entity.Account;
import entity.MyCart;
import entity.OrderHistory;
import entity.Product;
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
@WebServlet(name = "ResponseComplain", urlPatterns = {"/response-complain"})
public class ResponseComplain extends HttpServlet {

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
            out.println("<title>Servlet ResponseComplain</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ResponseComplain at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        String url = "complain-request?";
        try {
            OrderHistoryDAO orderHistoryDAO = new OrderHistoryDAO();
            ProductDAO productDAO = new ProductDAO();
            AccountDAO accountDAO = new AccountDAO();
            String complainId = request.getParameter("complainId");
            String responseComplain = request.getParameter("response");
            Boolean isPayback = Boolean.parseBoolean(request.getParameter("isPayback"));
            DAO dao = new DAO();
            OrderHistory orderHistory = orderHistoryDAO.getOrderHistoryComplainById(Integer.parseInt(complainId));
            if (isPayback) {
                if (!orderHistory.getIsPayback()) { //check for product is payback
                    entity.khanh.Product product = productDAO.getProductById(orderHistory.getProduct_id());
                    Account account = accountDAO.getAccountById(orderHistory.getBuyer_id()); //get seller Account
                    Double result = 0.0;
                    result = account.getWallet() + product.getPrice();
                    accountDAO.updateAccountWallet(account.getId(), result.floatValue());
                    orderHistory.setStatus(4);
                    // hoan tien cho nguoi mua
                }
            } else {
                entity.khanh.Product product = productDAO.getProductById(orderHistory.getProduct_id());
                Account account = accountDAO.getAccountById(orderHistory.getSeller_id()); //get seller Account
                Double result = 0.0;
                result = account.getWallet() + product.getPrice();
                accountDAO.updateAccountWallet(account.getId(), result.floatValue());
                orderHistory.setStatus(5);
                // hoan tien cho nguoi ban
            }
            orderHistory.setIsPayback(isPayback);
            orderHistoryDAO.updateComplainResponse(orderHistory.getId(), responseComplain, isPayback, orderHistory.getStatus());

            if (isPayback) {

                MyCart my = dao.getMyCartByOrderHistoryId(orderHistory.getId());
                my.setStatus_checking(4);
                dao.updateStatusMyCartTo4(my.getId());
            } else {
                MyCart my = dao.getMyCartByOrderHistoryId(orderHistory.getId());
                
                my.setStatus_checking(5);
                dao.updateStatusMyCartTo5(my.getId());
            }

            response.sendRedirect(url);
        } catch (Exception e) {
            e.printStackTrace();
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
