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


(defn append [parent & children]
  (do (doseq [child children]
        (dom/appendChild parent child))
      parent))

(defn clean-element [element] (set! (.-innerHTML element) ""))

(defn create-li [header body date]
  (let [li (.createElement js/document "li")
        small (.createElement js/document "small")
        divDate (.createElement js/document "div")
        divHeader (.createElement js/document "h4")
        divBody (.createElement js/document "h6")]
    (.setAttribute li "class" "list-group-item")
    (.setAttribute divDate "class" "text-right text-muted")
    (.setAttribute divHeader "class" "text-info")
    (set! (.-innerHTML divHeader) header)
    (set! (.-innerHTML divBody) body)
    (set! (.-innerHTML small) date)
    (dom/appendChild divDate small)
    (dom/appendChild li divHeader)
    (dom/appendChild li divBody)
    (dom/appendChild li divDate)
    li))




(defn setValue [element value] (set! (.-innerHTML element) value))


(defn logger [& x] (.log js/console (str x)))


(logger "123")


;;(.click (dom/getElement "onClick"))


((fn add [a b] (+ a b)) 1 2)
(doseq [i [0 1 2 3]] (logger i))
(loop [sum 0 cnt 10]
    (if (= cnt 0)
    ((fn[x] (logger sum) x) sum)
    ;;sum
    (recur (+ cnt sum) (dec cnt))))

(logger "123")


(defn show-message [{:keys [_id body date header]}]
  (logger _id body)
  (append (dom/getElement "messages") (create-li header body date)))

(defn show-messages [list]
  (doseq [x list] (show-message x)))


;; (defn get-message [] (GET "/messages/5546bcfc7356ffa786e1eb4f"
;;      {:handler show-message
;;       :error-handler logger
;;       :response-format :json
;;       :keywords? true}))

;;(get-message)


(defn get-all-messages [] (GET "/messages"
                               {:handler show-messages
                                :error-handler logger
                                :response-format :json
                                :keywords? true}))

(get-all-messages)

(defn render-message [{:keys [message user]}]
 [:li [:p {:id user} message " - " user]])


  ;;(.reload (.-location js/window))

(defn main
  []
  (let [counter (atom 0)
        button  (dom/getElement "onClick")
        display (dom/getElement "clicksnumber")
        showAll (dom/getElement "showAll")
        clear (dom/getElement "clear")]

    ;; Set initial value
    (set! (.-innerHTML display) @counter)

    ;; Assign event listener
    (events/listen button "click"
                   (fn [event]
                     ;; Increment the value
                     (swap! counter inc)
                     ;; Set new value in display element
                     (set! (.-innerHTML display) @counter)))
    (events/listen showAll "click" (fn [event]
                                     (get-all-messages)))
    (events/listen clear "click" (fn [event]
                                     (clean-element (dom/getElement "messages"))))))

  (main)

