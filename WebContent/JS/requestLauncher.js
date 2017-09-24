/**
 * 
 */

function incremente(id) {
	
	var nb = document.getElementById(id).value;
	
	var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", webAddress+"?idProduit="+id+"&quantite="+nb+"&action=add", true ); // false for synchronous request
    xmlHttp.send( null );
	document.getElementsByTagName('audio')[0].currentTime = 0;
    document.getElementsByTagName('audio')[0].play();
    
    alert(nb +" produits ajoutés");
	
}


function decremente(id) {
	
	var nb = document.getElementById(id).value;
	
	var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", webAddress+"?idProduit="+id+"&quantite="+nb+"&action=remove", true ); // false for synchronous request
    xmlHttp.send( null );
    

    alert(nb +" produits retiré");
	
}

function updatePrix(id, prix){
	var labelPrix = document.getElementById("label"+id);
	var quantite = document.getElementById(id).value;
	
	labelPrix.innerHTML = prix*quantite;
}