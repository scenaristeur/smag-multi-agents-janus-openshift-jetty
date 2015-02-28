window.onload = function() {

	
function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

  // Get references to elements on the page.
  var ajouteObjetForm = document.getElementById('ajouteObjetForm');
  var nomObjetField = document.getElementById('nomObjet');
  var adresseIpObjetField = document.getElementById('adresseIpObjet');
  var portObjetField = document.getElementById('portObjet');
  var commentaireObjetField = document.getElementById('commentaireObjet');
  var emailNouvelObjetField = document.getElementById('emailNouvelObjet');

var openshiftWebSocketPort = 8000; // Or use 8443 for wss

var email = getParameterByName('email');
var uri = getParameterByName('projet');
var projetId = getParameterByName('projet');

var wsUtilisateur = "ws://" + window.location.hostname + ":" + openshiftWebSocketPort + "/utilisateurws";
var websocketUtilisateur = new WebSocket(wsUtilisateur);

var titre = document.getElementById('titre');
 
 toggleMe('ajouteServiceDiv');
   
   console.log(email);
   titre.innerHTML=email;
    websocketUtilisateur.onopen = function(evt) { onOpenUtilisateur(evt) };
    websocketUtilisateur.onclose = function(evt) { onCloseUtilisateur(evt) };
    websocketUtilisateur.onmessage = function(evt) { onMessageUtilisateur(evt) };
    websocketUtilisateur.onerror = function(evt) { onErrorUtilisateur(evt) };
    
function onOpenUtilisateur(evt){
	console.log("onOpenUtilisateur");
	if (email!=null){
		console.log("envoi de l'email : "+email);
		//websocketUtilisateur.send(email);
		emailNouvelObjetField.value=email
		  var data = "{ \"type\" : \"email\", "+
    		"\"email\": \""+ email +"\", "+
    		"\"date\": \""+Date.now()+"\""+
 			 "}";
  		console.log(data);
  // Send the message through the WebSocket.
  websocketUtilisateur.send(data);
	}
	
// Send a message when the form is submitted.
ajouteObjetForm.onsubmit = function(e) {
e.preventDefault();

  // Retrieve the message from the textarea.
  var nomObjet = nomObjetField.value;
  var adresseIpObjet = adresseIpObjetField.value;
  var portObjet = portObjetField.value;
  var commentaireObjet = commentaireObjetField.value;
  var emailNouvelObjet = emailNouvelObjetField.value;
  var data = "{ \"type\" : \"nouvelObjetConnecte\", "+
    "\"nomObjet\": \""+ nomObjet +"\", "+
    "\"adresseIpObjet\": \""+ adresseIpObjet +"\", "+
    "\"portObjet\": \""+ portObjet +"\", "+
    "\"commentaireObjet\": \""+ commentaireObjet +"\", "+
    "\"emailNouvelObjet\": \""+ emailNouvelObjet +"\", "+
    "\"date\": \""+Date.now()+"\""+
  "}";
  console.log(data);

  // Send the message through the WebSocket.

  websocketUtilisateur.send(data);
  // Clear out the message field.
 // messageField.value = '';
 ajouteObjetConnecteDiv.disable="true";
 nomObjet.value = '';
 ajouteObjetConnecte.value='Creation de votre objet connect� en cours';
 toggleMe('ajouteObjetConnecteDiv');
  return false;
};
}
function onCloseUtilisateur(evt){
	console.log("onCloseUtilisateur");
}
function onErrorUtilisateur(evt){
	console.log("onErrorUtilisateur");
}
function onCloseUtilisateur(evt){
	console.log("onCloseUtilisateur");
}
function onMessageUtilisateur(evt){
	console.log("onMessageUtilisateur");
}
};