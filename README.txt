Bianca Bica (20161056)
Chaima Boussora (20159909)

Lien du repositoire: https://github.com/chaima-235/IFT3913---TP1.git

Comment exécuter le code: 

Dépendamment du système d'exploitation utilisé pour rouler le code:
-Mac OSX: Dans le fichier Jls.java à la ligne 38, Lcsec à la ligne 125, Egon.java à la ligne 176 --> il faut mettre un "," au lieu de ";" pour la méthode append.
-Windows: Dans le fichier jls.java à la ligne 38, Lcsec à la ligne 125, Egon.java à la ligne 176 --> il faut mettre un ";" pour la méthode append.

-Les fichiers CSV sont écrasés lors de l'insertion d'un nouveau chemin d'accès. Il faut sauvegarder une copie des fichiers CSV manuellement afin de pouvoir conserver les données. 

-Les fichiers CSV sont créés dans le dossier tp1.
  -file.csv : Partie 0
  -fileCsec.csv : Partie 2
  -fileEgon.csv : Partie 3

-Les chemins d'accès (paths) fonctionnent peu importe le système d'exploitation, il faut juste s'assurer de bien les écrire en fonction de Windows/Mac/Linux. 

-Nous avons créé une classe "Service.java" pour la fonction Main qui exécute toutes les fonctions voulues.

-Les résultats de la partie 0 sont affichés dans le terminal tel que mentionné dans l'énoncé.

-Il faut attendre environ 7 minutes avant que les résultats s'affichent à l'écran, voir la section "informations pertinentes" pour plus de détails. 


Informations pertinentes:

-Nous sommes conscientes que notre code n'est pas très efficace ou optimal car la durée de générer le fichier Egon.csv est d'environ 7 minutes lorsqu'on teste avec le dossier de la partie 4. Cela est dû au fait que nous avons créé notre code en fonction du dossier ckjm et que le fichier jfree-master est considérablement plus gros. 

-S'il n'y a pas de data à insérer dans le fichier CSV, il y aura toujours par défaut un point virgule qui s'affiche à cause de l'interpréteur CSV et du formattage. 

