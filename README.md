# Toussaint & Ritter

## Description des packages : 
- Algorithms : Contient les classes implantant les algorithmes principaux (Toussaint, Ritter & Graham)
- Geometry : Classes nécessaires pour calculs géométriques
- Mains : Classes executables
- Tools : Une classe, gestion des fichiers tests
- View : Classes Swing

## Commandes ant pour tester le code
- test : Lance MainTestFichiers, permet de tester les performances des algorithmes. Crée un fichier output.csv. Ne marche sûrement que sous linux (execution d'un script shell).
- ui : Lance MainUI, qui lance une JFrame dans laquelle on peut visualiser les résultats sur les fichiers de test.
Commandes : 
-"N" : passe au fichier suivant,
-"H" : enveloppe convexe,
-"R" : rectangle minimum (requiert H),
-"C" : cercle minimum
-"Q" : quitter
"PageUP / DOWN" : zoom / dezoom (pas très utile).
- step : Lance AlgoMain, qui lance une JFrame dans laquelle on peut visualiser la progression du rectangle dans l'algorithme de Toussaint.
Commandes : 
-"N" : passe au fichier suivant,
-"R" : rectangle suivant.
