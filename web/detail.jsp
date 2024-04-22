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
            <h2 class="title"><a href="javascript:history.go(-1)" style="color: #0056b3">&lt; Trở lại</a> Danh Sách Thông Tin</h2>
            <form action="muaHang" method="post">
                <table class="info-table">
                    <c:forEach items="${listP}" var="p">
                        <tr>
                            <td class="label">ID:</td>
                            <td name="id" class="value">${p.id}</td>
                            <input type="hidden" name="id" value="${p.id}">
                        </tr>
                        <tr>
                            <td class="label">Tên:</td>
                            <td name="name" class="value">${p.name}</td>
                        </tr>
                        <tr>
                            <td class="label">Contact:</td>
                            <td name="contact" class="value">${p.contact}</td>
                            <input type="hidden" name="contact" value="${p.contact}">
                        </tr>
                        <tr>
                            <td class="label">Price:</td>
                            <td name="price" class="value">${p.price}</td>
                            <input type="hidden" name="price" value="${p.price}">
                        </tr>
                        <tr>
                            <td class="label">Mô Tả:</td>
                            <td name="description" class="value">${p.description}</td>
                            <input type="hidden" name="description" value="${p.description}">
                        </tr>
                        <tr>
                            <td class="label">Bên Chịu Phí:</td>
                            <td  class="value">
                                <c:choose>
                                    <c:when test="${p.status_fee == 0}">
                                        Bên Bán
                                        <input type="hidden" name="status_fee" value="0">
                                    </c:when>
                                    <c:otherwise>
                                        Bên Mua
                                        <input type="hidden" name="status_fee" value="1">
                                    </c:otherwise>
                                </c:choose>
                            </td> 
                        </tr>
                        <tr>
                            <td class="label">Phí Trung Gian:</td>
                            <td class="value">${p.price*(1/10)}</td>
                        </tr>
                        <tr>
                            <td class="label">Tổng Phí:</td>
                            <td class="value">${(p.price*(1/10))+p.price}</td>
                        </tr>
                        <tr>
                            <td class="label">Người Bán:</td>
                            <c:forEach items="${requestScope.accountList}" var="ac">
                                <c:if test="${p.created_by eq ac.id}">
                                    <td name="user" class="value">${ac.user}</td> 
                                    <input type="hidden" name="created_by" value="${p.created_by}">
                                </c:if>
                            </c:forEach> 
                        </tr>
                        <tr>
                            <td class="label">Thời Gian Tạo:</td>
                            <td name="created_date" class="value">${p.created_date}</td>
                            <input type="hidden" name="created_date" value="${p.created_date}">
                        </tr>
                        <tr>
                            <td class="label">Thời Gian Cập Nhật:</td>
                            <td name="updated_date" class="value">${p.updated_date}</td>
                            <input type="hidden" name="updated_date" value="${p.updated_date}">
                        </tr>

                    </c:forEach>
                </table> 
                <input class="mua" type="submit" value="Mua Hàng">
            </form>
        </div>
    </body>
</html>
