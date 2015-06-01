<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<!-- das hier soll ein kleines popup fenster werden/-->

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Festival Planner</title>
    <meta name="eventpage" content="Die individuelle Veranstaltungsseite einer Party.">
    <link rel="stylesheet" href='../style/editprofil.css'>
    <link href="../style/fonts/fontAwesome/css/font-awesome.min.css" rel='stylesheet'>
    <link href='http://fonts.googleapis.com/css?family=Muli:400,300' rel='stylesheet' type='text/css'>
    <script type="text/javascript" src="../js/jquery-2.1.1.js"></script>
<script type="text/javascript" src="../js/jquery.datetimepicker.js"></script>
<script type="text/javascript" src="../js/handler.js"></script>
</head>

<body>
    <div id="headeri">
        <center>
            <div id="logoboxi">
                Festival Planner
            </div>
        </center>

    </div>

    <div id="contenti">
        <div id="whity">
            <center>
                <h1 id="editprofilheader">Edit Profil</h1>
            </center>
            <br>
            <br>
            <h1 id="changemailheader" class="changeheader">Email</h1>
            <div id="labelsi">
                <label for="changemail">Neue Email:</label>
                <br>
                <br>
                <h1 id="changepasswortheader" class="changeheader">Passwort</h1>
                <br>
                <label for="newpasswort">Neues Passwort:</label>
                <br>
                <label for="altespasswort">Altes Passwort:</label>
                <br>
                <br>
                <br>
                <h1 id="loeschenheader" class="changeheader">Entfernen</h1>
                <label for="profilloeschen">Möchtest du dein Profil löschen?</label>
            </div>
            <div id="changes">
                <form id="editemail">
                    <input type="email" id="changemail">
                    <input type="button" id="losmail" value="ändern" onclick="validateEmail('changemail')">
                    <br>
                    <br>
                </form>
                <form id="changepasswort">
                    <br>
                    <h1 id="placeholder01" class="changeheader">hidden</h1>
                    <input type="password" id="newpasswort">
                    <br>
                    <input type="password" id="altespasswort">
                    <input type="submit" id="lospasswort" value="ändern">
                    <br>
                    <br>
                </form>
                <form id="löschen">
                    <h1 id="placeholder02" class="changeheader">hidden</h1>
                    <br>
                    <input type="text" id="placeholdershiat" disabled>
                    <input type="submit" id="profilloeschen" value="löschen">
                </form>
            </div>
            </form>


        </div>
    </div>
</body>

</html>
