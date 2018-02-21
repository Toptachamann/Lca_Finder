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

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

public class Solver {

  /** object for reading graph from file */
  private GraphReader reader;

  /** object for finding lowest common ancestors */
  private LcaFinder finder;

  /**
   * initializes <code>reader</code> and <code>finder</code>
   *
   * <p><b>Note:</b> used for Spring dependency injection
   */
  public Solver(GraphReader reader, LcaFinder finder) {
    this.reader = reader;
    this.finder = finder;
  }

  /**
   * Application entry point
   *
   * <p>Defines the command line interface of the application and retrieves specified parameters
   */
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
        Option.builder("p")
            .longOpt("people")
            .numberOfArgs(2)
            .argName("person, person")
            .required()
            .desc("name of the two people")
            .build();
    Options options = new Options();
    options.addOption(file).addOption(aOption);

    CommandLineParser parser = new DefaultParser();
    try {
      CommandLine commandLine = parser.parse(options, args);
      String path = commandLine.getOptionValue('f');
      String[] p = commandLine.getOptionValues('p');
      if (p.length >= 2) {
        solver.handleInput(path, p[0], p[1]);
      } else {
        // Shouldn't happen due to command line option's format
        System.out.println("You have to specify two valid people");
      }
    } catch (ParseException e) {
      HelpFormatter helpFormatter = new HelpFormatter();
      helpFormatter.printHelp(
          100,
          "java -jar jgrapht_warmup-1.0-SNAPSHOT.jar -f [FILE] -p [PERSON_1, PERSON_2]\n\n",
          "Computes the lowest common ancestors of the specified people"
              + " in the graph, stored in the specified file\n\n",
          options,
          "");
    }
  }

  /**
   * Reads the graph from the file defines by the specified path, finds lowest common ancestors of
   * <code>a</code> and <code>b</code>, and outputs them to the standard output
   */
  public void handleInput(String path, String a, String b) {
    File file = new File(path);
    if (!file.exists() || !file.isFile() || !file.canRead()) {
      System.out.println("Specified file should be an existing readable file");
    } else {
      try {
        Graph<String, DefaultEdge> graph = reader.readGraph(file);
        if (!graph.containsVertex(a)) {
          System.out.println(
              String.format("Graph from file %s doesn't contain vertex %s", path, a));
        } else if (!graph.containsVertex(b)) {
          System.out.println(
              String.format("Graph from file %s doesn't contain vertex %s", path, b));
        } else if(a.equals(b)) {
          System.out.println("You specified identical vertices");
        }else {
          Set<String> lcas = finder.findLcas(graph, a, b);
          if (lcas.size() == 0) {
            System.out.println(String.format("No ancestors found for %s and %s in common", a, b));
          } else {
            System.out.print(
                String.format(
                    "Lowest common "
                        + (lcas.size() == 1 ? "ancestor" : "ancestors")
                        + " of %s and %s: ",
                    a,
                    b));
            System.out.println(lcas.stream().collect(Collectors.joining(", ")));
          }
        }
      } catch (IOException e) {
        System.out.println("An IOException occurred: " + e.getMessage());
      } catch (ImportException e) {
        System.out.println("An import exception occurred: " + e.getMessage());
      }
    }
  }
}
