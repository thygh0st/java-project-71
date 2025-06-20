package hexlet.code.formatters;

import hexlet.code.DiffDTO;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Plain {
    private static String valueFormatter(Object value) {
//        if ((value == null)
//            || (value.getClass().getSuperclass() == Number.class)
//            || (value.getClass() == Boolean.class)) {
//            return String.valueOf(value);
//        }
        if (value == null) {
            return String.valueOf(value);
        }
        if (value.getClass().equals(ArrayList.class) || value.getClass().equals(LinkedHashMap.class)) {
            return "[complex value]";
        }
        if (value.getClass().equals(String.class)) {
            return "'" + value + "'";
        }
        return value.toString();
    }

    public static String genOutput(List<DiffDTO> diff) throws Exception {
        StringBuilder resultStr = new StringBuilder();

        if (diff == null) {
            return "";
        }
        for (var entry : diff) {
            switch (entry.getStatus()) {
                case ADDED:
                    resultStr.append(
                            "\nProperty '" + entry.getKey() + "' was added with value: "
                            + valueFormatter(entry.getValue())
                    );
                    break;
                case REMOVED:
                    resultStr.append(
                            "\nProperty '" + entry.getKey() + "' was removed"
                    );
                    break;
                case CHANGED:
                    resultStr.append(
                            "\nProperty '" + entry.getKey() + "' was updated. From "
                            + valueFormatter(entry.getValue()) + " to " + valueFormatter(entry.getSecondValue())
                    );
                    break;
                case EQUAL:
                    break;
                default:
                    throw new Exception("Unknown entry status!");
            }
        }
        return resultStr.toString().trim();
    }
}
