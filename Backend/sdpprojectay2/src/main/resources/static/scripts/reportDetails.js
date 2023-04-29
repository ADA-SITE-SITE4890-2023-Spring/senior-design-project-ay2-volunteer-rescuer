// const editBtn = document.getElementById("editBtnReport")
// const reportModal = document.getElementById("reportModal")
// editBtn.onclick = function(){
//
//     reportModal.style.display = "block"
// }
//
// const closeModal = document.getElementById("closeModalBtn")
//
// closeModal.onclick= function(){
//     reportModal.style.display = "none"
//
// }


// var editBtnReport = document.getElementById("editBtnReport");
// var reportModal1 = document.getElementById("reportModal");
// var closeModalBtn = document.getElementById("closeModalBtn");
//
//
// // Get all modal buttons
// const modalBtns = document.querySelectorAll('.fa-pen-to-square');
//
// // Get the modal element
// const modal = document.getElementById('reportModal');
//
// // Get the close button
// const closeBtn = document.getElementById('closeModalBtn');
//
// // Loop through all modal buttons
// modalBtns.forEach(btn => {
//     // Add click event listener to open modal
//     btn.addEventListener('click', () => {
//         modal.style.display = 'block';
//     });
// });
//
// // Add click event listener to close button
// closeBtn.addEventListener('click', () => {
//     modal.style.display = 'none';
// });


function openModal(id) {
    document.getElementById('reportModal-' + id).style.display = 'block';
}

function closeModal(id) {
    document.getElementById('reportModal-' + id).style.display = 'none';
}