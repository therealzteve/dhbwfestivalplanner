<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="<c:url value="/style/mainpage.css" />" >
    <link href='http://fonts.googleapis.com/css?family=Muli:400,300' rel='stylesheet' type='text/css'>
    <link href="<c:url value="/style/fonts/fontAwesome/css/font-awesome.min.css" />" rel='stylesheet'>
     <script type="text/javascript" src='<c:url value="js/handler.js"></c:url>'></script>
         <script type="text/javascript" src='<c:url value="js/jquery-2.1.1.js"></c:url>'></script>
<title>	<tiles:insertAttribute name="pageTitle"></tiles:insertAttribute></title>
</head>
<body>
    <div id="header">
        <a class="icons" href="#" id="postfachbutton">g</a>
        <a class="icons" href="<c:url value="/logout"></c:url>" id="ausloggenbutton">L</a>
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
