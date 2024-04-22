<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <form action="updatePassword" method="post">
                    <c:if test="${requestScope.mess != ''}">
                        <p class="text-danger" style="padding: 4px; color: red">
                            ${mess}
                        </p>
                    </c:if>

                    <div class="form-input">
                        <label for="username">Email</label>
                        <input type="hidden" id="email" name="accountId" readonly="" value="${param.accountId}">
                        <input type="text" id="email" name="email" readonly="" value="${account.email}">

                    </div>

                    <div class="form-input">
                        <label for="username">Password</label>
                        <input type="password" placeholder="Enter password"  id="email" name="password" required="">
                    </div>
                    <div class="form-input">
                        <label for="username">Re-Password</label>
                        <input type="password" placeholder="Enter re-password"  id="email" name="rePassword" required="">
                    </div>
                    <div class="form-input2">
                        <button type="submit" id="login-btn">Update password</button>
                    </div>
                </form>
            </div>
        </div>

    </body>
</html>