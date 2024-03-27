<%@ page import="com.app.boutique.bo.Client" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    Client client = (Client) request.getSession().getAttribute("client");
    if (client != null)
    {
        request.getSession().setAttribute("error","Vous devez se connecter");
        response.sendRedirect(request.getContextPath() + "/AddProduct");
    }

%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Client Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .login-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.2);
            width: 300px;
            text-align: center;
        }

        h2 {
            color: #333;
        }

        input[type="text"],
        input[type="password"] {
            width: calc(100% - 20px);
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            width: 100%;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        .error-message {
            background-color: #ffe6e6; /* Light red background */
            border: 1px solid #ff9999; /* Red border */
            padding: 10px;
            margin: 10px 0;
            border-radius: 5px; /* Rounded corners */
        }

    </style>
    <link rel="stylesheet" href="resources/style.css">

</head>
<body>

<div class="login-container">
    <h2>Client</h2>
    <% if (request.getSession().getAttribute("error") != null) { %>
    <div class="error-message" style="color: red;">
        <%= request.getSession().getAttribute("error") %>
        <%session.removeAttribute("error");%>
    </div>
    <% } %>

    <form action="${pageContext.request.contextPath}/login" method="post">
        <input type="text" name="email" placeholder="Email" required><br>
        <input type="password" name="password" placeholder="Password" required><br>
        <input type="submit" value="Login">
    </form>
</div>
</body>
</html>
