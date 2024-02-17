<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="profil.css" />
    <title>RateFlix</title>
    <?php include ('../core.php'); ?>
</head>
<body>


    
    <?php
    //Check if the user is connected
    
    $username = $_SESSION["username"];
    $image = $_SESSION['image'];
    $birthday = $_SESSION['birthday'];
    $dateObj = new DateTime($birthday);
    $formatter = new IntlDateFormatter('fr_FR', IntlDateFormatter::LONG, IntlDateFormatter::NONE);
    $dateFormatee = $formatter->format($dateObj);
    ?>

    <div class="profil-page">
        <div class="header">
            <div class="overlap-group">
                <div class="text-wrapper-2">RateFlix</div>
            </div>
            <a class="deco" href="../logout.php">Déconnexion</a>   
            
        </div>

        <p id="date" hidden> <?php echo $birthday ?></p>

        <div class="profil-container">
            <div class="profil-image">
                <img src="../Profil_Image/<?php echo $image; ?>" alt="">
            </div>
            <div class="profil-info">
                <p class="profil-info-text">Nom d'utilisateur : <?php echo $username; ?></p>
                <p class="profil-info-text" id="birthday">Date de naissance : <?php echo $dateFormatee; ?>"</p>
            </div>
        </div>
    </div>

</body>
</html>