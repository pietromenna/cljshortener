(ns cljshortener.routes.home
  (:require [compojure.core :refer :all]
            [cljshortener.views.layout :as layout]
            [hiccup.form :refer :all]
            [cljshortener.models.db :as db]
            [digest :as digest]
            [ring.util.response :as response]))

(defn home [& message]
  (layout/common
    [:h1 "Welcome!"]

    [:p message]

    (form-to [:post "/"]
      [:p "Insert Long URL here:"]
      (text-field "longurl")
      (submit-button "submit!"))))

(defn shorturl-for [longurl]
  (clojure.string/join  (take 5 (digest/md5 longurl))))

(defn register-long [longurl]
  (do
    (db/save-link (shorturl-for longurl) longurl)
    (home (str "Created shorthand for " longurl " as " (shorturl-for longurl)))))

(defn redirect-using [shorturl]
  (response/redirect ((db/read-longurl-for shorturl) :longurl)))

(defroutes home-routes
  (GET "/" [] (home))
  (POST "/" [longurl] (register-long longurl))
  (GET "/:shorturl" [shorturl] (redirect-using shorturl)))
