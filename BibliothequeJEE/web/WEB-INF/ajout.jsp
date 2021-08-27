<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ajout</title>
</head>
<body>
<%@include file="header.jsp"%>

    <div class="container col-md-8">
        <div class="card">
            <div class="card-header bg-primary">
                Ajouter un livre
            </div>
            <div class="card-body bg-light">
                <form method="post" action="saveAjout.do">
                    <div class="form-group">
                        <label class="control-box" for="titre">Titre :</label>
                        <input class="form-control col-md-6" type="text" name="titre" id="titre">
                        <span></span>
                    </div>
                    <div class="form-group">
                        <label class="control-box" for="auteur">Auteur :</label>
                        <input class="form-control col-md-6" type="text" name="auteur" id="auteur">
                        <span></span>
                    </div>
                    <div class="form-group">
                        <label class="control-box" for="genre">Genre :</label>
                        <select class="form-control col-md-6" type="text" name="genre" id="genre">
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
                        <span></span>
                    </div>
                    <div>
                        <button type="submit" class="btn btn-primary">Ajouter</button>
                    </div>
                </form>

            </div>
        </div>
    </div>

</body>
</html>
