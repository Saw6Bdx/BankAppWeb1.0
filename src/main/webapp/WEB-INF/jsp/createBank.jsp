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
        <title>BankApp - New Bank</title>
    </head>
    <body>
        <header id="banniere" class="header">
            <img class="image-rounded" src="img/bank.jpg" alt="..."/>
            <h2>BankApp</h2>
        </header>
        <nav>
            <%@include file="menu.jsp" %>
        </nav>
        <form class="form-inline" method="post" action="<c:url value="/bankCreation"/>">

            <fieldset><legend>New bank</legend></fieldset>
            <div class="form-group">Agency name : <input class="form-control" type="text" name="agencyName" placeholder="Agency name" value="<c:out value="${param['agencyName']}"/>"/></div><br/><br/>
            <div class="form-group">Agency code : <input class="form-control" type="text" name="agencyCode" placeholder="Agency code" value="<c:out value="${param['agencyCode']}"/>"/></div><br/><br/>
            <div class="form-group">
                Bank name : <select class="form-control" name="bankId">
                <option value="0">Bank name...</option>
            	<c:forEach items="${bankList}" var="bank">
			         <option value ="${bank.id}">${bank}</option>
			    </c:forEach>
                </select></div><br/><br/>
            <!--div class="form-group">Bank code : <input class="form-control" type="text" name="bankCode" placeholder="Bank code" value="<c:out value="${param['bankCode']}"/>"/></div><br/><br/-->
            
            
            <div class="form-group">
                Address
                <input class="form-control" type="text" name="agencyAddressLine1" placeholder="First line" 
                       value="<c:out value="${param['agencyAddressLine1']}"/>"/>
                <br/><br/>
                <input class="form-control" type="text" name="agencyAddressLine2" placeholder="Second line (optional)" 
                       value="<c:out value="${param['agencyAddressLine2']}"/>"/>
            </div><br/><br/>    
            <div class="form-group">
                Postcode
                <input class="form-control" type="text" name="agencyPostCode" placeholder="Postcode" 
                       value="<c:out value="${param['agencyPostCode']}"/>"/>
            </div><br/><br/>
            <div class="form-group">
                City
                <input class="form-control" type="text" name="agencyCity" placeholder="City" 
                       value="<c:out value="${param['agencyCity']}"/>"/>
            </div><br/><br/>
            
            
            <div class="form-group">Manager's name : <input class="form-control" type="text" name="managerName" placeholder="Account manager name" value="<c:out value="${param['managerName']}"/>"/></div><br/><br/>
            <div class="form-group">Manager's first name : <input class="form-control" type="text" name="managerFirstName" placeholder="Account manager first name" value="<c:out value="${param['managerFirstName']}"/>"/></div><br/><br/>
            <div class="form-group">
                Phone
                <input class="form-control" type="text" name="managerPhone" placeholder="Phone number (optional)" 
                       value="<c:out value="${param['managerPhone']}"/>"/>
            </div><br/><br/>
            <div class="form-group">
                E-mail
                <input class="form-control" type="text" name="managerEmail" placeholder="E-mail (optional)" 
                       value="<c:out value="${param['userEmail']}"/>"/>
            </div><br/><br/>
            
            
            <div class="form-group">Assignment date : <select name="managerDay" class="form-control">
                    <option value="0" selected="selected">Day...</option>
                    <c:forEach var="i" begin="1" end="31">
                        <option><c:out value ="${i}">${i}</c:out></option>
                    </c:forEach>
                </select>                
                <select name="managerMonth" class="form-control">
                    <option value="0" selected="selected">Month...</option>
                    <c:forTokens items="January,February,March,April,May,June,July,August,September,October,November,December" delims="," var="month">
                        <option><c:out value="${month}"/></option>
                    </c:forTokens>
                </select>
                <select name="managerYear" class="form-control">
                    <option value="0" selected="selected">Year...</option>
                    <c:forEach var="i" begin="2015" end="2025">
                        <option><c:out value ="${i}">${i}</c:out></option>
                    </c:forEach>
			    </select></div><br/><br/>
            
            <input class="btn btn-primary" type="submit" name="applyBtn" value="Apply"/>
            <input class="btn btn-primary btn-xs" type="button" name="cancelBtn" value="Cancel"/>
        </form><br/><br/>

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="bootstrap/dist/js/bootstrap.min.js"></script>
    </body>
</html>