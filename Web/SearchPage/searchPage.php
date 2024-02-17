<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <link rel="stylesheet" href="searchPage.css" />
        <title>RateFlix</title>
        <?php include ('../core.php'); ?>
    </head>
    <body>
        <?php 
        //Vérifie si le user est connecté
        if (!isset($_SESSION['token_auth']) || !isset($_GET['token']) || $_SESSION['token_auth'] !== $_GET['token']) {
            header('Location: ../LoginScreen/login.php');
            exit();
        }
        
        $user_id = $_SESSION['userId'];
        $username = $_SESSION['username'];
        $image = $_SESSION['image'];

        $token_auth = $_GET['token'];
  
        ?>
        <div class="search-page">
            <div class="header">
                <div class="text-wrapper-2">Rateflix</div>
                <button class="deco" onclick="window.location.href='../logout.php'">Déconnexion</button>
                <img class="user-cicrle-duotone" src="../Profil_Image/<?php echo $image; ?>" onclick="goToProfil('<?php echo $token_auth; ?>')"/> 
                <p class="name-user" id='userId' onclick="goToProfil('<?php echo $token_auth; ?>')"><?php echo $username; ?></p>
            </div>
            <p id="token" hidden><?php echo $token_auth ?></p>
            <select id="listeDeroulante" hidden></select>
            <script src="script.js"></script>
            <div class="middle">  
                <input type="text" placeholder="🔍 Recherche" name="recherche" id="recherche" class="input-field" oninput="rechercherOptions()">
                <div class="conteneur-images" id="conteneurImages">
                    <img src="../Movie_Cover/American_Sniper.jpg" alt="" onclick="goToMovie('<?php echo $token_auth; ?>', 'American_Sniper')">                
                    <img src="../Movie_Cover/Avatar.jpg" alt="" onclick="goToMovie('<?php echo $token_auth; ?>', 'Avatar')">
                    <img src="../Movie_Cover/Fight_Club.jpg" alt="" onclick="goToMovie('<?php echo $token_auth; ?>', 'Fight_Club')">        
                    <img src="../Movie_Cover/Harry_Potter.jpg" alt="" onclick="goToMovie('<?php echo $token_auth; ?>', 'Harry_Potter')">                
                    <img src="../Movie_Cover/Hors_Normes.jpg" alt="" onclick="goToMovie('<?php echo $token_auth; ?>', 'Hors_Normes')">
                    <img src="../Movie_Cover/Interstellar.jpg" alt="" onclick="goToMovie('<?php echo $token_auth; ?>', 'Interstellar')">        
                    <img src="../Movie_Cover/Le_Parrain.jpg" alt="" onclick="goToMovie('<?php echo $token_auth; ?>', 'Le_Parrain')">
                    <img src="../Movie_Cover/Les_Tuches_3.jpg" alt="" onclick="goToMovie('<?php echo $token_auth; ?>', 'Les_Tuches_3')">
                    <img src="../Movie_Cover/Mamma_Mia_!.jpg" alt="" onclick="goToMovie('<?php echo $token_auth; ?>', 'Mamma_Mia_!')">
                    <img src="../Movie_Cover/Pulp_Fiction.jpg" alt="" onclick="goToMovie('<?php echo $token_auth; ?>', 'Pulp_Fiction')">                
                    <img src="../Movie_Cover/Skyfall.jpg" alt="" onclick="goToMovie('<?php echo $token_auth; ?>', 'Skyfall')">
                    <img src="../Movie_Cover/Titanic.jpg" alt="" onclick="goToMovie('<?php echo $token_auth; ?>', 'Titanic')">

                </div>
            </div>
        </div>
    </body>


</html>
