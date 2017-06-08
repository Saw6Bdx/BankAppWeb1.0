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
            <a href="index.html" title="...">Home</a>
            <a href="accounts.php" title="...">Transactions List</a>
            <a href='<c:url value="/transactionsCreation"><c:param name="accountId" value="${account.id}"/></c:url>'><c:out value="${account}"/>New Transaction</a>
        </nav>
        <section>
            <table class="table table-bordered table-hover">
                <tr>
                    <th>Date</th>
                    <th>Label</th>
                    <th>Amount</th>
                    <th>Category</th>
                    <th>Delete</th>
                </tr>
                <c:set var="total" value="${0}"/>
                <c:forEach items="${transactionsList}" var="transaction">
                    <tr>
                        <td>
                            <jsp:useBean id="transactionDate" scope="page" class="java.util.Date"/>
                            <fmt:formatDate value="${transaction.date}" pattern="dd/MM/yyyy" />
                        </td>
                        <td>
                            <c:out value="${transaction.label}"/>
                        </td>
                        <td>
                            <c:out value="${transaction.amount}"/> €
                            <c:set var="total" value="${total + transaction.amount}" />
                        </td>
                        <td>
                            <c:out value="${transaction.idCategory}"/>
                        </td>
                        <td>
                            <a href='<c:url value="/deleteTransaction">
                                   <c:param name = "transactionId" value="${transaction.id}"/>
                                   <c:param name = "transactionLabel" value="${transaction.label}"/>
                               </c:url>' 
                               title=""> <img src="img/RIP.jpg" alt="croix" title="Delete a transaction"/> </a>
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <td class="noborder"></td>
                    <td class="noborder"></td>
                    <td><fmt:formatNumber type = "number" maxFractionDigits = "2" value="${total}" /> €</td>
                </tr>
            </table>

        </section>
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="bootstrap/dist/js/bootstrap.min.js"></script>
    </body>
</html>