(ns webapp.core
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [compojure.response :refer [render]]
            [clojure.java.io :as io]
            ;;[webapp.styles]
            [webapp.mongo :as mg]
            [webapp.views :as wv]
            [clojure.data.json :as json]
            [org.httpkit.server :as httpkit]))

;; This is a handler that returns the
;; contents of `resources/index.html`
(defn static-home
  [req]
  (render (io/resource "index.html") req))

;;hiccup
;;return non-static page
(defn home [req] (render (wv/index) req))

(defn error [req] (render (wv/error) req))

(defn json-wrapper [body]
  {:status 200
   :headers {"Content-Type" "application/json; charset=utf-8"}
   :body (json/write-str body :escape-unicode false)})


(defn log [x] (.println (System/out) x))

;; Defines a handler that acts as router
(defroutes app
  (GET "/" [] home)
  (GET "/test" [] home)
  (GET "/static/home" [] static-home)
  (route/resources "/static")
  (context "/messages" [] (defroutes messages-routes
        (GET  "/" [] (json-wrapper (mg/get-all-messages)))
        (POST "/" {body :body} (mg/create-new-message body))
        (context "/:id" [id] (defroutes message-routes
          (GET    "/" [] (json-wrapper (mg/get-message id)))
          (PUT    "/" {body :body} (mg/update-message id body))
          (DELETE "/" [] (mg/delete-message id))))))
  (route/not-found error))



  (defn -main [& args]
   (let [port (Integer/parseInt (get (System/getenv) "OPENSHIFT_CLOJURE_HTTP_PORT" "5050"))
         ip (get (System/getenv) "OPENSHIFT_CLOJURE_HTTP_IP" "0.0.0.0")]
     (httpkit/run-server #'app {:ip ip :port port})))

  ;;(-main)


