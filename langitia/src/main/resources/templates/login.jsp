<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">

<%@include file="./layout/head.jsp"%>

<body>
    <canvas id="c"></canvas>

    <div class="container" id="main">

        <%@include file="./layout/header.jsp"%>

        <div class="border border-white shadow-lg p-3 rounded text-white" id="body-div">

            <div class="border">
                <c:if test="${loginError.equals('true')}">
                    <div class="alert alert-danger" role="alert">
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

        </div>        
    </div>

    <%@include file="./layout/footer.jsp"%>

</body>
</html>