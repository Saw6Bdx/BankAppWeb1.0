<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
    <html>

    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            html {
                font-family: Verdana, Tahoma, Sans Serif, Courrier New;
                font-size: 14px;
            }
            
            .header {
                font-size: 24px;
                border: solid 1px #CCEEDD;
                height: 100px;
                text-align: center;
            }
            
            article>header {
                border: none;
                font-size: 18px;
                color: white;
                background-color: black;
            }
            
            header>img {
                display: inline-block;
                height: 50px;
                margin: 20px 0 5px 5px;
            }
            
            header>h2 {
                display: inline-block;
                vertical-align: middle;
            }
            
            nav {
                width: 800px;
                text-align: center;
                margin: 10px auto;
            }
            
            nav a {
                margin: 0 10px;
                color: rgb(45, 128, 135);
            }
            
            a:link,
            a:visited,
            a:active {
                text-decoration: none;
            }
            
            table {
                margin: 0 auto;
                border-collapse: collapse;
            }
            
            td,
            th {
                border-width: 1px;
                border-color: #000000;
                border-style: solid;
                text-align: center;
                padding: 5px;
            }
            
            td img {
                width: 30px;
            }
            
            .noborder {
                border: none !important;
            }
            
            .table-bordered {
                border: none;
            }

        </style>
        <meta charset="utf-8">
        <title>BankApp - Accounts</title>
    </head>

    <body>
        <header id="banniere" class="header">
            <img src="img/bank.jpg" alt="..." />
            <h2>BankApp</h2>
        </header>
        <nav>
            <a href="index.jsp" title="...">Home</a>
            <a href="accounts.jsp" title="...">Transactions List</a>
            <a href="form.jsp" title="...">New Transaction</a>
        </nav>
        <section>
            <table class="table table-bordered table-hover">
                <tr>
                    <th>Account</th>
                    <th>Balance</th>
                    <th colspan="3">Actions</th>
                </tr>
                <?php
                $query = 'SELECT account.id, account.number AS number, account.firstBalance+IFNULL(SUM(amount),0) AS solde FROM account INNER JOIN transactions ON account.id=transactions.idAccount GROUP BY account.number, account.firstBalance, account.id';
                $SQLResultat = mysqli_query($bdd, $query);
                $somme = 0.00;

                while($SQLRow = mysqli_fetch_assoc($SQLResultat)){
                    print('<tr><td>');
                    print($SQLRow['number']);
                    print('</td><td>');
                    print($SQLRow['solde']);
                    print('</td><td><a href="infos.php?id='.$SQLRow['id'].'" title="Détails du compte"><img src="img/eye.png"/></a></td>
                        <td><a href="transactions.php?id='.$SQLRow['id'].'" title="Voir les transactions"><img src="img/lines.jpg"/></a></td>
                        <td><a href="..."><img src="img/RIP.jpg"/></a></td></tr>');
                    $somme += $SQLRow['solde'];
                }
                ?>
                    <tr>
                        <td class="noborder"></td>
                        <td>
                            ${somme}<?php print($somme);?>
                        </td>
                    </tr>
            </table>;

        </section>
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="bootstrap/dist/js/bootstrap.min.js"></script>
    </body>

    </html>
