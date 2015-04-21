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

	if (element.id == "design1") {
		bilddesign.src = "../style/images/FrauBallons.jpg";
	} else if (element.id == "design2") {
		bilddesign.src = "../style/images/Hase.jpg";
	} else if (element.id == "design3") {
		bilddesign.src = "../style/images/Glaeser.jpg";
	} else if (element.id == "design4") {
		bilddesign.src = "../style/images/GraffittiJunge.jpg";} 
	else if (element.id == "design5") {
			bilddesign.src = "../style/images/Hochzeit.jpg";} 
	else if (element.id == "design6") {
				bilddesign.src = "../style/images/Skyline.jpg";} 
	else if (element.id == "design7") {
					bilddesign.src = "../style/images/abendszene.jpg";
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
		document.getElementById("zzgaeste").innerHTML = itemsZ;
		document.getElementById("vzgaeste").innerHTML = itemsV;
	});



}

function sendEdit(url){
	$.post( url,
		$( "#eventdata" ).serialize()
		)
	.done(function(){document.getElementById("aftersend").innerHTML = "&Auml;nderungen erfolgreich &uuml;bertragen! Bitte schlie&szlig;e das Fenster." +
			"<br> <input type='button' value='Fenster schlie&szlig;en' onclick='javascript:window.close()'>";})
	.fail(function() {
		document.getElementById("aftersend").innerHTML = "Es ist ein Fehler aufgetreten!";
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

function validateEmail(id) 
{//return false;
	var email = document.getElementById(id);
	console.log(email.value);
	//alert("defgtzhujkl");
    var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    if (!filter.test(email.value)) {
        alert('Please provide a valid email address');
        //email.focus;
        return false;
}}

$(document).ready(function(){pickDate();pickTime();})


