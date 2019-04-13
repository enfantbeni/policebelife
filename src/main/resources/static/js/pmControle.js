/**
 * 
 */

/// Fonction pour desactive la DIV d'affiche de d'erreur
function desactiveAfficheErreur(){
	var afficheErreur=document.getElementById('afficheErreur');
	afficheErreur.style.display = ' none' ;	
}

desactiveAfficheErreur();

function checkAuthentification(){
	
	var identifiant=document.getElementById('identifiant') ;
	var identifiantChaine=identifiant.value.replace(/ /g,"");
	var valeurIdentifiant=identifiant.value.replace(/ /g,"").length
	
	var password=document.getElementById('password') ;
	var identifiantChaine=password.value.replace(/ /g,"");
	var valeurPassword=password.value.replace(/ /g,"").length
	
	if( valeurIdentifiant == 0 || identifiantChaine =='' || valeurPassword == 0 || passwordChaine =='' ){
		afficheErreur.style.display = 'inline-block' ;	
		
	}	
}


