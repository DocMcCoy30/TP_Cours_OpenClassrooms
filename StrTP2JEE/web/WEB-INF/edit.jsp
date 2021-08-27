<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<%@include file="menu.jsp"%>

<div class="container">
    <form method="post" action="edit">
        <input type="hidden" name="fileIdSource" value="${ strFileSource.fileId }">
        <input type="hidden" name="fileIdCible" value="${ strFileCible.fileId }">
        <div class="col-md-1 offset-11 btn-group fixed-top" id="boutons">
            <button type="submit" class="btn btn-dark" name="edit" value="enregistrer">enregistrer</button>
        </div>
        <div class="col-md-10 offset-1"><h5>Exporter la traduction dans un fichier : </h5></div>
        <div class="form-row" id="div-exporter">
            <div class="form-group col-md-3 offset-1">
                <label  class="control-box" for="titre">Titre : </label>
                <input type="text" class="form-control" name="titre" id="titre">
            </div>
            <div class="form-group col-md-3 offset-1">
                <label  class="control-box" for="langue">Langue : </label>
                <select class="form-control" type="text" name="langue" id="langue">
                    <c:forEach items="${langues}" var="langue" varStatus="id">
                        <option value=${id.index+1}>${langue.langueStr}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-md-1 offset-1">
                <button type="submit" name="edit" value="exporter" class="btn btn-dark offset-2 float-md-right">exporter</button>
            </div>
        </div>
        <c:if test="${ !empty message }"><p class="offset-2" id="message"><c:out value="${ message }"/></p></c:if>
        <div class="col-md-12">
            <div class="row col-md-12" id="div-strFile">
                <div class="col-md-5">${ strFileSource.fileId } - ${ strFileSource.titre } - ${ strFileSource.langue.langueStr }</div>
            </div>
            <div class="row col-md-12" id="div-str">
                <c:forEach var="sousTitreSource" items="${ sousTitresSource }"  varStatus="cptligne">
                    <div class="col-md-12">
                        Index : <c:out value="${ sousTitreSource.index}"/>
                        Time : <c:out value="${ sousTitreSource.timeStamp}"/>
                    </div>
                    <c:set var="soustitres" value="${ sousTitreSource.sousTitreOriginal }" scope="page"/>
                    <c:forEach items="${soustitres}" var="soustitre" varStatus="numchamp">
                        <c:if test="${!empty soustitre}">
                            <div class="col-md-5">
                                <c:out value="${soustitre}" />
                            </div>
                            <div class="col-md-6" id="input_traduction">
                                <input class = "form-control" type="text"
                                       name="line${cptligne.index+1}${numchamp.index}"
                                       id="line${cptligne.index+1}${numchamp.index}"
                                       value="${sousTitreSource.sousTitreTraduit[numchamp.index]}"
                                       size="35"/>
                            </div>
                        </c:if>
                    </c:forEach>
                </c:forEach>
            </div>
        </div>
    </form>
</div>
</body>
</html>
