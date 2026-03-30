package store.luniversdemm.common;

import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public static boolean onMatch(String pattern, CharSequence input, Consumer<Matcher> cb) {
        Matcher m = Pattern
                .compile(pattern)
                .matcher(input);
        final boolean found = m.find();
        if (m.matches()) {
            cb.accept(m);
        }
        return found;
    }
}