<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <link rel="stylesheet" href="editProfil.css" />
        <?php include ('../core.php'); ?>
    </head>
    <body>
        
        <?php
        $token_auth = $_GET['token'];
        $userId = $_SESSION['userId'];
        $username = $_SESSION["username"];
        $image = $_SESSION['image'];
        $birthday = $_SESSION['birthday'];
        $dateObj = new DateTime($birthday);
        $formatter = new IntlDateFormatter('fr_FR', IntlDateFormatter::LONG, IntlDateFormatter::NONE);
        $dateFormatee = $formatter->format($dateObj);


        if(isset($_POST['formsendUsername'])){
            $username = $_POST['username'];  
            if(!empty($username)){
                //La fonction real_escape_string, fonction de mysqli, protège les caractères spéciaux
                //d’une chaîne pour en permettre l’utilisation dans une requête SQL.
                $username_escaped = $mysqli->real_escape_string(trim($username));
                
                $sql = "UPDATE user
                SET username = '".$username_escaped."'
                WHERE userId = '".$userId."'";

                $result = $mysqli->query($sql);

                if ($result === TRUE) { 
                    // Mise à jour réussie
                    $_SESSION['username'] = $username_escaped;
                    $mysqli->close();
                } else {
                    // Erreur lors de la mise à jour
                    exit($mysqli->error);
                }                 
            } 
        }

        if(isset($_POST['formsendEmail'])){
            $email = $_POST['email'];  
            if(!empty($email)){
                //La fonction real_escape_string, fonction de mysqli, protège les caractères spéciaux
                //d’une chaîne pour en permettre l’utilisation dans une requête SQL.
                $email_escaped = $mysqli->real_escape_string(trim($email));
                
                $sql = "UPDATE user
                SET email = '".$email_escaped."'
                WHERE userId = '".$userId."'";

                $result = $mysqli->query($sql);

                if ($result === TRUE) { 
                    // Mise à jour réussie
                    $_SESSION['email'] = $email_escaped;
                    $mysqli->close();
                } else {
                    // Erreur lors de la mise à jour
                    exit($mysqli->error);
                }                             
            } 
        }

        if(isset($_POST['formsendBirthday'])){
            $birthday = $_POST['birthday'];  
            if(!empty($birthday)){
                //La fonction real_escape_string, fonction de mysqli, protège les caractères spéciaux
                //d’une chaîne pour en permettre l’utilisation dans une requête SQL.
                $birthday_escaped = $mysqli->real_escape_string(trim($birthday));
                
                $sql = "UPDATE user
                SET birthday = '".$birthday_escaped."'
                WHERE userId = '".$userId."'";
    
                $result = $mysqli->query($sql);
    
                if ($result === TRUE) { 
                    // Mise à jour réussie
                    $_SESSION['birthday'] = $birthday_escaped;
                    $mysqli->close();
                } else {
                    // Erreur lors de la mise à jour
                    exit($mysqli->error);
                }                             
            } 
        }

        if(isset($_POST['formsendPassword'])){
            $password = $_POST['password']; 
            $hashedPassword = password_hash($password, PASSWORD_DEFAULT); 
            if(!empty($password)){
                //La fonction real_escape_string, fonction de mysqli, protège les caractères spéciaux
                //d’une chaîne pour en permettre l’utilisation dans une requête SQL.
                $password_escaped = $mysqli->real_escape_string(trim($hashedPassword));
                
                $sql = "UPDATE user
                SET password = '".$password_escaped."'
                WHERE userId = '".$userId."'";

                $result = $mysqli->query($sql);

                if ($result === TRUE) { 
                    // Mise à jour réussie
                    $_SESSION['password'] = $password_escaped;
                    $mysqli->close();
                } else {
                    // Erreur lors de la mise à jour
                    exit($mysqli->error);
                }                            
            } 
        }

        if(isset($_POST['formsendImage'])){
            if (isset($_FILES["profilePicture"]) && $_FILES["profilePicture"]["error"] == 0) {
                // Spécifier le dossier de destination pour le fichier uploadé
                $uploadDir = "../Profil_Image/";
                $uploadFile = basename($_FILES["profilePicture"]["name"]);
                // Construire le chemin complet du fichier
                $uploadPath = $uploadDir . $uploadFile;
        
                // Déplacer le fichier téléchargé vers le dossier spécifié
                if (move_uploaded_file($_FILES["profilePicture"]["tmp_name"], $uploadPath)) {
        
                    $sql = "UPDATE User 
                    SET image = '$uploadFile' 
                    WHERE userId = $userId";
        
                    $mysqli->query($sql);

                    $_SESSION['image'] = $uploadFile;
                    $mysqli->close();
                }
            } 
        }

        ?>

        <div class="profil-edit">
            <div class="header">
                <div class="text-wrapper-2" onclick="window.location.href='../SearchPage/searchPage.php?token=<?php echo $token_auth; ?>'" style="cursor: pointer;">
                    Rateflix
                </div>                
                <button class="deco" onclick="window.location.href='../logout.php'">Déconnexion</button>
                <img class="user-cicrle-duotone" src="../Profil_Image/<?php echo $image; ?>"/> 
                <p class="name-user" id='userId'><?php echo $username; ?></p>
            </div>

            <div class="container">

                <div class="sidebar">
                    <!-- Contenu de la barre de navigation -->
                    
                    <div>
                        <button class="icon-button" onclick="window.location.href='homeProfil.php?token=<?php echo $token_auth; ?>'">
                            <img class="icon" src="icones/home.png" alt="Home Icon" />Voir profil
                        </button>
                    </div>

                    <div>
                        <button class="icon-button" onclick="window.location.href='editProfil.php?token=<?php echo $token_auth; ?>'">
                            <img class="icon" src="icones/parametre.png" alt="Settings Icon" />
                            Modifier profil
                        </button>
                    </div>
                </div>

                <div class="content-profil">
                    <!-- Contenu principal de la page -->
                    <img class="user-cicrle-2" src="../Profil_Image/<?php echo $image; ?>">
                    <form method="post" id="formImage" class="formImage" enctype="multipart/form-data">
                        <label for="profilePicture" class="label-file">Charger son Avatar (taille maximale : 2 Mo)</label>
                        <input type="file" name="profilePicture" id="photo">
                        <div class="overlap-2">
                            <input type="submit" value="Confirmer" id="idFormsend" name="formsendImage" class="login-button" />
                        </div>
                    </form>

                    <form method="post" id="formUsername" class="form">
                        <div class="overlap">
                            <input type="text" placeholder="Modifier le pseudo" name="username" id="idUsername" class="input-field" />
                            <input type="submit" value="Confirmer" id="idFormsendUsername" name="formsendUsername" class="login-button" />
                        </div>
                    </form>

                    <form method="post" id="formEmail" class="form">
                        <div class="overlap">
                            <input type="text" placeholder="Modifier l'email" name="email" id="idEmail" class="input-field" />
                            <input type="submit" value="Confirmer" id="idFormsendEmail" name="formsendEmail" class="login-button" />
                        </div>
                    </form>

                    <form method="post" id="formBirthday" class="form">
                        <div class="overlap">
                            <input type="date" placeholder="Modifier la date de naissance" name="birthday" id="idBirthday" class="input-field" />
                            <input type="submit" value="Confirmer" id="idFormsend" name="formsendBirthday" class="login-button" />
                        </div>
                    </form>

                    <form method="post" id="formPassword" class="form">
                        <div class="overlap">
                            <input type="password" placeholder="Modifier le mot de passe" name="password" id="idPassword" class="input-field" />
                            <input type="submit" value="Confirmer" id="idFormsendPassword" name="formsendPassword" class="login-button" />
                        </div>
                    </form>

                </div>
            </div>
        </div>
    </body>
</html>