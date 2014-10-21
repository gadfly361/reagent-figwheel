# reagent-figwheel

This is a simple starter template for [reagent](https://github.com/holmsand/reagent) applications with [figwheel](https://github.com/bhauman/lein-figwheel).

## usage

Create a new project based on the reagent-figwheel template.

```
$ lein new reagent-figwheel <name of your app>
```

cd into the folder you just made and start a repl.

```
$ lein repl
```

Then type the following to start your application.

```
(run)
```

Wait a little bit; your clojurescript file is getting compiled. Once you see *success*, you can hit enter and type the following.

```
(browser-repl)
```

Open a browser and go to *localhost:10555*. You should see your reagent application!

Next, open `core.cljs`, edit the `"FIXME"` to your name, then save.  You should see your change automatically pushed to the browser, courtesy of figwheel.

## other reagent templates

[reagent-template](https://github.com/reagent-project/reagent-template) - Still in development, but will be the reagent-project organization's template.
[liberagent](https://github.com/borkdude/lein-new-liberagent) - Template for apps that use Reagent on the client and Compojure+Liberator on the server.
[reagent-seed](https://github.com/gadfly361/reagent-seed) - Reagent template with a few batteries included.

## credits

This template is a pared down version of [chestnut](https://github.com/plexus/chestnut) for reagent applications.
