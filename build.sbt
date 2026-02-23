/* Scalatra version
 * - Defines which version of Scalatra framework to use.
 */
val ScalatraVersion = "3.1.2"

/* Project metadata
 * - Defines organization, name, and version for the project.
 */
organization := "com.example"
name := "Scalatra Playground"
version := "0.1.0-alpha"

/* Scala compiler version
 * - Scalatra 3.1.2 requires Scala 2.13+.
 */
scalaVersion := "2.13.18"

/* Library dependencies */
libraryDependencies ++= Seq(
    /* Core Scalatra framework with Jakarta EE 10 support */
    "org.scalatra" %% "scalatra-jakarta" % ScalatraVersion,

    /* Scalatra testing library */
    "org.scalatra" %% "scalatra-scalatest-jakarta" % ScalatraVersion % "test",

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

/* Make the embedded Jetty Launcher run via sbt run and specify the main class */
Compile / mainClass := Some("com.example.JettyLauncher")

/* Fork the JVM for run so logging and JVM properties don’t interfere */
Compile / fork := true

/* Ensure tests run in a separate JVM */
Test / fork := true
