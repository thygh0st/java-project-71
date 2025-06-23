package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public class Parser {
    private static ObjectMapper getMapper(String inputFormat) {
        if (inputFormat.equals("YAML")) {
            return new YAMLMapper();
        } else {
            return new ObjectMapper(); // FAIL_ON_NULL_FOR_PRIMITIVES ?
        }
    }
    public static Map<String, Object> parseMap(String text, String inputFormat) throws Exception {
        var mapper = getMapper(inputFormat);
        return mapper.readValue(text, new TypeReference<Map<String, Object>>() { });
    }
}
