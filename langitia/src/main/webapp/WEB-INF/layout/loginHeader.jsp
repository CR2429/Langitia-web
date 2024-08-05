<%@ page import="org.springframework.security.authentication.AnonymousAuthenticationToken"%>
<%@ page import="org.springframework.security.core.Authentication"%>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="loginUrl" value="/login/login"/>
<c:url var="logoutUrl" value="/login/logout"/>
<c:url var="registerUrl" value="/login/register"/>
<ul class="navbar-nav flex-grow-0">
        <%
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentUserName=authentication.getName();
    session.setAttribute("username",currentUserName);
    if (currentUserName.equals("anonymousUser")){%>
    <li class="nav-item">
        <a class="nav-link" href="${registerUrl}">S'enregistrer</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="${loginUrl}">Se connecter</a>
    </li>

        <%}else{%>
    <li class="nav-item">
        <span  class="nav-link">Bonjour <%= currentUserName%></span>
    </li>
    <li class="nav-item">
        <form class="form-inline" action="${logoutUrl}" method="post" >
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button  type="submit" class="nav-link btn btn-link ">Se deconnecter</button>
        </form>
    </li>
        <%}%>
