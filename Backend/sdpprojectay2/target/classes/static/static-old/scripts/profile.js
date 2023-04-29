const profileBtn = document.getElementById("prof-round")
const profileMenu = document.getElementById("round-inside")




profileBtn.onclick = function () {
    if (profileMenu.classList.contains("active-inside")) {
        profileMenu.classList.remove("active-inside")
    } else {
        profileMenu.classList.add("active-inside")

    }
}

window.addEventListener("scroll", function(){
    if(this.window.scrollY>=30){
        profileMenu.classList.remove("active-inside")
    }
})