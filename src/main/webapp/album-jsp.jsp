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
        <h3 class="text-center">List of Albums</h3>
        <hr>
        <div class="container text-left">

            <a href="<%=request.getContextPath()%>/albumaddform" class="btn btn-success">Add
                New Album</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Genre</th>
                <th>Author ID</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <%--            <% List<User> users = (LinkedList<User>) request.getAttribute("users");%>--%>
            <!--   for (Todo todo: todos) {  -->
            <c:forEach var="album" items="${albums}">

                <tr>
                    <td><c:out value="${album.getId()}" /></td>
                    <td><c:out value="${album.getAlbumName()}" /></td>
                    <td><c:out value="${album.getGenre()}" /></td>
                    <td><c:out value="${album.getAuthorName()}" /></td>
                    <td><a href="albumaddform?id=<c:out value='${album.getId()}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp; <a
                                href="deletealbum?id=<c:out value='${album.getId()}' />">Delete</a></td>
                </tr>
            </c:forEach>
            <!-- } -->
            </tbody>

        </table>
    </div>
</div>
</body>
</html>