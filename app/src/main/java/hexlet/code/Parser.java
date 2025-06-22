package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public class Parser {
    private static ObjectMapper getMapper(String extension) throws Exception {
        if (extension.equals("yml") || extension.equals("yaml")) {
            return new YAMLMapper();
        } else if (extension.equals("json")) {
            return new ObjectMapper(); // FAIL_ON_NULL_FOR_PRIMITIVES ?
        } else {
            throw new Exception("Unknown file extension: " + extension);
        }
    }
    public static Map<String, Object> parseMap(String text, String extension) throws Exception {
        var mapper = getMapper(extension);
        return mapper.readValue(text, new TypeReference<Map<String, Object>>() { });
    }
}
