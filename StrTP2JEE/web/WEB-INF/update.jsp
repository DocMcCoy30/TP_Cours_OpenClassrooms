<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update</title>
</head>
<body>
<%@include file="menu.jsp"%>

<div class=" col-md-6 offset-3">
    <div class="card">
        <div class="card-header"><h3>Modification du fichier : </h3><c:out value="${ strFileSource.nomFichier }"/></div>
        <div class="card-body">
            <form method="post" action="update">
                <div class="form-group align-content-center" >
                    <input type="hidden" class="form-control col-md-10" name="fileId" id="fileId" value="${ strFileSource.fileId }">
                </div>
                <div class="form-group">
                    <label for="titre" class="control-box">Titre : </label>
                    <input type="text" class="form-control col-md-10" name="titre" id="titre" value="">
                </div>
                <div class="form-group">
                    <label for="langue" class="control-box">Langue : </label>
                    <select class="form-control  col-md-10" type="text" name="langue" id="langue">
                        <c:forEach items="${langues}" var="langue" varStatus="id">
                            <option value=${id.index+1}>${langue.langueStr}</option>
                        </c:forEach>
                    </select>
                </div>
<%--                <div class="form-group">--%>
<%--                    <input type="hidden" class="form-control col-md-10" name="nomFichier" id="nomFichier" value="${ strFileSource.nomFichier }">--%>
<%--                </div>--%>
                <div class="form-group">
                    <button type="submit" class="btn btn-dark">modifier</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
