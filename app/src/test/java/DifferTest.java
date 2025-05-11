import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import hexlet.code.Differ;

// TODO обработка исключений
// TODO тест с полем-подстрокой другого поля
public class DifferTest {
    @Test
    public void testBothEmpty() throws Exception {
        String expected = "";
        assertEquals(expected, Differ.generate("empty.json", "empty.json"));
    }
    @Test
    public void testLeftEmpty() throws Exception {
        // https://blogs.oracle.com/javamagazine/post/text-blocks-come-to-java (Incidental White Space and Indentation)
        String expected = """
        \n{
          + follow: false
          + host: hexlet.io
          + proxy: 123.234.53.22
          + timeout: 50
        }""";
        assertEquals(expected, Differ.generate("empty.json", "file1.json"));
    }
    @Test
    public void testRightEmpty() throws Exception {
        String expected = """
        \n{
          - follow: false
          - host: hexlet.io
          - proxy: 123.234.53.22
          - timeout: 50
        }""";
        assertEquals(expected, Differ.generate("file1.json", "empty.json"));
    }
    @Test
    public void testExample() throws Exception {
        String expected = """
        \n{
          - follow: false
            host: hexlet.io
          - proxy: 123.234.53.22
          - timeout: 50
          + timeout: 20
          + verbose: true
        }""";
        assertEquals(expected, Differ.generate("file1.json", "file2.json"));
    }
}
