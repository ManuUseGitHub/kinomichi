package be.technifutur.M;

public class RequestTranslator {
    public long translate(String data){
        return switch (data) {
            case ":q" -> -999;
            default -> Long.parseLong(data);
        };
    }
}
