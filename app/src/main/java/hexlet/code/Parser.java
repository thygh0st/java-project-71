package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class Parser {
    public static Map<String, Object> parseMap(ObjectMapper mapper, String text) throws JsonProcessingException {
        return mapper.readValue(text, new TypeReference<Map<String, Object>>() { });
    }
}
