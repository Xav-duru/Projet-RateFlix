<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <link rel="stylesheet" href="homeProfil.css" />
        <?php include ('../core.php'); ?>
    </head>
    <body>
        <?php
        $userId = $_SESSION['userId'];
        $username = $_SESSION["username"];
        $email = $_SESSION["email"];
        $image = $_SESSION['image'];
        $birthday = $_SESSION['birthday'];
        $dateObj = new DateTime($birthday);
        $formatter = new IntlDateFormatter('fr_FR', IntlDateFormatter::LONG, IntlDateFormatter::NONE);
        $dateFormatee = $formatter->format($dateObj);
        $token_auth = $_GET['token'];

        function displayStars($note) {
            $numberStarsFull = floor($note); // Partie entière de la note
            $starNotFull = $note - $numberStarsFull; // Partie fractionnaire de la note
        
            for ($i = 1; $i <= 5; $i++) {
                if ($i <= $numberStarsFull) {
                    // Star full
                    echo '<img class="frame-2" src="../MoviePage/images/star_full.png" alt="star full">';
                } elseif ($starNotFull >= 0.25 && $starNotFull < 0.50 && $i == $numberStarsFull + 1) {
                    // Star quart
                    echo '<img class="frame-2" src="../MoviePage/images/star_quart.png" alt="star quart">';
                } elseif ($starNotFull >= 0.50 && $starNotFull < 0.75 && $i == $numberStarsFull + 1) {
                    // Star half
                    echo '<img class="frame-2" src="../MoviePage/images/star_half.png" alt="star half">';
                } elseif ($starNotFull >= 0.75 && $starNotFull < 0.99 && $i == $numberStarsFull + 1) {
                    // Star 3quart
                    echo '<img class="frame-2" src="../MoviePage/images/star_3quart.png" alt="star 3quart">';
                } else {
                    // Star empty
                    echo '<img class="frame-2" src="../MoviePage/images/star_empty.png" alt="star empty">';
                }
            }
        }

        if(isset($_POST['delete'])) {
            if (isset($_POST['reviewId'])) {
                $idReview = $_POST['reviewId'];
                echo $idReview;
        
                $sql = "DELETE FROM review WHERE reviewId = '".$idReview."'";
                $result = $mysqli->query($sql);
        
                if (!$result) { 
                    exit($mysqli->error);
                }
        
                header('location:homeProfil.php?token='.$token_auth);
            } else {
                echo "Erreur: reviewId non défini.";
            }

        }

        //----------------------------------RECUPERER LES AVIS----------------------------------
        
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

            <div class="content-profil">

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

                <div class="content">
                

                    <div class="profil-section">

                        <!-- Contenu principal de la page -->
                        <h2>Editer commentaires</h2>
                        <img class="user-cicrle-2" src="../Profil_Image/<?php echo $image; ?>">
                        <p class="infos-profil-separate">
                            <strong>Pseudo :</strong> <?php echo $username ?>
                        </p>
                        <p class="infos-profil-separate">
                            <strong>Mail :</strong> <?php echo $email ?>
                        </p>
                        <p class="infos-profil-separate">
                            <strong>Date de naissance :</strong> <?php echo $dateFormatee ?>
                        </p>
                    </div>

                    <div class="avis-section">


                        <div class="advices">
                            <?php
                            // -------------------------DISPLAT REVIEW-----------------------------------------------


                            // Récupération des avis pour le film en question
                            
                            $sql = "SELECT reviewId, movieId, mark, comment, date 
                            FROM review 
                            WHERE userID = '".$userId."'";

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
                            
                            <h2 class="title-review">Retrouvez tous vos avis RateFlix !</h2>
                            <?php if ($nb > 0): ?>
                            <?php foreach ($reviews as $review): ?>

                            <div class="reviews-container">

                                <?php
                                $sql = "SELECT name, image
                                FROM movie 
                                WHERE movieId = '".$review['movieId']."'";
                    
                                $result = $mysqli->query($sql);
                                $row = $result->fetch_assoc();


                                $movie_comment = $row['name'];
                                $movie_image = $row['image'];

                                ?>

                                <img src="../Movie_Cover/<?php echo $movie_image; ?>" alt="movie" class="movie-image" />


                                
                                <form method="post" >
                                    <input type="hidden" name="reviewId" value="<?php echo $review['reviewId']; ?>">
                                    <button type="submit" class="delete-icon" name="delete">
                                        <img class="trash" src="icones/trash.png"  alt="Supprimer"/>
                                    </button>
                                </form>
                                
                                <p class="movie"><?php echo $movie_comment; ?></p>
                                <?php echo $review['mark']; ?>
                                <div class="comment">
                                    <?php echo $review['comment']; ?>
                                </div>
                                <div class="stars">
                                    <?php displayStars($review['mark']) ?>
                                </div>
                                <p class="date"><?php echo date("d/m/Y", strtotime($review['date'])); ?></p>
                                
                            </div>

                            <?php endforeach; ?>
                            <?php else: ?>
                            <p>Aucun avis disponible pour ce film.</p>
                            <?php endif; ?>                                
                            <div class="overlap-3">
                                <img class="img-2" src="img/profil-edit-2.png" /> <img class="img-3" src="img/profil-edit.png" />
                            </div>

                        </div>
                    </div>
                </div>
            </div>

        </div>

    </body>
</html>
