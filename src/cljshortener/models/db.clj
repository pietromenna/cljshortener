(ns cljshortener.models.db
  (:require [clojure.java.jdbc :as sql])
  (:import java.sql.DriverManager))

(def db {:classname  "org.sqlite.JDBC",
         :subprotocol   "sqlite",
         :subname       "db.sq3"})

(defn create-links-table []
  (sql/with-connection
    db
    (sql/create-table
      :links
      [:shorturl "TEXT PRIMARY KEY"]
      [:longurl "TEXT"])))

(defn save-link [shorturl longurl]
  (sql/with-connection 
    db
    (sql/insert-values
      :links
      [:shorturl :longurl]
      [shorturl longurl])))

(defn read-longurl-for [shorturl]
  (sql/with-connection
    db
    (sql/with-query-results res
      ["SELECT longurl FROM links WHERE shorturl = ?" shorturl ]
      (first res))))
