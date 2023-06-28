function populateBestellingDropDown() {
    const dropdown = document.getElementById('bestellingDropDown');

    getAllBestellingen()
        .then(bestellingen => {
            bestellingen.forEach(bestelling => {
                const option = document.createElement('option');
                option.value = bestelling.bestellingId;
                option.text = "Bestelling: " + bestelling.bestellingId;
                dropdown.appendChild(option);
            });
        })
        .catch(error => console.log(error));

    const defaultOption = document.createElement('option');
    defaultOption.value = '';
    defaultOption.text = 'Selecteer uw bestelling';
    defaultOption.disabled = true;
    defaultOption.selected = true;
    dropdown.insertBefore(defaultOption, dropdown.firstChild);

    dropdown.addEventListener('change', function () {
        const selectedBestelling = dropdown.value;
        getBestellingById(selectedBestelling)
            .then(bestelling => {
                const detailsContainer = document.getElementById("bestellingDetails");

                detailsContainer.innerHTML = "";

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

                getSneakerByArtikelnummer(bestelling.sneaker.artikelnummer)
                    .then(sneaker => {
                        const sneakerDetailsContainer = document.getElementById("sneakerDetails");

                        sneakerDetailsContainer.innerHTML = "";

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

function getAllBestellingen() {
    return fetch('/restservices/bestelling')
        .then(response => response.json());
}

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

document.addEventListener('DOMContentLoaded', function () {
    populateBestellingDropDown();
});