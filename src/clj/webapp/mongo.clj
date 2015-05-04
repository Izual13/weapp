(ns webapp.mongo
  (:require [monger.core :as mg]
            [monger.collection :as mc]
            [webapp.utils :as utils]
            [monger.json])
  (:import [com.mongodb MongoOptions ServerAddress]
           [org.bson.types ObjectId]
           [com.mongodb DB WriteConcern]))


(def db (mg/get-db (mg/connect) "clojure"))
(def coll-messages "messages")

;; (def message {:header "Astronomers Found a Gas Giant Orbiting Surprisingly Close to a Tiny Star"
;;               :body "Astronomers watching a small red dwarf star 500 light years away were surprised to notice a brief dip in its already dim light. But they quickly identified the source of the change: the darker mass of a planet passing between the distant star and our vantage point on Earth."
;;               :date (utils/dateTime-now)})
;;(.get (mc/insert-and-return db "messages" message) :_id)

;;(mc/find-map-by-id db "messages" (ObjectId. "5532b09f73562985dfd0e5eb"))


;;(let [conn (mg/connect)
;;      db   (mg/get-db conn "clojure")]
  ;; with a generated document id, returns the complete
  ;; inserted document
  ;;(mc/insert-and-return db "messages" {:header "John" :body "Тестовое сообщение!" :date (date-now)})

  ;;(mc/find-map-by-id db "messages" (ObjectId. "5532b09f73562985dfd0e5eb")))



(defn get-all-messages [] (apply list (mc/find db coll-messages)))
(defn create-new-message [message] (mc/insert-and-return db coll-messages message))
(defn get-message [id] (mc/find-one db coll-messages { :_id (ObjectId. id) }))
(defn update-message [id message] (mc/update db coll-messages {:_id id} message))
(defn delete-message [id] (mc/remove db coll-messages {:_id id}))


;; (map? (mc/find-map-by-id db "messages" (ObjectId. "5532b09f73562985dfd0e5eb")))
;; (list? (apply list(mc/find-maps db coll-messages)))

;; (class (apply list (get-all-messages)))
