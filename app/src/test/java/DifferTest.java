import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import hexlet.code.Differ;

// TODO обработка исключений
// TODO проверка абсолютного пути до файла
public final class DifferTest {
    public void bothEmpty(String file1, String file2) throws Exception {
        String expected = "";
        assertEquals(expected, Differ.generate(file1, file2));
    }
    public void leftEmpty(String file1, String file2) throws Exception {
        // https://blogs.oracle.com/javamagazine/post/text-blocks-come-to-java (Incidental White Space and Indentation)
        String expected = """
        {
          + follow: false
          + host: hexlet.io
          + proxy: 123.234.53.22
          + timeout: 50\n}""";
        assertEquals(expected, Differ.generate(file1, file2));
    }
    public void rightEmpty(String file1, String file2) throws Exception {
        String expected = """
        {
          - follow: false
          - host: hexlet.io
          - proxy: 123.234.53.22
          - timeout: 50\n}""";
        assertEquals(expected, Differ.generate(file1, file2));
    }
    public void example(String file1, String file2) throws Exception {
        String expected = """
        {
            chars1: [a, b, c]
          - chars2: [d, e, f]
          + chars2: false
          - checked: false
          + checked: true
          - default: null
          + default: [value1, value2]
          - id: 45
          + id: null
          - key1: value1
          + key2: value2
            numbers1: [1, 2, 3, 4]
          - numbers2: [2, 3, 4, 5]
          + numbers2: [22, 33, 44, 55]
          - numbers3: [3, 4, 5]
          + numbers4: [4, 5, 6]
          + obj1: {nestedKey=value, isNested=true}
          - setting1: Some value
          + setting1: Another value
          - setting2: 200
          + setting2: 300
          - setting3: true
          + setting3: none\n}""";
        assertEquals(expected, Differ.generate(file1, file2));
    }
    public void examplePlain(String file1, String file2, String format) throws Exception {
        String expected = """
            Property 'chars2' was updated. From [complex value] to false
            Property 'checked' was updated. From false to true
            Property 'default' was updated. From null to [complex value]
            Property 'id' was updated. From 45 to null
            Property 'key1' was removed
            Property 'key2' was added with value: 'value2'
            Property 'numbers2' was updated. From [complex value] to [complex value]
            Property 'numbers3' was removed
            Property 'numbers4' was added with value: [complex value]
            Property 'obj1' was added with value: [complex value]
            Property 'setting1' was updated. From 'Some value' to 'Another value'
            Property 'setting2' was updated. From 200 to 300
            Property 'setting3' was updated. From true to 'none'""";
        assertEquals(expected, Differ.generate(file1, file2, format));
    }

    @Test
    public void testEmptyJson() throws Exception {
        bothEmpty("empty.json", "empty.json");
    }
    @Test
    public void testEmptyYaml() throws Exception {
        bothEmpty("empty.yml", "empty.yml");
    }
    @Test
    public void testLeftEmptyJson() throws Exception {
        leftEmpty("empty.json", "flat.json");
    }
    @Test
    public void testLeftEmptyYaml() throws Exception {
        leftEmpty("empty.yml", "flat.yml");
    }
    @Test
    public void testRightEmptyJson() throws Exception {
        rightEmpty("flat.json", "empty.json");
    }
    @Test
    public void testRightEmptyYaml() throws Exception {
        rightEmpty("flat.yml", "empty.yml");
    }
    @Test
    public void testExampleJson() throws Exception {
        example("file1.json", "file2.json");
    }
    @Test
    public void testExampleYaml() throws Exception {
        example("file1.yml", "file2.yml");
    }
    @Test
    public void testYamlPlain() throws Exception {
        examplePlain("file1.yml", "file2.yml", "plain");
    }
    @Test
    public void testJsonPlain() throws Exception {
        examplePlain("file1.json", "file2.json", "plain");
    }
}
