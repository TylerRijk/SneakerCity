// Deze functie wordt uitgevoerd wanneer er op de button wordt geklikt om te registreren
function gebruikerRegister() {
    // Ophalen invoervelden
    let username = document.getElementById('username').value;
    let email = document.getElementById('email').value;
    let password = document.getElementById('password').value;
    let passwordRepeat = document.getElementById('password-repeat').value;

    // Controleren of alle velden zijn ingevuld
    if (!username || !email || !password || !passwordRepeat) {
        document.getElementById('errorMessage').textContent = 'Vul alle velden in!';
        return;
    }

    // Controleren of password overeenkomt met elkaar
    if (password !== passwordRepeat) {
        document.getElementById('errorMessage').textContent = 'Wachtwoord komt niet overeen';
        return;
    }

    // Body aanmaken met gebruikersgegevens
    const jsonRequestBody = {
        'username': username,
        'email': email,
        'password': password
    };

    //console.log(jsonRequestBody);

    // Maak een POST verzoek naar de server om gebruiker te registreren
    fetch("/restservices/user/register", {
        headers: {"Content-Type": "application/json"},
        method: "POST",
        body: JSON.stringify(jsonRequestBody)
    })
        .then(function (response) {
            // Als gebruiker aangemaakt is, wordt registreerGebruiker aangeroepen om gelijk in te loggen
            if (response.ok) {
                registreerGebruiker(username, password);
            } else {
                document.querySelector('#errorMessage').innerHTML = "Gebruikersnaam bestaat al";
                console.log("error")
            }
        })
        .catch(error => console.log(error));
}

document.querySelector("#registreerButton").addEventListener("click", gebruikerRegister);

// Deze functie wordt aangeroepen nadat de gebruiker is geregistreerd
function registreerGebruiker(username, password) {
    // Body maken met username en password
    const jsonRequestBody = {'username': username, 'password': password};

    // POST verzoek maken om gebruiker in te laten loggen
    fetch("/restservices/authentication/login", {
        headers: {"Content-Type": "application/json"},
        method: "POST",
        body: JSON.stringify(jsonRequestBody)
    })
        .then(function(response) {
            if (response.ok) {
                return response.json();
            } else {
                document.querySelector('#errorMessage').innerHTML = "Gebruikersnaam of wachtwoord incorrect!";
                console.log("error")
            }
        })
        // JWT opslaan in sessionStorage van de browser
        .then(myJson => {
            window.sessionStorage.setItem("myJWT", myJson.JWT);
            window.sessionStorage.setItem("loggedInUser", "true")

            // GET verzoek maken om de rol op te halen van gebruiker
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
                        window.location.href = "../admin/account/account.html";
                    } else if (roleJson.role === "klant") {
                        window.location.href = "../klant/account/account.html";
                    } else {
                        console.log("Onbekende rol");
                    }
                });
        })
        .catch(error => console.log(error));
}