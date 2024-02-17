<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <link rel="stylesheet" href="movieProfil.css" />
    <?php include ('../core.php'); ?>
</head>
<body>
    <?php
    
    $userId = $_SESSION['userId'];
    $username = ucfirst($_SESSION['username']);    //Permet de mettre une majuscule en début de mot
    $image_user = $_SESSION['image'];

    $name=str_replace("_", " ", $_GET['movie']);
    $token_auth = $_GET['token'];

    $sql = "SELECT *
    FROM movie
    WHERE name = '".$name."'";

    $result = $mysqli->query($sql);

    //Récupère le nombre de ligne dont le résultat est vrai
    $nb = $result->num_rows;

    if (!$result) { 
        exit($mysqli->error);
    }
    else if($nb) { 
        $row = $result->fetch_assoc();
        $movieId=$row['movieId'];
        $director=$row['director'];
        $releaseYear=$row['releaseYear'];
        $description=$row['description'];
        $image=$row['image'];
    }

    $sql_mark = "SELECT mark
    FROM review
    WHERE movieId = '".$movieId."'
    AND userId = '".$userId."'";

    $result_mark = $mysqli->query($sql_mark);

    //Récupère le nombre de ligne dont le résultat est vrai
    $nb_mark = $result_mark->num_rows;

    if (!$nb_mark) { 
        $user_mark = "Pas de note";
    }
    else { 
        //Créé un tableau avec les différentes valeurs
        $row = $result_mark->fetch_assoc();
        $user_mark=$row['mark'] . " /5";
    }


    $sql = "SELECT AVG(mark) AS average
    FROM review
    WHERE movieId = '".$movieId."'";

    $result = $mysqli->query($sql);

    //Récupère le nombre de ligne dont le résultat est vrai
    $nb = $result->num_rows;

    if (!$result) { 
        exit($mysqli->error);
    }
    else if($nb) { 
        //Créé un tableau avec les différentes valeurs
        $row = $result->fetch_assoc();
        $average=number_format($row['average'],1);
    }

    function displayStars($note, $size = 20) {
        $numberStarsFull = floor($note); // Partie entière de la note
        $starNotFull = $note - $numberStarsFull; // Partie fractionnaire de la note
    
        $starSize = 'width: ' . $size . 'px;'; // Définir la taille des étoiles
    
        for ($i = 1; $i <= 5; $i++) {
            if ($i <= $numberStarsFull) {
                // Star full
                echo '<img class="frame-2" src="images/star_full.png" alt="star full" style="' . $starSize . ' border: 0;">';
            } elseif ($starNotFull >= 0.25 && $starNotFull < 0.50 && $i == $numberStarsFull + 1) {
                // Star quart
                echo '<img class="frame-2" src="images/star_quart.png" alt="star quart" style="' . $starSize . ' border: 0;">';
            } elseif ($starNotFull >= 0.50 && $starNotFull < 0.75 && $i == $numberStarsFull + 1) {
                // Star half
                echo '<img class="frame-2" src="images/star_half.png" alt="star half" style="' . $starSize . ' border: 0;">';
            } elseif ($starNotFull >= 0.75 && $starNotFull < 0.99 && $i == $numberStarsFull + 1) {
                // Star 3quart
                echo '<img class="frame-2" src="images/star_3quart.png" alt="star 3quart" style="' . $starSize . ' border: 0;">';
            } else {
                // Star empty
                echo '<img class="frame-2" src="images/star_empty.png" alt="star empty" style="' . $starSize . ' border: 0;">';
            }
        }
    }
    
    

    
    ?>
    <div class="movie-page">
        <div class="header">
            <div class="text-wrapper-2" onclick="window.location.href='../SearchPage/searchPage.php?token=<?php echo $token_auth; ?>'" style="cursor: pointer;">
                Rateflix
            </div>
            <button class="deco" onclick="window.location.href='../logout.php'">Déconnexion</button>
            <script src="script.js"></script>
            <img class="user-circle-duotone" src="../Profil_Image/<?php echo $image_user ?>" onclick="goToProfil('<?php echo $token_auth; ?>')" />
            <p class="name-user" onclick="goToProfil('<?php echo $token_auth; ?>')"><?php echo $username ?> </p>

        </div>  
        <div class="half">
            <img class="rectangle" src="../Movie_Cover/<?php echo $image; ?>" />
            <div class="text-wrapper-5"><?php echo $name; ?></div>
            <h2 class="director">Réalisateur : <?php echo $director ?></h2>
            <p class="p">
                <?php echo $description ?>
            </p>
            <div class="text-wrapper">Moyenne :</div>

            <div class="frame">
                <?php
                displayStars($average);
                ?>
                <div class="text-wrapper"><?php echo $average; ?></div>
            </div>
            <p class="text-wrapper"><span class="text-wrapper">⭐ Ma note : <?php echo $user_mark ?></span></p>
        </div> 

        <div class="half">
            <?php
            
            $movie_name = $_GET['movie'];
            $add_review_Err="";

            if ($_SERVER['REQUEST_METHOD'] === 'POST') {


                $sql_check = "SELECT userId, mark
                FROM review
                WHERE movieId = '".$movieId."'
                AND userId = '".$userId."'";

                $result_check = $mysqli->query($sql_check);

                //Récupère le nombre de ligne dont le résultat est vrai
                $nb_check = $result_check->num_rows;

                if ($nb_check) { 
                    $add_review_Err = "Vous avez déjà ajouté un avis pour ce film !";
                    header('location:movieProfil.php?movie='.$movie_name.'&token='.$token_auth);

                }
                else { 

                    echo "good";

                    $rating = $_POST['rating'];
                    $rating = floatval($rating);
                    $comment = $_POST['comment'];
                    $date = $_POST['date'];

                    $sql_count = "SELECT reviewId
                    FROM review";

                    $result_count = $mysqli->query($sql_count);

                    $sql_add = "INSERT INTO review (movieId, userId, mark, comment, date) 
                    VALUES ('$movieId', '$userId', '$rating', '$comment',  '$date')";


                    if (mysqli_query($mysqli, $sql_add)) {
                        echo "good";

                        $confirmeCreation = 'Bravo ! Ton compte a bien été créé !';
                        header('location:movieProfil.php?movie='.$movie_name.'&token='.$token_auth);
                    } 
                    else {
                        echo "Erreur : " . $sql_add . "<br>" . mysqli_error($mysqli);
                    }

                }   
            }
            ?>

            <!-- Formulaire d'avis -->
            <div class="add-review">
                <h2>Ajoute ton avis !</h2>  
                <form method="post" action="">
                    <label>Mon pseudo : <strong><?php echo $username; ?></strong></label>
                    <input type="hidden" name="movie_id" value="<?php echo $movieId; ?>">
                    <label for="rating">Note :</label>
                    <select class="rating" name="rating" required>
                        <?php
                        // Utilisation d'une boucle pour générer les options de 1 à 5
                        for ($i = 1; $i <= 5; $i++) {
                            echo '<option value="' . $i . '">' . $i . '</option>';
                        }
                        ?>
                    </select>
                    <input type="date" id="dateContainer" name="date" hidden></input>
                    <script>
                        document.getElementById("dateContainer").valueAsDate = new Date();
                    </script>
                    <label for="comment">Commentaire:</label>
                    <textarea name="comment" required></textarea>
                    <button type="submit">Ajouter l'avis</button>
                </form>
            </div>

        </div>
        
        <div class="advices">

            <?php
            // Récupération des avis pour le film en question
            
            $sql = "SELECT * 
            FROM review 
            WHERE movieId = '".$movieId."'";

            $result = $mysqli->query($sql);

            //Récupère le nombre de ligne dont le résultat est vrai
            $nb = $result->num_rows;
            
            if (!$result) { 
                exit($mysqli->error);
            }
            else { 
                // Récupère le nombre de lignes dont le résultat est vrai
                $nb = $result->num_rows;

                if ($nb > 0) {
                    // Crée un tableau avec les différentes valeurs
                    $reviews = $result->fetch_all(MYSQLI_ASSOC);
                }
            }
            ?>

            <!-- Affichage des avis existants -->
            <div>
                <h2>Commentaires</h2>
                <?php if ($nb > 0): ?>
                    <?php foreach ($reviews as $review): ?>
                        <div class="reviews-container">

                            <?php
                            $sql = "SELECT username 
                            FROM user 
                            WHERE userId = '".$review['userId']."'";
                
                            $result = $mysqli->query($sql);
                            $row = $result->fetch_assoc();

    
                            $username_comment = $row['username'];
                            ?>

                            <p><text><strong><?php echo $username_comment; ?></strong></text></p>
                            <p><text><strong><?php echo $review['mark']; ?>/5</strong></text></p>
                            <p><?php displayStars($review['mark']) ?></p>
                            <p><text><?php echo $review['comment']; ?></text></p>
                            <p><text><small><?php echo $review['date']; ?></small></text></p>
                        </div>
                    <?php endforeach; ?>
                <?php else: ?>
                    <p>Aucun avis disponible pour ce film.</p>          
                <?php endif; ?>
            </div>

            <?php
            $mysqli->close();
            ?>
        </div>
    </div>
</body>
</html>
