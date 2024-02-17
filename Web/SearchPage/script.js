function rechercherOptions() {
    // Récupérer la valeur de la barre de recherche
    var recherche = document.getElementById('recherche').value;

    // Récupérer l'élément de la liste déroulante
    var listeDeroulante = document.getElementById('listeDeroulante');

    // Récupérer l'élément du lien
    //var moviePhoto = document.getElementById('idMoviePhoto');
    var conteneurImages = document.getElementById('conteneurImages');

    //var user = document.getElementById('userId');
    var token = document.getElementById('token');


    // Effacer les options existantes dans la liste déroulante
    listeDeroulante.innerHTML = '';
    //moviePhoto.innerHTML = '';
    conteneurImages.innerHTML = '';



    // Effectuer une requête AJAX vers le serveur PHP
    var xhr = new XMLHttpRequest();

    // Définir la fonction qui sera appelée chaque fois que l'état de la requête change
    xhr.onreadystatechange = function () {
        // Vérifier si la requête est terminée (readyState = 4)
        if (xhr.readyState === XMLHttpRequest.DONE) {
            // Vérifier si la requête a réussi (status = 200)
            if (xhr.status === 200) {
                try {
                // Analyser la réponse JSON du serveur
                var options = JSON.parse(xhr.responseText);

                // Ajouter les options à la liste déroulante
                options.forEach(function (option) {
                    //Ajouter à la liste déroulante
                    var nouvelOption = document.createElement('option');
                    nouvelOption.text = option.name;
                    listeDeroulante.add(nouvelOption);

                    //Créer l'élément image
                    var newImage = document.createElement('img');
                    console.log('newImage = ' + newImage);
                    var source = option.image.replace(/ /g, "_");
                    newImage.src = "../Movie_Cover/" + source;
                    newImage.alt = option.name;
                    console.log('newImage.src = ' + newImage.src);
                    console.log('newImage.alt = ' + newImage.alt);
                    console.log('newImage.href = ' + newImage.href);

                    var newUser =document.createElement('p');
                    newUser.textContent = token.textContent;
                    console.log('user = ' + newUser.text);

                    //Ajouter l'élément image à un div
                    conteneurImages.appendChild(newImage);
                    console.log('conteneurImages = ' + conteneurImages);

                    var link = "../MoviePage/movieProfil.php";
                    console.log(link);
                        newImage.onclick = function() {
                            window.location.href = link + "?token="+encodeURIComponent(newUser.text)+"&movie="+encodeURIComponent(nouvelOption.text);
                        };

                    //Ajouter le lien à l'image
                    //var link = "moviePage/"+nouvelOption.text+".php";
                    //console.log(link);
                    //moviePhoto.src = option.image;
                    //console.log(moviePhoto.src);
                    //moviePhoto.href = link;
                    });
                } catch (e) {
                    console.error('Erreur lors de la conversion JSON : ' + e.message);
                }
            } else {
                // Gérer les erreurs éventuelles
                console.error('Erreur de requête AJAX : ' + xhr.status);
            }
        }
    };

    

    // Configurer la requête AJAX (GET) vers le fichier PHP avec la valeur de recherche en paramètre
    xhr.open('GET', 'recherche.php?recherche=' + recherche, true);

    // Envoyer la requête
    xhr.send();
}

function goToMovie(user, movie) {
    // Construire l'URL avec les paramètres
    var nouvellePageURL = "../MoviePage/movieProfil.php?token=" + encodeURIComponent(user) + "&movie=" + encodeURIComponent(movie);

    // Rediriger l'utilisateur vers la nouvelle page
    window.location.href = nouvellePageURL;
}

function goToProfil(user) {
    // Construire l'URL avec les paramètres
    var nouvellePageURL = "../ProfilPage/homeProfil.php?token=" + encodeURIComponent(user);

    // Rediriger l'utilisateur vers la nouvelle page
    window.location.href = nouvellePageURL;
}