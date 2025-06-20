package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

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
    private static ObjectMapper getMapper(String filename)  throws Exception {
        if (filename.endsWith("yml") || filename.endsWith("yaml")) {
            return new YAMLMapper();
        } else if (filename.endsWith("json")) {
            return new ObjectMapper(); // FAIL_ON_NULL_FOR_PRIMITIVES ?
        } else {
            throw new Exception("Unknown file extension");
        }
    }
    private static String getText(String filename) throws IOException {
        var path = getFilePath(filename);
        return Files.readString(path).trim();
    }

    public static List<DiffDTO> getDiff(String filename1, String filename2) throws Exception {
        var text1 = getText(filename1);
        var text2 = getText(filename2);
        var mappedFile1 = Parser.parseMap(getMapper(filename1), text1);
        var mappedFile2 = Parser.parseMap(getMapper(filename2), text2);

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
