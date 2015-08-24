<%@ page import="by.gsy.epamProject.controller.ConstantsController" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<jsp:useBean id="note" class="by.gsy.epamProject.model.beans.Note" scope="page"/>
<c:set var="user" scope="session" value="${sessionScope.user}"/>
<c:set var="noteTypes" scope="session" value="${sessionScope.noteTypes}"/>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link href="bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="bootstrap/css/note.css" type="text/css" rel="stylesheet">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.3/themes/smoothness/jquery-ui.css">
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="//code.jquery.com/ui/1.11.3/jquery-ui.js"></script>
    <title>Add note</title>

    <script type="text/javascript">
        $(function () {
            $("#datepicker").datepicker({
                dateFormat: 'dd-mm-yy'
            })
        });
    </script>
</head>
<form class="form-signin" name="noteForm" method="post" action="addNote" enctype="multipart/form-data">
    <div class="container">
        <div class="masthead">
            <h3 class="muted">
                Note <c:out value="${noteTypes.value}"></c:out>
                <div class="span3 pull-right navbar-text">
                    <h4>Logged in as ${user.name}</h4>
                </div>
            </h3>
        </div>
    </div>
    <div class="container">
        <fieldset>
            <legend><h3 class="text-info new-note">New note</h3></legend>
            <input id="inputTopic" class="form-control muted thema" type="text"
                   name="topic" value="" requried="" placeholder="Thema"/>

            <c:choose>
                <c:when test="${noteTypes.dateShow}">
                    <input id="datepicker" class="form-control width-pl" type="text"
                           name="date_execution" value="" requried="" placeholder="Data"/>
                </c:when>
                <c:otherwise>
                    <input class="input-xlarge width-pl" id="disabledInput" type="text"
                           placeholder="${noteTypes.dateFormatted}" disabled>

                </c:otherwise>
            </c:choose>

        <textarea id="inputText" class="width-pl"
                  type="text" name="text" value="" requried="" placeholder="Of note"></textarea>
        </fieldset>


        <input class="width-pl" type="file" name="filename" id="exampleInputFile">


        <ul class="span2 pull-left new-note pager">
            <li class="previous">
                <a href="note">&larr; Back</a>
            </li>
        </ul>
        <button class="btn btn-save width-btn-save" name="action" value="<%=ConstantsController.Actions.ADD%>">Save
        </button>
    </div>

</form>
</body>
</html>