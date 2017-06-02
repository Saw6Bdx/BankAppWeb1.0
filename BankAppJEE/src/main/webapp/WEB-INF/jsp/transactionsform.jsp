<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="style.css"/>
        <style>
            html{
                font-family: Verdana, Tahoma, Sans Serif, Courrier New;
                font-size: 14px;
            }
            .header{
                font-size: 24px;
                border: solid 1px #CCEEDD;
                height: 100px;
                text-align: center;
            }
            article>header{
                border: none;
                font-size: 18px;
                color: white;
                background-color: black;
            }
            header>img{
                display: inline-block;
                width: 50px;
                margin: 20px 0 5px 5px;
            }
            header>h2{
                display: inline-block;
                vertical-align: middle;
            }
            nav{
                width: 800px;
                text-align: center;
                margin: 10px auto;
            }
            nav a{
                margin: 0 10px;
                color: rgb(45, 128, 135);
            }
            a:link, a:visited, a:active{
                text-decoration: none;
            }
            form{
                margin: 0 auto;
                border-collapse: collapse;
                border-width: 1px;
                border-color: #000000;
                border-style: solid;
                text-align: center;
                padding: 5px;
                width: 500px;
            }
            legend{
                text-align: center;
                width: 100%;
                margin: 0 0 5px 0;
            }
            .radio-inline input[type=radio] {
                position: absolute;
                margin-top: 4px;
                margin-left: 5px;
            }
            .checkbox-inline input[type=checkbox] {
                position: absolute;
                margin-top: 4px;
                margin-left: 5px;
            }

        </style>
        <meta charset="utf-8">
        <title>BankApp - New Transaction</title>
    </head>
    <body>
        <header id="banniere" class="header">
            <img class="image-rounded" src="img/bank.jpg" alt="..."/>
            <h2>BankApp</h2>
        </header>
        <nav>
            <a href="index.jsp" title="...">Home</a>
            <a href="accounts.jsp" title="...">Transactions List</a>
            <a href="form.jsp" title="...">New Transaction</a>
        </nav>
        <form class="form-inline" method="post" action="<c:url value="/accounts"/>">

            <legend>Transaction type</legend>
            <div class="form-group">
                <label class="radio-inline">Withdrawal<input type="radio" name="rdType" checked="checked" value="Débit"/></label>
                <label class="radio-inline">Income<input type="radio" name="rdType" value="Crédit"/></label>
                <label class="radio-inline">Transfer<input type="radio" name="rdType" disabled="disabled" value="Virement"/></label></div><br/><br/>
            <legend>Label</legend><div class="form-group">
            <input class="form-control" type="text" name="txtLabel" placeholder="Label" style="text-align: center"/></div><br/><br/>
            <div class="form-group">
                <legend>Description</legend>
                <textarea class="form-control" name="txtDescription" rows="4" cols="30" placeholder="Description (150 char. max.)" maxlength="150" style="text-align: center"></textarea></div><br/><br/>
            <div class="form-group">
                Account : <select class="form-control" name="cbAccount">
                <option value="0">Account...</option>
            	<c:forEach items="${accountList}" var="account">
			         <option><c:out value ="${account}">${account}</c:out></option>
			    </c:forEach>
                </select></div><br/><br/>
            <div class="form-group">Amount : <input class="form-control" type="number" name="nbAmount" step="0.01" placeholder="0.00" style="text-align: right"/> €</div><br/><br/>
            <div class="form-group">Date : <select name="cbJours" class="form-control">
                <option value="0" selected="selected">Day...</option>
                <c:forEach var="i" begin="1" end="31">
			         <option><c:out value ="${i}">${i}</c:out></option>
			    </c:forEach>
			    </select>                
                <select name="cbMois" class="form-control">
                    <option value="0" selected="selected">Month...</option>
                <c:forTokens items="January,February,March,April,May,June,July,August,September,October,November,December" delims="," var="month">
     				<option><c:out value="${month}"/></option>
				</c:forTokens>
                </select>
                <select name="cbAnnees" class="form-control">
                    <option value="0" selected="selected">Year...</option>
                <c:forEach var="i" begin="2010" end="2025">
			         <option><c:out value ="${i}">${i}</c:out></option>
			    </c:forEach>
			    </select></div><br/><br/>
            <fieldset><div class="form-group"><label class="checkbox-inline">Periodic<input type="checkbox" name="cbPeriodicity"/></label></div><br/>
                <div class="form-group"><label class="radio-inline">Monthly<input type="radio" name="rdPeriodUnit" checked="checked" value="Mensuel"/></label>
                    <label class="radio-inline">Weekly<input type="radio" name="rdPeriodUnit" value="Trimestriel"/></label>
                    <label class="radio-inline">Annual<input type="radio" name="rdPeriodUnit" value="Annuel"/></label></div></fieldset><br/>
            <input class="btn btn-primary" type="button" name="annuler" value="Apply"/>
            <input class="btn btn-primary btn-xs" type="button" name="annuler" value="Cancel"/>
        </form><br/><br/>
        
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="dist/js/bootstrap.min.js"></script>
    </body>
</html>