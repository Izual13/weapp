# webapp

run:


open project src/clj/core.clj
and use repl on row with (defonce server (run-jetty {:ring-handler app :port 8080 :join? false}))

continue open in browser http://localhost:8080

