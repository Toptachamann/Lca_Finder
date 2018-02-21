# Lca Finder
Lca Finder is a basic command-line application for reading graphs from *.dot or other appropriately formatted files and finding lowest common ancestors of the given two vertices

### Prerequisites 
You should have JRE or JDK version 1.8 or later installed on your local mashine

### Installation
Download [this](Lca_Finder-1.0.jar) jar file and your are done!

### Usage

Assuming your are in the folder with jar file
```sh
$ java -jar Lca_Finder-1.0.jar -f path/to/file/with/graph -p person_1 person_2
```
If something goes wrong, you'll see this help message

```sh
usage: java -jar jgrapht_warmup-1.0-SNAPSHOT.jar -f [FILE] -p [PERSON_1, PERSON_2]

Computes the lowest common ancestors of the specified people in the graph, stored in the specified
file

 -f,--file <file>               path to file with graph
 -p,--people <person, person>   name of the two people
```