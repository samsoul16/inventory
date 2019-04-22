(ns inventory.effects
  (:require [re-frame.core :as re-frame]
            [reagent.core :as r]))

;;; effect handlers
(re-frame/reg-fx
 :change-route
 (fn [route]
   (set! (.-hash (.-location js/window)) route)))

(re-frame/reg-fx
 :can-submit
 (fn []
   (let [item (re-frame/subscribe [:edit-item])]
     (re-frame/dispatch [:set-submittable (or (empty? (:name @item))
                                              (empty? (str (:weight @item)))
                                              (empty? (:productUrl @item))
                                              (empty? (:pricingTier @item))
                                              (empty? (:priceRange @item))
                                              (nil? (:isEditable @item)))]))))
