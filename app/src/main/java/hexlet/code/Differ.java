package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static hexlet.code.DiffCalc.calcDifference;
import static hexlet.code.Formatter.genOutput;

public class Differ {
    private static Path getFilePath(String filename) {
        if (Paths.get(filename).isAbsolute()) {
            return Paths.get(filename).normalize();
        } else {
            return Paths.get("src", "test", "resources", filename)
                    .toAbsolutePath().normalize();
        }
    }
    private static String getText(String filename) throws IOException {
        var path = getFilePath(filename);
        return Files.readString(path).trim();
    }

    private static String getInputFormat(String extension) throws Exception {
        if (extension.equals("yml") || extension.equals("yaml")) {
            return "YAML";
        } else if (extension.equals("json")) {
            return "JSON";
        } else {
            throw new Exception("Unknown extension: " + extension); // файл без extension не рассматриваем?
        }
    }
    public static List<DiffDTO> getDiff(String filename1, String filename2) throws Exception {
//        var text1 = getText(filename1);
//        var text2 = getText(filename2);
        var splitFilename1 = filename1.split("\\.");
        var splitFilename2 = filename2.split("\\.");
        String inputFormat1 = getInputFormat(splitFilename1[splitFilename1.length - 1]);
        String inputFormat2 = getInputFormat(splitFilename2[splitFilename2.length - 1]);

        var mappedFile1 = Parser.parseMap(getText(filename1), inputFormat1);
        var mappedFile2 = Parser.parseMap(getText(filename2), inputFormat2);

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
