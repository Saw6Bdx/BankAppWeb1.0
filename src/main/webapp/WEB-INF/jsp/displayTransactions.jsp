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
            <a href='<c:url value="/accountDisplay"><c:param name="holderId" value="${param['holderId']}"/></c:url>' title="Back to home page">
                    Home</a>
                <a href='<c:url value="/transactionsCreation"><c:param name="holderId" value="${param['holderId']}"/><c:param name="accountId" value="${param['accountId']}"/></c:url>'><c:out value="${account}"/>
                    New Transaction</a>
                <a href='<c:url value="/displayCategories"><c:param name="holderId" value="${param['holderId']}"/><c:param name="accountId" value="${param['accountId']}"/></c:url>' 
                   title="display transactions by categorie">
                    Categories</a>
                <a href='<c:url value="/categoryCreation"><c:param name="holderId" value="${param['holderId']}"/><c:param name="accountId" value="${param['accountId']}"/></c:url>' title="Add a new category">
                    New category</a>
                <a href="index.html">
                    Disconnect</a>
            </nav>
            <section>
                <table class="table table-bordered table-hover">
                    <form class="form-inline form-horizontal" 
                          method="post" 
                          action="<c:url value="/transactionsDisplay">
                        <c:param name = "accountId" value="${accountId}"/>
                        <c:param name = "overdraft" value="${overdraft}"/>
                        <c:param name = "firstBalance" value="${firstBalance}"/>
                    </c:url>">
                    <tr>
                        <th>Date 
                            <button type="submit" name="sortDateDown" class="btn btn-default btn-xs">
                                <span class="glyphicon glyphicon-triangle-bottom" aria-hidden="true"></span>
                            </button>
                            <button type="submit" name="sortDateUp" class="btn btn-default btn-xs">
                                <span class="glyphicon glyphicon-triangle-top" aria-hidden="true"></span>
                            </button>
                        </th>
                        <th>Label</th>
                        <th>Debit
                            <button type="submit" name="sortDebitDown" class="btn btn-default btn-xs">
                                <span class="glyphicon glyphicon-triangle-bottom" aria-hidden="true"></span>
                            </button>
                            <button type="submit" name="sortDebitUp" class="btn btn-default btn-xs">
                                <span class="glyphicon glyphicon-triangle-top" aria-hidden="true"></span>
                            </button>
                        </th>
                        <th>Credit
                            <button type="submit" name="sortCreditDown" class="btn btn-default btn-xs">
                                <span class="glyphicon glyphicon-triangle-bottom" aria-hidden="true"></span>
                            </button>
                            <button type="submit" name="sortCreditUp" class="btn btn-default btn-xs">
                                <span class="glyphicon glyphicon-triangle-top" aria-hidden="true"></span>
                            </button>
                        </th>
                        <th>Category
                            <button type="submit" name="sortCategoryDown" class="btn btn-default btn-xs">
                                <span class="glyphicon glyphicon-triangle-bottom" aria-hidden="true"></span>
                            </button>
                            <button type="submit" name="sortCategoryUp" class="btn btn-default btn-xs">
                                <span class="glyphicon glyphicon-triangle-top" aria-hidden="true"></span>
                            </button>
                        </th>
                        <th>Modify</th>
                        <th>Delete</th>
                    </tr>
                    <c:set var="total" value="${firstBalance}"/>
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
                                <c:if test = "${transaction.amount < 0}">
                                    <c:out value="${transaction.amount}"/> €
                                    <c:set var="total" value="${total + transaction.amount}" />
                                </c:if>
                            </td>
                            <td>
                                <c:if test = "${transaction.amount > 0}">
                                    <c:out value="${transaction.amount}"/> €
                                    <c:set var="total" value="${total + transaction.amount}" />
                                </c:if>
                            </td>

                            <td>
                                <c:out value="${transaction.idCategory}"/>
                            </td>
                            <td>
                                <a href='<c:url value="/modifyTransaction">
                                       <c:param name = "holderId" value="${param['holderId']}"/>
                                       <c:param name = "accountId" value="${param['accountId']}"/>
                                       <c:param name = "transactionId" value="${transaction.id}"/>
                                   </c:url>' 
                                   title=""> <img src="img/edit.png" alt="croix" title="Modify a transaction"/> </a>
                            </td>
                            <td>
                                <a href='<c:url value="/deleteTransaction">
                                       <c:param name = "holderId" value="${param['holderId']}"/>
                                       <c:param name = "accountId" value="${param['accountId']}"/>
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
                        <c:choose>
                            <c:when test = "${total < overdraft}">
                                <td class="errorMsgColor"><fmt:formatNumber type = "number" maxFractionDigits = "2" value="${total}" /> €</td>
                            </c:when>    
                            <c:otherwise>
                                <td><fmt:formatNumber type = "number" maxFractionDigits = "2" value="${total}" /> €</td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                </form>
            </table>

            <c:choose>
                <c:when test = "${total < overdraft}">
                    <p class="errorMsgColor">You are under your overdraft allowed of ${overdraft}€, please replenish your account&nbsp;!</p><br><br>
                </c:when>
            </c:choose>

            <a href='<c:url value="/displayCategories"/>' 
               title="displayCategories">Categories</a>
        </section>
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="bootstrap/dist/js/bootstrap.min.js"></script>
    </body>
</html>
