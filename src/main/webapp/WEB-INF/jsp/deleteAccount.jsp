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
        <title>BankApp - Account</title>
    </head>
    <body>
        <header id="banniere" class="header">
            <img src="img/bank.jpg" alt="..."/>
            <h2>BankApp</h2>
        </header>
        <section>
            <form action="<c:url value="/deleteAccount">
                      <c:param name = "holderId" value="${param['holderId']}"/>
                      <c:param name = "accountId" value="${param['accountId']}"/>
                  </c:url>" method="post">
                <label>Are you sure you want to delete this account <c:out value="${account.number}"/> ?</label>
                </br>
                <a class="btn btn-primary btn-xs" href='<c:url value="/accountDisplay">
                       <c:param name="holderId" value="${param['holderId']}"/>
                   </c:url>' title="Back to home page">
                    No</a>
                <input class="btn btn-primary btn-xs" type="submit" name="yesBtn" value="Yes"/>
            </form>
        </section>
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="bootstrap/dist/js/bootstrap.min.js"></script>
    </body>
</html>