package com.example.scalatraplayground.controller

import org.specs2.mutable.Specification
import org.scalatra.test.specs2.MutableScalatraSpec

class ScalatraPlaygroundControllerSpec extends MutableScalatraSpec {

    addServlet(classOf[ScalatraPlaygroundController], "/*")

    "ScalatraPlaygroundController" should {

        "inherit functionality from RootServlet" in {
            get("/") {
                status must_== 200
            }
        }

        "inherit functionality from PageServlet for existing pages" in {
            get("/pages/bacon-ipsum") {
                status must_== 200
                body must_== "Bacon ipsum dolor sit amet hamburger"
            }

            get("/pages/veggie-ipsum") {
                status must_== 200
                body must_== "Arugula prairie turnip desert raisin sierra leone"
            }
        }

        "inherit functionality from PageServlet for non-existing pages" in {
            get("/pages/non-existent") {
                status must_== 404
                body must contain("not found")
            }
        }

        "handle root route correctly" in {
            get("/") {
                status must_== 200
            }
        }

        "handle page routes correctly" in {
            get("/pages/bacon-ipsum") {
                status must_== 200
                body must not beEmpty
            }
        }

        "return 404 for undefined routes" in {
            get("/undefined-route") {
                status must_== 404
            }
        }

        "maintain proper trait composition" in {
            // Test that controller is instance of both servlet traits
            val controller = new ScalatraPlaygroundController
            controller must beAnInstanceOf[com.example.scalatraplayground.routes.RootServlet]
            controller must beAnInstanceOf[com.example.scalatraplayground.routes.PageServlet]
        }

        "handle multiple successive requests correctly" in {
            get("/") {
                status must_== 200
            }

            get("/pages/bacon-ipsum") {
                status must_== 200
            }

            get("/pages/veggie-ipsum") {
                status must_== 200
            }

            get("/pages/non-existent") {
                status must_== 404
            }
        }

        "not support unsupported HTTP methods" in {
            post("/") {
                status must_== 405 // Method Not Allowed
            }

            put("/pages/bacon-ipsum") {
                status must_== 405 // Method Not Allowed
            }

            delete("/pages/bacon-ipsum") {
                status must_== 405 // Method Not Allowed
            }
        }
    }
}
