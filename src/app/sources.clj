(ns app.sources)
(require '[clojure.string :as str])


(declare code-secret)
(declare frequences)
(declare filtre-indications)
(declare indications)
(declare contientV)
(declare str-to-key)

(defn code-secret [n]
  (loop [i 1,res []]
    (if (> i n)
      res
      (recur (inc i) (conj res (rand-nth [:rouge :bleu :vert :jaune :noir :blanc]))))))

(defn frequences [v]
  (loop [s v,res {}]
    (if (seq s)
      (recur (rest s)
        (assoc res (first s) (inc (get res (first s) 0))))
      res)))


(defn filtre-indications [code, try, indic];renvoi les bonnes indications
  (loop [index 0, res [], freq (frequences code)]
    (if (< index (count code))
      (let [color (get try index), tag (get indic index)]
        (if (= tag :bad)
          (recur (inc index), (conj res tag), freq)
          (if (> (get freq color) 0)
            (recur (inc index), (conj res tag), (update freq color dec))
            (recur (inc index), (conj res :bad), freq))))
      res)))


(defn contientV [v i];Check si i est contenu dans le vecteur v
  (loop [s v]
    (if (seq s)
      (if (= i (first s))
        true
        (recur (rest s)))
      false)))

(defn indications [sol v];indique les :good | :color | :bad en fonction des positions
  (loop [s v,res [], m sol]
    (if (seq s)
      (if (= (first s) (first m))
          (recur (rest s) (conj res :good) (rest m))
          (if (contientV sol (first s))
            (recur (rest s) (conj res :color) (rest m))
            (recur (rest s) (conj res :bad) (rest m))))
      res)))

(defn str-to-key [str];transforme un string en vecteur de keywords (méthode trouvée sur internet)
  (let [vec (str/split str #" ")]
    (loop [s vec,res []]
      (if (seq s)
        (recur (rest s) (conj res (keyword (second (str/split (first s) #":")))))
        res))))
