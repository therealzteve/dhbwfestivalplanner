var guestList;

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
		bilddesign.src = "../style/images/GraffittiJunge.jpg";
	} else if (element.id == "design5") {
		bilddesign.src = "../style/images/Hochzeit.jpg";
	} else if (element.id == "design6") {
		bilddesign.src = "../style/images/Skyline.jpg";
	} else if (element.id == "design7") {
		bilddesign.src = "../style/images/abendszene.jpg";
	} else {
		alert("Kein Bild vorhanden");
	}
}
/* ab hier von yvonne */
function fetchguests(id) {
	var itemsZ = "";
	var itemsV = "";
	$.getJSON("event/display?id=" + id, function(data) {

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
		// document.getElementById("zzgaeste").innerHTML = itemsZ;
		// document.getElementById("vzgaeste").innerHTML = itemsV;

		$("#teiln").attr("href", "guestList/show?id=" + id);
	});

}

function sendEdit(url) {
	$
			.post(url, $("#eventdata").serialize())
			.done(
					function() {
						document.getElementById("aftersend").innerHTML = "&Auml;nderungen erfolgreich &uuml;bertragen! Bitte schlie&szlig;e das Fenster."
								+ "<br> <input type='button' value='Fenster schlie&szlig;en' onclick='javascript:window.close()'>";
					})
			.fail(
					function() {
						document.getElementById("aftersend").innerHTML = "Es ist ein Fehler aufgetreten!";
					});

}

function pickDate() {
	$('#date').datetimepicker(
			{
				lang : 'de',
				i18n : {
					de : {
						months : [ 'Januar', 'Februar', 'März', 'April', 'Mai',
								'Juni', 'Juli', 'August', 'September',
								'Oktober', 'November', 'Dezember', ],
						dayOfWeek : [ "So.", "Mo", "Di", "Mi", "Do", "Fr",
								"Sa.", ]
					}
				},
				timepicker : false,
				format : 'd.m.Y'
			});
}

function pickTime() {
	$('#time').datetimepicker({
		datepicker : false,
		format : 'H:i'
	});
}

function validateEmail(id) {
	var email = document.getElementById(id);

	var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	if (!filter.test(email.value)) {
		alert('Bitte gib eine gültige Emailadresse ein!');
		// email.focus;
		return false;
	}
	return true;
}

function validateName(id) {
	var name = document.getElementById(id);
	if (name.value == null || name.value == "") {
		alert('Bitte gib einen Namen ein!');
		return false;
	}
	return true;
}

function isDuplicate(gastname, email) {
	for (var i = 0; i < guestList.guestList.items.length; i++) {
		if (guestList.guestList.items[i].name == gastname) {
			if (guestList.guestList.items[i].email == email) {
				alert("Du hast diesen Gast bereits eingeladen!")
				return true;
				break;
			}

		}

	}
	return false;
}

function addGuest() {

	if (validateName('gastname') && validateEmail('emailgast')
			&& !isDuplicate($("#gastname").val(), $("#emailgast").val())) {

		var guest = {};
		guest.name = $("#gastname").val();
		guest.email = $("#emailgast").val();

		guestList.guestList.items.push(guest);

		var node = document.createElement("li"); // Create a <li> node
		var button = document.createElement("button");
		button.setAttribute("onclick", "removeGuest(event)");
		node.className = "dummy";
		var textnode = document.createTextNode(gastname.value + ", "
				+ emailgast.value); // Create a text node
		var buttontext = document.createTextNode("Entfernen");
		node.appendChild(textnode);
		button.appendChild(buttontext);
		node.appendChild(button);
		document.getElementById("zgaestea").appendChild(node); // Append <li>
																// to <ul> with
																// id="myList"
	}

}

function removeGuest(event) {
	var node = event.target.parentNode;
	var text = node.innerHTML;
	// console.log(text);
	var name = text.substr(0, text.indexOf(','));
	// console.log(name);
	var offset = text.indexOf(',') + 2;
	var email = text.substr(offset, text.indexOf('<') - offset);
	// console.log(email);
	for (var i = 0; i < guestList.guestList.items.length; i++) {
		if (guestList.guestList.items[i].name == name) {
			if (guestList.guestList.items[i].email == email) {
				guestList.guestList.items.splice(i, 1);
				break;
			}
		}
	}
	// console.log(guestList);
	document.getElementById("zgaestea").removeChild(node);
}

saveGuests = function() {
	// guestList +="]}";

	// var guestList = {};
	guestList.id = $("#eventid").val();
	alert($.toDictionary(guestList)+"Gäste wurden Gespeichert!");

	$.ajax({
		url : "/DhbwFestivalPlanner/guestList/save",
		type : "POST",
		data : JSON.stringify(guestList),
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function() {
		}
	});

}

$(document).ready(function() {
	if ($.datetimepicker) {
		pickDate();
		pickTime();
	}

	guestList = {};
	guestList.guestList = {};
	guestList.guestList.items = [];
});
