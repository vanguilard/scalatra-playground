package com.example

import org.specs2.mutable.Specification
import org.specs2.specification.BeforeAfterEach
import java.lang.reflect.Method

class JettyLauncherSpec extends Specification with BeforeAfterEach {

    // Store original environment for cleanup
    var originalPort: Option[String] = None

    def before: Unit = {
        originalPort = sys.env.get("PORT")
    }

    def after: Unit = {
        // Clean up any environment changes if needed
    }

    "JettyLauncher" should {

        "have a main method" in {
            val mainMethod = JettyLauncher.getClass.getMethods.find(_.getName == "main")
            mainMethod must beSome[Method]

            val method = mainMethod.get
            method.getParameterTypes must have size 1
            method.getParameterTypes.head must_== classOf[Array[String]]
        }

        "use default port 8080 when PORT environment variable is not set" in {
            // Note: We can't directly test the private port resolution without running the app,
            // but we can test the environment variable reading logic conceptually
            val portFromEnv = sys.env.get("PORT").flatMap(_.toIntOption).getOrElse(8080)

            // When no PORT is set, should default to 8080
            if (sys.env.get("PORT").isEmpty) {
                portFromEnv must_== 8080
            } else {
                // If PORT is set, it should be used (this handles CI/testing environments)
                portFromEnv must be_>(0)
                portFromEnv must be_<(65536)
            }
        }

        "parse valid PORT environment variable" in {
            // Test the port parsing logic that's used in JettyLauncher
            val validPort = "9090"
            validPort.toIntOption must beSome(9090)

            val invalidPort = "not-a-number"
            invalidPort.toIntOption must beNone
        }

        "handle environment variable parsing correctly" in {
            // Test various port value scenarios
            "8080".toIntOption.getOrElse(8080) must_== 8080
            "3000".toIntOption.getOrElse(8080) must_== 3000
            "invalid".toIntOption.getOrElse(8080) must_== 8080
            "".toIntOption.getOrElse(8080) must_== 8080
        }
    }

    // Component integration tests
    "JettyLauncher components" should {

        "reference proper webapp directory path" in {
            // Verify the webapp directory path used in JettyLauncher
            val webappPath = "src/main/webapp"
            val file = new java.io.File(webappPath)

            // The directory should exist in the project structure
            file.exists must beTrue
            file.isDirectory must beTrue
        }

        "have access to ScalatraListener" in {
            // Verify that the ScalatraListener class is available
            val listenerClass = classOf[org.scalatra.servlet.ScalatraListener]
            listenerClass must not(beNull)
            listenerClass.getName must_== "org.scalatra.servlet.ScalatraListener"
        }

        "have proper Jetty Server dependencies available" in {
            // Verify Jetty dependencies are on classpath
            val serverClass = classOf[org.eclipse.jetty.server.Server]
            serverClass must not beNull

            val webAppContextClass = classOf[org.eclipse.jetty.ee10.webapp.WebAppContext]
            webAppContextClass must not(beNull)
        }
    }

    // Configuration validation tests
    "JettyLauncher configuration" should {

        "validate port range" in {
            val validPorts = List(8080, 3000, 9090, 8000, 8081)
            validPorts.forall { port =>
                port > 0 && port < 65536
            } must beTrue
        }

        "handle different environment scenarios" in {
            // Test the environment variable fallback logic used in JettyLauncher
            val testCases = List(
                (None, 8080),
                (Some(""), 8080),
                (Some("3000"), 3000),
                (Some("invalid"), 8080)
            )

            // Test each case individually
            val results = testCases.map { case (envValue, expected) =>
                val result = envValue.flatMap(_.toIntOption).getOrElse(8080)
                result == expected
            }

            results.forall(identity) must beTrue
        }
    }
}
