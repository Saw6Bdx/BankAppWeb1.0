<%@page language="JAVA" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <form method="POST" action="j_security_check">
            <label>Login</label>
            <input type="text" name="j_username">
            <label>Mot de passe</label>
            <input type="password" name="j_password">
            <button type="submit">Login</button>
        </form>
    </body>
</html>
