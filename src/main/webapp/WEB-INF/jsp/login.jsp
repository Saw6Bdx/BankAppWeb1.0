<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            <%@include file="../style.css" %>
        </style>
        <meta charset="utf-8">
        <title>BankApp - Log in</title>
    </head>
    <body>
        <header id="banniere" class="header">
            <img class="image-rounded" src="img/bank.jpg" alt="..."/>
            <h2>BankApp</h2>
        </header>
        <nav>
        </nav>
        <form class="form-inline form-horizontal" 
              method="post" 
              action="<c:url value="/login"/>">
            <c:choose>
                <c:when test="${error eq 'login.does.not.exist'}">
                    <p class="errorMsgColor">This login does not exist&nbsp;!</p><br><br>
                </c:when>
            </c:choose>
            <div class="form-group">
                Login
                <input class="form-control" type="text" name="login" placeholder="login" 
                       value="<c:out value="${param['login']}"/>"/>
            </div><br/><br/>
            <c:choose>
                <c:when test="${error eq 'pwd.is.invalid'}">
                    <p class="errorMsgColor">The password is invalid&nbsp;!</p><br><br>
                </c:when>
            </c:choose>
            <div class="form-group">
                Password
                <input class="form-control" type="password" name="password" placeholder="********" 
                       value="<c:out value="${param['password']}"/>"/>
            </div><br/><br/>
            <a class="btn btn-primary btn-xs" href='<c:url value="/userCreation"/>' title="Create a new user account">
                New user</a>
            <input class="btn btn-primary" type="submit" name="applyBtn" value="Log in"/>
        </form><br/><br/>

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="bootstrap/dist/js/bootstrap.min.js"></script>
    </body>
</html>