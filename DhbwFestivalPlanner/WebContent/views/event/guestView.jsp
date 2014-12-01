<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Festival Planner</title>
    <meta name="eventpage" content="Die individuelle Veranstaltungsseite einer Party.">
    <c:if test="${event.design==1}">
    <link rel="stylesheet" href='../style/pagedesign01.css'>
    <link href='http://fonts.googleapis.com/css?family=Muli:400,300' rel='stylesheet' type='text/css'></c:if>
        <c:if test="${event.design==2}">
    <link rel="stylesheet" href='../style/pagedesign02.css'>
    </c:if>
        <c:if test="${event.design==3}">
    <link rel="stylesheet" href='../style/pagedesign03.css'>
    </c:if>
    <script type="text/javascript" src="<c:url   />/js/Handler.js"></script>
             <script type="text/javascript" src='<c:url value="js/jquery-2.1.1.js"></c:url>'></script>
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
        <h1 id="titel">${event.title}</h1>

        <div id="leftside">
            ${event.date}, ${event.time}
            <br>${event.veranstalter}
            <br>${event.address}
            <br>${event.plz} ${event.city}
            <br>
            <br>

            <div id="beschreibung">
                ${event.beschreibung}
            </div>
        </div>
        <div id="rightside">
            <div id="guestl">
                <div id="gaestelist">

                    Zugesagt:
                    <ul id="zgaeste">

                    </ul>
                </div>
                <div id="vgaestelist">
                    Vielleicht:
                    <ul id="vgaeste">
                       
                    </ul>
                </div>
            </div>
            <div id="buttons">
                <a href="#" id="like" class="button">I like it!</a><br>
                <a href="#" id="zusage" class="button">Zusagen</a><br>
                <a href="#" id="absage" class="button">Absagen :( </a><br>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript">
$(document).ready(fetchguests);</script>
</html>
