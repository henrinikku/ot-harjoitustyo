package flashcardapp.util;

/**
 * Provides utility functions for validating strings
 */
public class StringUtils {

    public static final int STRING_MAX_LENGTH = 255;

    /**
     * Checks if the given string is too long to be stored in a varchar field
     *
     * @param s The string we want to check
     * @return A boolean value indicating whether the given string is too long
     */
    public static boolean isTooLong(String s) {
        if (s == null) {
            return false;
        }
        return s.length() >= 255;
    }

    /**
     * Checks if the given string is null or blank
     *
     * @param s The string we want to check
     * @return A boolean value indicating whether the given string is null or
     *         blank
     */
    public static boolean isNullOrWhitespace(String s) {
        return s == null || s.isEmpty() || s.isBlank();
    }
}
