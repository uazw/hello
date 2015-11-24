package net.kimleo.hello.validate;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TokenValidatorTest {

    private Validator<String> validator;

    @Before
    public void setUp() throws Exception {
        validator = new TokenValidator();
    }

    @Test
    public void should_success_for_valid_token() throws Exception {
        assertTrue(validator.validate("hello"));
    }

    @Test
    public void should_fail_for_invalid_token() throws Exception {
        assertFalse(validator.validate(""));
    }

    @Test
    public void should_fail_for_null() throws Exception {
        assertFalse(validator.validate(null));
    }
}