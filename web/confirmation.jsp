<%-- 
    Document   : login
    Created on : Jan 7, 2024, 4:25:52 PM
    Author     : linhnghiem
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- Coding By CodingNepal - codingnepalweb.com -->
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Registration</title>
        <!---Custom CSS File--->
        <link rel="stylesheet" href="style.css">
        <style>
            *{
                margin: 0;
                padding: 0;
                box-sizing: border-box;
                font-family: 'Poppins', sans-serif;
            }
            body{
                min-height: 100vh;
                width: 100%;
                background: url('https://source.unsplash.com/1920x1080/?nature') no-repeat;
            }
            .container{
                position: absolute;
                top: 50%;
                left: 50%;
                transform: translate(-50%,-50%);
                max-width: 430px;
                width: 100%;
                background: #fff;
                border-radius: 7px;
                box-shadow: 0 5px 10px rgba(0,0,0,0.3);
                text-align: center;
                text-decoration: none;
                color: #007bff;
                font-weight: bold;
                transition: color 0.3s;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <p class="text-danger" style="padding: 5px; color: red">
                ${Mess}
            </p>
            <a style="text-decoration: none;" href="login.jsp" target="target">Login</a>
        </div>
    </body>
</html>