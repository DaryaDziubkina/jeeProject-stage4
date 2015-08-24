<%@ page import="by.gsy.epamProject.controller.ConstantsController" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link href="bootstrap/css/login.css" type="text/css" rel="stylesheet">
    <link href="bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <style type="text/css">
        <%@include file="bootstrap/css/login.css"%>
        <%--<%@include filename="bootstrap/css/bootstrap.min.css"%>--%>
    </style>
    <title>Please sign in</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Input</title>
</head>
<body>
<div class="container">

    <form class="form-signin" method="post" action="login">

        <h2 class="form-signin-heading">Please sign in</h2>

        <%--<label class="sr-only" for="inputEmail">--%>
        <%--Email address--%>
        <%--</label>--%>

        <input id="inputEmail" class="form-control width-height" type="email" requried autofocus
               placeholder="Email address" name="email" value=""/>
        <%--<label class="sr-only" for="inputPassword">--%>
        <%--Password--%>
        <%--</label>--%>

        <input id="inputPassword" class="form-control width-height" placeholder="Password" type="password"
               name="password" required="required" value=""/>

        <button class="btn btn-large btn-primary btn-block width-height-btn" type="submit" name="action"
                value="<%=ConstantsController.Actions.LOGIN%>">Sign in
        </button>

        <a class="btn btn-link" href="registration.jsp">Registration!</a>
    </form>
</div>
</body>
</html>