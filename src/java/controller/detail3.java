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
 * @author Fpt
 */
@WebServlet(name = "detail3", urlPatterns = {"/detail3"})
public class detail3 extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        //B1: lay data from dao
        DAO dao = new DAO();
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");
        request.setAttribute("name", a.getUser());
        List<Account> accountList = dao.getAllAccount();
        Product productDetail = dao.getProductByID(id);
        //B2: set data to jsp
        request.setAttribute("accountList3", accountList);
        request.setAttribute("productDetail3", productDetail);
        request.getRequestDispatcher("detail3.jsp").forward(request, response);
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
