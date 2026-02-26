package com.example

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.ee10.webapp.WebAppContext
import org.scalatra.servlet.ScalatraListener

object JettyLauncher {
    def main(args: Array[String]): Unit = {
        val port = sys.env.get("PORT").flatMap(_.toIntOption).getOrElse(8080)

        // Tell Jetty where the webapp directory is
        val context = new WebAppContext("src/main/webapp", "/")

        // Explicitly disable JSP support
        context.setAttribute("org.eclipse.jetty.ee10.jsp.precompiled", java.lang.Boolean.TRUE)
        context.setAttribute("org.eclipse.jetty.ee10.jsp.JettyJspServlet", null)

        // Tell Jetty to bootstrap Scalatra from web.xml configured listener
        context.addEventListener(new ScalatraListener)

        val server = new Server(port)
        server.setHandler(context)

        server.start()
        server.join()
    }
}
