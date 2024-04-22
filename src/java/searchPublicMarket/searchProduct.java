/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package searchPublicMarket;

import dao.DAO;
import dao.MyDAO;
import entity.Account;
import entity.Product;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author linhnghiem
 */
@WebServlet(name = "searchProduct", urlPatterns = {"/searchProduct"})
public class searchProduct extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao = new DAO();
        List<Product> listP = new ArrayList<>();
        String raw_id = request.getParameter("id");
        String raw_name = request.getParameter("name");
        String raw_contact = request.getParameter("contact");

        String raw_startPrice = request.getParameter("startPrice");
        String raw_endPrice = request.getParameter("endPrice");

        String raw_start_updated_date = request.getParameter("start_updated_date");
        String raw_end_updated_date = request.getParameter("end_updated_date");

        String raw_end_created_date = request.getParameter("end_created_date");
        String raw_start_created_date = request.getParameter("start_created_date");

        String raw_created_by = request.getParameter("created_by");
        String raw_status_fee = request.getParameter("status_fee");

        String query = ""
                + "SELECT * FROM product p where p.id in(     \n"
                + "SELECT p.id FROM product p\n"
                + "JOIN `account` ac on p.created_by = ac.id\n"
                + "where \n"
                + "p.status_product =1 and status_checking =0 \n";
        if (raw_id != null && !raw_id.trim().isEmpty()) {
            query += " and p.id = ? \n";
        }
        if (raw_name != null && !raw_name.trim().isEmpty()) {
            query += " and p.name like ? \n";
        }
        if (raw_contact != null && !raw_contact.trim().isEmpty()) {
            query += "and p.contact like ? \n";
        }
        if (raw_created_by != null && !raw_created_by.trim().isEmpty()) {
            query += " and ac.user like ? \n";
        }
        if (raw_status_fee != null && !raw_status_fee.trim().isEmpty()) {
            query += " and p.status_fee = ? \n";
        }
        if (raw_startPrice != null && raw_endPrice != null && !raw_startPrice.trim().isEmpty() && !raw_endPrice.trim().isEmpty()) {
            query += " and p.price BETWEEN ? and ? \n";
        }
        if (raw_start_updated_date != null && raw_end_updated_date != null && !raw_start_updated_date.trim().isEmpty() && !raw_end_updated_date.trim().isEmpty()) {
            query += " and p.updated_date BETWEEN ? AND ?\n";
        }
        if (raw_end_created_date != null && raw_start_created_date != null && !raw_end_created_date.trim().isEmpty() && !raw_start_created_date.trim().isEmpty()) {
            query += " and p.created_date BETWEEN ? AND ? \n";
        }
        query += ")";

        MyDAO myDao = new MyDAO();
        List<Account> accountList = dao.getAllAccount();
        
        try {
            PreparedStatement ps = myDao.con.prepareStatement(query);
            int index = 1;

            if (raw_id != null && !raw_id.trim().isEmpty()) {
                ps.setInt(index, Integer.parseInt(raw_id));
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
            if (raw_created_by != null && !raw_created_by.trim().isEmpty()) {
                ps.setNString(index, "%" + raw_created_by + "%");
                index++;
            }
            if (raw_status_fee != null && !raw_status_fee.trim().isEmpty()) {
                ps.setInt(index, Integer.parseInt(raw_status_fee));
                index++;
            }
            if (raw_startPrice != null && raw_endPrice != null && !raw_startPrice.trim().isEmpty() && !raw_endPrice.trim().isEmpty()) {
                ps.setInt(index, Integer.parseInt(raw_startPrice));
                index++;
                ps.setInt(index, Integer.parseInt(raw_endPrice));
                index++;
            }
            if (raw_start_updated_date != null && raw_end_updated_date != null && !raw_start_updated_date.trim().isEmpty() && !raw_end_updated_date.trim().isEmpty()) {
                ps.setString(index, raw_start_updated_date);
                index++;
                ps.setString(index, raw_end_updated_date);
                index++;
            }
            if (raw_end_created_date != null && raw_start_created_date != null && !raw_end_created_date.trim().isEmpty() && !raw_start_created_date.trim().isEmpty()) {
                ps.setString(index, raw_start_created_date);
                index++;
                ps.setString(index, raw_end_created_date);
                index++;
            }
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listP.add(new Product(rs.getInt("id"),
                        rs.getInt("created_by"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getFloat("price"),
                        rs.getInt("status_fee"),
                        rs.getString("description"),
                        rs.getString("contact"),
                        rs.getString("hidden_content"),
                        rs.getInt("status_product"),
                        rs.getDate("created_date"),
                        rs.getDate("updated_date")));

            }
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
        }
        request.setAttribute("accountList", accountList);
        request.setAttribute("listP", listP);
        request.getRequestDispatcher("include_productList.jsp").forward(request, response);

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
