(ns inventory.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::name
 (fn [db]
   (:name db)))

(re-frame/reg-sub
 ::active-panel
 (fn [db _]
   (:active-panel db)))

(re-frame/reg-sub
 :products
 (fn [db _]
   (:products db)))

(re-frame/reg-sub
 :pricing-info
 (fn [db _]
   (:pricing-info db)))

(re-frame/reg-sub
 :edit-item
 (fn [db _]
   (:edit-item db)))

(re-frame/reg-sub
 :submittable?
 (fn [db _]
   (:submittable? db)))
