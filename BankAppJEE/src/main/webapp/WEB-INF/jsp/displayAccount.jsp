<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
        <title>BankApp - Accounts</title>
    </head>
    <body>
        <header id="banniere" class="header">
            <img src="img/bank.jpg" alt="..."/>
            <h2>BankApp</h2>
        </header>
        <nav>
            <a href='<c:url value="/holderDisplay"></c:url>'>Change user</a>
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