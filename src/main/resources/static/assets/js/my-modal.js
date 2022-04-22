// Get the modal
var openModalId = '';

// When the user clicks on the button, open the modal
showPhoto = function(modalId) {
    document.getElementById('myModal-'+modalId).style.display = "block";
    openModalId = modalId;
}

// When the user clicks anywhere outside of the modal, close it
/*window.onclick = function(event) {
  if (event.target.id != openModalId) {
   document.getElementById(openModalId).style.display = "none";
  }
}*/

// When the user clicks on <span> (x), close the modal
closeModal = function(modalId) {
  document.getElementById('myModal-'+modalId).style.display = "none";
  openModalId = '';
}

document.onkeydown = function(e) {
    if(openModalId !== ''){
        switch (e.keyCode) {
            case 37:
                changeImage(null,-1);
                break;
            case 39:
                changeImage(null,1);
                break;
        }
     }
};

changeImage = function(btn, direction){
    var images = document.getElementsByClassName(openModalId+'photo');
    var imagesLength= images.length;
    var classes;
    for(var i=0;i<imagesLength;i++){
        var activeIndex = 0;
        if(imagesLength>1 && images[i].className.includes('active')){
            images[i].classList.remove('active');
            activeIndex = i + direction;
            if(activeIndex < 0){
                activeIndex = imagesLength - 1;
            }else if(activeIndex >= imagesLength ){
                activeIndex = 0;
            }
            images[activeIndex].classList.add('active');
            document.getElementById('pagin'+openModalId).innerHTML = (activeIndex+1) + '/' + imagesLength;
            break;
        }
    }
    if(btn != null)
        btn.style.color = 'black';
}