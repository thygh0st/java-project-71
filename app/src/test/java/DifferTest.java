import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import hexlet.code.Differ;

// TODO обработка исключений
// TODO тест с полем-подстрокой другого поля
public class DifferTest {
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
          + timeout: 50
        }""";
        assertEquals(expected, Differ.generate(file1, file2));
    }
    public void rightEmpty(String file1, String file2) throws Exception {
        String expected = """

        {
          - follow: false
          - host: hexlet.io
          - proxy: 123.234.53.22
          - timeout: 50
        }""";
        assertEquals(expected, Differ.generate(file1, file2));
    }
    public void example(String file1, String file2) throws Exception {
        String expected = """

        {
          - follow: false
            host: hexlet.io
          - proxy: 123.234.53.22
          - timeout: 50
          + timeout: 20
          + verbose: true
        }""";
        assertEquals(expected, Differ.generate(file1, file2));
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
        leftEmpty("empty.json", "file1.json");
    }
    @Test
    public void testLeftEmptyYaml() throws Exception {
        leftEmpty("empty.yml", "file1.yml");
    }
    @Test
    public void testRightEmptyJson() throws Exception {
        rightEmpty("file1.json", "empty.json");
    }
    @Test
    public void testRightEmptyYaml() throws Exception {
        rightEmpty("file1.yml", "empty.yml");
    }
    @Test
    public void testExampleJson() throws Exception {
        example("file1.json", "file2.json");
    }
    @Test
    public void testExampleYaml() throws Exception {
        example("file1.yml", "file2.yml");
    }
}
