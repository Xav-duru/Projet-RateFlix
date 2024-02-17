<?php
include ('../core.php');


// Récupérer tous les éléments de la table
$recherche = $_GET['recherche'];
$requete = "SELECT movieId, name, image FROM movie WHERE name LIKE '$recherche%'";
$resultat = $mysqli->query($requete);

// Retourner les résultats au format JSON
$options = array();
while ($row = $resultat->fetch_assoc()) {
    $options[] = $row;
}

echo json_encode($options);
?>
