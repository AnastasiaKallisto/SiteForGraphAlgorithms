const dropdown = document.querySelector('.dropdown');
const menu = dropdown.querySelector('.dropdown-content');

dropdown.addEventListener('click', function(event) {
    if (event.target.closest('.dropbtn')) {
        dropdown.classList.toggle('dropdown--active');
    }
});

document.addEventListener('click', function(event) {
    if (!dropdown.contains(event.target)) {
        dropdown.classList.remove('dropdown--active');
    }
});

