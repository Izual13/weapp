(ns webapp.core
  (:require [goog.events :as events]
            [goog.dom :as dom]))




(defn main
  []
  (let [counter (atom 0)
        button  (dom/getElement "button")
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


(.click (dom/getElement "button"))


((fn add [a b] (+ a b)) 1 2)
(doseq [i [0 1 2 3]] (logger i))
(loop [sum 0 cnt 10]
    (if (= cnt 0)
    ((fn[x] (logger sum) x) sum)
    ;;sum
    (recur (+ cnt sum) (dec cnt))))

;;(.reload (.-location js/window))
