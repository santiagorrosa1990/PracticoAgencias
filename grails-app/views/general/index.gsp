<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <asset:javascript src="application.js"/>
    <asset:stylesheet src="application.css"/>
    <title>Medios de Pago</title>
</head>
<body>

<div class="container">
    <h1>Buscar medios de pago</h1>

    <g:form controller="general" action="buscar">
        <label>Direccion: <g:textField class="form-control" name="direccion" required="true"/></label>
        <br>
        <label>Rango(mts): <g:textField class="form-control" name="rango" required="true"/></label>
        <br>
        <select name="mediopago" id="mediopago">
            <g:each in="${lista}" var="p">
                <option value="${p.id}">${p.name}</option>
            </g:each>
        </select>
        <g:actionSubmit value="Buscar"/>
    </g:form>
</div>



</body>
</html>