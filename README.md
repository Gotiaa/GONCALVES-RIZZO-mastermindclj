# Mastermind

Jeu Mastermind dans le terminal

## Comment jouer

Lancer la commande dans le terminal dans le dossier du jeu :

    $ lein run


## Règles 

Devinez la composition secrète générée aléatoirement en entrant des propositions sous la forme : ":couleur :couleur :couleur :couleur" parmis les couleurs ":bleu :rouge :jaune :vert :noir :blanc". Exemple :

    => :rouge :bleu :vert :jaune

L'application va répondre par un vecteur de trois indications parmis :good :bad :color. 
 :good : La couleur est présente et bien positionnée
 :bad : La couleur n'est pas présente du tout
 :color : la couleur est présente mais mal positionnée

Changez votre réponse en conséquence afin de résoudre la composition secréte en 6 tentatives ou moins ! Bonne chance !


## Développement

- Nous avons décidé de reprendre la logique du jeu mastermind et de l'écrire en clojure.
- Nous avons trouvé une méthode pour transformer une string en vecteur de mot clé que nous avons décidé d'utiliser pour gérer les couleurs et les indications

