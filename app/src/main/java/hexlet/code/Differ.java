package hexlet.code;

import java.util.ArrayList;
import java.util.TreeSet;

import static hexlet.code.Formatter.genOutput;

public class Differ {
    public static ArrayList<DiffDTO> getDiff(String filename1, String filename2) throws Exception {
        var mappedFile1 = Parcer.parseFile(filename1);
        var mappedFile2 = Parcer.parseFile(filename2);
        ArrayList<DiffDTO> resultList = new ArrayList<>();
        TreeSet<String> keysCombined = new TreeSet<>(mappedFile1.keySet());
        keysCombined.addAll(mappedFile2.keySet());

        if (keysCombined.isEmpty()) {
            return null;
        }
        for (var key : keysCombined) {
            // не уверен, что при парсинге одинаковых значений-массивов они будут храниться в памяти как один объект
            // + из условия "В рамках данного проекта нужно будет анализировать значения ключей только на первом
            // уровне вложенности." -> буду сравнивать, как String
            var value1 = mappedFile1.get(key);
            var value2 = mappedFile2.get(key);
            if (!mappedFile1.containsKey(key)) {
                resultList.add(new DiffDTO(key, value2, Utils.Status.ADDED));
            } else if (!mappedFile2.containsKey(key)) {
                resultList.add(new DiffDTO(key, value1, Utils.Status.REMOVED));
            } else {
                if (String.valueOf(value1).compareTo(String.valueOf(value2)) == 0) {
                    resultList.add(new DiffDTO(key, value2, Utils.Status.EQUAL));
                } else {
                    resultList.add(new DiffDTO(key, value1, Utils.Status.CHANGED, value2));
                }
            }
        }
//        }
        return resultList;
    }

    public static String generate(String filename1, String filename2, String format) throws Exception {
        var diff = getDiff(filename1, filename2);
        var res = genOutput(diff, format);
        return res;
    }
    public static String generate(String filename1, String filename2) throws Exception {
        var diff = getDiff(filename1, filename2);
        var res = genOutput(diff, "stylish");
        return res;
    }
}
