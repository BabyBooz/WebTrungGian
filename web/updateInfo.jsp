<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Information</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="css/login.css"/>
    </head>
    <body>
        <div class="background">
            <div class="login-container" >
                <form action="update" method="post">
                    <h1>User Information</h1>
                    <p class="text-danger" style="padding: 4px; color: red">
                        ${Mess}
                    </p>
                    <label for="username">Username</label>
                    <input type="text" name="username" value="${acc.user}" readonly="">

                    <label for="email">Email:</label>
                    <input type="email" name="email" value="${acc.email}"><br>

                    <label for="phone">Phone:</label>
                    <input type="text" name="phone" value="${acc.phone}"><br>

                    <label for="describe">Describe:</label>
                    <input type="text" name="describes" value="${acc.describes}"><br>
                    <input style="background-color: #007bff; color: white" type="submit" value="Update">
                </form>
                <form action="changepass" method="get">
                    <input style="background-color: #007bff; color: white" type="submit" value="Change PassWord">
                </form>
            </div>
        </div>
    </body>
</html>
