import org.eclipse.jetty.server.Server
import org.eclipse.jetty.ee10.webapp.WebAppContext
import org.scalatra.servlet.ScalatraListener

object JettyLauncher {
    def main(args: Array[String]): Unit = {
        val portNumber = 8080
        val port = sys.env.get("PORT").flatMap(_.toIntOption).getOrElse(portNumber)

        // Tell Jetty where the webapp directory is
        val context = new WebAppContext("src/main/webapp", "/")

        // Tell Jetty to bootstrap Scalatra from web.xml configured listener
        context.addEventListener(new ScalatraListener)

        val server = new Server(port)
        server.setHandler(context)

        server.start()
        server.join()
    }
}
