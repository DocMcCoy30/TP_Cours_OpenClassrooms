<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ConfirmationUpdate</title>
</head>
<body>
<%@include file="header.jsp"%>

    <div class="container col-md-8">
        <div class="card">
            <div class="card-header bg-primary">
                Le livre a bien été modifié.
            </div>
            <div class="card-body bg-light">
                <div class="form-group">
                    <label>ID : </label>
                    <label>${ livre.id }</label>
                </div>
                <div class="form-group">
                    <label>Titre : </label>
                    <label>${ livre.titre }</label>
                </div>
                <div class="form-group">
                    <label>Auteur : </label>
                    <label>${ livre.auteur }</label>
                </div>
                <div class="form-group">
                    <label>Genre : </label>
                    <label>${ livre.genre }</label>
                </div>
            </div>
        </div>
    </div>

</body>
</html>
