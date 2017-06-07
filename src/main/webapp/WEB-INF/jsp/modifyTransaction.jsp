<%@page pageEncoding="UTF-8" isErrorPage="true" contentType="text/html" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            <%@include file="../style.css" %>
        </style>
        <meta charset="utf-8">
        <title>BankApp - Transactions</title>
    </head>
    <body>
        <header id="banniere" class="header">
            <img src="img/bank.jpg" alt="..."/>
            <h2>BankApp</h2>
        </header>
        <nav>
            <%@include file="menu.jsp" %>
        </nav>
        <section>
            <form action="<c:url value="/modifyTransaction">
                      <c:param name = "transactionId" value="${param['transactionId']}"/>
                  </c:url>" method="post">
                <div class="form-group">
                    Date
                    <input class="form-control" type="txtLabel" name="transactionDate" placeholder="Name" 
                           value="<c:out value="${param['transactionDate']}"/>"/>
                </div><br/><br/>
                <div class="form-group">
                    Label
                    <input class="form-control" type="txtLabel" name="transactionLabel" placeholder="Name" 
                           value="<c:out value="${param['transactionLabel']}"/>"/>
                </div><br/><br/>
                <div class="form-group">
                    Amount
                    <input class="form-control" type="txtLabel" name="transactionAmount" placeholder="${param['']}" 
                           value="<c:out value="${param['transactionAmount']}"/>"/>
                </div><br/><br/>
                <div class="form-group">
                    Category
                    <input class="form-control" type="txtLabel" name="transactionIdCategory" placeholder="${param['']}" 
                           value="<c:out value="${param['transactionIdCategory']}"/>"/>
                </div><br/><br/>
                <input type="submit" name="yesBtn" value="Modify" />
                <input type="submit" name="noBtn" value="Cancel" />
            </form>
        </section>
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="bootstrap/dist/js/bootstrap.min.js"></script>
    </body>
</html>