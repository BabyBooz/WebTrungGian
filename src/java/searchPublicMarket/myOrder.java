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
@WebServlet(name = "myOrder", urlPatterns = {"/myOrder"})
public class myOrder extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet myOrder</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet myOrder at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");
        if (a != null) {
            List<MyCart> list = dao.getMyOrderPage(index, a.getId());
            request.setAttribute("userid", a.getId());
            request.setAttribute("name", a.getUser());
            request.setAttribute("wallet", a.getWallet());
            request.setAttribute("listO", list);
            List<Account> accountList = dao.getAllAccount();
            request.setAttribute("accountList", accountList);
            List<Product> listP = dao.getProductMarket();
            request.setAttribute("product", listP);
            int count = dao.getMyCartPage(a.getId());
            int endPage = count / 5;
            if (count % 5 != 0) {
                endPage++;
            }
            request.setAttribute("endP", endPage);
            request.getRequestDispatcher("myOrder.jsp").forward(request, response);
        }else{
            response.sendRedirect("login.jsp");
        }

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
