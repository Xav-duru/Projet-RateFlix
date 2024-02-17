<script>
      document.getElementById('form').addEventListener('submit', function (event) {
         // Empêcher la soumission immédiate du formulaire
         event.preventDefault();

         // Désactiver le formulaire pendant le délai
         document.getElementById('form').classList.add('desactive');

         // Ajouter un délai de 3 secondes (3000 millisecondes) avant de soumettre le formulaire
         setTimeout(function () {
            // Réactiver le formulaire
            document.getElementById('form').classList.remove('desactive');

            // Soumettre le formulaire après le délai
            document.getElementById('form').submit();
         }, 3000); // 3000 millisecondes = 3 secondes
      });
   </script>