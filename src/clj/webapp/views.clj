(ns webapp.views
  (:require [hiccup.core :as hc]))




(defn style [path]
  [:link {:href path :rel "stylesheet" :type "text/css"}])

(defn js [path]
  [:script {:src path}])

(defn layout [title & body]
  (hc/html
    [:html
     [:head
      [:title title]
      (style "/static/css/styles.css")]
     [:body
      [:div.container body]
      (js "/static/js/app.js")]]))


(defn index [] (layout "webapp" [:h1 "Hello, World!"]
                 [:section [:span "Cliks: "]
                           [:span {:id "clicksnumber"}]]
                 [:button {:id "button"} "Click me!"]))

