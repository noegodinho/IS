function toggle() {
    var elements = document.getElementsByClassName("athletes");

    for (var i =0; i<elements.length;i++){
        if (elements[i].style.display == 'none'){
            elements[i].style.display = 'table-row'
        }
        else{
            elements[i].style.display ='none';
        }
    }
}