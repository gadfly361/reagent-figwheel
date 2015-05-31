# reagent-figwheel

This is a simple starter template for [reagent](https://github.com/holmsand/reagent) applications with [figwheel](https://github.com/bhauman/lein-figwheel).

## Usage

```
lein new reagent-figwheel <project_name>
```

## Development Mode

```
lein clean
lein figwheel
```

Figwheel will automatically push cljs changes to the browser.

Wait a bit, then browse to [http://localhost:3449](http://localhost:3449).

## Production Build

```
lein clean
lein cljsbuild once min
```
