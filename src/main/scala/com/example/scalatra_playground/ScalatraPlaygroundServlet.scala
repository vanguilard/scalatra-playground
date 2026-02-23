package com.example.scalatra_playground

import org.scalatra._

class ScalatraPlaygroundServlet extends ScalatraServlet {
    get("/") {
        views.html.hello()
    }
}
