<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Thông Tin</title>
        <style>
            /* Container chứa hai nút */
            .button-container {
                display: flex;
                justify-content: space-around;
                margin-top: 15px;
            }
            /* Kiểu của nút */
            .input-button {
                flex: 1; /* Đảm bảo rằng mỗi nút sẽ chiếm một phần bằng nhau của không gian trong container */
                padding: 8px 20px; /* Khoảng cách xung quanh nút */
                font-size: 16px; /* Kích thước chữ */
                border: none; /* Loại bỏ viền */
                border-radius: 5px; /* Bo góc */
                cursor: pointer; /* Con trỏ chuột khi di chuột qua */
            }

            /* Hiệu ứng khi nút được nhấn */
            .input-button:active {
                background-color: #28a745; /* Màu nền khi nút được nhấn */
                color: #fff; /* Màu chữ khi nút được nhấn */
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
            input[type="text"] {
                width: calc(100% - 10px); /* Độ rộng của input sẽ bằng 100% trừ đi khoảng cách giữa các ô input */
                padding: 8px; /* Khoảng cách nội dung bên trong input */
                border: 1px solid #ccc; /* Viền input */
                border-radius: 4px; /* Bo tròn các góc của input */
                font-size: 16px; /* Kích thước chữ */
                margin-bottom: 10px; /* Khoảng cách dưới cùng của input */
            }
            .form-select {
                width: calc(100% - 10px); /* Độ rộng của input sẽ bằng 100% trừ đi khoảng cách giữa các ô input */
                padding: 8px; /* Khoảng cách nội dung bên trong input */
                border: 1px solid #ccc; /* Viền input */
                border-radius: 4px; /* Bo tròn các góc của input */
                font-size: 16px; /* Kích thước chữ */
                margin-bottom: 10px; /* Khoảng cách dưới cùng của input */
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2 class="title"><a href="javascript:history.go(-1)" style="color: #0056b3">&lt; Trở lại</a> Danh Sách Thông Tin</h2>
            <form action="updateDetail" method="post">
                <table class="info-table">
                    <c:forEach items="${listP}" var="p">
                        <tr>
                            <td class="label">ID:</td>
                            <td><input type="text" name="id" value="${p.id}" readonly></td>
                        </tr>
                        <tr>
                            <td class="label">Tên:</td>
                            <td><input type="text" name="name" value="${p.name}"></td>
                        </tr>
                        <tr>
                            <td class="label">Trạng Thái:</td>
                            <td><input type="text" name="" value="Sẵn Sàng Giao Dịch"></td>
                        </tr>
                        <tr>
                            <td class="label">Contact:</td>
                            <td><input type="text" name="contact" value="${p.contact}"></td>
                        </tr>
                        <tr>
                            <td class="label">Price:</td>
                            <td><input type="text" name="price" value="${p.price}" readonly=""></td>
                        </tr>
                        <tr>
                            <td class="label">Mô Tả:</td>
                            <td><input type="text" name="description" value="${p.description}"></td>
                        </tr>
                        <tr>
                            <td class="label">Bên Chịu Phí:</td>
                            <td class="value">
                                <select class="form-select" name="status_fee">
                                    <c:choose>
                                        <c:when test="${status_fee == 1}">
                                            <option value="1" selected>Bên Bán</option>
                                            <option value="0">Bên Mua</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="1">Bên Bán</option>
                                            <option value="0" selected>Bên Mua</option>
                                        </c:otherwise>
                                    </c:choose>
                                </select>
                            </td>
                            <td>
                                <input type="hidden" name="status_fee" value="${p.status_fee}">
                            </td>
                        </tr>
                        <tr>
                            <td class="label">Phí Trung Gian:</td>
                            <td><input type="text" name="fee" value="${p.price*(1/10)}" readonly=""></td>
                        </tr>
                        <tr>
                            <td class="label">Tổng Phí:</td>
                            <td><input type="text" name="total_fee" value="${(p.price*(1/10))+p.price}" readonly=""></td>
                        </tr>
                        <tr>
                            <td class="label">Người Bán:</td>
                            <td>
                                <c:forEach items="${requestScope.accountList}" var="ac">
                                    <c:if test="${p.created_by eq ac.id}">
                                        <input type="text" value="${ac.user}" readonly="">
                                        <input type="hidden" name="created_by" value="${p.created_by}">
                                    </c:if>
                                </c:forEach> 
                            </td>
                        </tr>
                        <tr>
                            <td class="label">Nội dung ẩn:</td>
                            <td><input type="text" name="hidden_content" value="${p.hidden_content}"></td>
                        </tr>
                        <tr>
                            <td class="label">Hiện Công Khai: </td>
                            <td>
                                <select class="form-select" name="status_product" >
                                    <option value="1">Hiện công khai</option>
                                    <option value="0">Thông tin ẩn</option>
                                </select>
                            </td>

                        </tr>
                        <tr>
                            <td class="label">Thời Gian Tạo:</td>
                            <td><input type="text" name="created_date" value="${p.created_date}" readonly=""></td>
                        </tr>
                        <tr>
                            <td class="label">Thời Gian Cập Nhật:</td>
                            <td><input type="text" name="updated_date" value="${p.updated_date}" readonly=""></td>
                        </tr>
                    </c:forEach>
                </table> 
                <input class="mua" type="submit" value="Update">
            </form>
        </div>
    </body>
</html>
