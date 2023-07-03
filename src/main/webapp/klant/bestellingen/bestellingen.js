function populateBestellingDropDown() {
    const dropdown = document.getElementById('bestellingDropDown');

    // Alle bestellingen verkrijgen
    getAllBestellingen()
        .then(bestellingen => {
            // Voor elke bestelling een nieuwe optie aanmaken
            bestellingen.forEach(bestelling => {
                const option = document.createElement('option');
                option.value = bestelling.bestellingId;
                option.text = "Bestelling: " + bestelling.bestellingId;
                dropdown.appendChild(option);
            });
        })
        .catch(error => console.log(error));

    // Standaard toevoegen als optie
    const defaultOption = document.createElement('option');
    defaultOption.value = '';
    defaultOption.text = 'Selecteer uw bestelling';
    defaultOption.disabled = true;
    defaultOption.selected = true;
    dropdown.insertBefore(defaultOption, dropdown.firstChild);

    // Event listener toevoegen aan dropdown om te reageren op veranderingen
    dropdown.addEventListener('change', function () {
        // Geselecteerde bestelling ophalen
        const selectedBestelling = dropdown.value;
        getBestellingById(selectedBestelling)
            .then(bestelling => {
                const detailsContainer = document.getElementById("bestellingDetails");

                // Alle details eerst leeg maken
                detailsContainer.innerHTML = "";

                // Bestelgegevens instellen
                const bestellingIdElement = document.createElement('p');
                bestellingIdElement.textContent = "Bestelnummer: " + bestelling.bestellingId;
                detailsContainer.appendChild(bestellingIdElement);

                const datumElement = document.createElement('p');
                const datum = new Date(bestelling.datum);

                const dag = datum.getDate();
                const maand = datum.getMonth() + 1; // Maanden beginnen bij 0, dus er moet 1 worden toegevoegd
                const jaar = datum.getFullYear();
                const uren = datum.getHours();
                const minuten = datum.getMinutes();

                const datumString = `Besteldatum: ${dag}-${maand}-${jaar}, ${uren}:${minuten}`;

                datumElement.textContent = datumString;
                detailsContainer.appendChild(datumElement);

                // Sneaker ophalen van de bestelling aan de hand van artikelnummer
                getSneakerByArtikelnummer(bestelling.sneaker.artikelnummer)
                    .then(sneaker => {
                        const sneakerDetailsContainer = document.getElementById("sneakerDetails");

                        // Alle details eerst leeg maken
                        sneakerDetailsContainer.innerHTML = "";

                        // Alle sneaker gegevens instellen
                        const artikelnummerElement = document.createElement('p');
                        artikelnummerElement.textContent = "Artikelnummer: " + sneaker.artikelnummer;
                        sneakerDetailsContainer.appendChild(artikelnummerElement);

                        const merkElement = document.createElement('p');
                        merkElement.textContent = "Merk: " + sneaker.merk;
                        sneakerDetailsContainer.appendChild(merkElement);

                        const beschrijvingElement = document.createElement('p');
                        beschrijvingElement.textContent = "Beschrijving: " + sneaker.beschrijving;
                        sneakerDetailsContainer.appendChild(beschrijvingElement);

                        const kleurElement = document.createElement('p');
                        kleurElement.textContent = "Kleur: " + sneaker.kleur;
                        sneakerDetailsContainer.appendChild(kleurElement);

                        const maatElement = document.createElement('p');
                        maatElement.textContent = "Maat: " + sneaker.maat;
                        sneakerDetailsContainer.appendChild(maatElement);

                        const prijsElement = document.createElement('p');
                        prijsElement.textContent = "Prijs: €" + sneaker.prijs;
                        sneakerDetailsContainer.appendChild(prijsElement);
                    })
                    .catch(error => {
                        console.log(error);
                    })
            })
            .catch(error => {
                console.log(error);
            });
    });
}

// Alle bestellingen ophalen uit het systeem
function getAllBestellingen() {
    return fetch('/restservices/bestelling')
        .then(response => response.json())
        .catch(error => console.log(error));
}

// Bestelling met specifieke id ophalen uit het systeem
function getBestellingById(bestellingId) {
    return fetch(`/restservices/bestelling/${bestellingId}`)
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Bestelling niet gevonden');
            }
        });
}

// Sneaker met specifieke artikelnummer ophalen uit systeem
function getSneakerByArtikelnummer(artikelnummer) {
    return fetch(`/restservices/sneakers/${artikelnummer}`)
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Sneaker niet gevonden');
            }
        });
}

// Als de DOM geladen is gelijk alle bestellingen instellen in de dropdown
document.addEventListener('DOMContentLoaded', function () {
    populateBestellingDropDown();
});