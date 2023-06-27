// Haal het artikelnummer uit de queryparameter van de URL
const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const artikelnummer = urlParams.get('artikelnummer');

// Functie om de sneaker-details op te halen en de HTML in te vullen
function fetchSneakerDetails() {
    fetch(`/restservices/sneakers/${artikelnummer}`)
        .then(response => response.json())
        .then(sneaker => populateSneakerDetails(sneaker))
        .catch(error => console.error('Error:', error));
}

// Functie om de sneaker-details in te vullen in de HTML
function populateSneakerDetails(sneaker) {
    const sneakerImage = document.getElementById('sneaker-image')
    const sneakerTitle = document.getElementById('sneaker-title');
    const sneakerMerk = document.getElementById('sneaker-merk');
    const sneakerKleur = document.getElementById('sneaker-kleur');
    const sneakerMaat = document.getElementById('sneaker-maat');
    const sneakerBeschrijving = document.getElementById('sneaker-beschrijving');
    const sneakerPrijs = document.getElementById('sneaker-prijs');
    const sneakerVoorraad = document.getElementById('sneaker-voorraad');
    // const orderButton = document.getElementById('order-button');

    sneakerImage.src = `../images/${sneaker.image}`;
    sneakerImage.alt = `Sneaker ${sneaker.beschrijving}`;
    sneakerTitle.textContent = `${sneaker.merk}`;
    sneakerMerk.textContent = `Artikelnummer: ${sneaker.artikelnummer}`;
    sneakerBeschrijving.textContent = `${sneaker.beschrijving}`;
    sneakerKleur.textContent = `Kleur: ${sneaker.kleur}`;
    sneakerMaat.textContent = `Maat: ${sneaker.maat}`;
    sneakerPrijs.textContent = `Prijs: €${sneaker.prijs}`;
    sneakerVoorraad.textContent = `Voorraad: ${sneaker.voorraad ? 'Op voorraad' : 'Niet op voorraad'}`;
}


function orderSneaker() {
    // Details ophalen
    let sneakerDetails = {
        title: document.getElementById("sneaker-title").innerText,
        beschrijving: document.getElementById("sneaker-beschrijving").innerText,
        merk: document.getElementById("sneaker-merk").innerText,
        kleur: document.getElementById("sneaker-kleur").innerText,
        maat: document.getElementById("sneaker-maat").innerText,
        voorraad: document.getElementById("sneaker-voorraad").innerText,
        prijs: document.getElementById("sneaker-prijs").innerText,
        image: document.getElementById("sneaker-image").src
    };

    // Querystring opbouwen met de details van de sneaker
    let queryString = "?";
    for (let key in sneakerDetails) {
        if (sneakerDetails.hasOwnProperty(key)) {
            queryString += key + "=" + encodeURIComponent(sneakerDetails[key]) + "&";
        }
    }

    queryString = queryString.slice(0, -1);

    // Open pagina voor bijbehorende sneaker bestelling
    window.location.href = "../bestelling/bestelling.html" + queryString;
}

// Roep de functie aan wanneer de DOM geladen is
document.addEventListener('DOMContentLoaded', fetchSneakerDetails);
