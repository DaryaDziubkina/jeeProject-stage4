<%@ page import="by.gsy.epamProject.controller.ConstantsController" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Error</title>
</head>
<body>
<%--<h3>ERROR ${nameError}</h3>--%>
<%--<a href="login.jsp">Go to login page</a>--%>

<div class="container">
    <div class="col-sm-offset-4 col-sm-4 panel panel-warning">
        <div class="text-danger text-center ">
            <h3>Error</h3>
        </div>
        <div class="text-danger text-center">
            <c:choose>
                <c:when test="${not empty errorMessage}">
                    <c:out value="${errorMessage}"/>
                </c:when>
                <c:otherwise>
                    <c:out value="<%= ConstantsController.MessagesError.UNSUPPORTED %>"/>
                </c:otherwise>
            </c:choose>

        </div>
        <br/>
        <c:if test="${not empty backPage}">
            <a class="btn btn-default col-sm-8 col-sm-offset-2" href="${backPage}">Back</a>
        </c:if>
        <a class="btn btn-default col-sm-8 col-sm-offset-2" href="logout">Back to login page</a>
    </div>
</div>
</body>
</html>