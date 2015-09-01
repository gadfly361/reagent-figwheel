# reagent-figwheel

Leiningen template for [reagent](https://github.com/holmsand/reagent) web apps with [figwheel](https://github.com/bhauman/lein-figwheel).

Through the use of profiles, this template lets the developer pick and choose what is included in their application.

## Usage

The base template includes:

* [reagent](https://github.com/reagent-project/reagent)
* [figwheel](https://github.com/bhauman/lein-figwheel)

To create an application with the base template:

```
lein new reagent-figwheel <project_name>
```

The optional profiles include:

* [garden](https://github.com/noprompt/garden) (`+garden`)
* [secretary](https://github.com/gf3/secretary) (`+routes`)
* [cljs.test](https://groups.google.com/forum/#!topic/clojure/gnCl0CySSk8) (`+test`)

To add a profile to the base template, just append the profile name (let's use `+garden` as an example):

```
lein new reagent-figwheel <project-name> +garden
```

Any combination of profiles can be added at once (let's add all the profiles as an example):

```
lein new reagent-figwheel <project-name> +garden +routes +test
```

## Development Mode

### Compile css (if using +garden):

Compile css file once.

```
lein garden once
```

Automatically recompile css file on change.

```
lein garden auto
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
lein cljsbuild auto test
```

*Note: This requires [phantomjs](https://www.npmjs.com/package/phantomjs).*

## Production Build

```
lein clean
lein cljsbuild once min
