<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="utf-8">
  <title id="titrePage">Smag</title>

  <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<script src="/js/processing.min.js"></script>
<script type="text/javascript">
	function toggleMe(a){
		var e=document.getElementById(a);
		if(!e)return true;
			if(e.style.display=="none"){
			e.style.display="block"
			}
			else{
				e.style.display="none"
			}
			return true;
	}
	
     


</script>

<div id="page-left">Visualisation</br>
<canvas id="projetPde" width="300" height="300" data-processing-sources="pde/smag/smag.pde /pde/smag/Noeud.pde"></canvas></br>
<button type="button" onclick="drawSomeText('projetPde')">Actualiser</button>
</div>
<div id="page-right">Emplacement Agent Projet<div id="agentDiv"></div></div>
<div id="methode">
</div>

<div class="gauche">
<div id="affiche-methode">
</div>
<canvas id="methodePde"  width="100" height="100" data-processing-sources="pde/diamond/diamond.pde /pde/diamond/Noeud.pde"></canvas></br>

</div>

<div id="liaisons">Liaisons entre les projets<br>
<ul id="projetsSimilairesDiv">
<li >Projets avec un nom similaire</li>
</ul>
</div>


  <div id="page-wrapper">
      <input type="button" value="Tous les projets / Proposer un projet" onclick="location.href='accueil.jsp'">
      <ul id="statutTitre">veuillez patienter, chargement en cours</ul>
    <h1 id="titreProjet">Smag0 d�tail d'un projet </h1>
    <ul id="descriptionProjet">description </ul>
     </br>
     <ul> <input type="button" type="button" id="ajouterInfo" onclick="return toggleMe('ajouteInfoDiv')" value="Ajouter une information en rapport avec ce projet (dev en cours)" >
  <div id="ajouteInfoDiv">
    <form id="ajouteInfo-form" action="#" method="post">
    Ins�rez une info au format rdf : Sujet / Propri�t� / Objet </br>(exemple : <i>mon robot / poss�de la capacit� de / se d�placer</i> ou </br>
    <i>mon projet / compl�ment d'information / les infos que j'ai � rajouter, blablabla...</i>)</br>
      <input id="infoSujet" placeholder="Sujet" required> </input>
   <!-- disabled=false    <div>
      <input type="radio" name="checkPropriete" value="libre" onclick="proprieteLibre()" checked="true">Texte Libre
		<input type="radio" name="checkPropriete" value="existante" onclick="proprieteExistante()">Propri�t� existante
   </div> -->
   <select id="selectPropriete" >
  <option value="indetermine">Lien ind�termin� (Dev en cours)</option>
  <option value="sameAS">Est identique �</option>
  <option value="partOf">Est un composant de</option>
  <option value="other">Selectionner une les proprri�t�s d'une ontologie</option>
</select>
     <input id="infoPropriete" placeholder="Entrez une propri�t� qui relie le sujet et l'objet" required></input>
		 <textarea id="infoObjet" placeholder="Entrez l'objet" required></textarea>
      <button type="submit">Ajouter</button>
      <button type="button" id="effaceInfo">Effacer</button>
    </form>
</div>
 </ul>
    <input type="button" onclick="return toggleMe('doapDiv')" value="Afficher/Cacher DOAP (description of a Project)"><br>
    <ul id="doapDiv"><li>Recherche du DOAP du projet, veuillez patienter, <div id="statutDoap">chargement en cours</div></li></ul>
      </div>
 <!-- <script src="/js/projetPde.js"></script> -->
  <script src="/js/projet.js"></script>

</body>
</html>