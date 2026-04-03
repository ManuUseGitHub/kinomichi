package store.luniversdemm.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
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

    public static void onMatches(String pattern, CharSequence input, Consumer<Matcher> cb) {
        onMatches(Pattern.compile(pattern),input,cb);
    }
    public static void onMatches(Pattern pattern, CharSequence input, Consumer<Matcher> cb) {
        Matcher m = pattern
                .matcher(input);
        while (m.find()) {
            cb.accept(m);
        }
    }

    public static void onReadTextFile(Consumer<String> cb ){
        StringBuilder sb = new StringBuilder();
        try(InputStreamReader reader = new InputStreamReader(
                new FileInputStream("saveState.Kino"), "UTF-8")){
            int c;
            while ((c = reader.read()) != -1) {
                sb.append((char) c);
            }
            cb.accept(sb.toString());

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}