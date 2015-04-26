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
      [:div.container body]]]))


(defn index [] (layout "Welcome!" [:h1 "Hello, World!"]
                       [:div.panel.panel-default
                        [:div.panel-heading[:span "Cliks: "][:span {:id "clicksnumber"}]]
                        [:div.panel-body [:button.btn.btn-sm.btn-default {:id "button"} "Click me!"]]]
                       (js "/static/js/app.js")
                       ))


(defn error [] (layout "Warning!"
                       [:div.panel.panel-danger
                        [:div.panel-heading "Date: " (utils/dateTime-now)]
                        [:div.panel-body "404"]]))

