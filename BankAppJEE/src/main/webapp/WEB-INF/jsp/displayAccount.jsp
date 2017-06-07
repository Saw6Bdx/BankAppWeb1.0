<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <title>BankApp - Accounts</title>
    </head>
    <body>
        <header id="banniere" class="header">
            <img src="img/bank.jpg" alt="..."/>
            <h2>BankApp</h2>
        </header>
        <nav>
            <a href='<c:url value="/holderDisplay"></c:url>'>Change user</a>
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
            <!--c:set var="balance" value="${a.firstBalance}"/-->
            <c:forEach items="${accountList}" var="account">
                <tr>
                    <td>
                        <a href='<c:url value="/transactionsDisplay"><c:param name="accountId" value="${account.id}"/></c:url>'><c:out value="${account}"/></a>
                    </td>
                    <td>
              </td>
                </tr>
            </c:forEach>
            </table>
        </section>
    </body>
</html>