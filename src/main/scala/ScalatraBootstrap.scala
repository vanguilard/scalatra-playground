import org.scalatra._
import jakarta.servlet.ServletContext
import com.example.scalatraplayground._

class ScalatraBootstrap extends LifeCycle {
    override def init(context: ServletContext): Unit = {
        context.mount(new ScalatraPlaygroundServlet, "/*")
    }
}
