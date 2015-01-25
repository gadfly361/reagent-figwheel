# reagent-figwheel

This is a simple starter template for [reagent](https://github.com/holmsand/reagent) applications with [figwheel](https://github.com/bhauman/lein-figwheel).

## usage

Create a new project based on the reagent-figwheel template.

```
$ lein new reagent-figwheel <name of your app>
```

cd into the folder you just made. Create a javascript file from your clojurescript files.

```
$ lein cljsbuild once
```

Start a repl.

```
$ lein repl
```

Start your application.

```
(run)
```

Open a browser and go to *localhost:10555*. You should see your reagent application!

Now, start figwheel.

```
(start-figwheel)
```

Finally, open `core.cljs`, change `"FIXME"` to your name, then save.  You should see your change automatically pushed to the browser, courtesy of figwheel.
