package hexlet.code.formatters;

import hexlet.code.DiffDTO;
import hexlet.code.Utils;

import java.util.ArrayList;

public class Stylish {
    public static String genOutput(ArrayList<DiffDTO> diff) throws Exception {
        StringBuilder resultStr = new StringBuilder("{");
        String operator = "";

        // пустая структура
        if (diff == null) {
            return "";
        }
        for (var entry : diff) {
            switch (entry.getStatus()) {
                case ADDED:
                    operator = "+ ";
                    break;
                case REMOVED:
                case CHANGED:
                    operator = "- ";
                    break;
                case EQUAL:
                    operator = "  ";
                    break;
                default:
                    throw new Exception("Unknown entry status!");
            }
            resultStr.append("\n  " + operator + entry.getKey() + ": " + entry.getValue());
            if (entry.getStatus() == Utils.Status.CHANGED) {
                resultStr.append("\n  " + "+ " + entry.getKey() + ": " + entry.getSecondValue());
            }
        }

        resultStr.append("\n}");
        return resultStr.toString();
    }
}
