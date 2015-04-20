(ns webapp.mongo
  (:require [monger.core :as mg]
            [monger.collection :as mc])
  (:import [com.mongodb MongoOptions ServerAddress]
           [org.bson.types ObjectId]
           [com.mongodb DB WriteConcern]))


(let [conn (mg/connect)
      db   (mg/get-db conn "clojure")]
  ;; with a generated document id, returns the complete
  ;; inserted document
  ;;(mc/insert-and-return db "messages" {:header "John" :body "Тестовое сообщение!" :date (date-now)})

  (mc/find-map-by-id db "messages" (ObjectId. "5532b09f73562985dfd0e5eb")))





