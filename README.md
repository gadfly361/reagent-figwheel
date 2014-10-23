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

## other reagent templates

[reagent-template](https://github.com/reagent-project/reagent-template) - Remake of [chestnut](https://github.com/plexus/chestnut) template for reagent.

[liberagent](https://github.com/borkdude/lein-new-liberagent) - Template for apps that use Reagent on the client and Compojure+Liberator on the server.

[reagent-seed](https://github.com/gadfly361/reagent-seed) - Reagent template with a few batteries included.
