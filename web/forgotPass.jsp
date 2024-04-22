<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="css/login.css"/>
        <title>Forgot Password</title>
    </head>
    <body>
        <div class="background">
            <div class="login-container">
                <form action="forgotPassword" method="post">
                    <h2>Forgot Password</h2>
                    <p class="text-danger" style="padding: 4px; color: red">
                        ${mess}
                    </p>
                    <div class="form-input">
                        <label for="username">Email</label>
                        <input type="text" placeholder="Enter email here"  id="email" name="email" required="">
                    </div>
                    <label for="captcha-input">Enter Captcha</label>
                    <div class="captcha">
                        <img src="CaptchaImage" alt="CAPTCHA Image">
                        <div class="captcha-refresh" onclick="refresh()">
                            <i class="fa fa-refresh fa-spin"></i> 
                        </div>
                    </div>
                    <input type="text" name="captcha" required>
                    <br/>
                    <div class="form-input2">
                        <button id="login-btn">Send To Email</button>
                    </div>
                </form>
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="js/capcha.js"></script>
    </body>
</html>