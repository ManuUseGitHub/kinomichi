package be.technifutur.kinomichi.C;

import be.technifutur.kinomichicommon.Constants;

import java.util.regex.Pattern;

public class RequestTranslator {
    public long translate(String data){
        return switch (data) {
            case "a" -> Constants.GO_HOME_CODE_GRANTED;
            case "r" -> Constants.BACK_CODE;
            case "q" -> Constants.EXIT_CODE;
            default ->
                tryParse(data);

        };
    }

    private long tryParse(String data) {
        return Pattern.compile("^-?\\d+$").matcher(data).find()?
                Long.parseLong(data):
                Constants.UNMATCH_CODE;
    }
}
