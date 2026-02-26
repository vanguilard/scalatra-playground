package com.example.scalatraplayground.routes

import org.scalatra._
import com.example.scalatraplayground.dao._

trait PageServlet extends ScalatraServlet {
    get("/pages/:slug") {
        // Set the response content type
        contentType = "text/html"

        // Search for a page with a matching slug
        PageDao.pages.find(_.slug == params("slug")) match {
            case Some(page) => page.title // Found – return the title
            case None       => halt(404, "not found") // Not found – stop with 404
        }
    }
}
