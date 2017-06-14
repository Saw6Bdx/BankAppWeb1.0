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
        <title>BankApp - New User</title>
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
              action="<c:url value="/userCreation"/>">
            <div class="form-group">
                Name
                <input class="form-control" type="txtLabel" name="userName" placeholder="Name" 
                       value="<c:out value="${param['userName']}"/>"/>
            </div><br/><br/>
            <div class="form-group">
                First name
                <input class="form-control" type="txtLabel" name="userFirstName" placeholder="First name" 
                       value="<c:out value="${param['userFirstName']}"/>"/>
            </div><br/><br/>
            <div class="form-group">
                Phone
                <input class="form-control" type="txtLabel" name="userPhone" placeholder="Phone number" 
                       value="<c:out value="${param['userPhone']}"/>"/>
            </div><br/><br/>
            <div class="form-group">
                E-mail
                <input class="form-control" type="txtLabel" name="userEmail" placeholder="E-mail" 
                       value="<c:out value="${param['userEmail']}"/>"/>
            </div><br/><br/>
            <div class="form-group">
                Birthday
                <select name="userDay" class="form-control" 
                       value="<c:out value="${param['userDay']}"/>">
                    <option value="0" selected="selected">Day...</option>
                    <c:forEach var="i" begin="1" end="31">
                        <option><c:out value ="${i}">${i}</c:out></option>
                    </c:forEach>
                </select>                
                <select name="userMonth" class="form-control" 
                       value="<c:out value="${param['userMonth']}"/>">
                    <option value="0" selected="selected">Month...</option>
                    <c:forTokens items="January,February,March,April,May,June,July,August,September,October,November,December" delims="," var="month">
                        <option><c:out value="${month}"/></option>
                    </c:forTokens>
                </select>
                <select name="userYear" class="form-control" 
                       value="<c:out value="${param['userYear']}"/>">
                    <option value="0" selected="selected">Year...</option>
                    <c:forEach var="i" begin="1917" end="2002">
                        <option><c:out value ="${i}">${i}</c:out></option>
                    </c:forEach>
                </select>
            </div><br/><br/>
            
            <div class="form-group">
                Address
                <input class="form-control" type="txtLabel" name="userAddressLine1" placeholder="First line" 
                       value="<c:out value="${param['userAddressLine1']}"/>"/>
                <br/><br/>
                <input class="form-control" type="txtLabel" name="userAddressLine2" placeholder="Second line (optional)" 
                       value="<c:out value="${param['userAddressLine2']}"/>"/>
            </div><br/><br/>    
            <div class="form-group">
                Postcode
                <input class="form-control" type="txtLabel" name="userPostCode" placeholder="Postcode" 
                       value="<c:out value="${param['userPostCode']}"/>"/>
            </div><br/><br/>
            <div class="form-group">
                City
                <input class="form-control" type="txtLabel" name="userCity" placeholder="City" 
                       value="<c:out value="${param['userCity']}"/>"/>
            </div><br/><br/>
            
            <div class="form-group">
                Login
                <input class="form-control" type="txtLabel" name="userLogin" placeholder="Login" 
                       value="<c:out value="${param['userLogin']}"/>"/>
            </div><br/><br/>
            <c:choose>
                <c:when test="${error eq 'login.already.exists'}">
                    <p class="errorMsgColor">This login already exists&nbsp;!</p><br><br>
                </c:when>
            </c:choose>
            <div class="form-group">
                Password
                <input class="form-control" type="password" name="userPassword" placeholder="********" 
                       value="<c:out value="${param['userPassword']}"/>"/>
            </div><br/><br/>
            <c:choose>
                <c:when test="${error eq 'pwd.not.identical'}">
                    <p class="errorMsgColor">Passwords are not identical&nbsp;!</p><br><br>
                </c:when>
            </c:choose>
            <div class="form-group">
                Confirm Password
                <input class="form-control" type="password" name="userPasswordConfirmation" placeholder="********" 
                       value="<c:out value="${param['userPasswordConfirmation']}"/>"/>
            </div><br/><br/>
            <input class="btn btn-primary" type="submit" name="applyBtn" value="Apply"/>
            <input class="btn btn-primary btn-xs" type="button" name="cancelBtn" value="Cancel"/>
        </form><br/><br/>

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="dist/js/bootstrap.min.js"></script>
    </body>
</html>