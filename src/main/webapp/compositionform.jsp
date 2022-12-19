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
            <c:if test="${((composition!=null)&&(updateerror!=null))||((composition!=null)&&(inserterror==null))}">
            <form action="updatecomposition?id=${composition.getId()}" method="post">
                </c:if>
                <c:if test="${(composition==null)||((inserterror!=null))}">
                <form action="insertcomposition" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${((composition!=null)&&(updateerror!=null))||((composition!=null)&&(inserterror==null))}">
                                Edit composition
                            </c:if>
                            <c:if test="${(composition==null)||(inserterror!=null)}">
                                Add New composition
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${composition != null}">
                        <label  name="id" value="<c:out value='${composition.getId()}' />" />
                    </c:if>
                    <c:if test="${(inserterror != null)||(updateerror!=null)}">
                        <div class="alert alert-danger" role="alert">
                            Composition with such name already exists!
                        </div>
                    </c:if>

                    <fieldset class="form-group">
                        <label>Composition Name</label> <input type="text"
                                                         value="<c:out value='${composition.getName()}' />" class="form-control"
                                                         name="CompositionName" required="required">
                    </fieldset>
                    <fieldset class="form-group">
                        <label>Duration</label> <input type="number" min="0" step="1"
                                                          value="<c:out value='${composition.getDuration()}' />" class="form-control"
                                                          name="Duration" required="required">
                    </fieldset>


                        <fieldset class="form-group">
                            <label>Album Name</label> <select class="form-control" name="AlbumId" required="required">
                            <c:forEach items="${albums}" var="album">
                                <option value="${album.getId()}">Id: ${album.getId()}, Name: ${album.getAlbumName()}</option>
                            </c:forEach>
                        </select>

                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>