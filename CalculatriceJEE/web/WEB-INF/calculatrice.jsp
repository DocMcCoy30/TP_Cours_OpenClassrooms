<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/links/bootstrap-4.3.1-dist/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/links/style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>CalculatriceJEE</title>
</head>
<body>

<div class="header">

    <div class="container">
        <form class="row col-lg-4 offset-lg-4" action="" method="POST" name="calcJEE" id="calcJEE">
            <label class="sr-only" for="nb1">Nombre 1</label>
            <input type="number" name="nb1" id="nb1" value="${ calcul.nb1}">

            <label class="sr-only" for="operateur">Operateur</label>
            <select name="operateur" id="operateur">
                <option value="+">+</option>
                <option value="-">-</option>
                <option value="x">x</option>
                <option value="/">/</option>
            </select>

            <label class="sr-only" for="nb2">Nombre 2</label>
            <input type="number" name="nb2" id="nb2" value="${ calcul.nb2}">

            <label class="sr-only" for="submit">Valider</label>
            <input type="submit" name="submit" id="submit" value="Valider">
        </form>

    </div>

        <div id="resultatMessage">
            ${ resultat}
        </div>

</div>

</body>
</html>
