(ns webapp.utils)

(defn dateTime-now [] (.format (java.text.SimpleDateFormat. "dd.MM.yyyy HH:mm:ss.SSS") (java.util.Date.)))
