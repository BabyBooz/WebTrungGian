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
            <h2 class="title"><a href="javascript:history.go(-1)" style="color: #0056b3">&lt; Trở lại</a> Danh Sách Thông Tin</h2>
            <form action="muaHang" method="post">
                <table class="info-table">
                    <c:forEach items="${requestScope.myCart}" var="m">
                        <tr>
                            <td class="label">ID:</td>
                            <td name="id" class="value">${m.id}</td>
                        </tr>
                        <tr>
                            <td class="label">Người Bán:</td>
                            <td>
                                <c:forEach items="${acc}" var="a">
                                    <c:if test="${a.id eq m.seller_id}">
                                        ${a.user}
                                    </c:if>
                                </c:forEach>
                            </td>
                        </tr>
                        <tr>
                            <td class="label">Người Mua:</td>
                            <td>
                                <c:forEach items="${acc}" var="a">
                                    <c:if test="${a.id eq m.buyer_id}">
                                        ${a.user}
                                    </c:if>
                                </c:forEach> 
                            </td>
                        </tr>
                        <tr>
                            <td class="label">Trạng Thái:</td>
                            <td>
                                <c:choose>
                                    <c:when test="${m.status_checking == 1}">
                                        Bên Mua Xem Hàng
                                    </c:when>
                                    <c:when test="${m.status_checking == 2}">
                                        Hoàn Thành
                                    </c:when>
                                    <c:otherwise>
                                        Khiếu Nại
                                    </c:otherwise>
                                </c:choose> 
                            </td>

                        </tr>
                        <tr>
                            <td class="label">Chủ Đề Trung Gian:</td>
                            <td>
                                <c:forEach items="${requestScope.product}" var="p">
                                    <c:if test="${p.id eq m.product_id}">
                                        ${p.name}
                                    </c:if>
                                </c:forEach> 
                            </td>

                        </tr>
                        <tr>
                            <td class="label">Price:</td>
                            <td name="price" class="value">${m.price}</td>
                        </tr>

                        <tr>
                            <td class="label">Bên Chịu Phí:</td>
                            <td>
                                ${m.status_fee == 0 ? "Bên Mua" : "Bên Bán"}
                            </td>
                        </tr>
                        <tr>
                            <td class="label">Phí Trung Gian:</td>
                            <td class="value">${m.price*(1/10)}</td>
                        </tr>
                        <tr>
                            <td class="label">Tổng Phí:</td>
                            <td class="value">${(m.price*(1/10))+m.price}</td>
                        </tr>
                        <tr>
                            <td class="label">Mô Tả:</td>
                            <td name="description" class="value">${m.description}</td>
                        </tr>
                        <tr>
                            <td class="label">Phương Thức Liên Hệ:</td>
                            <td name="description" class="value">${m.contact}</td>
                        </tr>
                        <tr>
                            <td class="label">Nội Dung Ẩn:</td>
                            <td>
                                <c:forEach items="${requestScope.product}" var="p">
                                    <c:if test="${p.id eq m.product_id}">
                                        ${p.hidden_content}
                                    </c:if>
                                </c:forEach>
                            </td>

                        </tr>
                        <tr>
                            <td class="label">Thời Gian Tạo:</td>
                            <td name="created_date" class="value">${m.created_date}</td>
                        </tr>
                        <tr>
                            <td class="label">Thời Gian Cập Nhật:</td>
                            <td name="updated_date" class="value">${m.updated_date}</td>
                        </tr>
                    </c:forEach>
                </table> 
            </form>
        </div>
    </body>
</html>
