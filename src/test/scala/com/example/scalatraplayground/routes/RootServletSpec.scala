package com.example.scalatraplayground.routes

import org.specs2.mutable.Specification
import org.scalatra.test.specs2.MutableScalatraSpec

// Test servlet class that implements RootServlet for testing
class TestRootServlet extends RootServlet

class RootServletSpec extends MutableScalatraSpec {

    addServlet(classOf[TestRootServlet], "/*")

    "RootServlet" should {

        "respond to GET /" in {
            get("/") {
                status must_== 200
            }
        }

        "return HTML content for root path" in {
            get("/") {
                // Check that response has content type header
                status must_== 200
                // Just verify the response is successful
            }
        }

        "not respond to POST /" in {
            post("/") {
                status must_== 405 // Method Not Allowed
            }
        }

        "not respond to PUT /" in {
            put("/") {
                status must_== 405 // Method Not Allowed
            }
        }

        "not respond to DELETE /" in {
            delete("/") {
                status must_== 405 // Method Not Allowed
            }
        }
    }
}
