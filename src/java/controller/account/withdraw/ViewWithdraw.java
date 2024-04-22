/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.account.withdraw;

import dao.khanh.WithdrawDAO;
import entity.Account;
import entity.khanh.WithdrawDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import paging.ParamsPaging;
import paging.filter.WithdrawFilter;

/**
 *
 * @author Fangl
 */
@WebServlet(name = "ViewWithdraw", urlPatterns = {"/ViewWithdraw"})
public class ViewWithdraw extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ViewWithdraw</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewWithdraw at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = "historyRecharge.jsp";
        try {

            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute("acc");

            if (account == null) {
                url = "login.jsp";
                request.getRequestDispatcher(url).forward(request, response);
                return;
            }
            Account a = (Account) session.getAttribute("acc");
            request.setAttribute("userid", a.getId());
            request.setAttribute("name", a.getUser());
            request.setAttribute("wallet", a.getWallet());
            
            Integer page = Integer.parseInt(request.getParameter("page") == null ? "1" : request.getParameter("page"));
            String searchValue = request.getParameter("searchValue") == null ? "" : request.getParameter("searchValue");
            Integer status = request.getParameter("status") == null || request.getParameter("status").trim().length() == 0 ? null : Integer.parseInt(request.getParameter("status"));

            WithdrawDAO withdrawDAO = new WithdrawDAO();

            WithdrawFilter withdrawFilter = new WithdrawFilter();
            withdrawFilter.setPage(page);
            withdrawFilter.setPageSize(ParamsPaging.PAGE_SIZE);
            withdrawFilter.setTotalPage(withdrawDAO.countTotalWithDraw());

            withdrawFilter.setSearchValue(searchValue);
            withdrawFilter.setStatus(status);

            List<WithdrawDTO> withdrawDTOs = withdrawDAO.getWithdrawsByFilterUser(withdrawFilter, account.getId());
            request.setAttribute("WITHDRAWS", withdrawDTOs);
            request.setAttribute("paging", withdrawFilter);
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher(url).forward(request, response);
        }
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
