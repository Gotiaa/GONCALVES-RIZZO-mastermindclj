(ns app.user)
(require '[clojure.string :as str])
(require '[app.sources :as src])

(declare entrer-code)
(declare isCompatible)
(declare isColor)
(declare check-code)
(declare end-game)



(def colors #{:rouge, :bleu, :jaune, :vert, :noir, :blanc})
(def g :good)
(def taille 4)
(def nbTenta 6) ;Nombre de tentative pour le joueur ;TODO : rendre paramétrable ?



(declare game)
(defn game []
  (let [soluce (src/code-secret taille)]
    (entrer-code soluce 0)))


(defn entrer-code [sol cpt];Boucle pour entrer un code isCompatible et verifie ce code
  (println "||--------- Tentative actuelle :" (inc cpt) "sur" nbTenta "----------||") ;TODO : gérer la tentative 7
  (if (= cpt nbTenta) ;on vérifie s'il reste des tentatives; 
    (end-game 0 sol)
    ;si oui on continue la partie
    (or (println "||            Entrez un code du type :             ||\n||       ':couleur ... :couleur' de taille"taille"      ||\n|| parmi" colors"||\n")
      (let [input (str/trim (read-line))] ;récupère l'input
        (if (isCompatible (src/str-to-key input)) ;On vérifie si l'input est de la bonne forme
          (check-code sol (src/str-to-key input) cpt) ;vérifie les correspondance entre l'input et le code secret
          (or (println "=> Le code n'est pas compatible\n") (entrer-code sol cpt)))))));si le code n'est pas isCompatible on relance la tentative



(defn isCompatible [vec];Vérifie si l'input est de la bonne forme
  (loop [s vec,cpt 0]
    (if (seq s)
      (if (isColor
       (first s)) ;Check si first s est une couleur
        (recur (rest s) (inc cpt))
        false)
      (if (not= cpt taille) ;Check si la longueur du code est bien 4
        false
        true))))

(defn isColor
 [str]
  (if (contains? colors str)
    true
    false))



(defn check-code [soluce input cpt];check si le code est bon
  (let [check (src/filtre-indications soluce input (src/indications soluce input))]
    (if (= check [g g g g]);si toutes les indications sont good c'est gagné
      (end-game 1 soluce)
      (or (println "=>" check "\n") (entrer-code soluce (inc cpt))))))



(defn end-game [i sol]
  (if (= i 1)
    (or (println "Bravo tu as trouvé la composition secrète, tu es super fort !") true)
    (or (println "Perdu, la combinaison était : " sol ".") false)))
