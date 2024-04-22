/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DAO;
import entity.Account;
import entity.Order1;
import entity.Product1;
import entity.StatusHistory;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author admin
 */
@WebServlet(name = "AddProductServlet", urlPatterns = {"/addProduct"})
public class AddProductServlet extends HttpServlet {

    static final int EXAMPLE_USER_ID = 2;
    static final float ADD_PRODUCT_FEE = 500;

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
            out.println("<title>Servlet AddProductServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddProductServlet at " + request.getContextPath() + "</h1>");
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
            // Người dùng đã đăng nhập, thực hiện các hành động mong muốn
            // Ví dụ: chuyển hướng đến trang chính sau khi đăng nhập
            response.sendRedirect("addProduct.jsp");
        } else {
            // Người dùng chưa đăng nhập, chuyển hướng đến trang đăng nhập
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
        // get user by session
        HttpSession session = request.getSession();

        if (session != null && session.getAttribute("acc") != null) {
            Account a = (Account) session.getAttribute("acc");
            // Retrieve data from the request
            int created_by = a.getId();
            String name = request.getParameter("title");
            Float price = Float.parseFloat(request.getParameter("price"));
            int status_fee = Integer.parseInt(request.getParameter("fee"));
            String description = request.getParameter("description");
            String contact = request.getParameter("contact");
            String hidden_content = request.getParameter("hiddenContent");
            int status_product = Integer.parseInt(request.getParameter("publicStatus"));
            if (price < 0) {
                String mess = "Số Tiền Lớn Hơn 0";
                request.setAttribute("errorMessage", mess);
                request.setAttribute("title", name);
                request.setAttribute("price", price);
                request.setAttribute("fee", status_fee);
                request.setAttribute("description", description);
                request.setAttribute("contact", contact);
                request.setAttribute("hiddenContent", hidden_content);
                request.setAttribute("publicStatus", status_product);
                request.getRequestDispatcher("addProduct.jsp").forward(request, response);
            } else {
                if (a.getWallet() < ADD_PRODUCT_FEE) {
                    request.setAttribute("errorMessage", "Bạn phải có 500đ để tạo bài.");
                    request.setAttribute("title", name);
                    request.setAttribute("price", price);
                    request.setAttribute("fee", status_fee);
                    request.setAttribute("description", description);
                    request.setAttribute("contact", contact);
                    request.setAttribute("hiddenContent", hidden_content);
                    request.setAttribute("publicStatus", status_product);
                    // Forward request đến trang JSP
                    request.getRequestDispatcher("addProduct.jsp").forward(request, response);
                } else {
                    Product1 product = new Product1(created_by, name, "", price, status_fee, description, contact, hidden_content, status_product);
                    DAO dao = new DAO();
                    int productId = dao.addProduct(product);

                    // Add the product to the database
                    if (productId > 0) {
                        Order1 order = new Order1(0, created_by, 0, productId, price, description, contact, hidden_content);

                        if (dao.addOrder(order)) {
                            StatusHistory statusHistory = new StatusHistory(status_fee, created_by, productId);
                            dao.insertStatusHistory(statusHistory);

                            a.setWallet(a.getWallet() - ADD_PRODUCT_FEE);
                            session.setAttribute("acc", a);
                            dao.updateWallet(a.getWallet(), a.getId());

                            // Product added successfully, redirect to success page or display success message
                            session.setAttribute("successMessage", "Tạo giao dịch mới thành công.");

                            // Chuyển hướng đến trang danh sách sản phẩm
                            response.sendRedirect("mySalesOrder");

                        } else {
                            // Failed to add product, redirect to error page or display error message
                            // Đặt thông điệp lỗi vào thuộc tính request
                            request.setAttribute("errorMessage", "Lỗi khi tạo giao dịch.");

                            // Forward request đến trang JSP
                            request.getRequestDispatcher("addProduct.jsp").forward(request, response);
                        }

                    } else {
                        // Failed to add product, redirect to error page or display error message
                        // Đặt thông điệp lỗi vào thuộc tính request
                        request.setAttribute("errorMessage", "Lỗi khi tạo giao dịch.");

                        // Forward request đến trang JSP
                        request.getRequestDispatcher("addProduct.jsp").forward(request, response);
                    }
                }
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
