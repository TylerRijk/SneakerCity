function checkLogin() {
    // JWT ophalen
    const jwt = sessionStorage.getItem('myJWT');

    // Controleer of er een JWT aanwezig is
    if (jwt) {
        fetch("/restservices/user/role", {
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + jwt
            },
            method: "GET"
        })
            .then(response => response.json())
            .then(roleJson => {
                // Kijken welke rol gebruiker heeft en doorsturen naar desbetreffende pagina
                if (roleJson.role === "admin") {
                    window.location.href = "../admin/account/account.html";
                } else if (roleJson.role === "klant") {
                    window.location.href = "../klant/account/account.html";
                } else {
                    console.log("Onbekende rol");
                }
            })
            .catch(error => console.log(error));
    } else {
        // Gebruiker is niet ingelogd, stuur door naar de inlogpagina
        window.location.href = "/login/login.html";
    }
}
