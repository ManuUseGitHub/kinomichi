package be.technifutur.kinomichi.C;

import be.technifutur.kinomichicommon.Constants;
import be.technifutur.kinomichicommon.interfaces.Savable;

import java.util.regex.Pattern;

public class RequestTranslator {
    public long translate(String data, Savable persistable) {
        return switch (data) {
            case "a" -> persistable.isSaved() ?
                    Constants.GO_HOME_CODE_GRANTED :
                    Constants.GO_HOME_CODE_CONDITIONED;
            case "r" -> Constants.BACK_CODE;
            case "q" -> Constants.EXIT_CODE;
            default -> tryParse(data);

        };
    }

    private long tryParse(String data) {
        return Pattern.compile("^-?\\d+$").matcher(data).find() ?
                Long.parseLong(data) :
                Constants.UNMATCH_CODE;
    }
}
