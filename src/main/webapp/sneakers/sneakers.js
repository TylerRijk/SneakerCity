// Functie om sneakers op te halen vanuit de back-end
function fetchSneakers() {
    return fetch('/restservices/sneakers')
        .then(response => response.json());
}

// Functie om alle sneaker cards te genereren
function generateSneakerCards(sneakers) {
    const sneakerList = document.querySelector('.sneaker-list');

    // Verwijder bestaande cards
    while (sneakerList.firstChild) {
        sneakerList.removeChild(sneakerList.firstChild);
    }

    sneakers.forEach(sneaker => {
        const sneakerCard = createSneakerCard(sneaker);
        sneakerList.appendChild(sneakerCard);
    });
}


// Functie om een sneaker card te maken
function createSneakerCard(sneaker) {
    const sneakerCard = document.createElement('div');
    sneakerCard.classList.add('sneaker-card');

    const img = document.createElement('img');
    img.src = `../images/${sneaker.image}`;
    img.alt = `Sneaker ${sneaker.beschrijving}`;
    sneakerCard.appendChild(img);

    const label = document.createElement('label');
    const h3 = document.createElement('h3');
    h3.textContent = `${sneaker.merk} ${sneaker.beschrijving}`;
    label.appendChild(h3);
    label.addEventListener('click', () => {
        navigateToSneakerProduct(sneaker.artikelnummer);
    });
    sneakerCard.appendChild(label);

    const p = document.createElement('p');
    p.textContent = `Prijs: €${sneaker.prijs}`;
    sneakerCard.appendChild(p);

    const button = document.createElement('button');
    button.textContent = 'Bestel';
    sneakerCard.appendChild(button);

    button.addEventListener('click', () => {
        navigateToSneakerProduct(sneaker.artikelnummer);
    });


    return sneakerCard;
}

// Functie om de product pagina te gaan
function navigateToSneakerProduct(artikelnummer) {
    window.location.href = `sneaker-product.html?artikelnummer=${artikelnummer}`;
}

// Roep de functie aan wanneer de DOM geladen is
document.addEventListener('DOMContentLoaded', () => {
    // Roep de promise aan van fetchSneakers
    fetchSneakers()
        .then(sneakers => generateSneakerCards(sneakers))
        .catch(error => console.error('Error:', error));
});
