<?php
session_start();

// Déconnecter l'utilisateur
session_destroy();
echo "deco";

// Rediriger vers la page de connexion
header('Location: LoginScreen/login.php');
exit();
?>