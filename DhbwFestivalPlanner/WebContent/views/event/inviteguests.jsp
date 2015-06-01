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
    
     <script type="text/javascript" src='<c:url value="/js/jquery-2.1.1.js"></c:url>'></script>
     <script type="text/javascript" src='<c:url value="/js/jquery.datetimepicker.js"></c:url>'></script>
     <script type="text/javascript" src='<c:url value="/js/jquery.toDictionary.js"></c:url>'></script>
     <script type="text/javascript" src='<c:url value="/js/handler.js"></c:url>'></script>
</head>

<body>
	<tiles:insertTemplate   template="/views/style/template/mainFrame.jsp"></tiles:insertTemplate>
		

    <div id="contenta">
        <div id="whitya">
            <div id="left">
                <div id="gaestelistea" class="gaestela">

                    <ul id="zgaestea"></ul>
                </div>
            </div>
            <div id="right">
                    <div id="labelseingaben">
                        <label for="gastname">Name:</label>
                        <br><br>
                        <label for="emailgast">Email:</label>
                        <br><br>
                        <input type="checkbox" id="einladenjanein">
                        <br><br>
                    </div>
                    <div id="eingaben">
                    	<input type="hidden" id="eventid" value="${eventId}" />
                        <input type="text" id="gastname">
                        <br><br>
                        <input id="emailgast">
                        <br><br>
                        <label for="emailgast" id="checkboxschrift">Gast soll einen Link auf die Einladungsseite bekommen</label>
                        <br><br>
                    </div>
                    <div id="absenden">
                    <input type="button" id="senden" value="hinzuf&uuml;gen" onclick="addGuest()"><br>
                    </div>
                <div id="platzhalterjo"></div>
                <div id="sendforumlar">
                <input class="testmal" type="button" id="saveList" value="Speichern und Senden" onclick="saveGuests()">
                </div>
            </div>
        </div>
    </div>
</body>

</html>
