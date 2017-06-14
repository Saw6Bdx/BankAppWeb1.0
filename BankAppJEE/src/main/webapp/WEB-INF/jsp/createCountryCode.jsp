<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Création d'un compte</title>
    </head>
    <body>

        <c:choose>
            <c:when test="${error eq 'countrycode.already.exists'}">
                This country code already exists&nbsp;!<br><br>
            </c:when>
        </c:choose>

        <form action="<c:url value="/countryCodes"/>" method="POST">
            <label>CountryCode</label>
            <input name="code" value="<c:out value="${param['code']}"/>"><br>
            <button type="submit">Créer</button>
        </form>

    </body>
</html>