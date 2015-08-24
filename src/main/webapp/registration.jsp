<%@ page import="by.gsy.epamProject.controller.ConstantsController" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link href="bootstrap/css/login.css" type="text/css" rel="stylesheet">
    <link href="bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Registration</title>
    <style>
        <%@include  file="bootstrap/css/login.css"%>
    </style>
</head>
<body>
<div class="container">

    <form class="form-signin" method="post" action="registration">

        <h3 class="form-signin-heading width-height">Fill in the required fields</h3>

        <input id="inputEmail" class="form-control width-height" placeholder="Email address" requried=""
               type="email" name="email" value=""/>

        <input id="inputName" class="form-control width-height" type="text" placeholder="Name" requried=""
               name="name" value=""/>

        <input id="inputPassword" class="form-control width-height" type="password" placeholder="Password"
               name="password" required="required" value=""/>

        <button class="btn btn-large btn-primary btn-block width-height-btn"
                name="action" value="<%=ConstantsController.Actions.REGISTER%>">Registration
        </button>

        <a class="btn btn-link" href="login.jsp">Go to login page...</a>

    </form>
</div>
</body>
</html>
