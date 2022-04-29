function openModal(element) {
    let modal = document.getElementById(element);
    modal.style.display = "block";
}

function closeModal(element) {
    let modal = document.getElementById(element);
    window.onclick = function(event) {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    }
}