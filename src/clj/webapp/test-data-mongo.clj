(ns webapp.test-data-mongo
  (:require [monger.core :as mg]
            [monger.collection :as mc]
            [webapp.utils :as utils]
            [monger.json])
  (:import [com.mongodb MongoOptions ServerAddress]
           [org.bson.types ObjectId]
           [com.mongodb DB WriteConcern]))


(def db (mg/get-db (mg/connect) "clojure"))
(def coll "messages")
(mc/remove db coll)


(def message {:header "Astronomers Found a Gas Giant Orbiting Surprisingly Close to a Tiny Star"
              :body "Astronomers watching a small red dwarf star 500 light years away were surprised to notice a brief dip in its already dim light. But they quickly identified the source of the change: the darker mass of a planet passing between the distant star and our vantage point on Earth."
              :date (utils/dateTime-now)})

(mc/insert-and-return db "messages" message)

(def message {:header "Google's Voice Assistant Gets Smarter, and All the Other News You Missed"
              :body "Uber upgrades its unfortunately necessary SOS button, the Apple Watch isn’t really as much to make as you think, and Google voice actions get waaaay better. BitStream is all the news and rumors you missed yesterday."
              :date (utils/dateTime-now)})

(mc/insert-and-return db "messages" message)


(def message {:header "Mesosphere открывает тестовый доступ к своей ОС для дата-центров"
              :body "Про операционные системы для дата-центров начали говорить относительно недавно. И буквально полгода назад компания Mesosphere заявила об успешной разработке подобной операционной системы. Сейчас компания сделала второе заявление: она открывает тестовый доступ к своей ОС, при этом в тестовый период ОС от Mesosphere будет доступна не всем, а предоставляться по заявкам заинтересованных организаций.
              <br /><br />
              Стоит отметить, что некоторые организации и стартапы получили такой доступ. К примеру, Kubernetes, сторонний проект Google, сейчас полностью интегрирован с ОС Mesosphere. Процесс интеграции начался еще в прошлом году, и сейчас, как видим, успешно завершен. Кстати, проект от Mesosphere заинтересовал не только представителей телекоммуникационной сферы, но и инвесторов, которые уже выделили около $50 млн на этот проект. Среди прочих инвесторов — Andreessen-Horowitz, Khosla Ventures, SV Angel и Fuel Capital."
              :date (utils/dateTime-now)})

(mc/insert-and-return db "messages" message)

