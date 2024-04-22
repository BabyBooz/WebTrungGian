/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin.product;

import dao.khanh.ProductDAO;
import entity.khanh.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;

/**
 *
 * @author Fangl
 */
@WebServlet(name = "DetailProduct", urlPatterns = {"/detail-product"})
public class DetailProduct extends HttpServlet {

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
            out.println("<title>Servlet DetailProduct</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DetailProduct at " + request.getContextPath() + "</h1>");
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
        String url = "admin/detail-product.jsp"; // Assuming the JSP file name for product detail
        try {
            ProductDAO productDAO = new ProductDAO();
            int productId = Integer.parseInt(request.getParameter("product_id") == null ? "0" : request.getParameter("product_id"));
            Product product = productDAO.getProductById(productId);
            if (product == null) {
                url = "admin/manager-product?";
            } else {
                request.setAttribute("PRODUCT_DETAIL", product);
            }
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
        String message = "";
        int productId = Integer.parseInt(request.getParameter("productId"));
        try {
            // Retrieve parameters from the request
            String name = request.getParameter("name");
            String image = request.getParameter("image");
            double price = Double.parseDouble(request.getParameter("price"));
            String description = request.getParameter("description");
            int statusFee = Integer.parseInt(request.getParameter("statusFee"));
            String contact = request.getParameter("contact");
            String hiddenContent = request.getParameter("hiddenContent");
            boolean statusProduct = Boolean.parseBoolean(request.getParameter("statusProduct"));

            // Create a Product object with the updated details
            Product updatedProduct = new Product();
            updatedProduct.setId(productId);
            updatedProduct.setName(name);
            updatedProduct.setImage(image);
            updatedProduct.setPrice(price);
            updatedProduct.setDescription(description);
            updatedProduct.setStatusFee(statusFee);
            updatedProduct.setContact(contact);
            updatedProduct.setHiddenContent(hiddenContent);
            updatedProduct.setStatusProduct(statusProduct);

            // Call your DAO method to update the product
            ProductDAO productDAO = new ProductDAO();

            Product oldProduct = productDAO.getProductById(productId);
            updatedProduct.setCreatedBy(oldProduct.getCreatedBy());
            updatedProduct.setCreatedDate(oldProduct.getCreatedDate());
            updatedProduct.setUpdatedDate(new Date(System.currentTimeMillis()).toString());
            boolean success = productDAO.updateProduct(updatedProduct);

            if (success) {
                // If update was successful, redirect to a success page or display a success message
                message = "Update success!";
            } else {
                message = "Update fail!";
            }
            request.setAttribute("MESS", message);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher("manager-product").forward(request, response);
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
