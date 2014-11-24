<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE hmtl>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Festival Planner</title>
    <meta name="firstPage" content="Die Startseite des FestivalPlanners der dualen Hochschule Karlsruhe.">
    <link rel="stylesheet" href="<c:url value="style/main.css"></c:url>" >
    <link href='http://fonts.googleapis.com/css?family=Muli:400,300' rel='stylesheet' type='text/css'>
    <script type="text/javascript" src='<c:url value="js/handler.js"></c:url>'></script>
    </head>

<body>
    <div id="logo">

        <div class="whitebox" id="logowhite">
            <h1 >FESTIVAL PLANNER</h1>
            </br>
            <p>Du willst eine Veranstaltung planen und die Organisation wächst dir über den Kopf?</br>
                </br>
                Du willst deine Gäste mit einer individuellen Seite zu deiner Party begeistern?</br>
                </br>
                Du willst den Überblick über dein Budget behalten und deine Gäste über Essen, Motto oder Deko abstimmen lassen?</br>
                </br>

                Dann bist du bei Festival Planner an der richtigen Stelle!</p>
        </div>

    </div>


    <div id="login">
        <div class="whitebox" id="loginwhite">
            <div class="tab actualtab" onclick="showlogin(this)">
                Login
       </div>
            <div class="tab" onclick="showregister(this)">Register
       </div>
            <br><br><br><br><br>
            <div id="loginform" >
                <!--<form method="post" action="login.php">  -->
                <form method="post" action="login">
                    <input name="username" type="text" placeholder="Benutzername">
                    <br>
                    <input name="password" type="password" placeholder="Passwort">
                    <br>
                    <br>
                    <!-- meins -->
                    <c:if test="${error}">
                    	<div class="errordiv" id="loginerror" >Benutzername und/oder Passwort falsch. Bitte versuche es erneut.</br></div>
                    </c:if>
                    <input type="submit" value="Einloggen" >
                </form>
                <form method="post" action="lostpassword.php">
                    <input type="submit" value="Passwort vergessen?">
                </form>
            </div>
            <div id="registerform">
                <!-- form method="post" action="register.php" -->
                <form method="post" action="register">
                    <input name="email" type="text" placeholder="E-Mail">
                    <br>
                    <input name="username" type="text" placeholder="Benutzername">
                    <br>
                    <input name="password" type="password" placeholder="Passwort">
                    <br>
                    <br>
                    <div class="errordiv" id="userexists" hidden="hidden">Nutzer existiert bereits! Bitte wähle einen anderen Namen!</div>
                    <div class="errordiv" id="emailvalidation" hidden="hidden">Bitte gebe eine gültige Emailadresse ein!</div>
                    <input type="submit" value="Registrieren">
                </form>
            </div>
        </div>
    </div>
    <a id="ImpressumLink" href="">Impressum</a>
</body>

</html>