function logout() {
    // Verwijder de JWT uit de sessieopslag
    sessionStorage.removeItem('myJWT');

    // Stuur de gebruiker door naar de login pagina
    window.location.href = "../../login/login.html";
}