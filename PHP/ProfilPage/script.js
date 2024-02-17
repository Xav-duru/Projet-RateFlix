function goBackToMovie(user) {
    // Construire l'URL avec les paramètres
    var nouvellePageURL = "../SearchPage/searchPage.php?token=" + encodeURIComponent(user);

    // Rediriger l'utilisateur vers la nouvelle page
    window.location.href = nouvellePageURL;
}