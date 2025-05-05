package hexlet.code;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.SortedMap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Differ {
    private static Path getFilePath(String filename) {
        return Paths.get("src", "main", "resources", filename)
                    .toAbsolutePath().normalize();
    }
    public static SortedMap<String, String> parseFile(String filename) throws Exception { // TODO switch to private
        var mapper = new ObjectMapper();
        return mapper.readValue(getFilePath(filename).toFile(), new TypeReference<SortedMap<String, String>>(){});
    }

    // метод формирования строки для вывода и удаление из мапы | UPD не добавил удаление из мапы, т.к. side effect
    private static String entryHandler(String key, String value, String sign) {
        return ("\n  " + sign + " " + key + ": " + value);
    }

    public static String generate(String filename1, String filename2) throws Exception {
        var mappedFile1 = parseFile(filename1);
        var mappedFile2 = parseFile(filename2);
//        ArrayList<String> resultList = new ArrayList<>();
        StringBuilder resultStr = new StringBuilder("\n{");

        if ((mappedFile1.isEmpty()) && (mappedFile2.isEmpty())) {
            return "";
        }

        while ((!mappedFile1.isEmpty()) && (!mappedFile2.isEmpty())) {
            var f1TopKey = mappedFile1.firstKey().trim().toLowerCase();
            var f2TopKey = mappedFile2.firstKey().trim().toLowerCase();
            var f1TopVal = mappedFile1.get(f1TopKey).trim();
            var f2TopVal = mappedFile2.get(f2TopKey).trim();

            int firstCharFlag = Character.compare(f1TopKey.charAt(0), f2TopKey.charAt(0)); // сравниваем первые буквы первого ключа

            if (firstCharFlag < 0) { // второй ключ начинается с большей буквы (дальше по алфавиту)
                resultStr.append(entryHandler(f1TopKey, f1TopVal, "-"));
                mappedFile1.remove(f1TopKey);
            } else if (firstCharFlag > 0) { // первый ключ начинается с большей буквы (дальше по алфавиту)
                resultStr.append(entryHandler(f2TopKey, f2TopVal, "+"));
                mappedFile2.remove(f2TopKey);
            } else if (firstCharFlag == 0) { // буквы совпадают
                if (f1TopKey.equals(f2TopKey)) { // если ключи одинаковые
                    if (f1TopVal.equals(f2TopVal)) { // если значения совпадают, добавляем строку без знака, но с отступом
                        resultStr.append(entryHandler(f1TopKey, f1TopVal, " "));
                    } else {
                        resultStr.append(entryHandler(f1TopKey, f1TopVal, "-"));
                        resultStr.append(entryHandler(f2TopKey, f2TopVal, "+"));
                    }
                    mappedFile1.remove(f1TopKey);
                    mappedFile2.remove(f2TopKey);
                } /* else { // TODO добавить проверку на substring
                } */
            }
        }

        if (!mappedFile1.isEmpty()) {
            while (!mappedFile1.isEmpty()) {
                var topKey = mappedFile1.firstKey().trim().toLowerCase();
                var topVal = mappedFile1.get(topKey).trim();
                resultStr.append(entryHandler(topKey, topVal, "-"));
                mappedFile1.remove(topKey);
            }
        }
        if (!mappedFile2.isEmpty()) {
            while (!mappedFile2.isEmpty()) {
                var topKey = mappedFile2.firstKey().trim().toLowerCase();
                var topVal = mappedFile2.get(topKey).trim();
                resultStr.append(entryHandler(topKey, topVal, "+"));
                mappedFile2.remove(topKey);
            }
        }

        resultStr.append("\n}");
        return resultStr.toString();
    }
}