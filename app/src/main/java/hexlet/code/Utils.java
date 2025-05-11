package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.SortedMap;

public class Utils {
    private static Path getFilePath(String filename) {
        if (Paths.get(filename).isAbsolute()) {
            return Paths.get(filename).normalize();
        } else {
            return Paths.get("src", "test", "resources", filename)
                    .toAbsolutePath().normalize();
        }
    }
    public static SortedMap<String, String> parseFile(String filename) throws Exception { // TODO switch to private
        var mapper = new ObjectMapper();
        return mapper.readValue(getFilePath(filename).toFile(), new TypeReference<SortedMap<String, String>>() { });
    }
}
