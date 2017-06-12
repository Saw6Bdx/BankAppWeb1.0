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
            <form class="form-inline" action="<c:url value="/modifyTransaction">
                      <c:param name = "transactionId" value="${param['transactionId']}"/>
                  </c:url>" method="post">
                <div class="form-group">
                    Date
                    <select name="transactionDay" class="form-control" 
                            value="<c:out value="${param['transactionDay']}"/>">
                        <c:forEach var="i" begin="1" end="31">
                            <c:choose>
                                <c:when test = "${i == param['transactionDay']}">
                                    <option selected="selected"><c:out value ="${i}">${i}</c:out></option>
                                </c:when>
                                <c:when test = "${i != param['transactionDay']}">
                                    <option><c:out value ="${i}">${i}</c:out></option>
                                </c:when>
                            </c:choose>
                        </c:forEach>
                    </select>       
                    <select name="transactionMonth" class="form-control" 
                            value="<c:out value="${param['transactionMonth']}"/>">
                        <c:forTokens items="January,February,March,April,May,June,July,August,September,October,November,December" delims="," var="month">
                            <c:choose>
                                <c:when test = "${month == param['transactionMonth']}">
                                    <option selected="selected"><c:out value="${month}"/></option>
                                </c:when>
                                <c:when test = "${month != param['transactionMonth']}">
                                    <option><c:out value="${month}"/></option>
                                </c:when>
                            </c:choose>
                        </c:forTokens>
                    </select>
                    <select name="transactionYear" class="form-control" 
                            value="<c:out value="${param['transactionYear']}"/>">
                        <c:forEach var="i" begin="2015" end="2025">
                            <c:choose>
                                <c:when test = "${i == param['transactionYear']}">
                                    <option selected="selected"><c:out value ="${i}">${i}</c:out></option>
                                </c:when>

                                <c:when test = "${i != param['transactionYear']}">
                                    <option><c:out value ="${i}">${i}</c:out></option>
                                </c:when>
                            </c:choose>
                        </c:forEach>
                    </select>  
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