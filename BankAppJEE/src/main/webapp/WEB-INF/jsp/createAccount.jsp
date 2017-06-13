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
        </nav>
        <form class="form-inline" method="post" action="<c:url value="/accountCreation"><c:param name="holderId" value="${param['holderId']}"/></c:url>">

            <fieldset><legend>New account</legend></fieldset>
            <div class="form-group">
                Account type : <select class="form-control" name="accountTypeId">
                <option value="0">Account type...</option>
            	<c:forEach items="${accountTypeList}" var="accountType">
			         <option value ="${accountType.id}">${accountType}</option>
			    </c:forEach>
                </select></div><br/><br/>
            <div class="form-group">
            Number : <input class="form-control" type="text" name="number" placeholder="00095632356" style="text-align: right" value="<c:out value="${param['number']}"/>"/></div><br/><br/>
            <div class="form-group">Creation date : <select name="userDay" class="form-control">
                    <option value="0" selected="selected">Day...</option>
                    <c:forEach var="i" begin="1" end="31">
                        <option><c:out value ="${i}">${i}</c:out></option>
                    </c:forEach>
                </select>                
                <select name="userMonth" class="form-control">
                    <option value="0" selected="selected">Month...</option>
                    <c:forTokens items="January,February,March,April,May,June,July,August,September,October,November,December" delims="," var="month">
                        <option><c:out value="${month}"/></option>
                    </c:forTokens>
                </select>
                <select name="userYear" class="form-control">
                    <option value="0" selected="selected">Year...</option>
                    <c:forEach var="i" begin="2015" end="2025">
                        <option><c:out value ="${i}">${i}</c:out></option>
                    </c:forEach>
			    </select></div><br/><br/>
            <div class="form-group">First balance : <input class="form-control" type="number" name="firstBalance" step="0.01" placeholder="0.00 €" style="text-align: right" value="<c:out value="${param['firstBalance']}"/>"/> €</div><br/><br/>
            <div class="form-group">Overdraft : <input class="form-control" type="number" name="overdraft" step="0.01" placeholder="-200.00 €" style="text-align: right" value="<c:out value="${param['overdraft']}"/>"/> €</div><br/><br/>
            <div class="form-group">Interest rate : <input class="form-control" type="number" name="interestRate" step="0.01" placeholder="1.00 %" style="text-align: right" value="<c:out value="${param['interestRate']}"/>"/> %</div><br/><br/>
            <div class="form-group">
            Country code : <select class="form-control" name="countryCodeId">
                <option value="0">Country code...</option>
            	<c:forEach items="${countryCodeList}" var="countryCode">
			         <option value ="${countryCode.id}">${countryCode}</option>
			    </c:forEach>
                </select></div><br/><br/>
            <div class="form-group">    
            Agency : <select class="form-control" name="agencyId">
                <option value="0">Agency...</option>
            	<c:forEach items="${agencyList}" var="agency">
			         <option value ="${agency.id}">${agency}</option>
			    </c:forEach>
                </select></div><br/><br/>
            <div class="form-group">
                Description : 
                <textarea class="form-control" name="description" rows="1" cols="30" placeholder="Description (optional)" maxlength="150" style="text-align: center"></textarea></div><br/><br/>
           
           	<a class="btn btn-primary btn-xs" href='<c:url value="/accountDisplay"><c:param name="holderId" value="${param['holderId']}"/></c:url>' title="Back to home page">
                Cancel</a>
            <input class="btn btn-primary" type="submit" name="applyBtn" value="Apply"/>
            </form><br/><br/>

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="bootstrap/dist/js/bootstrap.min.js"></script>
    </body>
</html>