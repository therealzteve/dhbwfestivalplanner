<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <!-- Angabe der 'StyleSheet'-Datei die hier verwendet wird -->
    <link rel="stylesheet" href="../style/creator.css">
     <link href='http://fonts.googleapis.com/css?family=Muli:400,300' rel='stylesheet' type='text/css'>

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
            <form method="post" id="eventdata">
                <label for="name">Name der Veranstaltung:</label>
                <input type="text" id="name">
                <br>
                <label for="datum">Datum:</label>
                <input type="date" id="date">
                <br>
                <label for="hour">Uhrzeit:</label>
                <input type="time" id="hour">
                <br>
                <label>Adresse:</label>
                <br>
                <label for="anschrift">Name:</label>
                <input type="text" id="anschrift">
                <br>
                <label for="strasse">Straße:</label>
                <input type="text" id="strasse">
                <br>
                <label for="hausnummer">Hausnummer:</label>
                <input type="text" id="hausnummer">
                <br>
                <label for="ort">Ort:</label>
                <input type="text" id="ort">
                <br>
                <label for="plz">Postleitzahl:</label>
                <input type="text" id="plz">
                <br>
                <input type="submit" value="Abbrechen">
                <input type="submit" value="Los geht's">
            </form>
        </div>
    </div>
</body>

</html>
