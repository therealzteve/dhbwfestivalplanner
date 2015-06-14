<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel='stylesheet'>
    <link rel="stylesheet" href='../style/umfrageerst.css'>
    <!--  <link href="../style/fonts/fontAwesome/css/font-awesome.min.css" rel='stylesheet'>-->
    
    <link href='http://fonts.googleapis.com/css?family=Muli:400,300' rel='stylesheet' type='text/css'>
    <script type="text/javascript" src="../js/jquery-2.1.1.js"></script>
    <script type="text/javascript" src="../js/highcharts.js"></script>
<script type="text/javascript" src="../js/jquery.datetimepicker.js"></script>
<script type="text/javascript" src="../js/handler.js"></script>
    <title> Umfrage erstellen</title>
</head>
<body>
    <div id="headera">
         <!--  <a class="icons" href="<c:url value="/eventpage/message.jsp"></c:url>" target="_blank" id="postfachbutton">g</a>-->
        <a class="icons" href="<c:url value="/logout"></c:url>" id="ausloggenbutton">L</a>
        <a class="icons" href="<c:url value="/mainpage/EditProfil.jsp"></c:url>" id="einstellungsbutton">Y</a>
        <div class="icons" id="headerplatzhalter"></div>
        <center>

            <div id="logoboxa">
                Festival Planner
            </div>
        </center>
</div>
<div id="contenta">
<div id="whitya">
    <center><h1>Umfrage erstellen</h1></center>
    <br><br>
    <form>
    <p>Erstelle hier eine Umfrage, an der deine Gaste teilnehmen können. </p>
    <form id="antworten"><br><br>
        <label for="Frage" class="left">Gib hier deine Frage ein: </label>
        <input type="text" class="right"><br><br>
        <label for="antw" class="left">Antwort 1: </label>
        <input type="text" class="right"><br><br>
        <label for="antw" class="left">Antwort 2: </label>
        <input type="text" class="right"><br><br>
        <label for="antw" class="left">Antwort 3: </label>
        <input type="text" class="right"><br><br>
        <label for="antw" class="left">Antwort 4: </label>
        <input type="text" class="right"><br><br>
        <label for="antw" class="left">Antwort 5: </label>
        <input type="text" class="right"><br><br>

        <a class="icons" id="postfachbutton" onclick="addAnswer()"><i class="fa fa-plus fontawicons"></i> weitere Antwort hinzufügen</a>
       <br><br>
        <center>
        <a href="<c:url value="/survey/update"></c:url>"><input type="button" value="erstellen" class="testmal"></a>
        </center>


        </form>



    </div></div>

</body>
</html>