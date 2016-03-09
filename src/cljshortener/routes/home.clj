(ns cljshortener.routes.home
  (:require [compojure.core :refer :all]
            [cljshortener.views.layout :as layout]
            [hiccup.form :refer :all]))

(defn home []
  (layout/common
    [:h1 "Welcome!"]

    (form-to [:post "/"]
      [:p "Insert Long URL here:"]
      (text-field "longurl")
      (submit-button "submit!"))))

(defroutes home-routes
  (GET "/" [] (home)))
