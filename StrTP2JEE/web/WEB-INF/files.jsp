<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>files</title>
</head>
<body>
<%@include file="menu.jsp"%>
<div class="container">
    <div class="col-md-12" id="formulaire">
        <form class="form-row" method="post" action="files" enctype="multipart/form-data">
            <div class="form-group col-md-3 offset-1">
                <label class="control-box" for="fichier">Importer le fichier : </label>
                <input class="form-control-file" type="file" name="fichier" id="fichier">
            </div>
            <div class="form-group col-md-2 offset-1">
                <label class="control-box" for="titre">Titre : </label>
                <input class="form-control" type="text" name="titre" id="titre">
            </div>
            <div class="form-group col-md-2 offset-1">
                <label class="control-box" for="langue">Langue : </label>
                <select class="form-control" type="text" name="langue" id="langue">
                    <c:forEach items="${langues}" var="langue" varStatus="id">
                        <option value=${id.index+1}>${langue.langueStr}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-md-1 offset-1">
                <button type="submit" class="form-control btn btn-dark" name="files" value="upload">upload</button>
            </div>
            <c:if test="${ !empty message }"><p class="offset-2" id="message"><c:out value="${ message }"/></p></c:if>
            <c:if test="${ !empty erreur }"><p class="offset-2" id="erreur"><c:out value="${ erreur }"/></p></c:if>
        </form>
    </div>
</div>

<div class="container-fluid">
    <div class="card">
        <div class="card-header">
            <h3>Liste des fichiers</h3>
        </div>
        <div class="card-body">
            <form method="post" action="files">
                <table class="table table-borderless">
                    <thead class="thead">
                    <tr>
                        <th>ID</th><th>Titre</th><th>Langue</th><th>Fichier</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${ strFileSource.strFiles }" var="strFileSource" varStatus="cpt">
                        <tr>
                            <td>${ strFileSource.fileId }</td>
                            <td>${ strFileSource.titre }</td>
                            <td>${ strFileSource.langue.langueStr }</td>
                            <td>${ strFileSource.nomFichier }</td>
                            <td class="col-md-1"><button class="btn-dark" type="submit" name="files" value="delete+${cpt.current.fileId}" onclick="return confirm('Supprimer ce fichier ?')">Supprimer</button></td>
                            <td class="col-md-1"><button class="btn-dark" type="submit" name="files" value="update+${cpt.current.fileId}">Modifier</button></td>
                            <td class="col-md-1"><button class="btn-dark" type="submit" name="files" value="edit+${cpt.current.fileId}">Editer</button></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </form>
        </div>
    </div>
</div>
</body>
</html>
