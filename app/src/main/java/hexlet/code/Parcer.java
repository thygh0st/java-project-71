package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.SortedMap;

public class Parcer {
    private static Path getFilePath(String filename) {
        if (Paths.get(filename).isAbsolute()) {
            return Paths.get(filename).normalize();
        } else {
            return Paths.get("src", "test", "resources", filename)
                    .toAbsolutePath().normalize();
        }
    }
    private static ObjectMapper getMapper(String filename) {
        if (filename.endsWith("yml")) {
            return new YAMLMapper();
        } else if (filename.endsWith("json")) {
            return new ObjectMapper();
        } else {
            return null;
        }
    }
    public static SortedMap<String, String> parseFile(String filename) throws Exception {
        ObjectMapper mapper = getMapper(filename);
        if (mapper != null) {
            return mapper.readValue(
                    getFilePath(filename).toFile(), new TypeReference<SortedMap<String, String>>() { }
            );
        } else {
            throw new Exception("Mapper is null!");
        }
    }
}
