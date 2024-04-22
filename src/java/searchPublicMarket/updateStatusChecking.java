/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package searchPublicMarket;

import dao.DAO;
import dao.khanh.OrderHistoryDAO;
import entity.Account;
import entity.MyCart;
import entity.OrderHistory;
import entity.StatusHistory;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(name="updateStatusChecking", urlPatterns={"/updateStatusChecking"})
public class updateStatusChecking extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet updateStatusChecking</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet updateStatusChecking at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        DAO dao = new DAO();
        String confirm = request.getParameter("confirm");
        String report = request.getParameter("report");
        String id = request.getParameter("id");
        String product_id = request.getParameter("product_id");
        String buyer_id = request.getParameter("buyer_id");
        int sellerId = Integer.parseInt(request.getParameter("sellerId"));
        float price = Float.parseFloat(request.getParameter("price"));
        Account a = dao.getAccountID(sellerId);
        float includeWalle = a.getWallet() + price;
        StatusHistory sh = new StatusHistory(2,Integer.parseInt(buyer_id), Integer.parseInt(product_id));
        if(confirm != null && !confirm.trim().isEmpty()){
            dao.updateWallet(includeWalle, sellerId);
            dao.updateStatusChekingOrder(Integer.parseInt(product_id));
            dao.updateStatusChekingCart(Integer.parseInt(product_id));
            dao.insertStatusHistory2(sh);
        }
        
        if(report != null && !report.trim().isEmpty()){
            dao.updateStatusChekingCart2(Integer.parseInt(product_id));
            
            String complain = request.getParameter("complain");
            if(complain == null){
                complain = "";
            }
            
            //Orderhistory
            OrderHistoryDAO orderHistoryDAO = new OrderHistoryDAO();
            MyCart myCart = dao.getMyCartById(Integer.parseInt(id));
            OrderHistory orderHistory = orderHistoryDAO.getOrderHistoryById(myCart.getOrder_history_id());
            orderHistory.setStatus(3);
            orderHistory.setIsComplain(true);
            orderHistory.setRequestComplain(complain);
            orderHistoryDAO.updateOrderHistory(orderHistory);
            
        }
        response.sendRedirect("myOrder");
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
