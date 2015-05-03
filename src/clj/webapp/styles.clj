(ns webapp.styles
  (:require [garden.def :refer [defstylesheet defstyles]]
            [garden.units :refer [px]]
            ))

;;(.mkdir (java.io.File. "resources/public/css"))

(defstylesheet screen
  {:output-to "resources/public/css/styles.css"}
  [:body
   {;;:font-family "sans-serif"
    ;;:font-size (px 16)
    ;;:line-height 1.5
    ;;:background "#3A3C42"
    ;;:color "#F8F8F2"
    }])

