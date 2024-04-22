/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package searchPublicMarket;

import com.oracle.wls.shaded.org.apache.bcel.generic.AALOAD;
import dao.DAO;
import dao.MyDAO;
import entity.Account;
import entity.MyCart;
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
@WebServlet(name = "searchMyCart", urlPatterns = {"/searchMyCart"})
public class searchMyCart extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao = new DAO();
        List<MyCart> listC = new ArrayList<>();
        
        String raw_statusChecking = request.getParameter("status_checking");
        String raw_sellerId = request.getParameter("seller_id");
        String raw_name = request.getParameter("product_name");
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

        String query = ""
                + "select * from myCart m where m.id in "
                + "(select m.id from myCart m join `account` ac join product p \n"
                + "on m.seller_id = ac.id and m.product_id = p.id where buyer_id = ? \n";
        if (raw_statusChecking != null && !raw_statusChecking.trim().isEmpty()) {
            query += "and m.status_checking = ? \n";
        }
        if (raw_sellerId != null && !raw_sellerId.trim().isEmpty()) {
            query += "and ac.user like ? \n";
        }
        if (raw_name != null && !raw_name.trim().isEmpty()) {
            query += " and p.name like ? \n";
        }
        if (raw_contact != null && !raw_contact.trim().isEmpty()) {
            query += "and m.contact like ? \n";
        }
        if (raw_statusProduct != null && !raw_statusProduct.trim().isEmpty()) {
            query += "and p.status_product = ? \n";
        }
        if (raw_startPrice != null && raw_endPrice != null && !raw_startPrice.trim().isEmpty() && !raw_endPrice.trim().isEmpty()) {
            query += " and m.price BETWEEN ? and ? \n";
        }

        if (raw_status_fee != null && !raw_status_fee.trim().isEmpty()) {
            query += " and m.status_fee = ? \n";
        }
        if (raw_end_created_date != null && raw_start_created_date != null && !raw_end_created_date.trim().isEmpty() && !raw_start_created_date.trim().isEmpty()) {
            query += " and m.created_date BETWEEN ? AND ? \n";
        }

        if (raw_start_updated_date != null && raw_end_updated_date != null && !raw_start_updated_date.trim().isEmpty() && !raw_end_updated_date.trim().isEmpty()) {
            query += " and m.updated_date BETWEEN ? AND ? \n";
        }
        if (raw_id != null && !raw_id.trim().isEmpty()) {
            query += " and m.id =? \n";
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
            if (raw_sellerId != null && !raw_sellerId.trim().isEmpty()) {
                ps.setNString(index,"%" + raw_sellerId + "%");
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
            while(rs.next()){
                 listC.add(new MyCart(
                        rs.getInt("id"),
                        rs.getInt("status_fee"),
                        rs.getInt("status_checking"),
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
        
        
        request.setAttribute("listO", listC);
        request.getRequestDispatcher("include_myCartList.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

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
