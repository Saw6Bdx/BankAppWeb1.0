<%-- 
    Document   : displayTransactions
    Created on : 31 mai 2017, 17:18:31
    Author     : Guest
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Transactions</title>
    </head>
    <body>
        <h1>Display transactions</h1>
        <c:choose>
            <c:when test="${error eq 'no.transactions.available'}">
                No transactions available&nbsp;!<br><br>
            </c:when>
        </c:choose>
        <%--<section>
            <c:out value="${transactions.label}"/>&nbsp;: 
        </section>--%>

        <section>
            <c:forEach items="${transactions}" var="trans">
                <tr>
                    <td>
                        <c:out value="${trans.label}"/>
                    </td>
                    <td>
                        <c:out value="${trans.amount}"/>
                    </td>
                    </br>
                </tr>
            </c:forEach>
        </section>
        <nav>
            <ul>
                <li><a href="<c:url value="/"/>">Home</a></li>
            </ul>
        </nav>
    </body>
</html>