(ns webapp.styles
  (:require [garden.def :refer [defstylesheet defstyles]]
            [garden.units :refer [px]]))

;; Change defstylesheet to defstyles.
(defstyles screen
  [:.test [:* {:font-family "Poiret One !important"}]])

