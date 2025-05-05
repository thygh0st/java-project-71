package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 0.1",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {
    @Parameters(index = "0", description = "path to first file")
    private String filepath1;
    @Parameters(index = "1", description = "path to second file")
    private String filepath2;

    @Option(names = {"-f", "--format"}, defaultValue = "stylish",
            description = "output format {plain, stylish} [default: ${DEFAULT-VALUE}]")
    private String format;

    @Override
    public Integer call() throws Exception {
//        System.out.println("Hello World!");
//        var file1 = Differ.parseFile(filepath1);
//        var file2 = Differ.parseFile(filepath2);
//        System.out.println(file1.toString());
//        System.out.println(file2.toString());

        System.out.println(Differ.generate(filepath1, filepath2));
        return 0;
    }
    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
