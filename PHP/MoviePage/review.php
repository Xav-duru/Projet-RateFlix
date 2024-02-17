<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="movieProfil.css" />
    <?php include ('../core.php'); ?>

</head>
<body>
    
<?php
$movie_name = $_GET['movie'];

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $movieId = 2; // Remplacez par l'ID du film en cours
    $userId = 1; // Remplacez par l'ID de l'utilisateur en cours
    $rating = $_POST['rating'];
    $comment = $_POST['comment'];
    $date = $_POST['date'];

    $sql_count = "SELECT reviewId
    FROM review";

    $result_count = $mysqli->query($sql_count);

    //Récupère le nombre de ligne dont le résultat est vrai
    $nb_count = $result_count->num_rows;

    //On fait +1 pour ajouter le nouvel utilisateur à la ligne suivante
    $nb_add = $nb_count+1;
    echo $nb_add;
    
    // Insertion de l'avis dans la base de données
    //$sql_add = $mysqli->prepare("INSERT INTO review (reviewId, movieId, userId, mark, comment, date) VALUES (?, ?, ?, ?, ?, ?)");
    //$sql_add->execute([$nb_add, $userId, $movieId, $rating, $comment,  $date]);

    $sql_add = "INSERT INTO review (reviewId, movieId, userId, mark, comment, date) 
    VALUES ('$nb_add', '$userId', '$movieId', '$rating', '$comment',  '$date')";

    if (mysqli_query($mysqli, $sql_add)) {
        echo "ddddddddddddddddddddddddddddddddddd";

        $confirmeCreation = 'Bravo ! Ton compte a bien été créé !';
        header('location:review.php?movie=Avatar');
    } 
    else {
        echo "Erreur : " . $sql_add . "<br>" . mysqli_error($mysqli);
    }

    //header('location:../MoviePage/movieProfil.php?movie='.$movie_name);
}

// Récupération des avis pour le film en question
$movieId = 2;
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
    <h2>Reviews</h2>
    <?php if ($nb > 0): ?>
        <?php foreach ($reviews as $review): ?>
            <div>
                <p>Rating: <?php echo $review['mark']; ?></p>
                <p>Comment: <?php echo $review['comment']; ?></p>
                <p>Date: <?php echo $review['date']; ?></p>
            </div>
        <?php endforeach; ?>
    <?php else: ?>
        <p>Aucun avis disponible pour ce film.</p>
        
    <?php endif; ?>
</div>

<!-- Formulaire d'avis -->
<div>
    <h2>Add a Review</h2>
    <form method="post" action="">
        <input type="hidden" name="movie_id" value="<?php echo $movieId; ?>">
        <label for="rating">Rating:</label>
        <input type="number" name="rating" min="1" max="5" required>
        <br><br>
        <input type="date" id="dateContainer" name="date"></input>
        <br>
        <script>
            // Obtenir la date actuelle en JavaScript
            // Afficher la date dans un élément HTML avec l'ID "dateContainer"
            document.getElementById("dateContainer").valueAsDate = new Date();
        </script>

        <label for="comment">Comment:</label>
        <textarea name="comment" required></textarea>
        <br>
        <button type="submit">Submit Review</button>
    </form>
</div>
</body>
</html>