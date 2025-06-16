package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.ArrayList;

public class Formatter {
    public static String genOutput(ArrayList<DiffDTO> diff, String format) throws Exception {
        String res;
        switch (format) {
            case "plain" :
                res = Plain.genOutput(diff);
                break;
            case "json":
                res = Json.genOutput(diff);
                break;
            case "stylish":
                res = Stylish.genOutput(diff);
                break;
            default:
                throw new Exception("Unknown format: " + format);
        }
        return res;
    }
}
