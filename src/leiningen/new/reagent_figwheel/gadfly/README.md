# {{name}}

A [reagent](https://github.com/reagent-project/reagent) application designed to ... well, that part is up to you.

## Development Mode

### cljs-devtools

To enable:

1. Open Chrome's DevTools,`Ctrl-Shift-i`
2. Open "Settings", `F1`
3. Check "Enable custom formatters" under the "Console" section
4. close and re-open DevTools

### Compile css:

Compile css file once.

```
lein garden once
```

Automatically recompile css file on change.

```
lein garden auto
```

Note: figwheel often does a clean when it first starts ... so it is best to create the css after figwheel is up and running.

### Run application:

```
lein figwheel dev
```

Figwheel will automatically push cljs changes to the browser.

Wait a bit, then browse to [http://localhost:3449](http://localhost:3449).

### Run tests:

```
lein test
```

## Production Build

```
lein clean
rm -rf resources/public/css
lein garden once
lein cljsbuild once min
```

## Devcards

```
lein figwheel devcards
```

Figwheel will automatically push cljs changes to the browser.

Wait a bit, then browse to [http://localhost:3449/cards.html](http://localhost:3449/cards.html).

---

To build a minified version:

```
lein clean
rm -rf resources/public/css
lein garden once
lein cljsbuild once hostedcards
```
Then open *resources/public/cards.html*
