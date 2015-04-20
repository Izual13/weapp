(ns webapp.core
  (:require [qbits.jet.server :refer [run-jetty]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [compojure.response :refer [render]]
            [clojure.java.io :as io]
            [webapp.styles]
            [webapp.mongo]
            [webapp.views :as wv]
            ))

;; This is a handler that returns the
;; contents of `resources/index.html`
(defn static-home
  [req]
  (render (io/resource "index.html") req))

;;hiccup
;;return non-static page
(defn home [req] (render (wv/index) req))

(defn error [req] (render (wv/error) req))

;; Defines a handler that acts as router
(defroutes app
  (GET "/" [] home)
  (GET "/static/home" [] static-home)
  (route/resources "/static")
  (route/not-found error))

;; Application entry point
;;(defn -main
;;  [& args]
;;  (let [app (wrap-defaults app site-defaults)]
;;    (run-jetty {:ring-handler app :port 5050})))

(unsigned-bit-shift-right -1 30)


;;;;test

(defonce server (run-jetty {:ring-handler app :port 8080 :join? false}))

;;(.stop server)

;;(.start server)

