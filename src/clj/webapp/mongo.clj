(ns webapp.mongo
  (:require [monger.core :as mg]
            [monger.collection :as mc]
            [webapp.utils :as utils]
            [monger.json])
  (:import [com.mongodb MongoOptions ServerAddress]
           [org.bson.types ObjectId]
           [com.mongodb DB WriteConcern]))


(def db (mg/get-db (mg/connect) "clojure"))

(def message {:header "John" :body "Тестовое сообщение!" :date (utils/dateTime-now)})
;;(.get (mc/insert-and-return db "messages" message) :_id)

;;(mc/find-map-by-id db "messages" (ObjectId. "5532b09f73562985dfd0e5eb"))


;;(let [conn (mg/connect)
;;      db   (mg/get-db conn "clojure")]
  ;; with a generated document id, returns the complete
  ;; inserted document
  ;;(mc/insert-and-return db "messages" {:header "John" :body "Тестовое сообщение!" :date (date-now)})

  ;;(mc/find-map-by-id db "messages" (ObjectId. "5532b09f73562985dfd0e5eb")))

(def coll-messages "messages")

(defn get-all-messages [] (apply list (mc/find db coll-messages)))
(defn create-new-message [message] (mc/insert-and-return db coll-messages message))
(defn get-message [id] (mc/find-one db coll-messages { :_id (ObjectId. id) }))
(defn update-message [id message] (mc/update db coll-messages {:_id id} message))
(defn delete-message [id] (mc/remove db coll-messages {:_id id}))


;; (map? (mc/find-map-by-id db "messages" (ObjectId. "5532b09f73562985dfd0e5eb")))
;; (list? (apply list(mc/find-maps db coll-messages)))

;; (class (apply list (get-all-messages)))
