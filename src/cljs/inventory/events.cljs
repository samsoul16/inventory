(ns inventory.events
  (:require
   [re-frame.core :as re-frame]
   [inventory.db :as db]
   ))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))

(re-frame/reg-event-db
 ::set-active-panel
 (fn [db [_ active-panel]]
   (assoc db :active-panel active-panel)))

(re-frame/reg-event-fx
 :set-edit-item
 (fn [{:keys [db]} [_ idx edit-item]]
   {:db (assoc db :editing idx :edit-item edit-item )
    :change-route "/edit"
    :can-submit nil}))

(re-frame/reg-event-fx
 :set-item-kv
 (fn [{:keys [db]} [_ k v]]
   {:db (assoc-in db [:edit-item k] v)
    :can-submit nil}))

(re-frame/reg-event-db
 :set-submittable
 (fn [db [_ bool]]
   (assoc db :submittable? bool)))

(re-frame/reg-event-fx
 :edit-complete
 (fn [{:keys [db]} [_ _]]
   {:db (merge (assoc-in db [:products (:editing db)] (:edit-item db))
               {:editing false
                :submittable? false})
    :change-route "/"}))
