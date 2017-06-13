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
            <fmt:setLocale value="en_US" />
            <form class="form-inline" action="<c:url value="/modifyAccount">
                      <c:param name = "holderId" value="${param['holderId']}"/>
                      <c:param name = "accountId" value="${param['accountId']}"/>
                  </c:url>" method="post">
                <div class="form-group">
                    Number
                    <input class="form-control" type="txtLabel" name="accountNumber" 
                           value="<c:out value="${account.number}"/>"/>
                </div>
                <div class="form-group">
                    Creation date
                    <fmt:formatDate value="${account.creationDate}" pattern="dd" var="accountDay" />
                    <fmt:formatDate value="${account.creationDate}" pattern="MMMM" var="accountMonth" />
                    <fmt:formatDate value="${account.creationDate}" pattern="yyyy" var="accountYear" />
                    <select name="accountDay" class="form-control" 
                            value="<c:out value="${accountDay}"/>">
                        <c:forEach var="i" begin="1" end="31">
                            <option ${i == accountDay ? 'selected' : ''}><c:out value="${i}">${i}</c:out></option>
                        </c:forEach>
                    </select>       
                    <select name="accountMonth" class="form-control" 
                            value="<c:out value="${accountMonth}"/>">
                        <c:forTokens items="January,February,March,April,May,June,July,August,September,October,November,December" delims="," var="month">
                            <option ${month == accountMonth ? 'selected' : ''}><c:out value="${month}"/></option>
                        </c:forTokens>
                    </select>
                    <select name="accountYear" class="form-control" 
                            value="<c:out value="${accountYear}"/>">
                        <c:forEach var="i" begin="2015" end="2025">
                            <option ${i == accountYear ? 'selected' : ''}><c:out value="${i}">${i}</c:out></option>
                        </c:forEach>
                    </select>  
                </div><br/><br/>
                <div class="form-group">
                    First balance
                    <input class="form-control" type="txtLabel" name="accountFirstBalance" placeholder="Name" 
                           value="<c:out value="${account.firstBalance}"/>"/>
                </div><br/><br/>
                <div class="form-group">
                    Overdraft
                    <input class="form-control" type="txtLabel" name="accountOverdraft" placeholder="Name" 
                           value="<c:out value="${account.overdraft}"/>"/>
                </div><br/><br/>
                <div class="form-group">
                    Description
                    <input class="form-control" type="txtLabel" name="accountDescription" placeholder="Name" 
                           value="<c:out value="${account.description}"/>"/>
                </div><br/><br/>
                <div class="form-group">
                    Interest rate
                    <input class="form-control" type="txtLabel" name="accountInterestRate" placeholder="Name" 
                           value="<c:out value="${account.interestRate}"/>"/>
                </div><br/><br/>


                <div class="form-group">
                    Agency name
                    <input class="form-control" type="txtLabel" name="agencyName" 
                           value="<c:out value="${agency.name}"/>"/>
                </div><br/><br/>
                <div class="form-group">
                    Agency code
                    <input class="form-control" type="txtLabel" name="agencyCode" 
                           value="<c:out value="${agency.code}"/>"/>
                </div><br/><br/>


                <div class="form-group">
                    Address
                    <input class="form-control" type="txtLabel" name="addressLine1" 
                           value="<c:out value="${address.line1}"/>"/>
                    <input class="form-control" type="txtLabel" name="addressLine2" 
                           value="<c:out value="${address.line2}"/>"/>
                </div><br/><br/>
                <div class="form-group">
                    Postcode
                    <input class="form-control" type="txtLabel" name="postcodePostcode" 
                           value="<c:out value="${postcode.postcode}"/>"/>
                </div><br/><br/>
                <div class="form-group">
                    City
                    <input class="form-control" type="txtLabel" name="postcodeCity" 
                           value="<c:out value="${postcode.city}"/>"/>
                </div><br/><br/>
                
                
                <div class="form-group">
                    Bank name
                    <input class="form-control" type="txtLabel" name="bankName" 
                           value="<c:out value="${bank.name}"/>"/>
                </div><br/><br/>
                <div class="form-group">
                    Bank code
                    <input class="form-control" type="txtLabel" name="bankCode" 
                           value="<c:out value="${bank.code}"/>"/>
                </div><br/><br/>
                
                
                <div class="form-group">
                    Account type
                    <input class="form-control" type="txtLabel" name="accounttypeType" 
                           value="<c:out value="${accountType.type}"/>"/>
                </div><br/><br/>
                
                <div class="form-group">
                    Country code
                    <input class="form-control" type="txtLabel" name="countrycodeCode" 
                           value="<c:out value="${countryCode.code}"/>"/>
                </div><br/><br/>
                
                <a class="btn btn-primary btn-xs" href='<c:url value="/accountDisplay">
                       <c:param name="holderId" value="${param['holderId']}"/>
                   </c:url>' title="Back to home page">
                    Cancel</a>
                <input class="btn btn-primary" type="submit" name="yesBtn" value="Modify"/>
            </form>
        </section>
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="bootstrap/dist/js/bootstrap.min.js"></script>
    </body>
</html>