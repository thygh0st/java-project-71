package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class DiffCalc {
    public static List<DiffDTO> calcDifference(Map<String, Object> map1, Map<String, Object> map2) {
        List<DiffDTO> resultList = new ArrayList<>();
        TreeSet<String> keysCombined = new TreeSet<>(map1.keySet());
        keysCombined.addAll(map2.keySet());

        if (keysCombined.isEmpty()) {
            return null;
        }
        for (var key : keysCombined) {
            // не уверен, что при парсинге одинаковых значений-массивов они будут храниться в памяти как один объект
            // + из условия "В рамках данного проекта нужно будет анализировать значения ключей только на первом
            // уровне вложенности." -> буду сравнивать, как String
            var value1 = map1.get(key);
            var value2 = map2.get(key);
            if (!map1.containsKey(key)) {
                resultList.add(new DiffDTO(key, value2, Status.ADDED));
            } else if (!map2.containsKey(key)) {
                resultList.add(new DiffDTO(key, value1, Status.REMOVED));
            } else if (String.valueOf(value1).compareTo(String.valueOf(value2)) == 0) {
                resultList.add(new DiffDTO(key, value2, Status.EQUAL));
            } else {
                resultList.add(new DiffDTO(key, value1, Status.CHANGED, value2));
            }
        }
        return resultList;
    }
}
