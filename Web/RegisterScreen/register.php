<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <link rel="stylesheet" href="register.css" />
    <title>RateFlix</title>
    <?php include ('../core.php'); ?>
</head>
<body>
    <div class="register-screen">
        <div class="header"> 
            <div class="text-wrapper-6">Rateflix</div>
        </div>

        <?php
        $usernameErr = $emailErr = $passwordErr = $passwordErr = $confirmPasswordErr ="";
        $username = $email = $password = $confirmPassword ="";
        $confirmeCreation = "";

        if(isset($_POST['formsend'])){
            if(empty($_POST['username'])){ $usernameErr = 'Username is required';}
            else{$username  = $_POST['username'];}

            if(empty($_POST['birthday'])){$birthday  = NULL;}
            else{$birthday  = $_POST['birthday'];}
            
            if(empty($_POST['email'])){$emailErr = 'email is required';} 
            else { $email  = $_POST['email'];}

            if(empty($_POST['password'])){ $passwordErr = 'password is required';}
            else{
                $password  = $_POST['password'];
                $hashedPassword = password_hash($password, PASSWORD_DEFAULT);
            }

            if($_POST['confirmPassword']!=$_POST['password']){ $confirmPasswordErr = "passwords don't match";}
            else{$confirmPassword  = $_POST['password'];}

            /**
             * Lorsque tous les champs ont été correctement rempli, et que le bouton Soumettre a été appuyé, on cherche à ajouter
             * l'utilisateur dans la BDD.
             */
            if($usernameErr =="" && $emailErr =="" && $passwordErr =="" && $confirmPasswordErr ==""){
            
                $sql_count = "SELECT userId
                FROM user";

                $result_count = $mysqli->query($sql_count);

                //Récupère le nombre de ligne dont le résultat est vrai
                $nb_count = $result_count->num_rows;

                //On fait +1 pour ajouter le nouvel utilisateur à la ligne suivante
                $nb_add = $nb_count+1;
                                
                $sql_add = "INSERT INTO user (userId, username, password, email, birthday, admin)
                VALUES('$nb_add', '$username', '$hashedPassword', '$email', '$birthday',  0)";
                if (mysqli_query($mysqli, $sql_add)) {
                    $confirmeCreation = 'Bravo ! Ton compte a bien été créé !';
                    header('location:../LoginScreen/login.php');
                } 
                else {
                    // Vérification si l'erreur est due à une violation d'unicité (email déjà utilisé)
                    if ($mysqli->errno == 1062) {
                        $emailErr = 'Email already exists';
                    } else {
                        echo "Erreur : " . $mysqli->error;
                    }
                }

            }  
        }
        ?>
        <div class="middle">
            <form method="post" class="form">
                <input type="text" placeholder="Pseudo" name="username" id="idUsername" class="input-field"/>
                <span class="error">* <?php echo $usernameErr?> </span>
                <input type="text" placeholder="Adresse email" name="email" id="idEmail" class="input-field" />
                <span class="error">* <?php echo $emailErr?> </span>
                <input type="date" placeholder="Date d'anniversaire" name="birthday" id="idBirthday" class="input-field" />
                <input type="password" placeholder="Mot de passe" name="password" id="idPassword" class="input-field" />
                <span class="error">* <?php echo $passwordErr?> </span>
                <input type="password" placeholder="Confirmation mot de passe" name="confirmPassword" id="idConfirmPassword" class="input-field" />
                <span class="error">* <?php echo $confirmPasswordErr?> </span>
                <input type="submit" value="Création de compte" name="formsend" id="idFormsend" class="login-button" />
            </form>
            <p class="already-have-an">
                <span class="span">Tu as déjà un compte ? </span>
                <a href="../LoginScreen/login.php" class="text-wrapper-5">Connexion</a>
            </p>
        </div>
        
    </div>
</body>
</html>

