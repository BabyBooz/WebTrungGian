/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package searchPublicMarket;

import dao.DAO;
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
import java.util.List;
import java.util.Date;
/**
 *
 * @author linhnghiem
 */
@WebServlet(name="updateDetail", urlPatterns={"/updateDetail"})
public class updateDetail extends HttpServlet {
   

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet updateDetail</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet updateDetail at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        DAO dao = new DAO();
        int id = Integer.parseInt(request.getParameter("id"));
        List<Product> list = dao.getProductMarketById(id);
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");
        request.setAttribute("name", a.getUser());
        List<Account> accountList = dao.getAllAccount();
        request.setAttribute("accountList", accountList);
        request.setAttribute("listP", list);
        request.getRequestDispatcher("updateDetailMarket.jsp").forward(request, response);
    } 


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        DAO dao = new DAO();
        int id = Integer.parseInt(request.getParameter("id"));
        String contact = request.getParameter("contact");
        String name = request.getParameter("name");
        float price = Float.parseFloat(request.getParameter("price"));
        String description = request.getParameter("description");
        int status_fee = Integer.parseInt(request.getParameter("status_fee"));
        String hidden_content = request.getParameter("hidden_content");
        int created_by = Integer.parseInt(request.getParameter("created_by"));
        int status_product =Integer.parseInt(request.getParameter("status_product"));
        Product product = new Product(created_by, name, price, status_fee, description, contact, hidden_content, status_product);
        dao.updateProduct(product, id);
        
        List<Product> list = dao.getProductMarketById(id);
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");
        request.setAttribute("name", a.getUser());
        List<Account> accountList = dao.getAllAccount();
        request.setAttribute("accountList", accountList);
        request.setAttribute("listP", list);
        request.getRequestDispatcher("updateDetailMarket.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
