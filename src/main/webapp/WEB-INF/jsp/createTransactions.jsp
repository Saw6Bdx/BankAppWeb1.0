<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
        </nav>
        <form class="form-inline" method="post" action="<c:url value="/transactionsCreation"><c:param name="holderId" value="${param['holderId']}"/><c:param name="accountId" value="${param['accountId']}"/></c:url>">

                <fieldset><legend>New Transaction</legend></fieldset><br/>
                <div class="form-group">
                    Account : <select class="form-control" name="accountId">
                        <option value="0">Account...</option>
                    <c:forEach items="${accountList}" var="account">
                        <option value ="${account.id}">${account}</option>
                    </c:forEach>
                </select></div><br/><br/>
            <div class="form-group">
                Transaction type : <select class="form-control" name="transactionTypeId">
                    <option value="0">Transaction type...</option>
                    <c:forEach items="${transactionTypeList}" var="transactionType">
                        <option value ="${transactionType.id}">${transactionType}</option>
                    </c:forEach>
                </select></div><br/><br/>     
            <div class="form-group">Label : <input class="form-control" type="text" name="label" placeholder="Label" value="<c:out value="${param['label']}"/>"></div><br/><br/>
            <div class="form-group">Amount : <input class="form-control" type="number" name="amount" step="0.01" placeholder="-200.00" style="text-align: right"> €</div><br/><br/>
            <div class="form-group">Date : 
                <fmt:setLocale value="en_US" />
                <jsp:useBean id="now" class="java.util.Date"/>    
                <fmt:formatDate value="${now}" pattern="dd" var="todayDay" />
                <fmt:formatDate value="${now}" pattern="MMMM" var="todayMonth" />
                <fmt:formatDate value="${now}" pattern="yyyy" var="todayYear" />
                <select name="userDay" class="form-control">
                    <c:forEach var="i" begin="1" end="31">
                        <option ${i == todayDay ? 'selected' : ''}><c:out value="${i}">${i}</c:out></option>
                    </c:forEach>
                </select>     
                <select name="userMonth" class="form-control">
                    <c:forTokens items="January,February,March,April,May,June,July,August,September,October,November,December" delims="," var="month">
                        <option ${month == todayMonth ? 'selected' : ''}><c:out value="${month}"/></option>
                    </c:forTokens>
                </select>
                <select name="userYear" class="form-control">
                    <c:forEach var="i" begin="2015" end="2025">
                        <option ${i == todayYear ? 'selected' : ''}><c:out value="${i}">${i}</c:out></option>
                    </c:forEach>
                </select></div><br/><br/>
            <div class="form-group">End date : 
                <select name="userEndDay" class="form-control">
                    <c:forEach var="i" begin="1" end="31">
                        <option ${i == todayDay ? 'selected' : ''}><c:out value="${i}">${i}</c:out></option>
                    </c:forEach>
                </select>                
                <select name="userEndMonth" class="form-control">
                    <c:forTokens items="January,February,March,April,May,June,July,August,September,October,November,December" delims="," var="month">
                        <option ${month == todayMonth ? 'selected' : ''}><c:out value="${month}"/></option>
                    </c:forTokens>
                </select>
                <select name="userEndYear" class="form-control"> 
                    <c:forEach var="i" begin="2015" end="2025">
                        <option ${i == todayYear ? 'selected' : ''}><c:out value="${i}">${i}</c:out></option>
                    </c:forEach>
                </select></div><br/><br/>
            <div class="form-group">
                Category : <select class="form-control" name="categoryId">
                    <option value="0">Category...</option>
                    <c:forEach items="${categoryList}" var="category">
                        <option value ="${category.id}">${category}</option>
                    </c:forEach>
                </select></div><br/><br/>
            <div class="form-group">
                Comment : <textarea class="form-control" name="comment" rows="1" cols="30" placeholder="Comment (optional)" maxlength="150"></textarea></div><br/><br/>

            <!-- if we have time to implement periodic of transactions -->
            <!-- fieldset><div class="form-group"><label class="checkbox-inline">Periodic<input type="checkbox" name="cbPeriodicity"/></label></div><br/>
                <div class="form-group"><label class="radio-inline">Monthly<input type="radio" name="rdPeriodUnit" checked="checked" value="Mensuel"/></label>
                    <label class="radio-inline">Weekly<input type="radio" name="rdPeriodUnit" value="Trimestriel"/></label>
                    <label class="radio-inline">Annual<input type="radio" name="rdPeriodUnit" value="Annuel"/></label></div></fieldset><br/-->
            <a class="btn btn-primary btn-xs" href='<c:url value="/transactionsDisplay"><c:param name="holderId" value="${param['holderId']}"/><c:param name="accountId" value="${param['accountId']}"/></c:url>' title="Back to home page">
                Cancel</a>
            <input class="btn btn-primary" type="submit" name="applyBtn" value="Apply"/>
        </form><br/><br/>

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="bootstrap/dist/js/bootstrap.min.js"></script>
    </body>
</html>