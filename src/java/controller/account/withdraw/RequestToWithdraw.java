/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.account.withdraw;

import dao.DAO;
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
import java.sql.Date;

/**
 *
 * @author Fangl
 */
@WebServlet(name = "RequestToWithdraw", urlPatterns = {"/RequestToWithdraw"})
public class RequestToWithdraw extends HttpServlet {

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
            out.println("<title>Servlet RequestToWithdraw</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RequestToWithdraw at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        String url = "request-withdraw.jsp";
        try {
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute("acc");
            if (account == null) {
                url = "login.jsp";
            }

            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
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
        String url = "request-withdraw.jsp";
        String message = "";
        try {

            WithdrawDAO withdrawDAO = new WithdrawDAO();
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute("acc");

            if (account == null) {
                url = "login.jsp";
                request.getRequestDispatcher(url).forward(request, response);
                return;
            }

            if (withdrawDAO.getWithdrawIsRequested(account.getId()) != null) {
                message = "Bạn đã yêu cầu yêu cầu rút tiền vui lòng đợi";
                request.setAttribute("mess", message);
                request.getRequestDispatcher(url).forward(request, response);
                return;
            }

            String bankName = request.getParameter("bankName");
            String bankBranch = request.getParameter("bankBranch");
            String accountNumber = request.getParameter("accountNumber");
            String accountName = request.getParameter("accountName");

            Double amount = Double.valueOf(request.getParameter("amount") == null ? "0.0" : request.getParameter("amount"));
            if (amount < 50000) {
                message = "Số tiền tối thiểu được rút là 50.000(VNĐ)";
                request.setAttribute("mess", message);
                request.getRequestDispatcher(url).forward(request, response);
                return;
            } else {
                if (amount > account.getWallet()) {
                    message = "Số dư bạn không đủ";
                    request.setAttribute("mess", message);
                    request.getRequestDispatcher(url).forward(request, response);
                    return;
                } else {
                    request.setAttribute("wallet", account.getWallet() - Integer.parseInt(accountNumber));
                    request.setAttribute("name", account.getUser());
                    session.setAttribute(url, url);
                    WithdrawDTO withdrawDTO = new WithdrawDTO();
                    withdrawDTO.setUserId(account.getId());
                    withdrawDTO.setBankName(bankName);
                    withdrawDTO.setBankBranch(bankBranch);
                    withdrawDTO.setAccountNumber(accountNumber);
                    withdrawDTO.setAccountName(accountName);
                    withdrawDTO.setPrice(amount.floatValue());
                    withdrawDTO.setStatusTransaction(0);
                    withdrawDTO.setCreatedDate(new Date(System.currentTimeMillis()));
                    withdrawDTO.setUpdatedDate(new Date(System.currentTimeMillis()));
                    DAO dao = new DAO();
                    float wallet = account.getWallet() - amount.floatValue();
                    dao.updateWallet(wallet, account.getId());
                    Account a = (Account) dao.getAccountID(account.getId());
                    session.setAttribute("acc", a);
                    if (withdrawDAO.addWithdraw(withdrawDTO)) {
                        message = "Yêu cầu rút thành công";
                    } else {
                        message = "Yêu cầu rút thất bại";
                    }
                    request.setAttribute("mess", message);
                    request.getRequestDispatcher(url).forward(request, response);
                }
                }

            }catch (Exception e) {
            e.printStackTrace();
        }
        }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo
        
            () {
        return "Short description";
        }// </editor-fold>

    }