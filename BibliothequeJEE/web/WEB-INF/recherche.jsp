<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Recherche</title>
</head>
<body>
<%@include file="header.jsp"%>

    <div class="container col-md-8">
        <div class="card">
            <div class="card-header bg-primary">
                Rechercher un livre
            </div>
            <div class="card-body bg-light">
                <div class="row">
                <form class="form-row" method="get" action="rechercheTitre.do">
                    <div class="form-group col-md-12">
                    <label for="rechercheTitre">Titre ou mot-clé : </label>
                    <input type="text" name="rechercheTitre" id="rechercheTitre" value="${ modele.motCle }">
                    <button type="submit" class="btn btn-primary">Rechercher</button>
                    </div>
                </form>
                    <form class="form-row" method="get" action="rechercheGenre.do">
                    <div class="form-group col-md-12 offset-md-2">
                    <label for="rechercheGenre">Recherche par genre :</label>
                    <select class="col-md-3" type="text" name="rechercheGenre" id="rechercheGenre">
                        <option value="Default" selected="selected"></option>
                        <option value="Roman">Roman</option>
                        <option value="Policier">Policier</option>
                        <option value="Thriller">Thriller</option>
                        <option value="Horreur">Horreur</option>
                        <option value="Science-fiction">Science-fiction</option>
                        <option value="Biographie">Biographie</option>
                        <option value="Essai">Essai</option>
                        <option value="Philosophie">Philosophie</option>
                        <option value="Histoire">Histoire</option>
                        <option value="Theatre">Theatre</option>
                        <option value="Poesie">Poesie</option>
                        <option value="Cours">Cours</option>
                        <option value="BD">BD</option>
                    </select>
                        <button type="submit" class="btn btn-primary">Rechercher</button>
                    </div>
                </form>
                </div>
                <table class="table">
                    <tr>
                        <th>ID</th><th>Titre</th><th>Auteur</th><th>Genre</th>
                    </tr>
                    <c:forEach items="${ modele.livres }" var="livre">
                        <tr>
                            <td>${ livre.id }</td>
                            <td>${ livre.titre }</td>
                            <td>${ livre.auteur }</td>
                            <td>${ livre.genre }</td>
                            <td><a onclick="return confirm('Supprimer ce livre ?')" href="delete.do?id=${ livre.id }">Supprimer</a></td>
                            <td><a href="update.do?id=${ livre.id }">Editer</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>

</body>
</html>
