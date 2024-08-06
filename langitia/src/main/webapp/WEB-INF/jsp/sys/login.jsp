<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<%@include file="../../layout/head.jsp"%>
<body onload='document.loginForm.username.focus();'>
<%@include file="../../layout/header.jsp"%>

<div id="login-box">


    <c:if test="${loginError.equals('true')}">
        <div class="error">
            Erreur des informations d'identification. Essayez encore.<br/>
            Cause : ${LoginErrorMessage}
        </div>
    </c:if>
    <h3>Connexion avec le nom d'usager et le mot de passe</h3>
    <form name='loginForm' method='post' action="<c:url value='/login/loginpost'/>">

        <div class="container">
            <div class="row m-1">
                <div class="col text-end pe-1">Le nom de l'usager:</div>
                <div class="col ps-1"><input type='text' name='username' required></div>
            </div>
            <div class="row m-1">
                <div class="col text-end pe-1">Le mot de passe:</div>
                <div class="col ps-1"><input type='password' name='password' required/></div>
            </div>
            <div class="row m-1">
                <div class="col"></div>
                <div class="col-md-auto">
                    <input type="submit"/>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </div>
                <div class="col"></div>
            </div>
        </div>
    </form>
</div>
<%@include file="../../layout/footer.jsp"%>
</body>
</html>
