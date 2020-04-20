package flashcardapp.util;

import flashcardapp.dao.FakeUserDao;
import flashcardapp.model.User;
import flashcardapp.service.DefaultUserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.*;

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
