<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" href='../style/umfrageuser.css'>
    <link href="../style/fonts/fontAwesome/css/font-awesome.min.css" rel='stylesheet'>
    <link href='http://fonts.googleapis.com/css?family=Muli:400,300' rel='stylesheet' type='text/css'>

    <title> Umfrage erstellen</title>
</head>

<body>
    <div id="headera">
        <a class="iconsa" href="#" id="postfachbuttona">g</a>
        <a class="iconsa" href="#" id="ausloggenbuttona">L</a>
        <a class="iconsa" href="#" id="einstellungsbuttona">Y</a>
        <div class="iconsa" id="headerplatzhaltera"></div>
        <center>

            <div id="logoboxa">
                Festival Planner
       </div>
</center>

</div>
<div id="contenta">
    <div id="whitya">
        <center>
            <p>Hallo! DerGastgeber hat dich zu seiner Feier eingeladen und hat ein paar Fragen an dich.
     <br>Beantworte doch einfach die von ihm erstellten Umfragen </p>
</center><br>

<div class="frage">
    <form>Umfrage 1
        <p>Ist jemand Vegetarier?</p>

        <input type="radio" name="answers1">ja<br>
        <input type="radio" name="answers1">nein<br>
        <input type="button" value="abstimmen" onclick="alert('Vielen Dank für deine Stimme!')">
    </form>
</div>

<div class="frage">
    <form>Umfrage 2
        <p>Wann habt ihr Zeit?</p>
        <input type="radio" name="answers2">Freitag<br>
        <input type="radio" name="answers2">Samstag<br>
        <input type="radio" name="answers2">Sonntag<br>
    <input type="button" value="abstimmen" onclick="alert('Vielen Dank für deine Stimme!')">
    </form>
</div>

<div class="frage">
    <form>Umfrage 3
        <p>Wo wollen wir feiern?</p>
       <input type="radio" name="answers3">Zuhause<br>
        <input type="radio" name="answers3">Brauhaus<br>
        <input type="radio" name="answers3">Schlossgarten<br>
    <input type="button" value="abstimmen" onclick="alert('Vielen Dank für deine Stimme!')">
    </form>
</div>

<!--  <div class="frage">
    <form>Umfrage 4
        <p>Hier ist die erste Frage</p>
       <input type="radio" name="answers4">Antwort<br>
        <input type="radio" name="answers4">Antwoooort<br>
    <input type="button" value="abstimmen">
    </form>
</div>


<div class="frage">
    <form>Umfrage 5
        <p>Hier ist die erste Frage</p>
        <input type="radio" name="answers5">Antwooort<br>
        <input type="radio" name="answers5">Antwort<br>
    <input type="button" value="abstimmen">
    </form>
</div>-->



</div>
</div>
</body>

</html>