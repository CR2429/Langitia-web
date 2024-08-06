<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<%@include file="../../layout/head.jsp"%>
<body>
<%@include file="../../layout/header.jsp"%>
<div>
    <h1>Attention, une erreur s’est produite dans la ressource demandée. Veuillez contacter le support technique. </h1>
    <h2>Message : ${message}</h2>
    <h2>Cause : ${error}</h2>
    <h2>Date :${timestamp}</h2>
    <h2>Code : ${status}</h2>
    <h2>URL : ${path}</h2>


    <c:url var="accueilURL" value="/"/>
    <hr/> <a href="${accueilURL}"> allez à l'accueil</a>

</div>
<%@include file="../../layout/footer.jsp"%>
</body>
</html>