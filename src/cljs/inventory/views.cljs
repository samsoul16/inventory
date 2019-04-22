(ns inventory.views
  (:require
   [re-frame.core :as re-frame]
   [inventory.subs :as subs]
   [inventory.home :as home]
   [inventory.edit :as edit]
   ))

;; main page

(defn- panels [panel-name]
  (case panel-name
    :home [home/home]
    :edit-item [edit/edit-item]
    [:div]))

(defn show-panel [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [::subs/active-panel])]
    [show-panel @active-panel]))
