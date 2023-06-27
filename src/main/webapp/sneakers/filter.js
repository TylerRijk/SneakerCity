// Functie om alle filters in te vullen
function generateFilterOptions(sneakers) {
    const brandSelect = document.getElementById('merk-filter');
    const colorSelect = document.getElementById('kleur-filter');
    const sizeSelect = document.getElementById('maat-filter');

    // Select boxen vullen met bijbehorende data
    const uniqueBrands = [...new Set(sneakers.map(sneaker => sneaker.merk))];
    const uniqueColors = [...new Set(sneakers.map(sneaker => sneaker.kleur))];
    const uniqueSizes = [...new Set(sneakers.map(sneaker => sneaker.maat))];

    // Optie toevoegen voor alle merken
    const allBrandsOption = document.createElement('option');
    allBrandsOption.value = '';
    allBrandsOption.textContent = 'Alle merken';
    brandSelect.appendChild(allBrandsOption);

    // Opties toevoegen voor elk uniek merk
    uniqueBrands.forEach(brand => {
        const option = document.createElement('option');
        option.value = brand;
        option.textContent = brand;
        brandSelect.appendChild(option);
    });

    // Optie toevoegen voor alle kleuren
    const allColorsOption = document.createElement('option');
    allColorsOption.value = '';
    allColorsOption.textContent = 'Alle kleuren';
    colorSelect.appendChild(allColorsOption);

    // Opties toevoegen voor elke unieke kleur
    uniqueColors.forEach(color => {
        const option = document.createElement('option');
        option.value = color;
        option.textContent = color;
        colorSelect.appendChild(option);
    });

    // Optie toevoegen voor alle maten
    const allSizesOption = document.createElement('option');
    allSizesOption.value = '';
    allSizesOption.textContent = 'Alle maten';
    sizeSelect.appendChild(allSizesOption);

    // Opties toevoegen voor elke unieke maat
    uniqueSizes.forEach(size => {
        const option = document.createElement('option');
        option.value = size;
        option.textContent = size;
        sizeSelect.appendChild(option);
    });
}


// Functie om de prijsranges te genereren
function generatePriceRanges(sneakers) {
    const priceSelect = document.getElementById('prijs-filter');

    // Prijs ranges instellen
    const priceRanges = [
        { min: 50, max: 100 },
        { min: 100, max: 200 },
        { min: 200, max: 300 },
        { min: 300, max: 500}
    ];

    // Optie toevoegen voor alle prijsranges
    const allPricesOption = document.createElement('option');
    allPricesOption.value = '';
    allPricesOption.textContent = 'Alle prijzen';
    priceSelect.appendChild(allPricesOption);

    // Opties toevoegen voor elke prijsrange
    priceRanges.forEach(range => {
        const option = document.createElement('option');
        option.value = `${range.min}-${range.max}`;
        option.textContent = `${range.min} - ${range.max}`;
        priceSelect.appendChild(option);
    });
}


// Functie om de geselecteerde filterwaarden op te halen
function getSelectedFilters() {
    const brandFilter = document.getElementById('merk-filter').value;
    const colorFilter = document.getElementById('kleur-filter').value;
    const sizeFilter = document.getElementById('maat-filter').value;
    const priceFilter = document.getElementById('prijs-filter').value;

    return {
        brand: brandFilter,
        color: colorFilter,
        size: sizeFilter,
        price: priceFilter
    };
}


// Functie om alle sneakers te filteren op basis van criteria
function filterSneakers(sneakers, filters) {
    return sneakers.filter(sneaker => {
        // Merk filter
        if (filters.brand && sneaker.merk !== filters.brand) {
            return false;
        }
        // Kleur filter
        if (filters.color && sneaker.kleur !== filters.color) {
            return false;
        }
        // Maat filter
        if (filters.size && sneaker.maat !== parseInt(filters.size)) {
            return false;
        }
        // Prijs filter
        if (filters.price) {
            const [minPrice, maxPrice] = filters.price.split('-');
            const sneakerPrice = sneaker.prijs;
            if (minPrice && sneakerPrice < Number(minPrice)) {
                return false;
            }
            if (maxPrice && sneakerPrice >= Number(maxPrice)) {
                return false;
            }
        }
        return true;
    });
}


// Functie om de filters te updaten en de gesorteerde sneakers opnieuw te genereren
function updateFilteredSneakers(sneakers) {
    const filters = getSelectedFilters();
    const filteredSneakers = filterSneakers(sneakers, filters);
    generateSneakerCards(filteredSneakers);
}


// Roep de functie aan wanneer de DOM geladen is
document.addEventListener('DOMContentLoaded', () => {
    let sneakers;
    const brandSelect = document.getElementById('merk-filter');
    const colorSelect = document.getElementById('kleur-filter');
    const sizeSelect = document.getElementById('maat-filter');
    const priceSelect = document.getElementById('prijs-filter');

    // Voeg een 'change' event listener toe aan de filter select boxen
    brandSelect.addEventListener('change', () => updateFilteredSneakers(sneakers));
    colorSelect.addEventListener('change', () => updateFilteredSneakers(sneakers));
    sizeSelect.addEventListener('change', () => updateFilteredSneakers(sneakers));
    priceSelect.addEventListener('change', () => updateFilteredSneakers(sneakers));

    // Roep de promise aan van fetchSneakers
    fetchSneakers()
        .then(data => {
            sneakers = data;
            generatePriceRanges(sneakers);
            generateFilterOptions(sneakers);
            updateFilteredSneakers(sneakers);
        })
        .catch(error => console.error('Error:', error));
});
