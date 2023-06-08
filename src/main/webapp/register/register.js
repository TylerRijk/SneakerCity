function gebruikerRegister() {
    let username = document.getElementById('username').value;
    let email = document.getElementById('email').value;
    let password = document.getElementById('password').value;
    let passwordRepeat = document.getElementById('password-repeat').value;

    if (!username || !email || !password || !passwordRepeat) {
        document.getElementById('errorMessage').textContent = 'Vul alle velden in!';
        return;
    }

    if (password !== passwordRepeat) {
        document.getElementById('errorMessage').textContent = 'Wachtwoord komt niet overeen';
        return;
    }

    const jsonRequestBody = {
        'username': username,
        'email': email,
        'password': password
    };

    //console.log(jsonRequestBody);

    fetch("/restservices/user/register", {
        headers: {"Content-Type": "application/json"},
        method: "POST",
        body: JSON.stringify(jsonRequestBody)
    })
        .then(function (response) {
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

function registreerGebruiker(username, password) {
    const jsonRequestBody = {'username': username, 'password': password};

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
        .then(myJson => {
            window.sessionStorage.setItem("myJWT", myJson.JWT);
            window.sessionStorage.setItem("loggedInUser", "true")

            fetch("/restservices/user/role", {
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": "Bearer " + myJson.JWT
                },
                method: "GET"
            })
                .then(response => response.json())
                .then(roleJson => {
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