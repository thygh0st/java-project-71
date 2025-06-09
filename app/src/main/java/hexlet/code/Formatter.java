package hexlet.code;

import java.util.ArrayList;

public class Formatter {
    public static String genOutput(ArrayList<DiffDTO> diff, String format) throws Exception {
        String res;
        switch (format) {
            case "plain" :
                res = "";
                break;
            default:
                res = stylish(diff);
                break;
        }
        return res;
    }
    public static String stylish(ArrayList<DiffDTO> diff) throws Exception {
        StringBuilder resultStr = new StringBuilder("\n{");
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
