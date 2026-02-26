package com.example.scalatraplayground

import org.scalatra._

class ScalatraPlaygroundServlet extends ScalatraServlet {
    get("/") {
        views.html.hello()
    }
}
