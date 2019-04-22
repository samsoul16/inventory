# Product Inventory

A simple application desgined for inventory editing project

## Steps to Run application:

```
1. Clone the repo
2. Navigate to invetory/resources/public
3. Open index.html
```

## Project Guide

### Project details

This is a re-frame Project is written in clojurescript

Frameworks / Tools used for development:
```
1. Re-frame - project template
2. Re-frisk - state(app-db) view
3. Secretary - navigation
```

### Project Structure

All the source code lies in `src/cljs/inventory/` directory

#### Code Structure details
````
└── cljs
    └── inventory
        ├── config.cljs
        ├── core.cljs - The app start point
        ├── db.cljs - The initial db state
        ├── edit.cljs - Edit Item View
        ├── effects.cljs - The effects of events
        ├── events.cljs - Events to change the app state
        ├── home.cljs - Home view of app with products list
        ├── routes.cljs - The routes which change the views in main page
        ├── subs.cljs - Subscriptions to app state
        └── views.cljs - The Main Page of the app which holds views based on route
```


## Development Guide

A [re-frame](https://github.com/Day8/re-frame) application designed to ... well, that part is up to you.

### Run application:

```
lein clean
lein figwheel dev
```

Figwheel will automatically push cljs changes to the browser.

Wait a bit, then browse to [http://localhost:3449](http://localhost:3449).

## Production Build


To compile clojurescript to javascript:

```
lein clean
lein cljsbuild once min
```
