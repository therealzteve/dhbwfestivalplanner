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
        <link href='<c:url value="/style/fonts/fontAwesome/css/font-awesome.min.css"></c:url>' rel='stylesheet'>
        <link href='http://fonts.googleapis.com/css?family=Muli:400,300' rel='stylesheet' type='text/css'>
    <c:if test="${event.design==1}">
    <link rel="stylesheet" href='<c:url value="/style/pagedesign01.css"></c:url>'>
    </c:if>
        <c:if test="${event.design==2}">
    <link rel="stylesheet" href='<c:url value="/style/pagedesign02.css"></c:url>'>
    </c:if>
        <c:if test="${event.design==3}">
    <link rel="stylesheet" href='<c:url value="/style/pagedesign03.css"></c:url>'>
    </c:if>
        <c:if test="${event.design==4}">
    <link rel="stylesheet" href='<c:url value="/style/pagedesign04.css"></c:url>'>
    </c:if>
        <c:if test="${event.design==5}">
    <link rel="stylesheet" href='<c:url value="/style/pagedesign05.css"></c:url>'>
    </c:if>
        <c:if test="${event.design==6}">
    <link rel="stylesheet" href='<c:url value="/style/pagedesign06.css"></c:url>'>
    </c:if>
            <c:if test="${event.design==7}">
    <link rel="stylesheet" href='<c:url value="/style/pagedesign07.css"></c:url>'>
    </c:if>
                 <script type="text/javascript" src='<c:url value="/js/jquery-2.1.1.js"></c:url>'></script>
    <script type="text/javascript" src='<c:url value="/js/handler.js"></c:url>'></script>
<script type="text/javascript">
window.addEventListener('load',
	function(){fetchguests('<c:url value="event/display?id=${event.id}"></c:url>');}
	);
</script>
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


        <div id="leftside">
        <center>
                <h1 id="titel">${event.title}</h1>
                </center>
          ${event.date}, ${event.time} 
            <br>${event.name}
            <br>${event.address}
            <br>${event.plz} ${event.city}
            <br>
            <br>

            <div id="beschreibung">
                ${event.description}
            </div>
        </div>
        <div id="rightside">
            <div id="zguestl">
                <div id="zgaestelist">

                    Zugesagt:
                    <ul id="zzgaeste">

                    </ul>
                </div>
                <div id="vzgaestelist">
                    Vielleicht:
                    <ul id="vzgaeste">
                       
                    </ul>
                </div>
            </div>
            <div id="buttons">
                <br>
                <center> <a href="#" id="like" class="button"><i class="fa fa-heart-o"></i>
I like it!</a>
                    <br>15 Leute mögen das.
                    <br>
                                       <br><br>
                   <a href="/DhbwFestivalPlanner/survey" id="Umfrage" class="button" ><i class="fa fa-pie-chart"></i> Umfrage </a>
                    <br>
                    <br>
               <!--      <a href="<c:url value="/DhbwFestivalPlanner/message"></c:url>" target="_blank" id="mail" class="button"><i class="fa fa-envelope-o"></i>
Nachricht </a> -->
                    <div id="imbutton"><a href="#" id="zusage" class="button"><i class="fa fa-check"></i>Zusagen </a>
                        <br>
                        <br>
                        <a href="#" id="absage" class="button"><i class="fa fa-meh-o"></i> Absagen </a>
                        <br>
                </center>
                </div>
                <br>
            </div>
        </div>
    </div>
</body>

</html>
