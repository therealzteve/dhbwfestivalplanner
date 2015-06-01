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
	<tiles:insertTemplate   template="/views/style/template/mainFrame.jsp"></tiles:insertTemplate>
		
		<%-- Page Title: --%>
		<tiles:putAttribute name="pageTitle" value="Nachricht an Veranstalter"></tiles:putAttribute>

<div id="contenta">
<div id="whitya">
    <h1>Nachricht</h1>
<br>
    <form id="formular">
        <div id="labl">
            <label name="empfaenger" for="empfanger"> Empf&aaml;nger: </label><br>
        <br>
            <label name="nachrichht" for="nachricht"> Nachricht: </label><br>
    <br>    <br><br><br><br><br><br><br><br> <br> <br>
            <label name="schicken" for="abschicken">  </label><br>
        <br>
        </div>
        <div id="inpt">
            <input type="text" id="empfanger"></input>

        <br>
        <br>
        <input type="text" id="nachricht"> </input>
        <br>
        <br>
    </div>
<br><br>

        <input type="button" value="senden" id="abschicken" class="testmal" onlick="validateEmail('empfanger')">

    </form>
</div>
</div>

</body>

</html>
