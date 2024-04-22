/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DAO;
import entity.Account;
import entity.Order1;
import entity.Product1;
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
 * @author admin
 */
@WebServlet(name = "productDetailsServlet", urlPatterns = {"/product-details"})
public class ProductDetailsServlet extends HttpServlet {

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
            out.println("<title>Servlet productDetailsServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet productDetailsServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession(false); // Không tạo session mới nếu không tồn tại
        if (session != null && session.getAttribute("acc") != null) {
            Account a = (Account) session.getAttribute("acc");
            int orderId = Integer.parseInt(request.getParameter("id"));
            DAO dao = new DAO();
            Order1 order = dao.getOrderById(orderId);
            if (a.getId() == order.getSellerId()) {
                order.setProduct(dao.getProductById(order.getProductId()));
                request.setAttribute("order", order);
                request.getRequestDispatcher("detailsMyOrder.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("detailsOrder.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("login.jsp");
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
        HttpSession session = request.getSession();
        if (session != null && session.getAttribute("acc") != null) {
            System.out.println("orderId" + request.getParameter("orderId"));
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            int productId = Integer.parseInt(request.getParameter("productId"));
            String name = request.getParameter("title");
            Float price = Float.parseFloat(request.getParameter("price"));
            int status_fee = Integer.parseInt(request.getParameter("fee"));
            String description = request.getParameter("description");
            String contact = request.getParameter("contact");
            String hidden_content = request.getParameter("hiddenContent");
            int status_product = Integer.parseInt(request.getParameter("public"));

            Product1 product = new Product1(productId, name, price, status_fee, description, contact, hidden_content, status_product);
            DAO dao = new DAO();

            // Add the product to the database
            if (dao.updateProduct(product)) {
                Order1 order = new Order1(orderId, price, description, contact, hidden_content);

                if (dao.updateOrder(order)) {

                    // Product added successfully, redirect to success page or display success message
                    session.setAttribute("successMessage", "Cập nhật giao dịch thành công.");

                    // Chuyển hướng đến trang danh sách sản phẩm
                    response.sendRedirect("mySalesOrder");

                } else {
                    // Failed to add product, redirect to error page or display error message
                    // Đặt thông điệp lỗi vào thuộc tính request
                    request.setAttribute("errorMessage", "Lỗi khi cập nhật giao dịch.");

                    // Forward request đến trang JSP
                    request.getRequestDispatcher("detailsMyOrder.jsp").forward(request, response);
                }

            } else {
                // Failed to add product, redirect to error page or display error message
                // Đặt thông điệp lỗi vào thuộc tính request
                request.setAttribute("errorMessage", "Lỗi khi cập nhật giao dịch.");

                // Forward request đến trang JSP
                request.getRequestDispatcher("detailsMyOrder.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("login.jsp");
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
