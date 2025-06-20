import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import hexlet.code.Differ;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.nio.file.Files;
import java.nio.file.Paths;

// TODO обработка исключений
// TODO проверка абсолютного пути до файла
public final class DifferTest {
    @ParameterizedTest
    @ValueSource(strings = {".json", ".yml"})
    public void bothEmpty(String extension) throws Exception {
        String expected = "";
        assertEquals(expected, Differ.generate("empty" + extension, "empty" + extension));
    }
    @ParameterizedTest
    @ValueSource(strings = {".json", ".yml"})
    public void leftEmpty(String extension) throws Exception {
        var path = Paths.get("src", "test", "resources", "fixtures", "leftEmpty")
                .toAbsolutePath().normalize();
        var expected = Files.readString(path).trim();
        assertEquals(expected, Differ.generate("empty" + extension, "flat" + extension));
    }
    @ParameterizedTest
    @ValueSource(strings = {".json", ".yml"})
    public void rightEmpty(String extension) throws Exception {
        var path = Paths.get("src", "test", "resources", "fixtures", "rightEmpty")
                .toAbsolutePath().normalize();
        var expected = Files.readString(path).trim();
        assertEquals(expected, Differ.generate("flat" + extension, "empty" + extension, "stylish"));
    }
    @ParameterizedTest
    @ValueSource(strings = {".json", ".yml"})
    public void example(String extension) throws Exception {
        var path = Paths.get("src", "test", "resources", "fixtures", "exampleStylish")
                .toAbsolutePath().normalize();
        var expected = Files.readString(path).trim();
        assertEquals(expected, Differ.generate("file1" + extension, "file2" + extension));
    }
    @ParameterizedTest
    @ValueSource(strings = {".json", ".yml"})
    public void examplePlain(String extension) throws Exception {
        var path = Paths.get("src", "test", "resources", "fixtures", "examplePlain")
                .toAbsolutePath().normalize();
        var expected = Files.readString(path).trim();
        assertEquals(expected, Differ.generate("file1" + extension, "file2" + extension, "plain"));
    }
}
