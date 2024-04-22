/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package searchPublicMarket;

import dao.DAO;
import dao.MyDAO;
import entity.Account;
import entity.OrderHistory;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author linhnghiem
 */
@WebServlet(name = "searchOrderHistory", urlPatterns = {"/searchOrderHistory"})
public class searchOrderHistory extends HttpServlet {

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
        DAO dao = new DAO();
        List<OrderHistory> listO = new ArrayList<>();

        String raw_statusChecking = request.getParameter("status_checking");
        String raw_buyerId = request.getParameter("buyer_id");
        String raw_name = request.getParameter("nameProduct");
        String raw_contact = request.getParameter("contact");
        String raw_statusProduct = request.getParameter("status_product");

        String raw_startPrice = request.getParameter("startPrice");
        String raw_endPrice = request.getParameter("endPrice");

        String raw_status_fee = request.getParameter("status_fee");

        String raw_start_updated_date = request.getParameter("start_updated_date");
        String raw_end_updated_date = request.getParameter("end_updated_date");

        String raw_end_created_date = request.getParameter("end_created_date");
        String raw_start_created_date = request.getParameter("start_created_date");

        String raw_id = request.getParameter("id");

        String query = "select * from order_history o where o.id in \n"
                + "(select o.id from order_history o \n"
                + "join account ac \n"
                + "join product p where seller_id = ?";
        
        if (raw_statusChecking != null && !raw_statusChecking.trim().isEmpty()) {
            query += " and o.status = ? \n";
        }
        if (raw_buyerId != null && !raw_buyerId.trim().isEmpty()) {
            query += " and ac.user like ? \n";
        }
        if (raw_name != null && !raw_name.trim().isEmpty()) {
            query += " and o.description like ? \n";
        }
        if (raw_contact != null && !raw_contact.trim().isEmpty()) {
            query += " and o.contact like ? \n";
        }
        if (raw_statusProduct != null && !raw_statusProduct.trim().isEmpty()) {
            query += " and p.status_product = ? \n";
        }
        if (raw_startPrice != null && raw_endPrice != null && !raw_startPrice.trim().isEmpty() && !raw_endPrice.trim().isEmpty()) {
            query += " and o.price BETWEEN ? and ? \n";
        }

        if (raw_status_fee != null && !raw_status_fee.trim().isEmpty()) {
            query += " and p.status_fee = ? \n";
        }
        if (raw_end_created_date != null && raw_start_created_date != null && !raw_end_created_date.trim().isEmpty() && !raw_start_created_date.trim().isEmpty()) {
            query += " and o.created_date BETWEEN ? AND ? \n";
        }

        if (raw_start_updated_date != null && raw_end_updated_date != null && !raw_start_updated_date.trim().isEmpty() && !raw_end_updated_date.trim().isEmpty()) {
            query += " and o.updated_date BETWEEN ? AND ? \n";
        }
        if (raw_id != null && !raw_id.trim().isEmpty()) {
            query += " and o.id =? \n";
        }

        query += ")";
        MyDAO myDao = new MyDAO();
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");
        List<Account> accountList = dao.getAllAccount();
        request.setAttribute("accountList", accountList);
        List<Product> accountProduct = dao.getProductMarket();
        request.setAttribute("product", accountProduct);

        try {
            int index = 1;
            PreparedStatement ps = myDao.con.prepareStatement(query);
            ps.setInt(index, a.getId());
            index++;
            if (raw_statusChecking != null && !raw_statusChecking.trim().isEmpty()) {
                ps.setInt(index, Integer.parseInt(raw_statusChecking));
                index++;
            }
            if (raw_buyerId != null && !raw_buyerId.trim().isEmpty()) {
                ps.setNString(index, "%" + raw_buyerId + "%");
                index++;
            }
            if (raw_name != null && !raw_name.trim().isEmpty()) {
                ps.setNString(index, "%" + raw_name + "%");
                index++;
            }
            if (raw_contact != null && !raw_contact.trim().isEmpty()) {
                ps.setNString(index, "%" + raw_contact + "%");
                index++;
            }
            if (raw_statusProduct != null && !raw_statusProduct.trim().isEmpty()) {
                ps.setInt(index, Integer.parseInt(raw_statusProduct));
                index++;
            }
            if (raw_startPrice != null && raw_endPrice != null && !raw_startPrice.trim().isEmpty() && !raw_endPrice.trim().isEmpty()) {
                ps.setFloat(index, Float.parseFloat(raw_startPrice));
                index++;
                ps.setFloat(index, Float.parseFloat(raw_endPrice));
                index++;
            }
            if (raw_status_fee != null && !raw_status_fee.trim().isEmpty()) {
                ps.setInt(index, Integer.parseInt(raw_status_fee));
                index++;
            }
            if (raw_end_created_date != null && raw_start_created_date != null && !raw_end_created_date.trim().isEmpty() && !raw_start_created_date.trim().isEmpty()) {
                ps.setString(index, raw_start_created_date);
                index++;
                ps.setString(index, raw_end_created_date);
                index++;
            }
            if (raw_start_updated_date != null && raw_end_updated_date != null && !raw_start_updated_date.trim().isEmpty() && !raw_end_updated_date.trim().isEmpty()) {
                ps.setString(index, raw_start_updated_date);
                index++;
                ps.setString(index, raw_end_updated_date);
                index++;
            }
            if (raw_id != null && !raw_id.trim().isEmpty()) {
                ps.setInt(index, Integer.parseInt(raw_id));
            }
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listO.add(new OrderHistory(
                        rs.getInt("id"),
                        rs.getInt("status"),
                        rs.getInt("seller_id"),
                        rs.getInt("buyer_id"),
                        rs.getInt("product_id"),
                        rs.getFloat("price"),
                        rs.getString("description"),
                        rs.getString("contact"),
                        rs.getString("hidden_content"),
                        rs.getDate("created_date"),
                        rs.getDate("updated_date")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("orders", listO);
        request.getRequestDispatcher("include_OrderHistoryList.jsp").forward(request, response);
    }


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
