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
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${((user!=null)&&(updateerror!=null))||((user!=null)&&(inserterror==null))}">
            <form action="updateauthor?id=${user.getId()}" method="post">
                </c:if>
                <c:if test="${(user==null)||((inserterror!=null))}">
                <form action="insertauthor" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${((user!=null)&&(updateerror!=null))||((user!=null)&&(inserterror==null))}">
                                Edit User
                            </c:if>
                            <c:if test="${(user==null)||(inserterror!=null)}">
                                Add New User
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${user != null}">
                        <label  name="id" value="<c:out value='${user.getId()}' />" />
                    </c:if>
                    <c:if test="${(inserterror != null)||(updateerror!=null)}">
                        <div class="alert alert-danger" role="alert">
                            Author with such name already exists!
                        </div>
                    </c:if>

                    <fieldset class="form-group">
                        <label>Author Name</label> <input type="text"
                                                        value="<c:out value='${user.getName()}' />" class="form-control"
                                                        name="UserName" required="required">
                    </fieldset>

                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>