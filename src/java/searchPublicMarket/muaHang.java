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
import entity.Product;
import entity.StatusHistory;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author linhnghiem
 */
@WebServlet(name = "muaHang", urlPatterns = {"/muaHang"})
public class muaHang extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet muaHang</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet muaHang at " + request.getContextPath() + "</h1>");
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
        int id = Integer.parseInt(request.getParameter("id"));
        String contact = request.getParameter("contact");
        float price = Float.parseFloat(request.getParameter("price"));
        String description = request.getParameter("description");
        int status_fee = Integer.parseInt(request.getParameter("status_fee"));
        String created_date1 = request.getParameter("created_date");
        java.sql.Date created_date = java.sql.Date.valueOf(created_date1);
        String updated_date1 = request.getParameter("updated_date");
        java.sql.Date updated_date = java.sql.Date.valueOf(updated_date1);
        int created_by = Integer.parseInt(request.getParameter("created_by"));
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");
        request.setAttribute("userid", a.getId());
        request.setAttribute("name", a.getUser());
        request.setAttribute("wallet", a.getWallet());
        Product p = dao.getProductMarket(id);
        int status_checking = 1;
        float price1 = (p.getPrice() + (p.getPrice() * 0.1f));
        if (price1 <= a.getWallet()) {
            double wallet = a.getWallet() - price1;
            if (wallet > 0) {
                float newWallet = a.getWallet() - price1;
                dao.updateWallet(newWallet, a.getId());
                OrderHistory order = dao.getOrderId2(id);
                dao.updateStatus1Order(order.getId());
                dao.updateStatusProduct(id);
                List<Product> list = dao.getProductMarketById(id);
                OrderHistoryDAO orderHistoryDAO = new OrderHistoryDAO();
                OrderHistory odh = orderHistoryDAO.getOrderHistoryById(order.getId());
                odh.setBuyer_id(a.getId()); // set Buyer ID
                orderHistoryDAO.updateOrderHistory(odh);

                MyCart myCart = new MyCart(status_fee, status_checking, p.getCreated_by(), a.getId(), id, price, description, contact, p.getHidden_content(), created_date, updated_date);
                myCart.setOrder_history_id(odh.getId());
                dao.insertMyCart(myCart);
                StatusHistory statusHistory = new StatusHistory(1, p.getCreated_by(), id);
                dao.insertStatusHistory(statusHistory);
                Account ac = (Account) dao.getAccountID(a.getId());
                session.setAttribute("acc", ac);
                request.setAttribute("userid", ac.getId());
                request.setAttribute("name", ac.getUser());
                request.setAttribute("wallet", ac.getWallet());
            } else {
                request.setAttribute("mess", "Tài Khoản Của Bạn Không Đủ Tiền");
            }

        } else {
            request.setAttribute("mess", "Tài Khoản Của Bạn Không Đủ Tiền");
        }
        String indexP = request.getParameter("index");
        if (indexP == null) {
            indexP = "1";
        }
        int index = Integer.parseInt(indexP);
        List<Product> list = dao.getProductMarketPage(index);

        List<Account> accountList = dao.getAllAccount();
        request.setAttribute("accountList", accountList);
        request.setAttribute("listP", list);
        int count = dao.getProductPage(); //13
        int endPage = count / 5;
        if (count % 5 != 0) {
            endPage++;
        }
        request.setAttribute("endP", endPage);
        request.getRequestDispatcher("publicMarket.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
