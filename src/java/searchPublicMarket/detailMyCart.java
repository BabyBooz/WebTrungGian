/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package searchPublicMarket;

import dao.DAO;
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
import java.util.List;

/**
 *
 * @author linhnghiem
 */
@WebServlet(name = "detailMyCart", urlPatterns = {"/detailMyCart"})
public class detailMyCart extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao = new DAO();
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");
        if (a != null) {
            String id = request.getParameter("id");
            List<MyCart> myCart = dao.getMyCartsById(Integer.parseInt(id));
            List<Account> acc = dao.getAllAccount();
            List<Product> p = dao.getProductMarket();
            request.setAttribute("acc", acc);
            request.setAttribute("product", p);
            request.setAttribute("myCart", myCart);
            request.getRequestDispatcher("detailMyCart.jsp").forward(request, response);
        }else{
            response.sendRedirect("login.jsp");
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
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
