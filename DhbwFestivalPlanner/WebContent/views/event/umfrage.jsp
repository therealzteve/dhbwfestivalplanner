
<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" href='../style/umfrage.css'>
    <link href="../style/fonts/fontAwesome/css/font-awesome.min.css" rel='stylesheet'>
    <link href='http://fonts.googleapis.com/css?family=Muli:400,300' rel='stylesheet' type='text/css'>
    <script type="text/javascript" src="../js/jquery-2.1.1.js"></script>
    <script type="text/javascript" src="../js/highcharts.js"></script>
<script type="text/javascript" src="../js/jquery.datetimepicker.js"></script>
<script type="text/javascript" src="../js/handler.js"></script>
    <title> Umfrage Tool</title>
</head>

<body>
    <div id="headera">
         <a class="icons" href="<c:url value="/eventpage/message.jsp"></c:url>" target="_blank" id="postfachbutton">g</a>
        <a class="icons" href="<c:url value="/logout"></c:url>" id="ausloggenbutton">L</a>
        <a class="icons" href="<c:url value="/mainpage/EditProfil.jsp"></c:url>" id="einstellungsbutton">Y</a>
        <div class="icons" id="headerplatzhalter"></div>
        <center>

            <div id="logoboxa">
                Festival Planner
            </div>
        </center>

    </div>
<br>
    <div id="contenta">
            <div id="leftside">
                <h1>Deine Umfragen: </h1><br>
               <div id=gaesteliste class=gaestel>
                 <ul id="zgaeste">
                     <li class="dummy" id=hinzufugen> <i class="fa fa-plus fontawicons"></i> Neue Umfrage hinzufügen...
                        </li>
                        <li class="dummy"><a onclick="showChart">test2</a>
                        </li>
                   </ul></div>
            </div>
        <div id="rightside">
        <p> Hier sollen die Umfrageergebnisse angezeigt werden. </p><br>
        <p>Mit klick auf das jeweilige listenelement wird die Umfrage ausgewählt, die gezeigt werden soll</p>

        </div>
    </div>




</body>

</html>
