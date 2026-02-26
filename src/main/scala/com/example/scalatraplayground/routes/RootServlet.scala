package com.example.scalatraplayground.routes

import org.scalatra._

trait RootServlet extends ScalatraServlet {
    get("/") {
        views.html.hello()
    }
}
