<%@ page import="java.util.List" %>
<%@ page import="Model.Author" %>
<%@ page import="java.util.LinkedList" %><%--<%@ page import="model.User" %>--%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>User Management Application</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
<script src="${pageContext.request.contextPath}/scripts/compscript.js"></script>
<script src="http://code.jquery.com/jquery-2.2.4.js"></script>
<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: darkblue">
        <div>
            <a href="<%=request.getContextPath()%>">Back</a>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/author-servlet"
                   class="nav-link">Authors</a></li>

        </ul>
        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/album-servlet"
                   class="nav-link">Albums</a></li>

        </ul>
        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/composition-servlet"
                   class="nav-link">Compositions</a></li>

        </ul>
    </nav>
</header>
<br>

<div class="row">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->



    <div class="container">
        <h3 class="text-center">List of Compositions</h3>
        <hr>
        <div class="container text-left">
            <form action="compositionlist-servlet" method="post">
                <fieldset>
                    <label>Album Name</label>
                    <input  list = "AlbumName" name ="AlbumName"  value="${albumname}" class ="form-control" required = "required" id="inp" style="width: 20%">
                    <script>
                        var input = document.getElementById('inp')
                        input.oninput = function()
                        {
                            setOption(input.value)
                        };
                    </script>
                    <datalist id ="AlbumName">
                        <div id = "options"></div>
                    </datalist>

                    <button type="submit" style="margin-top: -38px;margin-left: 220px"
                            class="btn btn-success">Search
                    </button>
                </fieldset>

        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Duration</th>

            </tr>
            </thead>
            <tbody>
            <%--            <% List<User> users = (LinkedList<User>) request.getAttribute("users");%>--%>
            <!--   for (Todo todo: todos) {  -->
            <c:forEach var="composition" items="${compositions}">

                <tr>
                    <td><c:out value="${composition.getId()}" /></td>
                    <td><c:out value="${composition.getName()}" /></td>
                    <td><c:out value="${composition.getDuration()}" /></td>
                </tr>
            </c:forEach>
            <!-- } -->
            </tbody>

        </table>
    </div>
</div>
</body>
</html>