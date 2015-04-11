(ns webapp.core
  (:require [qbits.jet.server :refer [run-jetty]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [compojure.response :refer [render]]
            [clojure.java.io :as io]
            [webapp.styles :as cs]
            ))

;; This is a handler that returns the
;; contents of `resources/index.html`
(defn home
  [req]
  (render (io/resource "index.html") req))

;; Defines a handler that acts as router
(defroutes app
  (GET "/" [] home)
  (route/resources "/static")
  (route/not-found "<h1>Page not found</h1>"))

;; Application entry point
(defn -main
  [& args]
  (let [app (wrap-defaults app site-defaults)]
    (run-jetty {:ring-handler app :port 5050})))


