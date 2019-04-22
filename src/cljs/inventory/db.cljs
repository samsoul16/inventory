(ns inventory.db
  (:require [products :as products]))

(def default-db
  {:name "re-frame"
   :active-page :home
   :products (:products (js->clj products :keywordize-keys true))
   :pricing-info (:pricingInfo (js->clj products :keywordize-keys true))})
