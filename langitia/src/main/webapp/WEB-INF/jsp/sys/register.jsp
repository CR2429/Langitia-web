<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<%@include file="../../layout/head.jsp"%>
<body>
<%@include file="../../layout/header.jsp"%>
<div>
    <div id="register-box">

        <c:if test="${not empty registerError}" >
            <div class="error">${registerErrorMessage}</div>
        </c:if>
        <h2>Spring Security Register Form</h2>
        <form name='registerForm' action="<c:url value='/login/register' />" method='POST'>
            <table>
                <tr>
                    <td>L'email:</td>
                    <td><input type='text' name='username' required></td>
                </tr>
                <tr>
                    <td>Le mot de passe:</td>
                    <td><input type='password' name='password' required></td>
                </tr>
                <tr>
                    <td>Le nom:</td>
                    <td><input type='text' name='nom' required></td>
                </tr>
                <tr>
                    <td>Le prenom:</td>
                    <td><input type='text' name='prenom' required></td>
                </tr>
                <tr>
                    <td>L'adresse:</td>
                    <td><input type='text' name='adresse' required></td>
                </tr>
                <tr>
                    <td>Le telephone:</td>
                    <td><input type='text' name='telephone' required></td>
                </tr>
                <tr>
                    <td colspan='2'>
                        <input name="submit" type="submit"  value="submit" />
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
<%@include file="../../layout/footer.jsp"%>
</body>
</html>