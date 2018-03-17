# reagent-figwheel (leiningen template)

Leiningen template for [reagent](https://github.com/reagent-project/reagent) web apps.

Through the use of profiles, this template lets the developer pick and choose what is included in their application.

## Usage

The base template includes:

* [reagent](https://github.com/reagent-project/reagent)
* [figwheel](https://github.com/bhauman/lein-figwheel)

To create an application with the base template:

```
lein new reagent-figwheel <project-name>
```

The optional profiles include:

* CSS
	* [garden](https://github.com/noprompt/garden) (`+garden`)
	* [less](https://github.com/montoux/lein-less) (`+less`)
* Development
	* [cljs.test](https://github.com/clojure/clojurescript/blob/master/src/main/cljs/cljs/test.cljs) and [doo](https://github.com/bensu/doo) (`+test`)
	* [devcards](https://github.com/bhauman/devcards) (`+devcards`)
	* [cljs-devtools](https://github.com/binaryage/cljs-devtools) (`+devtools`)
	* [re-frisk](https://github.com/flexsurfer/re-frisk) (`+re-frisk`)
* Editor
	* [cider](https://github.com/clojure-emacs/cider) (`+cider`)
* Routing
	* [secretary](https://github.com/gf3/secretary) (`+routes`)


To add a profile to the base template, just append the profile name (let's use `+routes` as an example):

```
lein new reagent-figwheel <project-name> +routes
```

Any combinations of profiles can be added at once, for example:

```
lein new reagent-figwheel <project-name> +cider +test +garden +less +routes +re-frisk
```

## Development Mode

### Start Cider from Emacs (if using +cider):

Put this in your Emacs config file:

```
(setq cider-cljs-lein-repl "(do (use 'figwheel-sidecar.repl-api) (start-figwheel!) (cljs-repl))")
```

Navigate to a clojurescript file and start a figwheel REPL with `cider-jack-in-clojurescript` or (`C-c M-J`)

### cljs-devtools (if using +devtools)

To enable:

1. Open Chrome's DevTools,`Ctrl-Shift-i`
2. Open "Settings", `F1`
3. Check "Enable custom formatters" under the "Console" section
4. close and re-open DevTools

### Compile css (if using +garden or +less):

Compile css file once.

```
lein garden once
```

or

```
lein less once
```

Automatically recompile css file on change.

```
lein garden auto
```

or

```
lein less auto
```

### Run application:

```
lein clean
lein figwheel dev
```

Figwheel will automatically push cljs changes to the browser.

Wait a bit, then browse to [http://localhost:3449](http://localhost:3449).

### Run tests (if using +test):

```
lein clean
lein doo phantom test once
```

The above command assumes that you have [phantomjs](https://www.npmjs.com/package/phantomjs) installed. However, please note that [doo](https://github.com/bensu/doo) can be configured to run cljs.test in many other JS environments (chrome, ie, safari, opera, slimer, node, rhino, or nashorn).

### Devcards (if using +devcards)

```
lein clean
lein figwheel devcards
```

Figwheel will automatically push cljs changes to the browser.

Wait a bit, then browse to [http://localhost:3449/cards.html](http://localhost:3449/cards.html).

---

To build a minified version:

```
lein clean
lein cljsbuild once hostedcards
```

Then open *resources/public/cards.html*

## Production Build

```
lein clean
lein cljsbuild once min
```

## Other Templates

* [re-frame-template](https://github.com/Day8/re-frame-template)
* [chestnut](https://github.com/plexus/chestnut)
* [luminus](https://github.com/luminus-framework/luminus-template)
* [pedestal](https://github.com/pedestal/pedestal)
* [reagent-seed](https://github.com/gadfly361/reagent-seed)
* [thi.ng babel](https://github.com/thi-ng/babel)
* [vase](https://github.com/cognitect-labs/vase)

There is also a public [comparison chart](https://goo.gl/ZZH8fm) of the common templates.

## License

```
The MIT License (MIT)

Copyright © 2014 Matthew Jaoudi

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
