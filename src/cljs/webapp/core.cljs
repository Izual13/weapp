(ns webapp.core
  (:require [goog.events :as events]
            [goog.dom :as dom]
            [ajax.core :refer [abort ajax-request
                      url-request-format
                      edn-response-format
                      edn-request-format
                      raw-response-format
                      transit-request-format
                      transit-response-format
                      GET POST]]))




(defn main
  []
  (let [counter (atom 0)
        button  (dom/getElement "onClick")
        display (dom/getElement "clicksnumber")]

    ;; Set initial value
    (set! (.-innerHTML display) @counter)

    ;; Assign event listener
    (events/listen button "click"
                   (fn [event]
                     ;; Increment the value
                     (swap! counter inc)
                     ;; Set new value in display element
                     (set! (.-innerHTML display) @counter)))))

(main)
(defn logger [x] (.log js/console x))


(logger "123")


(.click (dom/getElement "onClick"))


((fn add [a b] (+ a b)) 1 2)
(doseq [i [0 1 2 3]] (logger i))
(loop [sum 0 cnt 10]
    (if (= cnt 0)
    ((fn[x] (logger sum) x) sum)
    ;;sum
    (recur (+ cnt sum) (dec cnt))))

;;(.reload (.-location js/window))

(def message (GET "/messages"))
(logger (GET "/messages"))

;; (set! (.-innerHTML (dom/getElement "clicksnumber"))((map (GET "/messages"))))
(GET "/messages" {:handler logger :error-handler (fn [x](.log js/console "error")(.log js/console x))})



(GET "/messages/554536e673566e17b0917cab"
     {:handler show-message
      :error-handler logger
      :response-format :json
      :keywords? true})






(defn show-message [{:keys [_id body x]}]
  (set! (.-innerHTML (dom/getElement "clicksnumber"))
        ((map (GET "/messages")))))

(defn show-message [x] (logger x))


(defn render-message [{:keys [message user]}]
 [:li [:p {:id user} message " - " user]])



;;(->> (GET "/messages")
  ;;   (map render-message))
