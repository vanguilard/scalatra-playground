package com.example.scalatraplayground

import org.scalatra.test.scalatest._
import com.example.scalatraplayground.controller.ScalatraPlaygroundServlet

class ScalatraPlaygroundServletTests extends ScalatraFunSuite {

    addServlet(classOf[ScalatraPlaygroundServlet], "/*")

    test("GET / on ScalatraPlaygroundServlet should return status 200") {
        get("/") {
            status should equal(200)
        }
    }

}
