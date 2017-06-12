<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <title>BankApp - Accounts</title>
    </head>
    <body>
        <header id="banniere" class="header">
            <img src="img/bank.jpg" alt="..."/>
            <h2>BankApp</h2>
        </header>
        <nav>
            <%@include file="menu.jsp" %>
            <a href='<c:url value="/accountCreation"><c:param name="holderId" value="${holder.id}"/></c:url>'><c:out value="${holder}"/>New Account</a>
        </nav>
        <c:choose>
            <c:when test="${error eq 'no.account.available'}">
                No account registered&nbsp;!<br><br>
            </c:when>
        </c:choose>

        <section>
            <table class="table table-bordered table-hover">
                <tr>
                    <th>Account</th>
                    <th>Balance</th>
                </tr>
                <c:set var="pos" value="${0}"/>
                <c:forEach items="${accountList}" var="account">
                    <tr>
                        <td>
                            <a href='<c:url value="/transactionsDisplay">
                                   <c:param name="accountId" value="${account.id}"/></c:url>'>
                               <c:out value="${account}"/></a>
                        </td>
                        <td>
                            <fmt:formatNumber type = "number" maxFractionDigits = "2" value="${account.firstBalance+sumTransactions[pos]}" /> €
                        </td>
                    </tr>
                    <c:set var="pos" value="${pos + 1}" />
                </c:forEach>
            </table>
        </section>
    </body>
</html>