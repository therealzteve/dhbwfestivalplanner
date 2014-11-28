function showlogin(element) {
	document.getElementsByClassName("actualtab")[0].classList
			.remove("actualtab");
	element.classList.add("actualtab");
	loginform.style.display = "block";
	registerform.style.display = "none";

}

function showregister(element) {
	document.getElementsByClassName("actualtab")[0].classList
			.remove("actualtab");
	element.classList.add("actualtab");
	loginform.style.display = "none";
	registerform.style.display = "block";
}

function changedesign(element) {
	debugger;
	if (element.id == "design1") {
		bilddesign.src = "../style/images/FrauBallons.jpg";
	} else if (element.id == "design2") {
		bilddesign.src = "../style/images/Hase.jpg";
	} else if (element.id == "design3") {
		bilddesign.src = "../style/images/LustigeBrille.jpg";
	} else {
		alert("Kein Bild vorhanden");
	}
}
/* ab hier von yvonne */
function fetchguests(url) {
	var itemsZ = "";
	var itemsV = "";
	$.getJSON(url, function(data) {

		for (var i = 0; i < data.guests.length; i++) {
			if (data.guests[i].confirmed == true)
				itemsZ += ("<li id='guest" + data.guests[i].id + "'>"
						+ data.guests[i].name + "</li>");
			else
				itemsV += ("<li id='guest" + data.guests[i].id + "'>"
						+ data.guests[i].name + "</li>");// was ist mit den
															// abgesagten?
		}
		document.getElementById("zgaeste").innerHTML = itemsZ;
		document.getElementById("vgaeste").innerHTML = itemsV;
	});



}

function pickDate(){
	$('#date').datetimepicker({
	 lang:'de',
	 i18n:{
	  de:{
	   months:[
	    'Januar','Februar','März','April',
	    'Mai','Juni','Juli','August',
	    'September','Oktober','November','Dezember',
	   ],
	   dayOfWeek:[
	    "So.", "Mo", "Di", "Mi", 
	    "Do", "Fr", "Sa.",
	   ]
	  }
	 },
	 timepicker:false,
	 format:'d.m.Y'
	});}

function pickTime(){
$('#time').datetimepicker({
	  datepicker:false,
	  format:'H:i'
	});}

