cat tmp.csv | sort -n -t '-' -k 2 > output.csv
sed -i 1i"Nom du fichier,Aire du Rectangle,Aire de l'enveloppe,Ratio Rectangle, Ratio Cercle" output.csv 
