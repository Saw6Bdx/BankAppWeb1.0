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
            <a href='<c:url value="/accountCreation"><c:param name="holderId" value="${param['holderId']}"/></c:url>'><c:out value="${holder}"/>
                    New Account</a>
                <a href="index.html">
                    Disconnect</a>
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
                    <th>Modify</th>
                    <th>Delete</th>
                </tr>
                <c:set var="pos" value="${0}"/>
                <c:set var="total" value="${0}"/>
                <c:forEach items="${accountList}" var="account">
                    <tr>
                        <td>
                            <a href='<c:url value="/transactionsDisplay">
                                   <c:param name="holderId" value="${param['holderId']}"/>
                                   <c:param name="accountId" value="${account.id}"/></c:url>'>
                               <c:out value="${account}"/></a>
                        </td>
                        <td>
                            <fmt:formatNumber type = "number" maxFractionDigits = "2" value="${account.firstBalance+sumTransactions[pos]}" /> €
                            <c:set var="total" value="${total + account.firstBalance+sumTransactions[pos]}" />
                        </td>
                        <td>
                            <a href='<c:url value="/modifyAccount">
                                   <c:param name="holderId" value="${param['holderId']}"/>
                                   <c:param name = "accountId" value="${account.id}"/>
                               </c:url>' 
                               title=""> <img src="img/edit.png" alt="croix" title="Modify an account"/> </a>
                        </td>
                        <td>
                            <a href='<c:url value="/deleteAccount">
                                   <c:param name="holderId" value="${param['holderId']}"/>
                                   <c:param name = "accountId" value="${account.id}"/>
                               </c:url>' 
                               title=""> <img src="img/RIP.jpg" alt="croix" title="Delete an account"/> </a>
                        </td>
                    </tr>
                    <c:set var="pos" value="${pos + 1}" />
                </c:forEach>
                <tr>
                    <td class="noborder"></td>
                    <td><fmt:formatNumber type = "number" maxFractionDigits = "2" value="${total}" /> €</td>
                </tr>
            </table>
        </section>
    </body>
</html>