/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DAO;
import entity.Account;
import entity.Product;
import java.io.IOException;
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
@WebServlet(name = "publicMarket", urlPatterns = {"/publicMarket"})
public class publicMarket extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao = new DAO();
        String indexP = request.getParameter("index");
        if (indexP == null) {
            indexP = "1";
        }
        int index = Integer.parseInt(indexP);
        List<Product> list = dao.getProductMarketPage(index);
        
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");
        request.setAttribute("userid", a.getId());
        request.setAttribute("name", a.getUser());
        request.setAttribute("wallet", a.getWallet());
        
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
