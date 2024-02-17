function goToProfil(user) {
    // Construire l'URL avec les paramètres
    var nouvellePageURL = "../ProfilPage/homeProfil.php?token=" + encodeURIComponent(user);

    // Rediriger l'utilisateur vers la nouvelle page
    window.location.href = nouvellePageURL;
}