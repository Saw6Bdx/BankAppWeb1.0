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
        <title>BankApp - New Account</title>
    </head>
    <body>
        <header id="banniere" class="header">
            <img class="image-rounded" src="img/bank.jpg" alt="..."/>
            <h2>BankApp</h2>
        </header>
        <nav>
            <%@include file="menu.jsp" %>
        </nav>
        <form class="form-inline" method="post" action="page de traitement">

            <fieldset><legend>New account</legend></fieldset>
            <div class="form-group">
                Account type : <select class="form-control" name="accountType" value="<c:out value="${param['accountType']}"/>">
                <option value="0">Account type...</option>
            	<c:forEach items="${accountTypeList}" var="accountType">
			         <option><c:out value ="${accountType}">${accountType}</c:out></option>
			    </c:forEach>
                </select></div><br/><br/>
            <div class="form-group">
            Number : <input class="form-control" type="text" name="number" placeholder="00095632356" style="text-align: right"value="<c:out value="${param['number']}"/>"/></div><br/><br/>
            <div class="form-group">Date : <select name="cbJours" class="form-control">
                <option value="0" selected="selected">Day...</option>
                <c:forEach var="i" begin="1" end="31">
			         <option><c:out value ="${i}">${i}</c:out></option>
			    </c:forEach>
			    </select>
                
                <select name="cbMois" class="form-control">
                    <option value="0" selected="selected">Month...</option>
                <c:forTokens items="January,February,March,April,May,June,July,August,September,October,November,December" delims="," var="month">
     				<option><c:out value="${month}"/></option>
				</c:forTokens>
                </select>

                <select name="cbAnnees" class="form-control">
                    <option value="0" selected="selected">Year...</option>
                <c:forEach var="i" begin="2010" end="2025">
			         <option><c:out value ="${i}">${i}</c:out></option>
			    </c:forEach>
			    </select></div><br/><br/>
            <div class="form-group">First balance : <input class="form-control" type="number" name="firstBalance" step="0.01" placeholder="0.00 €" style="text-align: right" value="<c:out value="${param['firstBalance']}"/>"/> €</div><br/><br/>
            <div class="form-group">Overdraft : <input class="form-control" type="number" name="overdraft" step="0.01" placeholder="-200.00 €" style="text-align: right" value="<c:out value="${param['overdraft']}"/>"/> €</div><br/><br/>
            <div class="form-group">Interest rate : <input class="form-control" type="number" name="interestRate" step="0.01" placeholder="1.00 %" style="text-align: right" value="<c:out value="${param['interestRate']}"/>"/> %</div><br/><br/>
            
            <div class="form-group">
                Description : 
                <textarea class="form-control" name="description" rows="1" cols="30" placeholder="Description (optional)" maxlength="150" style="text-align: center" value="<c:out value="${param['description']}"/>"></textarea></div><br/><br/>
           
            <input class="btn btn-primary" type="button" name="ApplyBtn" value="Apply"/>
            <input class="btn btn-primary btn-xs" type="button" name="CancelBtn" value="Cancel"/>
        </form><br/><br/>

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="bootstrap/dist/js/bootstrap.min.js"></script>
    </body>
</html>