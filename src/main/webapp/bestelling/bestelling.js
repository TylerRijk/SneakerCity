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
window.onload = function() {
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