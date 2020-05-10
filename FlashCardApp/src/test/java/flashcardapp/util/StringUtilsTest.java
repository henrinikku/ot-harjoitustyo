package flashcardapp.util;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StringUtilsTest {

    @Test
    public void nullStringIsChecked() {
        assertTrue(StringUtils.isNullOrWhitespace(null));
    }

    @Test
    public void emptyStringIsChecked() {
        assertTrue(StringUtils.isNullOrWhitespace(""));
    }

    @Test
    public void whiteSpaceIsNotAllowed() {
        assertTrue(StringUtils.isNullOrWhitespace("   "));
    }

    @Test
    public void nonEmptyStringIsAllowed() {
        assertFalse(StringUtils.isNullOrWhitespace("test"));
    }

    @Test
    public void nullStringIsNotTooLong() {
        assertFalse(StringUtils.isTooLong(null));
    }

    @Test
    public void tooLongStringIsNotAllowed() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < StringUtils.STRING_MAX_LENGTH * 2; i++) {
            builder.append("a");
        }
        assertTrue(StringUtils.isTooLong(builder.toString()));
    }
}
