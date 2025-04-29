package hexlet.code;
//import java.nio.file.Files;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Differ {
    private static Path getFilePath(String filename) {
        return Paths.get("src", "main", "resources", filename)
                    .toAbsolutePath().normalize();
    }
    public static Map<String, String> parseFile(String filename) throws Exception {
        var mapper = new ObjectMapper();
        return mapper.readValue(getFilePath(filename).toFile(), new TypeReference<Map<String, String>>(){});
    }
}
