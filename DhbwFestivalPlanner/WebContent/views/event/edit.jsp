<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <!-- Angabe der 'StyleSheet'-Datei die hier verwendet wird -->
    <link rel="stylesheet" href="../style/creator.css">
    <link rel="stylesheet" href="../style/jquery.datetimepicker.css">
     <link href='http://fonts.googleapis.com/css?family=Muli:400,300' rel='stylesheet' type='text/css'>
         <script type="text/javascript" src="../js/handler.js"></script>
         <script type="text/javascript" src="../js/jquery-2.1.1.js"></script>
         <script type="text/javascript" src="../js/jquery.datetimepicker.js"></script>

    <title>Festival Planner: Party bearbeiten</title>
</head>

<body>
    <div id="header">
        <center>
            <div id="logobox">
                Neue Party
            </div>
        </center>

    </div>
    <div id="content">
        <div id="whitewrapper">
        <div id=leftside>
            <form method="post" id="eventdata" action="<c:url value="/event/save" />">
            <input type="hidden" id="id" value="${event.id}">
                <label for="title">Name der Veranstaltung:</label>
                <input type="text" id="title">
                <br>
                <label for="date">Datum:</label>
                <input type="text" id="date" onfocus="pickDate()">
                <br>
                <label for="time">Uhrzeit:</label>
                <input type="text" id="time" onfocus="pickTime()">
                <br>
                <label>Adresse:</label>
                <br>
                <label for="address">Name:</label>
                <input type="text" id="address">
                <br>
                <label for="address">Adresse:</label>
                <input type="text" id="address">
                <br>
                <!--  <label for="hausnummer">Hausnummer:</label>
                <input type="text" id="hausnummer">
                
                <br>-->
                <label for="city">Ort:</label>
                <input type="text" id="city">
                <br>
                <label for="plz">Postleitzahl:</label>
                <input type="text" id="plz">
                <br>
                <input type="submit" value="Abbrechen">
                <input type="submit" value="Los geht's">
                </div>
                
                            <div id="rightside">
                <h1>Party Homepage: </h1>
                Wähle ein passendes Design für deine Veranstaltungsseite:
                <br>
                <label for="design1">Design 1:</label>
                <input type="radio" checked="checked" id="design1" onchange="changedesign(this)" name="designw">
                <br>
                <label for="design2">Design 2:</label>
                <input type="radio" id="design2" onchange="changedesign(this)" name="designw">
                <br>
                <label for="design3">Design 3:</label>
                <input type="radio" id="design3" onchange="changedesign(this)" name="designw">
                <br>
                <label for="design4">Design 4:</label>
                <input type="radio" id="design4" onchange="changedesign(this)" name="designw">
              


                    <img src="../style/images/FrauBallons.jpg" id="bilddesign">
                    
            </div>
           </form>
        </div>
    </div>
</body>
</html>
