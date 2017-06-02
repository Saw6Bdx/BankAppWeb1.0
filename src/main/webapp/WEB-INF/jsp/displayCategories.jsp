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
        <title>BankApp - Categories</title>
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
            <table class="table table-bordered table-hover">
                <tr>
                    <th>Category</th>
                    <th>Amount</th>
                    <th>Percent</th>
                </tr>
                <c:set var="pos" value="${0}"/>
                <c:set var="total" value="${0}"/>
                <c:forEach items="${categoriesList}" var="category">
                    <tr>
                        <td>
                            <c:out value="${category.label}"/>
                        </td>
                        <td>
                            <c:out value="${amount[pos]}"/> €
                            <c:set var="total" value="${total + amount[pos]}" />
                        </td>
                        <td>
                            <c:out value="${percentByCategories[pos]}"/>
                        </td>
                        <c:set var="pos" value="${pos + 1}" />
                    </tr>
                </c:forEach>
                <tr>
                    <td class="noborder"></td>
                    <td><fmt:formatNumber type = "number" maxFractionDigits = "2" value="${total}" /> €</td>
                </tr>
            </table>

            <a href='<c:url value="/displayCategories"/>' 
               title="Categories">  Categories</a>
        </section>

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="bootstrap/dist/js/bootstrap.min.js"></script>
    </body>
</html>