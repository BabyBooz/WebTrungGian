
<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html lang="en">
    <head>

        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="css/login.css"/>
        <title>Login Page</title>
    </head>
    <body>
        <div class="background">
            <div class="login-container" >
                <form action="validation" method="post">
                    <h2>Login</h2>
                    <p class="text-danger" style="padding: 4px; color: red">
                        ${Mess}
                    </p>
                    <label for="username">Username</label>
                    <input type="text" id="username" name="username" required="">

                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" required="">
                    <label for="captcha-input">Enter CaptCha</label>
                    <div class="captcha">
                        <img src="CaptchaImage" alt="CAPTCHA Image">
                        <div class="captcha-refresh" onclick="refresh()">
                            <i class="fa fa-refresh fa-spin"></i> 
                        </div>
                    </div>
                    <input type="text" name="captcha" required="">
                    <div class="signup-container">
                        <a href="signup.jsp">Signup</a>
                        <a href="forgotPass.jsp">Forgot Password</a>
                    </div>

                    <button type="submit">Login</button>
                </form>
            </div>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="js/capcha.js"></script>

    </body>
</html>