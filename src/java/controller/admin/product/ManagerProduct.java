/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin.product;

import paging.filter.AccountFilter;
import dao.AccountDAO;
import dao.khanh.ProductDAO;
import entity.Account;
import entity.khanh.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import paging.ParamsPaging;
import paging.filter.ProductFilter;

/**
 *
 * @author Fangl
 */
@WebServlet(name = "ManagerProduct", urlPatterns = {"/manager-product"})
public class ManagerProduct extends HttpServlet {

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
            out.println("<title>Servlet ManagerProduct</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManagerProduct at " + request.getContextPath() + "</h1>");
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
        String url = "admin/manager-product.jsp";
        try {
            ProductDAO productDAO = new ProductDAO();
            Integer page = Integer.parseInt(request.getParameter("page") == null ? "1" : request.getParameter("page"));
            ProductFilter productFilter = new ProductFilter();
            productFilter.setPage(page);
            productFilter.setTotalPage(productDAO.countTotalProduct()); //lay tong sp (11)
            productFilter.setOffset(0);
            productFilter.setPageSize(ParamsPaging.PAGE_SIZE);
            //default value
            productFilter.setSearchValue("");

            List<Product> products = productDAO.findAllByFilter(productFilter);
            request.setAttribute("paging", productFilter); //
            request.setAttribute("PRODUCTS", products);
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
        String url = "admin/manager-product.jsp";
        try {
            ProductDAO productDAO = new ProductDAO();

            Boolean status = Boolean.parseBoolean(request.getParameter("status"));
            String searchValue = request.getParameter("searchValue") == null ? "" : request.getParameter("searchValue");
            String sortBy = request.getParameter("sortBy") == null ? "id" : request.getParameter("sortBy");
            String minPrice = request.getParameter("minPrice") == null ? "0.00" : request.getParameter("minPrice");
            String maxPrice = request.getParameter("maxPrice") == null ? "0.00" : request.getParameter("maxPrice");
            if (minPrice.isEmpty()) {
                minPrice = "0.00";
            }
            if (maxPrice.isEmpty()) {
                maxPrice = productDAO.getMaxPrice().toString();
            }

            Integer page = Integer.parseInt(request.getParameter("page") == null ? "1" : request.getParameter("page"));
            ProductFilter productFilter = new ProductFilter();
            productFilter.setPage(page);
            productFilter.setTotalPage(productDAO.countTotalProduct()); //lay tong sp (11)
            productFilter.setOffset(0);
            productFilter.setPageSize(ParamsPaging.PAGE_SIZE);

            productFilter.setStatus(status);
            productFilter.setSearchValue(searchValue);
            productFilter.setSortBy(sortBy);
            productFilter.setMinPrice(Double.parseDouble(minPrice));
            productFilter.setMaxPrice(Double.parseDouble(maxPrice));
            //default value
            List<Product> products = productDAO.findAllByFilter(productFilter);
            request.setAttribute("paging", productFilter); //
            request.setAttribute("PRODUCTS", products);
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
