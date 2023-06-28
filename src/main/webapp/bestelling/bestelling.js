const jwt = sessionStorage.getItem('myJWT');

// Controleren of er een JWT aanwezig is
if (!jwt) {
    window.location.href = "../login/login.html"
}

function getParameterByName(name, url) {
    // Als er geen url is gebruik de huidige pagina
    if (!url) {
        url = window.location.href;
    }

    // Speciale tekens vervangen
    name = name.replace(/[\[\]]/g, "\\$&");

    // Waardes matchen met parameterwaarde
    let regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
        results = regex.exec(url);
    // Als er geen resultaat is, null teruggeven
    if (!results) return null;
    // Als er geen parameters zijn, lege string teruggeven
    if (!results[2]) return "";

    // Decodeer waardes in parameter en geef deze terug
    return decodeURIComponent(results[2].replace(/\+/g, " "));
}

// Onderstaande wordt uitgevoerd als de pagina is geladen
window.onload = function () {
    // Details van een sneaker ophalen uit de query parameters in de url
    let sneakerDetails = {
        title: getParameterByName("title"),
        beschrijving: getParameterByName("beschrijving"),
        merk: getParameterByName("merk"),
        kleur: getParameterByName("kleur"),
        maat: getParameterByName("maat"),
        voorraad: getParameterByName("voorraad"),
        prijs: getParameterByName("prijs"),
        image: getParameterByName("image")
    };

    // Sneaker details op de pagina tonen
    document.getElementById("sneaker-title").innerText = sneakerDetails.title;
    document.getElementById("sneaker-beschrijving").innerText = sneakerDetails.beschrijving;
    document.getElementById("sneaker-merk").innerText = sneakerDetails.merk;
    document.getElementById("sneaker-kleur").innerText = sneakerDetails.kleur;
    document.getElementById("sneaker-maat").innerText = sneakerDetails.maat;
    document.getElementById("sneaker-voorraad").innerText = sneakerDetails.voorraad;
    document.getElementById("sneaker-prijs").innerText = sneakerDetails.prijs;
    document.getElementById("sneaker-image").src = sneakerDetails.image;
};

function buySneaker() {
    let voornaam = document.getElementById("voornaam").value;
    let achternaam = document.getElementById("achternaam").value;
    let email = document.getElementById("email").value;
    let adres = document.getElementById("adres").value;
    let postcode = document.getElementById("postcode").value;
    let woonplaats = document.getElementById("woonplaats").value;

    if (!voornaam || !achternaam || !email || !adres || !postcode || !woonplaats) {
        alert("Vul alle velden in om uw bestelling af te ronden")
        return;
    }

    // let artikelnummerText = document.getElementById("sneaker-merk").innerText;
    // let artikelnummer = artikelnummerText.replace("Artikelnummer: ", "");


    let sneakerDetails = {
        merk: document.getElementById("sneaker-title").innerText,
        beschrijving: document.getElementById("sneaker-beschrijving").innerText,
        artikelnummer: document.getElementById("sneaker-merk").innerText.replace("Artikelnummer: ", ""),
        kleur: document.getElementById("sneaker-kleur").innerText.replace("Kleur: ", ""),
        maat: parseInt(document.getElementById("sneaker-maat").innerText.replace("Maat: ", "")),
        voorraad: document.getElementById("sneaker-voorraad").innerText === "Op voorraad" ? 0 : 1,
        prijs: parseFloat(document.getElementById("sneaker-prijs").innerText.replace("Prijs: €", "")),
        image: document.getElementById("sneaker-image").src
    };


    let bestelling = {
        voornaam: voornaam,
        achternaam: achternaam,
        email: email,
        adres: adres,
        postcode: postcode,
        woonplaats: woonplaats,
        artikelnummer: sneakerDetails.artikelnummer
    }

    console.log(bestelling);

    fetch('/restservices/bestelling', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(bestelling)
    })
        .then(response => {
            if (response.ok) {
                alert("Bedankt voor uw bestelling!");
                window.location.href = "../klant/account/account.html";
            } else {
                alert("Er is een probleem opgetreden bij het verwerken van uw bestelling")
            }
        })
        .catch(error => {
            console.log(error);
        })
}