<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Thông báo</title>
        <style>
            body {
                background-color: #f0f0f0; /* Màu xám nhạt */
                font-family: Arial, sans-serif;
                text-align: center;
                padding-top: 50px;
            }

            .message-box {
                background-color: #fff;
                border-radius: 10px;
                padding: 20px;
                box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1); /* Đổ bóng */
                width: 50%;
                margin: auto;
                
            }
            .text-danger {
                padding: 50px;
                color: red;
                font-size: 50px; /* Kích thước font 50px */
            }
            .title {
                font-family: Arial, sans-serif; /* Font chữ */
                color: #0056b3; /* Màu chữ */
                text-decoration: none; /* Loại bỏ gạch chân */
            }

            .title a {
                color: #0056b3; /* Màu chữ cho liên kết */
                text-decoration: none; /* Loại bỏ gạch chân cho liên kết */
            }

            .title a:hover {
                text-decoration: underline; /* Gạch chân khi di chuột qua liên kết */
            }

            /* CSS cho nút */
            .custom-button {
                background-color: #0056b3; /* Màu nền của nút */
                color: white; /* Màu chữ */
                border: none; /* Loại bỏ viền */
                padding: 10px 20px; /* Kích thước nút */
                cursor: pointer;
                border-radius: 5px; /* Bo tròn góc */
                transition: background-color 0.3s; /* Hiệu ứng chuyển đổi màu */
            }

            .custom-button:hover {
                background-color: #007bff; /* Màu nền khi di chuột qua */
            }
        </style>
    </head>
    <body>
        <div class="message-box">
            <h2>Thông Báo</h2>
            <p class="text-danger" >
                ${mess}
            </p>
            <h2> <button onclick="goBack()" class="custom-button" style="font-size: 20px; "> Trở lại</button> </h2> 
        </div>

        <script>
            function goBack() {
                window.history.back();
            }
        </script>
    </body>
</html>

