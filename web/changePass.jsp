<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html lang="en">
    <head>

        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/login.css"/>
        <title>Login Page</title>
    </head>
    <body>
        <div class="background">
            <div class="login-container" >
                <form action="changepass" method="POST">
                    <h2>Change PassWord</h2>
                    <p class="text-danger" style="padding: 4px; color: red">
                        ${Mess}
                    </p>
                    <label for="username">Username</label>
                    <input type="text" id="username" value="${acc.user}" readonly>
                    <label for="password">Old Password</label>
                    <input type="password" id="password" name="password" >
                    <label for="password">New PassWord</label>
                    <input type="password" name="newpass">
                    <label for="password">Re-type PassWord</label>
                    <input type="password" name="repass">

                    <button type="submit">Change Pass</button>
                </form>
            </div>
        </div>
    </body>
</html>