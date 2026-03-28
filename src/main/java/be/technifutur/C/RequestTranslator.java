package be.technifutur.C;

import java.util.regex.Pattern;

public class RequestTranslator {
    public long translate(String data){
        return switch (data) {
            case "r" -> -998;
            case "q" -> -999;
            default ->
                tryParse(data);

        };
    }

    private long tryParse(String data) {
        return Pattern.compile("^-?\\d+$").matcher(data).find()?
                Long.parseLong(data):
                -1;
    }
}
