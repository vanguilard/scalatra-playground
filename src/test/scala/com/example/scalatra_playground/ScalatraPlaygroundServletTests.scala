package com.example.scalatra_playground

import org.scalatra.test.scalatest._

class ScalatraPlaygroundServletTests extends ScalatraFunSuite {

    addServlet(classOf[ScalatraPlaygroundServlet], "/*")

    test("GET / on ScalatraPlaygroundServlet should return status 200") {
        get("/") {
            status should equal(200)
        }
    }

}
