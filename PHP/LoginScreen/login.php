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
            $username = $_POST['username'];
            $password = $_POST['password'];
            if(!empty($username) && !empty($password)){
                //La fonction real_escape_string, fonction de mysqli, protège les caractères spéciaux
                //d’une chaîne pour en permettre l’utilisation dans une requête SQL.
                $username_escaped = $mysqli->real_escape_string(trim($username));
                $password_escaped = $mysqli->real_escape_string(trim($password));
                
                $sql = "SELECT *
                FROM user
                WHERE username = '".$username_escaped."'";

                $result = $mysqli->query($sql);
                $connexionErr = 'Pseudo ou mot de passe incorrect';

                if (!$result) { 
                    $mysqli->error;
                }
                else if ($result->num_rows > 0) { 
                    $row = $result->fetch_assoc();
        
                    // Utilisation de password_verify pour comparer le mot de passe
                    if (password_verify($password_escaped, $row['password'])) {
                        $token_auth = bin2hex(random_bytes(16));
                        $_SESSION['userId'] = $row['userId'];
                        $_SESSION['username'] = $row['username'];
                        $_SESSION['email'] = $row['email'];
                        $_SESSION['birthday'] = $row['birthday'];
                        $_SESSION['image'] = $row['image'];
                        $_SESSION['token_auth'] = $token_auth;
        
                        $mysqli->close();
                        header('location:../SearchPage/searchPage.php?token='.$token_auth);
                    } 
                }    
            } 
        }
        ?>
        <div class="middle">
            <form method="post" id="form">
                <div class="overlap">
                    <input type="text" placeholder="Pseudo" name="username" id="idUsername" class="input-field" />
                </div>
                <div class="overlap">
                    <input type="password" placeholder="Mot de passe" name="password"  id="idPassword" class="input-field" />
                </div>
                <div class="overlap-2">
                    <input type="submit" value="Connexion" id="idFormsend" name="formsend" class="login-button" />
                </div>
            </form>
            <p class="you-don-t-have-an">
                <span class="span">Tu n'as pas de compte ? </span>
                <a href="../RegisterScreen/register.php" class="text-wrapper-5">Créer son compte</a>
            </p>
            <p class="you-don-t-have-an">
                <a href="passwordForgot.php" span class="span">Mot de passe oublié ? </a>
            </p>

            <span class="text-wrapper-5"><?php echo $connexionErr?> </span>

        </div>
        
    </div>
</body>
</html>
