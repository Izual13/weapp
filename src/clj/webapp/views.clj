(ns webapp.views
  (:require [hiccup.core :as hc]
            [hiccup.page :as hp]
            [webapp.utils :as utils]))


(hp/include-css "/css/main.css")


(defn layout [title & body]
  (hc/html
    [:html
     [:head
      [:title title]
      (hp/include-css "/static/css/bootstrap.min.css")
      (hp/include-css "/static/css/monokai_sublime.css")
      (hp/include-css "/static/css/styles.css")]
     [:body
      [:div.container body]]]))


(defn index [] (layout "Welcome!" [:h1 "Hello, World!"]
                       [:div.panel.panel-default
                        [:div.panel-heading[:span "Cliks: "][:span {:id "clicksnumber"}]]
                        [:div.panel-body
                         [:button.btn.btn-sm.btn-default {:id "onClick"} "Click me!"]"&nbsp;"
                         [:button.btn.btn-sm.btn-default {:id "showAll"} "Show all..."]"&nbsp;"
                         [:button.btn.btn-sm.btn-default {:id "clear"} "Clear list"]]]
                       [:div.panel.panel-default
                        [:div.panel-heading "Posts"]
                        [:ul.list-group {:id "messages"}]]
                       (hp/include-js "/static/js/app.js")
                       ))


(defn error [] (layout "Warning!"
                       [:div.panel.panel-danger
                        [:div.panel-heading "Date: " (utils/dateTime-now)]
                        [:div.panel-body "404"]]))

