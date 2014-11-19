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

/*ab hier von mir*/

function login() {
	var name='Test';
	var pw=1234;
	if (document.getElementsByName("username")[0].value==name){
		if(document.getElementsByName("password")[0].value==pw){
		window.alert("Login erfolgreich");}
	}
	else{
    document.getElementById("loginerror").removeAttribute("hidden");}

}
