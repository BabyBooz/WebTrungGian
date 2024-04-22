<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Add Product</title>
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
            input, select {
                width: calc(100% - 10px); /* Độ rộng của input sẽ bằng 100% trừ đi khoảng cách giữa các ô input */
                padding: 8px; /* Khoảng cách nội dung bên trong input */
                border: 1px solid #ccc; /* Viền input */
                border-radius: 4px; /* Bo tròn các góc của input */
                font-size: 16px; /* Kích thước chữ */
                margin-bottom: 10px; /* Khoảng cách dưới cùng của input */
            }
            textarea {
                width: calc(100% - 10px); /* Độ rộng của input sẽ bằng 100% trừ đi khoảng cách giữa các ô input */
                padding: 8px; /* Khoảng cách nội dung bên trong input */
                border: 1px solid #ccc; /* Viền input */
                border-radius: 4px; /* Bo tròn các góc của input */
                font-size: 16px; /* Kích thước chữ */
                margin-bottom: 10px; /* Khoảng cách dưới cùng của input */
            }
            .error-message {
                color: red;
                font-weight: bold;
                /* Thêm các thuộc tính CSS khác tùy thuộc vào thiết kế mong muốn */
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2 class="title"><a href="javascript:history.go(-1)" style="color: #0056b3">&lt; Back</a> Giao dịch mới</h2>
            <%
String errorMessage = (String) request.getAttribute("errorMessage");
if (errorMessage != null) {
            %>
            <div class="error-message">
                <%= errorMessage %>
            </div>
            <%
                // Xóa thông điệp từ session sau khi đã hiển thị
                session.removeAttribute("errorMessage");
            }
            %>
            <form action="addProduct" method="post">
                <table class="info-table">
                    <tr>
                        <td class="label">Chủ đề:</td>
                        <td><input type="text" name="title" required="" value="${not empty requestScope.title ? requestScope.title : ''}"></td>
                    </tr>
                    <tr>
                        <td class="label">Giá:</td>
                        <td><input type="number" name="price" required="" min=""></td>
                    </tr>
                    <tr>
                        <td class="label">Người chịu phí:</td>
                        <td>                    
                            <select name="fee" required="">
                                <option value="0" ${not empty requestScope.fee && requestScope.fee == '0' ? 'selected' : ''}>Người mua</option>
                                <option value="1" ${not empty requestScope.fee && requestScope.fee == '1' ? 'selected' : ''}>Người bán</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="label">Mô tả:</td>
                        <td><textarea name="description" required="">${not empty requestScope.description ? requestScope.description : ''}</textarea></td>
                    </tr>
                    <tr>
                        <td class="label">Phương thức liên lạc:</td>
                        <td><input type="text" name="contact" required="" value="${not empty requestScope.contact ? requestScope.contact : ''}"></td>
                    </tr>
                    <tr>
                        <td class="label">Nội dung ẩn:</td>
                        <td><textarea name="hiddenContent" required="">${not empty requestScope.hiddenContent ? requestScope.hiddenContent : ''}</textarea></td>
                    </tr>
                    <tr>
                        <td class="label">Công khai:</td>
                        <td>                        
                            <select name="publicStatus" required="">
                                <option value="1" ${not empty requestScope.publicStatus && requestScope.publicStatus == '1' ? 'selected' : ''}>Công khai</option>
                                <option value="0" ${not empty requestScope.publicStatus && requestScope.publicStatus == '0' ? 'selected' : ''}>Riêng tư</option>
                            </select>
                        </td>
                    </tr>
                </table> 
                <input class="mua" type="submit" value="Tạo">
            </form>
        </div>
    </body>
</html>