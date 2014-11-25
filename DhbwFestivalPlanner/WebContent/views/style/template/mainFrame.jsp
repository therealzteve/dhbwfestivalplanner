<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="../style/mainpage.css">
    <link href='http://fonts.googleapis.com/css?family=Muli:400,300' rel='stylesheet' type='text/css'>
    <link href="../style/fonts/fontAwesome/css/font-awesome.min.css" rel='stylesheet'>
<title>	<tiles:insertAttribute name="pageTitle"></tiles:insertAttribute></title>
</head>
<body>
    <div id="header">
        <a class="icons" href="#" id="postfachbutton">g</a>
        <a class="icons" href="#" id="ausloggenbutton">L</a>
        <a class="icons" href="#" id="einstellungsbutton">Y</a>
        <div class="icons" id="headerplatzhalter"></div>
        <center>
            <div id="logobox">
                Festival Planner
            </div>
        </center>

    </div>
	<tiles:insertAttribute name="content"></tiles:insertAttribute>    
</body>
</html>