<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script type="text/javascript" src="includes/fonctions.js"></script>
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
        <title>BankApp - Infos</title>
    </head>
    <body>
        <header id="banniere" class="header">
            <img class="image-rounded" src="images/bank.jpg" alt="..."/>
            <h2>BankApp</h2>
        </header>
        <nav>
            <a href="index.html" title="...">Home</a>
            <a href="accounts.php" title="...">Transactions List</a>
            <a href="form.php" title="...">New Transaction</a>
        </nav>
        <form class="form-inline" method="post" action="page de traitement">

            <?php
            if(empty($_POST)){
                $id = $_GET['id'];
                $query = 'SELECT account.number, account.creationDate, account.firstBalance, account.overdraft, account.description, account.interestRate, agency.agencyName, countryCode.code, accountType.type FROM account INNER JOIN agency ON agency.id=account.idAgency INNER JOIN countryCode ON countryCode.id=account.idCountryCode INNER JOIN accountType ON accountType.id=account.idAccountType WHERE account.id='.$id;
                $SQLResultat = mysqli_query($bdd, $query);

                if($SQLRow = mysqli_fetch_assoc($SQLResultat)){
                    $number = $SQLRow['number'];
                    $creationDate = $SQLRow['creationDate'];
                    $firstBalance = $SQLRow['firstBalance'];
                    $overdraft = $SQLRow['overdraft'];
                    $description = $SQLRow['description'];
                    $interestRate = $SQLRow['interestRate'];
                    $agencyName = $SQLRow['agencyName'];
                    $type = $SQLRow['type'];
                    $code = $SQLRow['code'];
                }
                mysqli_fetch_assoc($SQLResultat);}
            /*else(){
                
            }*/
            ?>

            <legend>Account</legend>
            <div class="form-group">N° : <input class="form-control" type="text" name="txtLabel" value="<?php print($number);?>"/></div><br/><br/>
            <div class="form-group">Creation Date : <input class="form-control" type="text" name="txtLabel" value="<?php print($creationDate);?>"/></div><br/><br/>
            <div class="form-group">First Balance : <input class="form-control" type="text" name="txtLabel"value="<?php print($firstBalance);?>"/> €</div><br/><br/>
            <div class="form-group">Overdraft : <input class="form-control" type="text" name="txtLabel" value="<?php print($overdraft);?>"/> €</div><br/><br/>
            <div class="form-group">Description : <input class="form-control" type="text" name="txtLabel" value="<?php print($description);?>"/></div><br/><br/>
            <div class="form-group">Interest Rate (%) : <input class="form-control" type="text" name="txtLabel" value="<?php print($interestRate);?>"/></div><br/><br/>
            <div class="form-group">Agency : <input class="form-control" type="text" name="txtLabel" value="<?php print($agencyName);?>"/></div><br/><br/>
            <div class="form-group">Account Type : <input class="form-control" type="text" name="txtLabel" value="<?php print($type);?>"/></div><br/><br/>
            <div class="form-group">Country Code : <input class="form-control" type="text" name="txtLabel" value="<?php print($code);?>"/></div><br/><br/>

            <input class="btn btn-primary" type="button" name="annuler" value="Apply"/>
            <input class="btn btn-primary btn-xs" type="button" name="annuler" value="Cancel"/>
        </form><br/><br/>
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="bootstrap/dist/js/bootstrap.min.js"></script>
    </body>
</html>