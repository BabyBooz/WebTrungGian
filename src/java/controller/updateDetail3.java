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
@WebServlet(name = "updateDetail3", urlPatterns = {"/update3"})
public class updateDetail3 extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        DAO dao = new DAO();
//        HttpSession session = request.getSession();
//        Product pro = (Product) session.getAttribute("productDetail3");
//        int id = pro.getId();
//
//        String name = request.getParameter("name");
//        float price = Float.parseFloat(request.getParameter("price"));
//        String description = request.getParameter("description");
//        String contact = request.getParameter("contact");
//        String hidden_conten = request.getParameter("hidden_conten");
//        dao.updateProduct(name, price, description, contact, hidden_conten, id);
////        dao.updateAccount(user, email, phone, describes, id);
//        Product updatedProduct = dao.getProductByID(id);
////        Account updatedAccount = dao.getAccountID(id);
//        session.setAttribute("productDetail3", updatedProduct);
//
//        request.getRequestDispatcher("detail3.jsp").forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        String name = request.getParameter("name");
        float price = 0;
        try {
            price = Float.parseFloat(request.getParameter("price"));
        } catch (Exception e) {
            // ignored
        }
        int status_fee = Integer.parseInt(request.getParameter("status_fee"));
        String description = request.getParameter("description");
        String contact = request.getParameter("contact");
        String hidden_content = request.getParameter("hidden_content");
        int status_product = Integer.parseInt(request.getParameter("status_product"));
        HttpSession session = request.getSession();

        Account a = (Account) session.getAttribute("acc");
        int accountId = a.getId();
        DAO dao = new DAO();
        dao.updateProduct(name, price, status_fee, description, contact, hidden_content, status_product, productId);
        Product updatedProduct = dao.getProductByID(productId);

        List<Account> accountList = dao.getAllAccount();

        request.setAttribute("accountList3", accountList);
        request.setAttribute("productDetail3", updatedProduct);

        request.getRequestDispatcher("detail3.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
