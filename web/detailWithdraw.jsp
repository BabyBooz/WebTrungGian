<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Thông Tin</title>
        <style>
            /* Reset CSS */
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }

            body {
                font-family: Arial, sans-serif;
                background-color: #f8f9fa;
            }

            .container {
                max-width: 800px;
                margin: 0 auto;
                padding: 20px;
            }

            .title {
                text-align: center;
                margin-bottom: 20px;
            }

            .info-table {
                width: 100%;
                border-collapse: collapse;
            }

            .info-table tr {
                border-bottom: 1px solid #ccc;
            }

            .info-table td {
                padding: 10px 0;
            }

            .label {
                font-weight: bold;
                width: 150px;
            }

            .value {
                padding-left: 20px;
            }

            /* Hover effect */
            .info-table tr:hover {
                background-color: #f0f0f0;
            }
            body{
                background-color: #ccc;
            }
            .container{
                background-color: #ffffff;
            }
            .mua {
                display: block; /* Hiển thị dạng block để căn giữa */
                margin: 0 auto; /* Căn giữa theo chiều ngang */
                padding: 10px 20px; /* Đặt khoảng cách xung quanh nút */
                font-size: 16px; /* Đặt kích thước chữ */
                background-color: #343a40; /* Màu nền */
                color: #fff; /* Màu chữ */
                border: none; /* Loại bỏ viền */
                border-radius: 5px; /* Bo góc */
                cursor: pointer; /* Con trỏ chuột khi di chuột qua */
                margin-top: 15px;
            }
            .mua1 {
                display: block; /* Hiển thị dạng block để căn giữa */
                margin: 0 auto; /* Căn giữa theo chiều ngang */
                padding: 10px 20px; /* Đặt khoảng cách xung quanh nút */
                font-size: 16px; /* Đặt kích thước chữ */
                background-color: #343a40; /* Màu nền */
                color: #fff; /* Màu chữ */
                border: none; /* Loại bỏ viền */
                border-radius: 5px; /* Bo góc */
                cursor: pointer; /* Con trỏ chuột khi di chuột qua */
                margin-top: 15px;
            }
            .mua1:hover {
                background-color: red; /* Màu nền khi di chuột qua */
            }
            .mua:hover {
                background-color: #0056b3; /* Màu nền khi di chuột qua */
            }
            .title {
                text-align: center;
                margin-bottom: 20px;
                position: relative;
            }

            .title a {
                position: absolute;
                top: 50%;
                left: 10px;
                transform: translateY(-50%);
                color: #007bff;
                text-decoration: none;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2 class="title"><a href="javascript:history.go(-1)" style="color: #0056b3">&lt; Trở lại</a>Thông Tin</h2>
            <table class="info-table">
                <c:forEach items="${requestScope.listDraw}" var="w">
                    <tr>
                        <td class="label">ID:</td>
                        <td  class="value"  >${w.id}</td>
                    </tr>
                    <tr>
                        <td class="label">Tên Ngân Hàng:</td>
                        <td>${w.bankName} </td>
                    </tr>
                    <tr>
                        <td class="label">Cơ Sở:</td>
                        <td>${w.bankBranch}</td>
                    </tr>
                    <tr>
                        <td class="label">Số Tài Khoản:</td>
                        <td>${w.accountNumber}</td>

                    </tr>
                    <tr>
                        <td class="label">Người Tạo:</td>
                        <td>${w.accountName}</td>

                    </tr>
                    <tr>
                        <td class="label">Số Tiền:</td>
                        <td name="price" class="value">${w.price}</td>
                    </tr>

                    <tr>
                        <td class="label">Trạng Thái:</td>
                        <td>
                            <c:choose>
                                <c:when test="${w.statusTransaction == '0'}"> 
                                    <p class="text-primary">Đang tiếp nhận</p>
                                </c:when>
                                <c:when test="${w.statusTransaction == '1'}"> 
                                    <p class="text-success">Chấp nhận</p>
                                </c:when>
                                <c:when test="${w.statusTransaction == '2'}"> 
                                    <p class="text-danger">Từ chối</p>
                                </c:when>
                            </c:choose>
                        </td>
                    </tr>
                    <tr>
                        <td class="label">Ngày Tạo:</td>
                        <td class="value">${w.createdDate}</td>
                    </tr>
                </c:forEach>
            </table> 
        </div>
    </body>
</html>
