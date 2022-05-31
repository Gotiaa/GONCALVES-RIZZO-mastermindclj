(ns app.core)
(require '[app.user :as u])

(defn -main []
  (println "Prêt à lancer la partie ? (Appuyez sur une entrer pour commencer ou ecrivez exit pour quitter)")
  (let [x (read-line)]
    (if (= x "exit")
      0
      (u/game))))
