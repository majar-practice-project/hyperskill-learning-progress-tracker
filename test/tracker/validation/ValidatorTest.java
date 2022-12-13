package tracker.validation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {
    private Validator validator = new Validator();

    @Test
    void validateLastName() {
        assertTrue(validator.validateLastName("surname"));
        assertTrue(validator.validateLastName("surna'me"));
        assertTrue(validator.validateLastName("surna me"));
        assertFalse(validator.validateLastName("surna''me"));
        assertFalse(validator.validateLastName("surna' me"));
        assertFalse(validator.validateLastName("e"));
        assertFalse(validator.validateLastName("surna''me'"));
    }
}