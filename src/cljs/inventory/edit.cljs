(ns inventory.edit
  (:require [re-frame.core :as re-frame]))

;; Edit Page

(defn edit-item []
  (let [item (re-frame/subscribe [:edit-item])
        pricing-info (re-frame/subscribe [:pricing-info])
        submittable? (re-frame/subscribe [:submittable?])
        on-change (fn [key val]
                    (re-frame/dispatch [:set-item-kv key (-> val .-target .-value)]))]
    (fn []
      [:div.parent
       [:h1 "Edit Item " ]
       [:hr]
       [:form {:on-submit (fn [e]
                            (.preventDefault e)
                            (re-frame/dispatch [:edit-complete])) }
        [:table
         [:tbody
          [:tr
          [:td [:label {:for "name"} "Name"]]
          [:td [:input {:type "text"
                        :placeholder "Enter Name"
                        :name "name"
                        :required true
                        :value (:name @item)
                        :on-change #(on-change :name %)}]]]
         [:tr
          [:td [:label {:for "weight"} "Weight"]]
          [:td [:input {:type "text"
                        :placeholder "Enter Weight"
                        :name "weight"
                        :required true
                        :value (:weight @item)
                        :on-change #(on-change :weight %)}]]]
         [:tr
          [:td [:label {:for "availability"} "Availability"]]
          [:td [:input {:type "number"
                        :placeholder "Enter Availability"
                        :name "availability"
                        :value (:availability @item)
                        :on-change #(on-change :availability %)}]]]
         [:tr
          [:td [:label {:for "url"} "Product Url"]]
          [:td [:input {:type "url"
                        :required true
                        :pattern "http[s]?://.*"
                        :placeholder "Enter Product Url"
                        :name "name"
                        :value (:productUrl @item)
                        :on-change #(on-change :productUrl %)}]]]
         [:tr
          [:td [:label "Price Tier"]]
          [:td [:input {:type "radio"
                        :name "price"
                        :value "budget"
                        :checked (= "budget" (:pricingTier @item))
                        :on-change #(on-change :pricingTier %)}]  "Budget"
           [:input {:type "radio"
                    :name "price"
                    :value "premier"
                    :checked (= "premier" (:pricingTier @item))
                    :on-change #(on-change :pricingTier %)}] "Premier"]]

         [:tr
          [:td [:label "Price Range"]]
          [:td [:select {:name "range"
                         :placeholder "Select Price Range"
                         :value (:priceRange @item)
                         :on-change #(on-change :priceRange %)}
                (map-indexed (fn [idx x]
                               ^{:key idx}
                               [:option {:value x} x])
                             (if (= "budget" (:pricingTier @item))
                               (:budget @pricing-info)
                               (:premier @pricing-info)))]]]
         [:tr
          [:td [:label {:for "editable"} "Editable"]]
          [:td [:input {:type "checkbox"
                  :name "editable"
                  :required true
                  :checked (:isEditable @item)
                        :on-change #(re-frame/dispatch [:set-item-kv :isEditable (-> % .-target .-checked)])}]]]]]
        [:button.submit {:type "submit"
                         :disabled @submittable?}
         "Submit"]]
       ])))
