// Functie om de dropdown te vullen met sneakers
function populateSneakerDropdown() {
    const dropdown = document.getElementById('sneakerDropdown');
    const formInputs = document.querySelectorAll('#sneakerForm input');
    const wijzigButton = document.getElementById('wijzigButton');
    const verwijderButton = document.getElementById('verwijderButton');

    // Verwijder de bestaande opties
    while (dropdown.firstChild) {
        dropdown.removeChild(dropdown.firstChild);
    }

    // Alle sneakers ophalen en toevoegen aan dropdown
    fetchSneakers()
        .then(sneakers => {
            sneakers.forEach(sneaker => {
                const option = document.createElement('option');
                option.value = sneaker.artikelnummer;
                option.text = sneaker.artikelnummer + ": " + sneaker.beschrijving;
                dropdown.appendChild(option);
            });
        })
        .catch(error => console.log(error));

    // Standaardoptie toevoegen aan dropdown
    const defaultOption = document.createElement('option');
    defaultOption.value = '';
    defaultOption.text = 'Selecteer een sneaker';
    defaultOption.disabled = true;
    defaultOption.selected = true;
    dropdown.insertBefore(defaultOption, dropdown.firstChild);

    // Eventlistener toevoegen om de geselecteerde sneaker op te halen en het formulier in te vullen
    dropdown.addEventListener('change', function () {
        const selectedArtikelnummer = dropdown.value;
        fetchSneakerByArtikelnummer(selectedArtikelnummer)
            .then(sneaker => {
                // Invullen van formulier
                document.getElementById('artikelnummer').value = sneaker.artikelnummer;
                document.getElementById('merk').value = sneaker.merk;
                document.getElementById('kleur').value = sneaker.kleur;
                document.getElementById('maat').value = sneaker.maat;
                document.getElementById('beschrijving').value = sneaker.beschrijving;
                document.getElementById('prijs').value = sneaker.prijs;
                document.getElementById('image').value = sneaker.image;
                document.getElementById('voorraad').value = sneaker.voorraad;

                // Schakel de invoervelden en de knop in
                formInputs.forEach(input => input.disabled = false);
                wijzigButton.disabled = false;
                verwijderButton.disabled = false;
            })
            .catch(error => {
                console.log(error);
            });
    });

    // Schakel de invoervelden en de knop uit
    formInputs.forEach(input => input.disabled = true);
    wijzigButton.disabled = true;
    verwijderButton.disabled = true;
}



// Functie om sneakers op te halen vanuit de back-end
function fetchSneakers() {
    return fetch('/restservices/sneakers')
        .then(response => response.json());
}

// Functie om specifieke sneaker op te halen op basis van artikelnummer
function fetchSneakerByArtikelnummer(artikelnummer) {
    return fetch(`/restservices/sneakers/${artikelnummer}`)
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Sneaker niet gevonden');
            }
        });
}

// Wacht tot het DOM geladen is en roep de functie aan op de dropdown aan te vullen
document.addEventListener('DOMContentLoaded', function () {
    populateSneakerDropdown();
});

function sneakerWijzigen() {
    // Ophalen waarden van de invoervelden
    let artikelnummer = document.getElementById('artikelnummer').value;
    let merk = document.getElementById('merk').value;
    let kleur = document.getElementById('kleur').value;
    let maat = document.getElementById('maat').value;
    let beschrijving = document.getElementById('beschrijving').value;
    let prijs = document.getElementById('prijs').value;
    let image = document.getElementById('image').value;
    let voorraad = document.getElementById('voorraad').value;

    // Body aanmaken met de gegevens van de gewijzigde sneaker
    const jsonRequestBody = {
        'artikelnummer': artikelnummer,
        'merk': merk,
        'kleur': kleur,
        'maat': maat,
        'beschrijving': beschrijving,
        'prijs': prijs,
        'image': image,
        'voorraad': voorraad
    }

    // PUT verzoek naar de server maken om sneaker te wijzigen
    fetch("/restservices/sneakers/" + jsonRequestBody["artikelnummer"], {
        headers: {"Content-Type": "application/json"},
        method: "PUT",
        body: JSON.stringify(jsonRequestBody)
    })
        .then(function (response) {
            // Als het antwoord van de server ok is, invoervelden leeg maken, sneakerDropdown updaten en melding geven
            if (response.ok) {
                document.getElementById('artikelnummer').value = '';
                document.getElementById('merk').value = '';
                document.getElementById('kleur').value = '';
                document.getElementById('maat').value = '';
                document.getElementById('beschrijving').value = '';
                document.getElementById('prijs').value = '';
                document.getElementById('image').value = '';
                document.getElementById('voorraad').value = '';

                populateSneakerDropdown();
                alert("Sneaker gewijzigd")
            }
            // Als server status 400 stuurt, dan zijn niet alle velden ingevuld
            else if (response.status === 400) {
                alert("Vul alle velden");
            } else {
                console.log("Fout bij het verzenden van het formulier");
            }
        })
        .catch(error => console.log(error))
}

function sneakerVerwijderen() {
    // Ophalen artikelnummer van bestaande sneaker uit invoerveld
    const artikelnummer = document.getElementById('artikelnummer').value;

    // Een bevestiging weergeven om te controleren of de gebruiker de sneaker wilt verwijderen
    if (!confirm("Weet je zeker dat je deze sneaker wilt verwijderen?")) {
        return;
    }

    // DELETE verzoek naar de server maken om bestaande sneaker te verwijderen
    fetch(`/restservices/sneakers/${artikelnummer}`, {
        method: "DELETE"
    })
        .then(function (response) {
            if (response.ok) {
                // Als het antwoord van de server ok is, invoervelden leeg maken en melding geven van succes
                document.getElementById('artikelnummer').value = '';
                document.getElementById('merk').value = '';
                document.getElementById('kleur').value = '';
                document.getElementById('maat').value = '';
                document.getElementById('beschrijving').value = '';
                document.getElementById('prijs').value = '';
                document.getElementById('image').value = '';
                document.getElementById('voorraad').value = '';

                populateSneakerDropdown();
                alert("Sneaker succesvol verwijderd");
            }
            // Als server status 404 stuurt, dan bestaat de sneaker niet met ingevulde artikelnummer
            else if (response.status === 404) {
                alert("Sneaker niet gevonden");
            } else {
                console.log("Fout bij het verwijderen van de sneaker");
            }
        })
        .catch(error => console.log(error));
}
