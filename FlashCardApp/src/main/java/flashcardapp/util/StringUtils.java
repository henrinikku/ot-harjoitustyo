package flashcardapp.util;

public class StringUtils {
    public static boolean isNullOrWhitespace(String s) {
        return s == null || s.isEmpty() || s.isBlank();
    }
}