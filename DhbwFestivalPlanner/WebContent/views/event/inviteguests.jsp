<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<!-- das hier soll ein kleines popup fenster werden/-->

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Festival Planner</title>
    <meta name="eventpage" content="Die individuelle Veranstaltungsseite einer Party.">
    <link rel="stylesheet" href='<c:url value="/style/inviteguests.css"></c:url>'>
    <link href="../style/fonts/fontAwesome/css/font-awesome.min.css" rel='stylesheet'>
    <link href='http://fonts.googleapis.com/css?family=Muli:400,300' rel='stylesheet' type='text/css'>
    
     <script type="text/javascript" src='<c:url value="/js/jquery-2.2.1.js"></c:url>'></script>
     <script type="text/javascript" src='<c:url value="/js/handler.js"></c:url>'></script>
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
            <div id="left">
                <div id="gaestelistea" class="gaestela">

                    <ul id="zgaestea"></ul>
                </div>
            </div>
            <div id="right">
                <form id="namemaileingeben"  onsubmit="return addGuest()">
                    <div id="labelseingaben">
                        <label for="gastname">Name:</label>
                        <br><br>
                        <label for="emailgast">Email:</label>
                        <br><br>
                        <input type="checkbox" id="einladenjanein">
                        <br><br>
                    </div>
                    <div id="eingaben">
                        <input type="text" id="gastname">
                        <br><br>
                        <input id="emailgast">
                        <br><br>
                        <label for="emailgast" id="checkboxschrift">Gast soll einen Link auf die Einladungsseite bekommen</label>
                        <br><br>
                    </div>
                    <div id="absenden">
                    <input type="submit" id="senden" value="hinzufügen"><br>
                    </div>
                </form>
                <div id="platzhalterjo"></div>
                <div id="sendforumlar">
                <input class="testmal" type="submit" value="Speichern und Senden">
                </div>
            </div>
        </div>
    </div>
</body>

</html>
