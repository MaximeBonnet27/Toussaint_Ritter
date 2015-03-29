# Toussaint & Ritter

## Description des packages : 
- Algorithms : Contient les classes implantant les algorithmes principaux (Toussaint, Ritter & Graham)
- Geometry : Classes n�cessaires pour calculs g�om�triques
- Mains : Classes executables
- Tools : Une classe, gestion des fichiers tests
- View : Classes Swing

## Commandes ant pour tester le code
- test : Lance MainTestFichiers, permet de tester les performances des algorithmes. Cr�e un fichier output.csv. Ne marche s�rement que sous linux (execution d'un script shell).
- ui : Lance MainUI, qui lance une JFrame dans laquelle on peut visualiser les r�sultats sur les fichiers de test.
Commandes : 
-"N" : passe au fichier suivant,
-"H" : enveloppe convexe,
-"R" : rectangle minimum (requiert H),
-"C" : cercle minimum
-"Q" : quitter
"PageUP / DOWN" : zoom / dezoom (pas tr�s utile).
- step : Lance AlgoMain, qui lance une JFrame dans laquelle on peut visualiser la progression du rectangle dans l'algorithme de Toussaint.
Commandes : 
-"N" : passe au fichier suivant,
-"R" : rectangle suivant.
