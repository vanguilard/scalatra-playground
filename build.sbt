/* Imports */
import RevolverPlugin.autoImport._

/* Scalatra version
 * - Defines which version of Scalatra framework to use.
 */
val ScalatraVersion = "3.1.2"

/* Environment variables
 */
val commonEnvVars = Map(
    "PORT" -> "8080"
)

/* Project metadata
 * - Defines organization, name, and version for the project.
 */
organization := "com.example"
name := "scalatraplayground"
version := "0.1.0-alpha"

/* Scala compiler version
 * - Scalatra 3.1.2 requires Scala 2.13+.
 */
scalaVersion := "2.13.18"

/* Library dependencies */
libraryDependencies ++= Seq(
    /* Core Scalatra framework with Jakarta EE 10 support */
    "org.scalatra" %% "scalatra-jakarta" % ScalatraVersion,

    /* Testing library */
    // "org.scalatra" %% "scalatra-scalatest-jakarta" % ScalatraVersion % "test",
    "org.scalatra" %% "scalatra-specs2-jakarta" % ScalatraVersion % "test",

    /* Logback for structured logging
     * - This is your one SLF4J binding (Logback).
     */
    "ch.qos.logback" % "logback-classic" % "1.5.19",

    /* Jakarta Servlet API for compilation */
    "jakarta.servlet" % "jakarta.servlet-api" % "6.0.0" % "provided"
)

/* Twirl template compiler
 * - Compiles .scala.html templates under src/main/twirl into Scala sources.
 */
enablePlugins(SbtTwirl)

/* Configure revolver to use JettyLauncher */
reStart / mainClass := Some("com.example.JettyLauncher")

/* Optional: JVM options for revolver */
reStart / javaOptions ++= Seq(
    "-Xmx1G"
)

/* Optional: Environment variables for revolver */
reStart / envVars ++= commonEnvVars

/* Keep existing run configuration (for non-revolver usage) */
Compile / run / mainClass := Some("com.example.JettyLauncher")
Compile / run / fork := true

/* Optional: Environment variables for run */
Compile / run / envVars ++= commonEnvVars

/* Ensure tests run in a separate JVM */
Test / fork := true
