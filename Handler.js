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
