<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<!--   PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">-->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- Angabe der 'StyleSheet'-Datei die hier verwendet wird -->
<link rel="stylesheet" href="../style/creator.css">
<link rel="stylesheet" href="../style/jquery.datetimepicker.css">
<link href='http://fonts.googleapis.com/css?family=Muli:400,300'
	rel='stylesheet' type='text/css'>
<script type="text/javascript" src="../js/handler.js"></script>
<script type="text/javascript" src="../js/jquery-2.1.1.js"></script>
<script type="text/javascript" src="../js/jquery.datetimepicker.js"></script>

<title>Festival Planner: Party bearbeiten</title>
</head>

<body>
	<div id="header">
		<center>
			<div id="logobox">Neue Party</div>
		</center>

	</div>
	<div id="content">
		<div id="whitewrapper">
			<form method="post" id="eventdata"
				action="<c:url value="/event/save" />">
				<div id=leftside>
					<h1>Allgemeines:</h1>
					<div id="labels">
						<input type="hidden"  name="id" id="id" value="${event.id}"> <label
							for="title">Name der Veranstaltung:</label> <br> <label
							for="date">Datum:</label> <br> <label for="time">Uhrzeit:</label>
						<br> <label for="veranstalter">Name:</label> <br> <label
							for="address">Adresse:</label> <br> <label for="plz">Postleitzahl:</label>
						<br> <label for="city">Ort:</label> <br> <label
							for="beschreibung">Beschreibung:</label> <br>
					</div>
					<div id="inputs">
						<input type="text" name="title" id="title" value="${event.title}"> <br> <input
							type="text" name="date" id="date" value="${event.date}" placeholder="dd.mm.yyyy"
							onfocus="pickDate()"> <br> <input type="text"
							name="time" id="time" placeholder="hh:mm" value="${event.time}" onfocus="pickTime()"> <br>

						<input type="text" name="veranstalter" id="veranstalter" value="${event.veranstalter}"> <br> <input
							type="text" name="address" id="address" value="${event.address}"> <br>
						<!--  <label for="hausnummer">Hausnummer:</label>
                <input type="text" id="hausnummer">
                
                <br>-->

						<input type="text" name="plz" id="plz" value="${event.plz}"> <br> 
						<input type="text" name="city" id="city" value="${event.city}"> <br>
						<textarea id="beschreibung">Füge hier einen Beschreibungstext für deine Gäste ein!</textarea>
						<br>
						<c:if test="${error}">
							<div class="errordiv infodiv" id="editerror">
								Bitte fülle alle Felder aus!</br>
							</div>
						</c:if>
						<input type="submit" value="Abbrechen"
							onclick="javascript:window.close()"> <input type="submit"
							value="Los geht's" onsubmit="sendEdit()">
							<div id="aftersend"></div>
					</div>
				</div>

				<div id="rightside">
					<h1>Party Homepage:</h1>
					Wähle ein passendes Design für deine Veranstaltungsseite: <br>
					<label for="design1">Design 1:</label> <input type="radio"
						checked="checked" id="design1" onchange="changedesign(this)"
						name="designw"> <br> <label for="design2">Design
						2:</label> <input type="radio" id="design2" onchange="changedesign(this)"
						name="designw"> <br> <label for="design3">Design
						3:</label> <input type="radio" id="design3" onchange="changedesign(this)"
						name="designw"> <br> <label for="design4">Design
						4:</label> <input type="radio" id="design4" onchange="changedesign(this)"
						name="designw"> <img src="../style/images/FrauBallons.jpg"
						id="bilddesign">

				</div>
			</form>
		</div>
	</div>
</body>
</html>
