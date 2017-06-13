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
        <title>BankApp - New Category</title>
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
              action="<c:url value="/categoryCreation"/>">
            <div class="form-group">
                Name category
                <input class="form-control" type="txtLabel" name="categoryLabel" placeholder="Name" 
                       value="<c:out value="${param['categoryLabel']}"/>"/>
            </div><br/><br/>
            <c:choose>
                <c:when test="${error eq 'category.already.exists'}">
                    <p class="errorMsgColor">This category already exists&nbsp;!</p><br><br>
                </c:when>
            </c:choose>
            <input class="btn btn-primary" type="submit" name="applyBtn" value="Apply"/>
            <input class="btn btn-primary btn-xs" type="button" name="cancelBtn" value="Cancel"/>
        </form><br/><br/>

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="dist/js/bootstrap.min.js"></script>
    </body>
</html>