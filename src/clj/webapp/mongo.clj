(ns webapp.mongo
  (:require [monger.core :as mg]
            [monger.collection :as mc]
            [webapp.utils :as utils]
            [monger.json])
  (:import [com.mongodb MongoOptions ServerAddress]
           [org.bson.types ObjectId]
           [com.mongodb DB WriteConcern]))


(def mongo-url (get (System/getenv) "OPENSHIFT_MONGODB_DB_HOST" "127.0.0.1"))
(def mongo-port (Integer/parseInt (get (System/getenv) "OPENSHIFT_MONGODB_DB_PORT" "27017")))
(def mongo-user  (get (System/getenv) "OPENSHIFT_MONGODB_DB_USERNAME" ""))
(def mongo-password (get (System/getenv) "OPENSHIFT_MONGODB_DB_PASSWORD" ""))
(def mongo-db (get (System/getenv) "OPENSHIFT_GEAR_NAME" "clojure"))

(.println (System/out) mongo-url)
(.println (System/out) mongo-port)
(.println (System/out) mongo-user)
(.println (System/out) mongo-password)
(.println (System/out) mongo-db)

(def db (mg/get-db (mg/connect {:host mongo-url :port mongo-port}) mongo-db))
(mg/authenticate db mongo-user (.toCharArray mongo-password))

(def coll-messages "messages")

(defn get-all-messages [] (apply list (mc/find db coll-messages)))
(defn create-new-message [message] (mc/insert-and-return db coll-messages message))
(defn get-message [id] (mc/find-one db coll-messages { :_id (ObjectId. id) }))
(defn update-message [id message] (mc/update db coll-messages {:_id id} message))
(defn delete-message [id] (mc/remove db coll-messages {:_id id}))

