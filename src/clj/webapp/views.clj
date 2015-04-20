(ns webapp.views
  (:require [hiccup.core :as hc]
            [webapp.utils :as utils]))




(defn style [path]
  [:link {:href path :rel "stylesheet" :type "text/css"}])

(defn js [path]
  [:script {:src path}])

(defn layout [title & body]
  (hc/html
    [:html
     [:head
      [:title title]
      (style "/static/css/bootstrap.min.css")
      (style "/static/css/monokai_sublime.css")
      (style "/static/css/styles.css")]
     [:body
      [:div.container body]
      (js "/static/js/app.js")]]))


(defn index [] (layout "webapp" [:h1 "Hello, World!"]
                 [:section [:span "Cliks: "]
                           [:span {:id "clicksnumber"}]]
                 [:button {:id "button"} "Click me!"]))


(defn error [] (layout "Warning!"
                       [:div.panel.panel-danger
                        [:div.panel-heading "Date: " (utils/dateTime-now)]
                        [:div.panel-body "404"]]))

