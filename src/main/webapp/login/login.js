function gebruikerLogin() {
    // Ophalen invoervelden
    let username = document.getElementById('username').value;
    let password = document.getElementById('password').value;

    // Controleren of alle velden zijn ingevuld
    if (!username || !password) {
        document.querySelector('#errorMessage').innerHTML = "Vul zowel gebruikersnaam en wachtwoord in!";
        return;
    }

    // Body aanmaken met gebruikersgegevens
    const jsonRequestBody = {'username': username, 'password': password};

    // POST verzoek maken naar de server om in te loggen
    fetch("/restservices/authentication/login", {
        headers: {"Content-Type": "application/json"},
        method: "POST",
        body: JSON.stringify(jsonRequestBody)
    })
        .then(function (response) {
            if (response.ok) {
                return response.json();
            } else {
                document.querySelector('#errorMessage').innerHTML = "Gebruikersnaam of wachtwoord incorrect!";
                console.log("error")
            }
        })
        // JWT opslaan in sessionStorage van de browser
        .then(myJson => {
            window.sessionStorage.setItem("myJWT", myJson.JWT)

            // GET verzoek maken om de rol op te halen van de gebruiker
            fetch("/restservices/user/role", {
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": "Bearer " + myJson.JWT
                },
                method: "GET"
            })
                .then(response => response.json())
                .then(roleJson => {
                    // Kijken welke rol gebruiker is en doorsturen naar desbetreffende pagina
                    if (roleJson.role === "admin") {
                        window.location.href = "../admin/account/account.html"
                    } else if (roleJson.role === "klant") {
                        window.location.href = "../klant/account/account.html"
                    } else {
                        console.log("Onbekende role");
                    }
                })
        })
        .catch(error => console.log(error));
}

document.querySelector("#login").addEventListener("click", gebruikerLogin);
