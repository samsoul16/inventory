(ns inventory.home
  (:require [re-frame.core :as re-frame]))

;; Home Page

(defn home []
  (let [products (re-frame/subscribe [:products])]
    [:div.parent
     [:h1 "Products"]
     [:hr]
     [:table.products-table
      [:thead
       [:tr
        [:th.cell "Name"]
        [:th.cell "Weight"]
        [:th.cell "Availability"]
        [:th.cell "Editable"]]]
      [:tbody
       (map-indexed
        (fn [idx row]
          ^{:key idx}
          [:tr
           [:td.cell (:name row)]
           [:td.cell (:weight row)]
           [:td.cell (:availability row)]
           [:td.cell (if (:isEditable row)
                       [:button.edit
                        {:on-click #(re-frame/dispatch [:set-edit-item idx row])}
                        "Edit"])]])
        @products)]]]))
