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
    public void blankStringIsChecked() {
        assertTrue(StringUtils.isNullOrWhitespace(""));
    }

    @Test
    public void nonEmptyStringIsAllowed() {
        assertFalse(StringUtils.isNullOrWhitespace("test"));
    }
}
