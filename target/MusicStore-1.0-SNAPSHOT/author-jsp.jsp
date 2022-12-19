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
        <h3 class="text-center">List of Authors</h3>
        <hr>
        <div class="container text-left">

            <a href="<%=request.getContextPath()%>/authoraddform" class="btn btn-success">Add
                New Author</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <%--            <% List<User> users = (LinkedList<User>) request.getAttribute("users");%>--%>
            <!--   for (Todo todo: todos) {  -->
            <c:forEach var="user" items="${users}">

                <tr>
                    <td><c:out value="${user.getId()}" /></td>
                    <td><c:out value="${user.getName()}" /></td>
                    <td><a href="authoraddform?id=<c:out value='${user.getId()}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp; <a
                                href="deleteauthor?id=<c:out value='${user.getId()}' />">Delete</a></td>
                </tr>
            </c:forEach>
            <!-- } -->
            </tbody>

        </table>
    </div>
</div>
</body>
</html>