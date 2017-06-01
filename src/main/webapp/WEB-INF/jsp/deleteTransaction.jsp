<%@page pageEncoding="UTF-8" isErrorPage="true" contentType="text/html" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
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
                height: 50px;
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
            table{
                margin: 0 auto;
                border-collapse: collapse;
            }
            td, th{
                border-width: 1px;
                border-color: #000000;
                border-style: solid;
                text-align: center;
                padding: 5px;
            }
            td img{
                width: 30px;
            }
            .noborder{
                border: none !important;
            }
            .table-bordered{border: none;}

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
            <a href="index.html" title="...">Home</a>
            <a href="accounts.php" title="...">Transactions List</a>
            <a href="form.php" title="...">New Transaction</a>
        </nav>
        <section>
            <form action="<c:url value="/deleteTransaction"><c:param name = "transactionId" value="${param['transactionId']}"/></c:url>" method="post">
                <label>Are you sure you want to delete this transaction <c:out value="${param['transactionLabel']}"/> ?</label>
                <input type="submit" name="yesBtn" value="Yes" />
                <input type="submit" name="noBtn" value="No" />
            </form>
        </section>
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="bootstrap/dist/js/bootstrap.min.js"></script>
    </body>
</html>