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
        <section>
            <fmt:setLocale value="en_US" />
            <form class="form-inline" action="<c:url value="/modifyTransaction">
                      <c:param name = "holderId" value="${param['holderId']}"/>
                      <c:param name = "accountId" value="${param['accountId']}"/>
                      <c:param name = "transactionId" value="${transaction.id}"/>
                  </c:url>" method="post">
                <div class="form-group">
                    Date
                    <fmt:formatDate value="${transaction.date}" pattern="dd" var="transactionDay" />
                    <fmt:formatDate value="${transaction.date}" pattern="MMMM" var="transactionMonth" />
                    <fmt:formatDate value="${transaction.date}" pattern="yyyy" var="transactionYear" />
                    <select name="transactionDay" class="form-control" 
                            value="<c:out value="${transactionDay}"/>">
                        <c:forEach var="i" begin="1" end="31">
                            <option ${i == transactionDay ? 'selected' : ''}><c:out value="${i}">${i}</c:out></option>
                        </c:forEach>
                    </select>       
                    <select name="transactionMonth" class="form-control" 
                            value="<c:out value="${transactionMonth}"/>">
                        <c:forTokens items="January,February,March,April,May,June,July,August,September,October,November,December" delims="," var="month">
                            <option ${month == transactionMonth ? 'selected' : ''}><c:out value="${month}"/></option>
                        </c:forTokens>
                    </select>
                    <select name="transactionYear" class="form-control" 
                            value="<c:out value="${transactionYear}"/>">
                        <c:forEach var="i" begin="2015" end="2025">
                            <option ${i == transactionYear ? 'selected' : ''}><c:out value="${i}">${i}</c:out></option>
                        </c:forEach>
                    </select>  
                </div><br/><br/>
                <div class="form-group">
                    End date
                    <fmt:formatDate value="${transaction.endDate}" pattern="dd" var="transactionEndDay" />
                    <fmt:formatDate value="${transaction.endDate}" pattern="MMMM" var="transactionEndMonth" />
                    <fmt:formatDate value="${transaction.endDate}" pattern="yyyy" var="transactionEndYear" />
                    <select name="transactionEndDay" class="form-control" 
                            value="<c:out value="${transactionEndDay}"/>">
                        <c:forEach var="i" begin="1" end="31">
                            <option ${i == transactionEndDay ? 'selected' : ''}><c:out value="${i}">${i}</c:out></option>
                        </c:forEach>
                    </select>       
                    <select name="transactionEndMonth" class="form-control" 
                            value="<c:out value="${transactionEndMonth}"/>">
                        <c:forTokens items="January,February,March,April,May,June,July,August,September,October,November,December" delims="," var="month">
                            <option ${month == transactionEndMonth ? 'selected' : ''}><c:out value="${month}"/></option>
                        </c:forTokens>
                    </select>
                    <select name="transactionEndYear" class="form-control" 
                            value="<c:out value="${transactionEndYear}"/>">
                        <c:forEach var="i" begin="2015" end="2025">
                            <option ${i == transactionEndYear ? 'selected' : ''}><c:out value="${i}">${i}</c:out></option>
                        </c:forEach>
                    </select>  
                </div><br/><br/>
                <div class="form-group">
                    Label
                    <input class="form-control" type="txtLabel" name="transactionLabel" placeholder="Name" 
                           value="<c:out value="${transaction.label}"/>"/>
                </div><br/><br/>
                <div class="form-group">
                    Amount
                    <input class="form-control" type="txtLabel" name="transactionAmount" 
                           value="<c:out value="${transaction.amount}"/>"/>
                </div><br/><br/>
                <div class="form-group">
                    Category
                    <select class="form-control" name="categoryLabel"
                            value="<c:out value="${category}"/>">
                        <c:forEach items="${categoriesList}" var="category">
                            <option ${category == transaction.idCategory.label ? 'selected' : ''}>
                                <c:out value="${category}">${category.id}</c:out>
                                </option>
                        </c:forEach>
                    </select>
                </div><br/><br/>
                <input class="btn btn-primary" type="submit" name="yesBtn" value="Modify"/>
                <input class="btn btn-primary btn-xs" type="button" name="noBtn" value="Cancel"/>
            </form>
        </section>
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="bootstrap/dist/js/bootstrap.min.js"></script>
    </body>
</html>