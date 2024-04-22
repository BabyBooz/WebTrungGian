/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DAO;
import entity.Account;
import entity.OrderHistory;
import entity.Order1;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author admin
 */
@WebServlet(name = "mySalesOrder", urlPatterns = {"/mySalesOrder"})
public class MySalesOrder extends HttpServlet {

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
            out.println("<title>Servlet myOrder</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet myOrder at " + request.getContextPath () + "</h1>");
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
            DAO dao = new DAO();
        String indexP = request.getParameter("index");
        if (indexP == null) {
            indexP = "1";
        }
        int index = Integer.parseInt(indexP);
        HttpSession session = request.getSession(false);
        
        if (session != null && session.getAttribute("acc") != null) {
        Account a = (Account) session.getAttribute("acc");
        List<OrderHistory> list = dao.getOrderHistoryPage(index, a.getId());
        request.setAttribute("userid", a.getId());
        request.setAttribute("name", a.getUser());
        request.setAttribute("wallet", a.getWallet());
        request.setAttribute("orders", list);
        List<Account> accountList = dao.getAllAccount();
        request.setAttribute("accountList", accountList);
        List<Product> listP = dao.getProductMarket();
        request.setAttribute("product", listP);
        int count = dao.getMySaleOrderPage(a.getId());
        int endPage = count/5;
        if (count % 5 != 0) {
            endPage++;
        }
        request.setAttribute("endP", endPage);
        request.getRequestDispatcher("mySales.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
        
        
        
        
        
        
//        DAO dao = new DAO();
//        List<Account> accountList = dao.getAllAccount();
//        HttpSession session = request.getSession(false); // Không tạo session mới nếu không tồn tại
//        if (session != null && session.getAttribute("acc") != null) {
//            Account a = (Account) session.getAttribute("acc");
//            
//
//            String indexP = request.getParameter("index");
//            if (indexP == null) {
//                indexP = "1";
//            }
//            int limit = 5;
//            int offset = Integer.parseInt(indexP);
//            String orderBy = "created_date";
//            boolean ascending = false;
//            Map<String, Object> filters = new HashMap<>();
//            filters.put("seller_id", a.getId());
//            List<Order1> orders = dao.listOrder(limit, (offset-1)*limit, orderBy, ascending, filters);
//            
//            
//            for (Order1 o : orders) {
//                o.setProduct(dao.getProductById(o.getProductId()));
//            }
//            int count = dao.getMySaleOrderPage(a.getId()); //13
//            int endPage = count / 5;
//            if (count % 5 != 0) {
//                endPage++;
//            }
//            request.setAttribute("endP", endPage);
//            request.setAttribute("userid", a.getId());
//            request.setAttribute("name", a.getUser());
//            request.setAttribute("wallet", a.getWallet());
//            request.setAttribute("orders", orders);
//            request.setAttribute("accountList", accountList);
//            request.getRequestDispatcher("mySales.jsp").forward(request, response);
//        } else {
//            response.sendRedirect("login.jsp");
//        }
        
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
//        int id = Integer.parseInt(request.getParameter("id"));
//
//        DAO dao = new DAO();
//        int limit = 10;
//        int offset = 0;
//        String orderBy = "created_date";
//        boolean ascending = false;
//
//        Map<String, Object> filters = new HashMap<>();
//        if (id > 0) {
//            filters.put("id", "%" + id + "%");
//        }
//
//        List<Order1> orders = dao.listOrder(limit, (offset-1)*limit, orderBy, ascending, filters);
//        List<Account> accountList = dao.getAllAccount();
//        for (Order1 o : orders) {
//            o.setProduct(dao.getProductById(o.getProductId()));
//            System.out.println(o.toString());
//        }
//        request.setAttribute("accountList", accountList);
//
//        request.setAttribute("orders", orders);
//        request.getRequestDispatcher("mySales.jsp").forward(request, response);
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
