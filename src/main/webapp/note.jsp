<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.gsy.epamProject.controller.ConstantsController" %>
<%@ page import="by.gsy.epamProject.model.beans.NoteTypes" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>


<jsp:useBean id="note" class="by.gsy.epamProject.model.beans.Note" scope="page"/>

<html>
<head>
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="bootstrap/css/note.css" rel="stylesheet">
    <link href="bootstrap/img/glyphicons-halflings-white.png" rel="icon">
    <title>Notes</title>
    <script type="text/javascript" src="js/script.js"></script>
    <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
    <style>
        <%@include file="bootstrap/css/note.css"%>
    </style>
</head>
<body onload=" activeClass('<c:out value="${noteTypes.value}"/>');">

<div class="container">
    <form name="noteForm" action="action" method="post">
        <div class="masthead">
            <h3 class="muted height-logotip">
                Notes
                <div class="span3 pull-right navbar-text">
                    <h4>Logged in as ${user.name}</h4>
                </div>
            </h3>

            <div class="navbar">
                <div class="navbar-inner height-menu">
                    <ul class="nav height-menu">
                        <li id="<%=NoteTypes.TODAY.getValue()%>">
                            <a href="JavaScript:sendForm('<%= NoteTypes.TODAY %>')"> <i class="icon-book"> </i>
                                <%= NoteTypes.TODAY.getValue() %> (<%=NoteTypes.TODAY.getDateFormatted2()%>)
                            </a>&nbsp;
                        </li>
                        <li id="<%= NoteTypes.TOMORROW.getValue() %>">
                            <a href="JavaScript:sendForm('<%= NoteTypes.TOMORROW %>')">
                                <%= NoteTypes.TOMORROW.getValue() %> (<%=NoteTypes.TOMORROW.getDateFormatted2()%>)
                            </a>&nbsp;
                        </li>
                        <li id="<%= NoteTypes.SOMEDAY.getValue() %>">
                            <a href="JavaScript:sendForm('<%= NoteTypes.SOMEDAY %>')">
                                <%= NoteTypes.SOMEDAY.getValue() %>
                            </a>&nbsp;
                        </li>
                        <li id="<%= NoteTypes.FIXED.getValue() %>">

                            <a href="JavaScript:sendForm('<%= NoteTypes.FIXED %>')"> <i class="icon-ok-circle"> </i>
                                <%= NoteTypes.FIXED.getValue() %>
                            </a>&nbsp;
                        </li>
                        <li id="<%= NoteTypes.RECYCLE_BIN.getValue() %>">
                            <a href="JavaScript:sendForm('<%= NoteTypes.RECYCLE_BIN %>')"> <i class="icon-trash"> </i>
                                <%= NoteTypes.RECYCLE_BIN.getValue() %>
                            </a>&nbsp;
                        </li>
                        <li>
                            <a href="logout"> <i class="icon-off"> </i>
                                Logout
                            </a>
                        </li>
                        <input type=hidden name="action" value=""/>
                        <input type="hidden" id="noteId" name="noteId" value=""/>
                    </ul>
                </div>
            </div>
        </div>

        <div class="pull-right btns">
            <%--<a class="btn btn-user-delete" href="JavaScript:sendForm('<%=ConstantsController.Actions.USER_DELETE%>')">--%>
            <%--User delete</a>&nbsp;--%>
            <a class="btn btn-info add" href="JavaScript:sendForm('<%=ConstantsController.Actions.ADD%>')">Add</a>&nbsp;
            <a class="btn btn-info remove-fixed"
               href="JavaScript:sendForm('<%=ConstantsController.Actions.REMOVE%>')">Remove</a>&nbsp;
            <c:if test="${noteTypes.fixedShow}">
                <a class="btn btn-info remove-fixed"
                   href="JavaScript:sendForm('<%= ConstantsController.Actions.FIX %>')">Fix</a>&nbsp;
            </c:if>
        </div>


        <c:choose>
            <c:when test="${empty notesList}">
                <div class="span4 left-null lead">
                    <%= ConstantsController.MessagesError.NOTES_LIST_EMPTY%><br/>
                </div>
            </c:when>
            <c:otherwise>
                <table class="table table-bordered table-hover" border="1">
                    <tr>
                        <th class="col-md-1"></th>
                        <th class="col-md-2"> Topic</th>
                        <th class="col-md-2">Text</th>
                        <th class="col-md-2 ">File</th>
                        <c:if test="${noteTypes.dateShow}">
                            <th class="col-md-2">Execution date</th>
                        </c:if>

                    </tr>
                    <c:forEach items="${notesList}" var="note">
                        <tr>
                            <td>
                                <input type="checkbox" name="select"
                                       value="<jsp:getProperty name="note" property="id"/>"/>

                            </td>
                            <td>
                                <jsp:getProperty name="note" property="topic"/>

                            </td>
                            <td>
                                <jsp:getProperty name="note" property="text"/>

                            </td>
                            <td>
                                <c:if test="${not empty note.filename}">
                                    <a class="btn btn-link"
                                       href="JavaScript:activeNote('<%= ConstantsController.Actions.DOWNLOAD_FILE %>', '<jsp:getProperty name="note" property="id"/>')">
                                        <jsp:getProperty name="note" property="filename"/>
                                    </a>
                                    <a class="btn"
                                       href="JavaScript:activeNote('<%= ConstantsController.Actions.REMOVE_FILE %>', '<jsp:getProperty name="note" property="id"/>')">Remove</a>&nbsp;
                                </c:if>
                            </td>
                            <c:if test="${noteTypes.dateShow}">
                                <td>
                                    <jsp:getProperty name="note" property="dateExecution"/>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
    </form>
</div>
</body>
</html>


