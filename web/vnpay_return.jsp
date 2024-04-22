
<%@page import="java.net.URLEncoder"%>
<%@page import="java.nio.charset.StandardCharsets"%>
<%@page import="common_vnpay.Config"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.LinkedList" %>
<%@page import="dao.DAO" %>
<%@page import="entity.Account" %>
<%@page import="entity.khanh.WithdrawDTO" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Payment Bill</title>
        <!-- Bootstrap core CSS -->
        <link href="/vnpay_jsp/assets/jumbotron-narrow.css" rel="stylesheet"> 
        <script src="assets/js/jquery-1.11.3.min.js"></script>
        <link rel="stylesheet" href="css/TransactionDetails.css"/>
        <link rel="stylesheet" href="css/bootstrap-grid.css"/>
        <style>
            /* Thêm ki?u cho ph?n t? container */
            .container {
                margin-top: 100px;
                border-radius: 5px;
                background-color: #f8f9fa; /* Màu n?n */
                padding: 20px;
                box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1); /* Hi?u ?ng bóng ?? */
            }
            /* Thêm ki?u cho ph?n t? header */
            .header h1 {
                color: orange;
                text-align: center;
            }
            /* Thêm ki?u cho các th? td */
            td {
                padding-top: 10px;
                padding-bottom: 10px;
            }
            /* Thêm ki?u cho ph?n t? input */
            input[type="submit"] {
                margin-right: 10px;
                background-color: #007bff; /* Màu n?n */
                color: #fff; /* Màu ch? */
                border: none;
                padding: 8px 20px;
                border-radius: 5px;
                cursor: pointer;
            }
            /* Thêm ki?u cho ph?n t? input khi ???c hover */
            input[type="submit"]:hover {
                background-color: #0056b3; /* Màu n?n hover */
            }
        </style>
    </head>
    <body>
        <%
                //Begin process return from VNPAY
                Map fields = new HashMap();
                for (Enumeration params = request.getParameterNames(); params.hasMoreElements();) {
                    String fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
                    String fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
                    if ((fieldValue != null) && (fieldValue.length() > 0)) {
                        fields.put(fieldName, fieldValue);
                    }
                }

                String vnp_SecureHash = request.getParameter("vnp_SecureHash");
                if (fields.containsKey("vnp_SecureHashType")) {
                    fields.remove("vnp_SecureHashType");
                }
                if (fields.containsKey("vnp_SecureHash")) {
                    fields.remove("vnp_SecureHash");
                }
                String signValue = Config.hashAllFields(fields);

        %>
        <div class="container">
            <div class="header clearfix">
                <h1>Payment Bill</h1>
            </div>
            <table class="table-responsive" style="margin-left: 19px">
                <!-- Các dòng thông tin -->
                <tr class="form-group">
                    <td>1. Mã Giao Dịch:</td>
                    <td><%=request.getParameter("vnp_TxnRef")%></td>
                </tr>    
                <tr class="form-group">
                    <td>2. Số Tiền:</td>
                    <td><%= Double.parseDouble(request.getParameter("vnp_Amount")) / 100 %></td>
                </tr>  
                <tr class="form-group">
                    <td style="width: 250px">3. Mô Tả:</td>
                    <td><%=request.getParameter("vnp_OrderInfo")%></td>
                </tr> 
                <tr class="form-group">
                    <td >4. Tên Ngân Hàng:</td>
                    <td><%=request.getParameter("vnp_BankCode")%></td>
                </tr> 
                <tr class="form-group">
                    <td>5. Mã Ngày Tạo:</td>
                    <td><%=request.getParameter("vnp_PayDate")%></td>
                </tr> 
                <tr class="form-group">
                    <td>6. Trạng Thái:</td>
                    <%
    HttpSession ss = request.getSession();
    Account a = (Account) ss.getAttribute("acc");
    int id = 0; // Khai báo và khởi tạo biến id
    if (a != null) {
        
        id = a.getId(); // Gán giá trị cho biến id nếu đối tượng Account không null
    }
                    %>
                    <td>
                        <% 
                            DAO dao = new DAO();
                            // Kiểm tra xem đã tính toán total chưa
                            boolean isTotalCalculated = ss.getAttribute("isTotalCalculated") != null && (boolean) ss.getAttribute("isTotalCalculated");
                            if (signValue.equals(vnp_SecureHash)) {
                                if (!isTotalCalculated) {
                                    if ("00".equals(request.getParameter("vnp_TransactionStatus"))) {
                                        out.print("Thành công");
                                        float wallet = a.getWallet();
                                        float recharge1 = Float.parseFloat(request.getParameter("vnp_Amount")) / 100;
                                        float recharge = recharge1 + wallet;
                                        
                                        int userId = id;
                                        String bankName = request.getParameter("vnp_BankCode");
                                        String user = a.getUser();
                                        
                                        WithdrawDTO withdraw = new WithdrawDTO
                                        (userId, bankName, 
                                        user, 
                                        recharge1 , 4);
                                        
                                        dao.insertWithDraw(withdraw);
                                        
                                        dao.updateWallet(recharge, id);
                                        // Đặt cờ là đã tính toán total
                                        session.setAttribute("isTotalCalculated", true);
                                    } else {
                                        out.print("Không thành công");
                                    }
                                } else {
                                    out.print("Thành công.");
                                }
                            } else {
                                out.print("invalid signature");
                            }        
                        %>
                    </td>
                </tr> 
            </table>
        </div>
        <br>
        <form action="validation" method="get" style="text-align: center;">
            <input type="hidden" name="id" value="<%= id %>">
            <input type="submit" value="Home Page">
        </form>
    </body>
</html>
