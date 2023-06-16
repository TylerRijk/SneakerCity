function sneakerToevoegen() {
    let artikelnummer = document.getElementById('artikelnummer').value;
    let merk = document.getElementById('merk').value;
    let kleur = document.getElementById('kleur').value;
    let maat = document.getElementById('maat').value;
    let beschrijving = document.getElementById('beschrijving').value;
    let prijs = document.getElementById('prijs').value;
    let image = document.getElementById('image').value;
    let voorraad = document.getElementById('voorraad').value;

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

    // console.log(jsonRequestBody);

    fetch("/restservices/sneakers", {
        headers: {"Content-Type": "application/json"},
        method: "POST",
        body: JSON.stringify(jsonRequestBody)
    })
        .then(function (response) {
            if (response.ok) {
                document.getElementById('artikelnummer').value = '';
                document.getElementById('merk').value = '';
                document.getElementById('kleur').value = '';
                document.getElementById('maat').value = '';
                document.getElementById('beschrijving').value = '';
                document.getElementById('prijs').value = '';
                document.getElementById('image').value = '';
                document.getElementById('voorraad').value = '';

                alert("Nieuwe sneaker toegevoegd");
            } else if (response.status === 400) {
                alert("Vul alle velden in");
            } else if (response.status === 409) {
                alert("Dit artikelnummer bestaat al");
            } else {
                console.log("Fout bij het verzenden van het formulier");
            }
        })
        .catch(error => console.log(error));
}
