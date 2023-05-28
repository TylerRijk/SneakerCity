function selectSneaker(image) {
    localStorage.setItem('selectedSneaker', image);
    window.location.href = 'sneaker-product.html';
}

window.addEventListener('DOMContentLoaded', (event) => {
    const selectedSneaker = localStorage.getItem('selectedSneaker');
    if (selectedSneaker) {
        const sneakerImage = document.getElementById('sneakerImage');
        sneakerImage.src = '../images/' + selectedSneaker;
        sneakerImage.alt = 'Sneaker Image';
    }
});
