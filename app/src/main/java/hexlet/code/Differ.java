package hexlet.code;

import java.util.List;

import static hexlet.code.DiffCalc.calcDifference;
import static hexlet.code.Formatter.genOutput;

public class Differ {
    public static List<DiffDTO> getDiff(String filename1, String filename2) throws Exception {
        var mappedFile1 = Parser.parseFile(filename1);
        var mappedFile2 = Parser.parseFile(filename2);

        return calcDifference(mappedFile1, mappedFile2);
    }

    public static String generate(String filename1, String filename2, String format) throws Exception {
        var diff = getDiff(filename1, filename2);
        var res = genOutput(diff, format);
        return res;
    }
    public static String generate(String filename1, String filename2) throws Exception {
        var diff = getDiff(filename1, filename2);
        var res = genOutput(diff, "stylish");
        return res;
    }
}
