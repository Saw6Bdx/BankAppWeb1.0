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
        <title>BankApp - New Transaction</title>
    </head>
    <body>
        <header id="banniere" class="header">
            <img class="image-rounded" src="img/bank.jpg" alt="..."/>
            <h2>BankApp</h2>
        </header>
        <nav>
            <a href="displayHolder.jsp">Home</a>
            <a href="displayAccount.jsp">Transactions List</a>
            <a href="createTransactions.jsp">New Transaction</a>
        </nav>
        <form class="form-inline" method="post" action="">

            <fieldset><legend>New Transaction</legend></fieldset><br/>
            	<div class="form-group">
                Account : <select class="form-control" name="account" value="<c:out value="${param['account']}"/>">
                <option value="0">Account...</option>
            	<c:forEach items="${accountList}" var="acc">
			         <option><c:out value ="${acc}">${acc}</c:out></option>
			    </c:forEach>
                </select></div><br/><br/>
                <div class="form-group">
                Transaction type : <select class="form-control" name="transactionType" value="<c:out value="${param['transactionType']}"/>">
                <option value="0">Transaction type...</option>
            	<c:forEach items="${transactionTypeList}" var="transactionType">
			         <option><c:out value ="${transactionType}">${transactionType}</c:out></option>
			    </c:forEach>
                </select></div><br/><br/>
            <div class="form-group">Label : <input class="form-control" type="text" name="label" placeholder="Label" value="<c:out value="${param['label']}"/>"></div><br/><br/>
            <div class="form-group">Amount : <input class="form-control" type="number" name="nbAmount" step="0.01" placeholder="0.00" style="text-align: right"/> €</div><br/><br/>
            <div class="form-group">Date : <select name="userDay" class="form-control" value="<c:out value="${param['userDay']}"/>">
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
                    <c:forEach var="i" begin="2015" end="2025">
                        <option><c:out value ="${i}">${i}</c:out></option>
                    </c:forEach>
			    </select></div><br/><br/>
			    <div class="form-group">End date : <select name="userEndDay" class="form-control" value="<c:out value="${param['userEndDay']}"/>">
                    <option value="0" selected="selected">Day...</option>
                    <c:forEach var="i" begin="1" end="31">
                        <option><c:out value ="${i}">${i}</c:out></option>
                    </c:forEach>
                </select>                
                <select name="userEndMonth" class="form-control" 
                       value="<c:out value="${param['userEndMonth']}"/>">
                    <option value="0" selected="selected">Month...</option>
                    <c:forTokens items="January,February,March,April,May,June,July,August,September,October,November,December" delims="," var="month">
                        <option><c:out value="${month}"/></option>
                    </c:forTokens>
                </select>
                <select name="userEndYear" class="form-control" 
                       value="<c:out value="${param['userEndYear']}"/>">
                    <option value="0" selected="selected">Year...</option>
                    <c:forEach var="i" begin="2015" end="2025">
                        <option><c:out value ="${i}">${i}</c:out></option>
                    </c:forEach>
			    </select></div><br/><br/>
			    <div class="form-group">
                Description : <textarea class="form-control" name="description" rows="1" cols="30" placeholder="Description (optional)" maxlength="150" value="<c:out value="${param['userDay']}"/>"></textarea></div><br/><br/>
            
            <!-- if we have time to implement periodic of transactions -->
            <!-- fieldset><div class="form-group"><label class="checkbox-inline">Periodic<input type="checkbox" name="cbPeriodicity"/></label></div><br/>
                <div class="form-group"><label class="radio-inline">Monthly<input type="radio" name="rdPeriodUnit" checked="checked" value="Mensuel"/></label>
                    <label class="radio-inline">Weekly<input type="radio" name="rdPeriodUnit" value="Trimestriel"/></label>
                    <label class="radio-inline">Annual<input type="radio" name="rdPeriodUnit" value="Annuel"/></label></div></fieldset><br/-->
            <input class="btn btn-primary" type="button" name="annuler" value="Apply"/>
            <input class="btn btn-primary btn-xs" type="button" name="annuler" value="Cancel"/>
        </form><br/><br/>
        
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="bootstrap/dist/js/bootstrap.min.js"></script>
    </body>
</html>