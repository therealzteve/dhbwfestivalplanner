<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" href="../style/message.css">
</head>

<body>


<div id="contenta">
<div id="whitya">
    <h1>Nachricht</h1>
<br>
    <form id="formular" method="post" action="send">
        <div id="labl">
        <input type="hidden" name="id" value="${eventid}"/>
        <!--     <label name="empfaenger" for="empfanger"> Empf&aaml;nger: </label><br>
        <br>--> 
            <label name="nachrichht" for="nachricht"> Nachricht: </label><br>
    <br>    <br><br><br><br><br><br><br><br> <br> <br>
            <label name="schicken" for="abschicken">  </label><br>
        <br>
        </div>
        <div id="inpt">
           <!--   <input type="text" id="empfanger"></input>-->

       
        <input name="message" type="text" id="nachricht"> </input>
        <br>
        <br>
    </div>
<br><br>

        <input type="submit" value="senden" id="abschicken" class="testmal">

    </form>
</div>
</div>

</body>

</html>
