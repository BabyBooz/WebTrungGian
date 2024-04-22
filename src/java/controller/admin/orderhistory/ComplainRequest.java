/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin.orderhistory;

import paging.filter.AccountFilter;
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
import java.util.List;
import paging.ParamsPaging;
import paging.filter.ComplainFilter;

/**
 *
 * @author Fangl
 */
@WebServlet(name = "ComplainRequest", urlPatterns = {"/complain-request"})
public class ComplainRequest extends HttpServlet {

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
            out.println("<title>Servlet ComplainRequest</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ComplainRequest at " + request.getContextPath() + "</h1>");
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
        String url = "admin/complain-request.jsp";
        try {
            OrderHistoryDAO orderHistoryDAO = new OrderHistoryDAO();
            ComplainFilter complainFilter = new ComplainFilter();
            String searchValue = request.getParameter("searchValue") == null ? "" : request.getParameter("searchValue");
            Integer status = request.getParameter("status") == null || request.getParameter("status").trim().length() == 0 ? null : Integer.parseInt(request.getParameter("status"));

            Integer page = Integer.parseInt(request.getParameter("page") == null ? "1" : request.getParameter("page"));
            complainFilter.setPage(page);
            complainFilter.setTotalPage(orderHistoryDAO.countTotalOrderHistory());
            complainFilter.setOffset(0);
            complainFilter.setPageSize(ParamsPaging.PAGE_SIZE);
            
            
            
            complainFilter.setSearchValue(searchValue);
            complainFilter.setStatus(status);

            List<OrderHistory> ohs = orderHistoryDAO.getOrderHistoryComplain(complainFilter);
            AccountDAO accountDAO = new AccountDAO();
            ProductDAO productDAO = new ProductDAO();
            for (OrderHistory oh : ohs) {
                oh.setBuyerName(accountDAO.getAccountById(oh.getBuyer_id()).getUser());
                oh.setSellerName(accountDAO.getAccountById(oh.getSeller_id()).getUser());
                oh.setProductName(productDAO.getProductById(oh.getProduct_id()).getName());
            }
            request.setAttribute("ORDERHISTORY", ohs);
            request.setAttribute("paging", complainFilter);
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
