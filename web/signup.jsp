<%-- 
    Document   : signup
    Created on : Jan 12, 2024, 1:43:41 PM
    Author     : linhnghiem
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="css/login.css"/>
    </head>
    <body>
        <div class="background">
            <div class="login-container">
                <form action="register" method="post">
                    <h2>Sign Up</h2>
                    <p class="text-danger" style="padding: 4px; color: red">
                        ${Mess}
                    </p>
                    <div class="form-input">
                        <label for="username">Username</label>
                        <input type="text" name="user" id="username" required="" value="${not empty requestScope.user ? requestScope.user : ''}">
                    </div>
                    <div class="form-input">
                        <label for="email">Enter Your Email</label>
                        <input type="text" name="email" id="email" required="" value="${not empty requestScope.email ? requestScope.email : ''}">
                    </div>
                    <div class="form-input">
                        <label for="password">Create a Password</label>
                        <input type="password" id="password" name="password" required="">
                    </div>
                    <div class="form-input">
                        <label for="confirm-password">Confirm Your Password</label>
                        <input type="password" name="confirmPassword" id="confirm-password" required="">
                    </div>
                    <br/>
                    <label for="captcha-input">Enter CaptCha</label>
                    <div class="captcha">
                        <img src="CaptchaImage" alt="CAPTCHA Image">
                        <div class="captcha-refresh" onclick="refresh()">
                            <i class="fa fa-refresh fa-spin"></i> 
                        </div>
                    </div>
                    <input type="text" name="captcha" required="">
                    <br/>
                    <div class="form-input2">
                        <button id="signup-btn">Sign Up</button>
                    </div>
                </form>
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="js/capcha.js"></script>
    </body>
</html>