package com.example.scalatraplayground.routes

import org.specs2.mutable.Specification
import org.scalatra.test.specs2.MutableScalatraSpec

// Test servlet class that implements PageServlet for testing
class TestPageServlet extends PageServlet

class PageServletSpec extends MutableScalatraSpec {

    addServlet(classOf[TestPageServlet], "/*")

    "PageServlet" should {

        "return page title for existing bacon-ipsum slug" in {
            get("/pages/bacon-ipsum") {
                status must_== 200
                body must_== "Bacon ipsum dolor sit amet hamburger"
            }
        }

        "return page title for existing veggie-ipsum slug" in {
            get("/pages/veggie-ipsum") {
                status must_== 200
                body must_== "Arugula prairie turnip desert raisin sierra leone"
            }
        }

        "return 404 for non-existent page slug" in {
            get("/pages/non-existent-page") {
                status must_== 404
                body must contain("not found")
            }
        }

        "return 404 for empty slug" in {
            get("/pages/") {
                // This should not match the route pattern and return 404
                status must_== 404
            }
        }

        "handle special characters in slug correctly" in {
            get("/pages/test-with-dashes") {
                status must_== 404 // Should not find this page
                body must contain("not found")
            }
        }

        "set correct content type for existing pages" in {
            get("/pages/bacon-ipsum") {
                status must_== 200
                body must not beEmpty
            }
        }

        "not respond to POST requests" in {
            post("/pages/bacon-ipsum") {
                status must_== 405 // Method Not Allowed
            }
        }

        "not respond to PUT requests" in {
            put("/pages/bacon-ipsum") {
                status must_== 405 // Method Not Allowed
            }
        }

        "not respond to DELETE requests" in {
            delete("/pages/bacon-ipsum") {
                status must_== 405 // Method Not Allowed
            }
        }
    }
}
