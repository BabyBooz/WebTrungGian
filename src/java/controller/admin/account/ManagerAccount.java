/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin.account;

import paging.filter.AccountFilter;
import dao.AccountDAO;
import entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import paging.Pagination;
import paging.ParamsPaging;

/**
 *
 * @author Fangl
 */
@WebServlet(name = "ManagerAccount", urlPatterns = {"/manager-account"})
public class ManagerAccount extends HttpServlet {

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
        doGet(request, response);
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
        String url = "admin/manager-account.jsp";
        try {

            AccountDAO accountDAO = new AccountDAO();
            Integer page = Integer.parseInt(request.getParameter("page") == null ? "1" : request.getParameter("page"));
            AccountFilter accountFilter = new AccountFilter();
            accountFilter.setPage(page);
            accountFilter.setTotalPage(accountDAO.countTotalAccount()); //lay tong sp (11)
            accountFilter.setOffset(0);
            accountFilter.setPageSize(ParamsPaging.PAGE_SIZE);
              //default value
            List<Account> accounts = accountDAO.findAllByFilter(accountFilter);
            request.setAttribute("paging", accountFilter); //
            request.setAttribute("ACCOUNTS", accounts);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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

        String url = "admin/manager-account.jsp";
        try {

            AccountDAO accountDAO = new AccountDAO();
            String role = request.getParameter("role") == null ? "" : request.getParameter("role");
            Boolean status = Boolean.parseBoolean(request.getParameter("status"));
            String searchValue = request.getParameter("searchValue") == null ? "" : request.getParameter("searchValue");
            String sortBy = request.getParameter("sortBy");
            Integer page = Integer.parseInt(request.getParameter("page") == null ? "1" : request.getParameter("page"));

            AccountFilter accountFilter = new AccountFilter(null, role, status, searchValue, sortBy,
                    page, accountDAO.countTotalAccount(), 0, ParamsPaging.PAGE_SIZE);

            List<Account> accounts = accountDAO.findAllByFilter(accountFilter);
            request.setAttribute("paging", accountFilter);
            request.setAttribute("ACCOUNTS", accounts);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }

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
