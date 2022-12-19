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
<script src="${pageContext.request.contextPath}/scripts/script.js"></script>
<script src="http://code.jquery.com/jquery-2.2.4.js"></script>
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
            <c:if test="${((album!=null)&&(updateerror!=null))||((album!=null)&&(inserterror==null))}">
            <form action="updatealbum?id=${album.getId()}" method="post">
                </c:if>
                <c:if test="${(album==null)||((inserterror!=null))}">
                <form action="insertalbum" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${((album!=null)&&(updateerror!=null))||((album!=null)&&(inserterror==null))}">
                                Edit Album
                            </c:if>
                            <c:if test="${(album==null)||(inserterror!=null)}">
                                Add New Album
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${album != null}">
                        <label  name="id" value="<c:out value='${album.getId()}' />" />
                    </c:if>
                    <c:if test="${(inserterror != null)||(updateerror!=null)}">
                        <div class="alert alert-danger" role="alert">
                            Album with such name already exists!
                        </div>
                    </c:if>

                    <fieldset class="form-group">
                        <label>Album Name</label> <input type="text"
                                                          value="<c:out value='${album.getAlbumName()}' />" class="form-control"
                                                          name="AlbumName" required="required">
                    </fieldset>
<%--                        <fieldset class="form-group">--%>
<%--                            <label>Author Name</label> <input type="text"--%>
<%--                                                             value="<c:out value='${album.getAuthorName()}' />" class="form-control"--%>
<%--                                                             name="AuthorName" required="required">--%>
<%--                        </fieldset>--%>
                        <fieldset class="form-group">
                            <label>Genre</label> <input type="text"
                                                             value="<c:out value='${album.getGenre()}' />" class="form-control"
                                                             name="Genre" required="required">
                        </fieldset>


                        <fieldset class="form-group">
                            <label>Author Name</label>
                            <input list = "SingerName" name ="AuthorName" class ="form-control" required = "required" id="inp">
                            <script>
                                var input = document.getElementById('inp')
                                input.oninput = function()
                                {
                                    setOption(input.value)
                                };
                            </script>
                            <datalist id ="SingerName">
                                <div id = "options"></div>
                            </datalist>
                        </fieldset>
                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>