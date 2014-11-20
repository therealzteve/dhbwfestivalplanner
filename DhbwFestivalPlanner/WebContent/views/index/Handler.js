function showlogin(element) {
    document.getElementsByClassName("actualtab")[0].classList.remove("actualtab");
    element.classList.add("actualtab");
    loginform.style.display = "block";
    registerform.style.display = "none";

}

function showregister(element) {
    document.getElementsByClassName("actualtab")[0].classList.remove("actualtab");
    element.classList.add("actualtab");
    loginform.style.display = "none";
    registerform.style.display = "block";
}

/*ab hier von yvonne*/

function login() {
	var name='Test';
	var pw=1234;
	if (document.getElementsByName("username")[0].value==name){
		if(document.getElementsByName("password")[0].value==pw){
		window.alert("Login erfolgreich!");}
	}
	else{
    document.getElementById("loginerror").removeAttribute("hidden");}

}

function register() {
	var name='Test';
	var pw=1234;
	if (document.getElementsByName("username")[1].value==name){
		if(document.getElementsByName("password")[0].value==pw){
			document.getElementById("userexists").removeAttribute("hidden");}
	}
	else{
	    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	    if (re.test(email)){
	    	window.alert("Eine Bestätigungsemail wurde versendet!");
	    }
	    else{
	    	document.getElementById("emailvalidation").removeAttribute("hidden");}
	    }
	}
    

