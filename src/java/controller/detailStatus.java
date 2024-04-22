/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin.orderhistory;

import dao.AccountDAO;
import dao.khanh.OrderHistoryDAO;
import dao.khanh.ProductDAO;
import entity.OrderHistory;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
@WebServlet(name = "detailStatus", urlPatterns = {"/detail1"})
public class detailStatus extends HttpServlet {

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
            out.println("<title>Servlet DetailOrderHistory</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DetailOrderHistory at " + request.getContextPath() + "</h1>");
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
        String url = "admin/detail-order-history.jsp";

        try {
            AccountDAO accountDAO = new AccountDAO();
            ProductDAO productDAO = new ProductDAO();
            OrderHistoryDAO orderHistoryDAO = new OrderHistoryDAO();
            String ohsId = request.getParameter("ohsId");
            OrderHistory orderHistory = orderHistoryDAO.getOrderHistoryById(Integer.parseInt(ohsId));

            if (orderHistory != null) {
                orderHistory.setBuyerName(accountDAO.getAccountById(orderHistory.getBuyer_id()).getUser());
                orderHistory.setSellerName(accountDAO.getAccountById(orderHistory.getSeller_id()).getUser());
                orderHistory.setProductName(productDAO.getProductById(orderHistory.getProduct_id()).getName());
            }

            request.setAttribute("ORDERHISTORY", orderHistory);
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
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
