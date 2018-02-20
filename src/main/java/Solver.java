import com.timofey.chudakov.GraphReader;
import com.timofey.chudakov.LcaFinder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Solver {

  private GraphReader reader;
  private LcaFinder finder;


  public Solver(GraphReader reader, LcaFinder finder) {
    this.reader = reader;
    this.finder = finder;
  }

  public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/beans.xml");
    Solver solver = context.getBean(Solver.class);

  }
}
