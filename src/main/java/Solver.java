import jgrapht.GraphReader;
import jgrapht.LcaFinder;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.io.ImportException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

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
    Option file =
        Option.builder("f")
            .longOpt("file")
            .required()
            .valueSeparator()
            .argName("file")
            .hasArg()
            .desc("path to file with graph")
            .build();
    Option aOption =
        Option.builder("a")
            .longOpt("person-1")
            .argName("name")
            .required()
            .hasArg()
            .desc("name of the first person")
            .build();
    Option bOption =
        Option.builder("b")
            .longOpt("person-2")
            .argName("name")
            .required()
            .hasArg()
            .desc("name of the second person")
            .build();

    Options options = new Options();
    options.addOption(file).addOption(aOption).addOption(bOption);

    CommandLineParser parser = new DefaultParser();
    try {
      CommandLine commandLine = parser.parse(options, args);
      String path = commandLine.getOptionValue('f');
      String a = commandLine.getOptionValue('a');
      String b = commandLine.getOptionValue('b');
      Set<String> lcas = solver.getLcas(path, a, b);
      if (lcas.size() == 0) {
        System.out.println(String.format("No ancestors found for %s and %s in common", a, b));
      } else {
        System.out.print(
            String.format(
                "Lowest common " + (lcas.size() == 1 ? "ancestor" : "ancestors") + " of %s and %s: ",
                a,
                b));
        System.out.println(lcas.stream().collect(Collectors.joining(", ")));
      }
    } catch (ParseException e) {
      HelpFormatter helpFormatter = new HelpFormatter();
      helpFormatter.printHelp(
          100,
          "java -jar jgrapht_warmup-1.0-SNAPSHOT.jar -a [PERSON_A] -b [PERSON_B] -f [FILE]\n\n",
          "Computes the lowest common ancestors of the specified people a "
              + "and b in the graph, stored in the specified file\n\n",
          options,
          "\nEnd of the usage");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (ImportException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public Set<String> getLcas(String path, String a, String b) throws IOException, ImportException {
    Graph<String, DefaultEdge> graph = reader.readGraph(path);
    return finder.findLcas(graph, a, b);
  }
}
