package store.luniversdemm.common;

import be.technifutur.kinomichicommon.ParticipantType;
import be.technifutur.kinomichicommon.interfaces.HasName;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

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

    public static void onReadTextFile(String fileName,Consumer<String> cb ){
        StringBuilder sb = new StringBuilder();
        try(InputStreamReader reader = new InputStreamReader(
                new FileInputStream(fileName), "UTF-8")){
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

    public static <T> T[][] expandWithSpaces(T[][] input, Class<T> clazz) {
        int rows = input.length;
        int cols = input[0].length;

        int newRows = rows * 2 - 1;
        int newCols = cols * 2 - 1;

        @SuppressWarnings("unchecked")
        T[][] result = (T[][]) java.lang.reflect.Array.newInstance(clazz, newRows, newCols);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                result[r * 2][c * 2] = input[r][c];
            }
        }

        return result;
    }

    public static <T> boolean hasListDuplicates(List<T> list) {
        Set<T> set = new HashSet<>();
        boolean duplicates = false;
        for (T i : list) {
            if (!duplicates && set.contains(i)) {
                duplicates = true;
            } else {
                set.add(i);
            }
        }
        return duplicates;
    }

    public static <T extends Enum<T> & HasName> T[] evenListOf(Class<T> enumType, AtomicInteger maxSize){
        T[] typeList = enumType.getEnumConstants();
        int n = typeList.length;
        T[] arr = (T[]) Array.newInstance(enumType, n%2 == 0? n: n+1);

        IntStream.range(0,arr.length).forEach(i -> {
            if(i<typeList.length) {
                T type = typeList[i];
                maxSize.set(Math.max(type.getName().length(), maxSize.get()));
                arr[i] = typeList[i];
            }
        });

        return arr;
    }
}