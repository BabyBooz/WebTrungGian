/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin.withdraw;

import dao.khanh.WithdrawDAO;
import entity.khanh.WithdrawDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import paging.ParamsPaging;
import paging.filter.WithdrawFilter;

/**
 *
 * @author Fangl
 */
@WebServlet(name = "ManagerWithdraw", urlPatterns = {"/ManagerWithdraw"})
public class ManagerWithdraw extends HttpServlet {

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
        String url = "/admin/manager-withdraw.jsp";
        try {
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

            List<WithdrawDTO> withdrawDTOs = withdrawDAO.getWithdrawsByFilter(withdrawFilter);
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
        String url = "/admin/manager-withdraw.jsp";
        try {
            Integer page = Integer.parseInt(request.getParameter("page") == null ? "1" : request.getParameter("page"));
            String searchValue = request.getParameter("searchValue");
            Integer status = request.getParameter("status") == null ? null : Integer.parseInt(request.getParameter("status"));

            WithdrawDAO withdrawDAO = new WithdrawDAO();

            WithdrawFilter withdrawFilter = new WithdrawFilter();
            withdrawFilter.setPage(page);
            withdrawFilter.setPageSize(ParamsPaging.PAGE_SIZE);
            withdrawFilter.setTotalPage(withdrawDAO.countTotalWithDraw());

            withdrawFilter.setSearchValue(searchValue);
            withdrawFilter.setStatus(status);

            List<WithdrawDTO> withdrawDTOs = withdrawDAO.getWithdrawsByFilter(withdrawFilter);
            request.setAttribute("WITHDRAWS", withdrawDTOs);
            request.setAttribute("paging", withdrawFilter);
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
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
