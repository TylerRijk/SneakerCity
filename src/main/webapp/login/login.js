function gebruikerLogin() {
    let username = document.getElementById('username').value;
    let password = document.getElementById('password').value;

    if (!username || !password) {
        document.querySelector('#errorMessage').innerHTML = "Vul zowel gebruikersnaam en wachtwoord in!";
        return;
    }
    const jsonRequestBody = {'username': username, 'password': password};

    //console.log(jsonRequestBody);

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
        .then(myJson => {
            window.sessionStorage.setItem("myJWT", myJson.JWT)

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
