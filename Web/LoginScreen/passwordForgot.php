<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <link rel="stylesheet" href="login.css" />
    <title>RateFlix</title>
    <?php include ('../core.php'); ?>
</head>
<body>
    <div class="login-screen">
        <div class="header">
            <div class="text-wrapper-6">Rateflix</div>
        </div>
        
        <?php
        $connexionErr ="";
        if(isset($_POST['formsend'])){
            $email = $_POST['email'];
            $password = $_POST['password'];
            $confirmPassword = $_POST['confirmPassword'];
            if(!empty($email) && !empty($password)&& !empty($confirmPassword)){
                //La fonction real_escape_string, fonction de mysqli, protège les caractères spéciaux
                //d’une chaîne pour en permettre l’utilisation dans une requête SQL.
                $email_escaped = $mysqli->real_escape_string(trim($email));
                if($_POST['confirmPassword']!=$_POST['password']){ 
                    $connexionErr = "Les mots de passe ne correspondent pas";
                }
                else{
                    $hashedPassword = password_hash($password, PASSWORD_DEFAULT);

                    $sql = "UPDATE user
                    SET password = '".$hashedPassword."'
                    WHERE email = '".$email."'";

                    $result = $mysqli->query($sql);

                    if ($result === TRUE) { 
                        // Mise à jour réussie
                        $mysqli->close();
                        header('location:login.php');
                    } else {
                        // Erreur lors de la mise à jour
                        exit($mysqli->error);
                    }                 
                } 
            } else {
                $connexionErr = 'Veuillez remplir tous les champs';
            }
        }
        
        ?>
        <div class="middle">
            <form method="post" class="form">
                <input type="text" placeholder="Email" name="email" id="idemail" class="input-field" />
                <input type="password" placeholder="Nouveau mot de passe" name="password"  id="idPassword" class="input-field" />
                <input type="password" placeholder="Confirmation mot de passe" name="confirmPassword" id="idConfirmPassword" class="input-field" />
                <input type="submit" value="Confirmer le changement de mot de passe" id="idFormsend" name="formsend" class="login-button" />
            </form>
            <p class="you-don-t-have-an">
            <span class="text-wrapper-5"><?php echo $connexionErr?> </span>
                <a href="login.php"class="text-wrapper-5">Se connecter </a>            
            </p>  

        </div>
        
    </div>
</body>
</html>
