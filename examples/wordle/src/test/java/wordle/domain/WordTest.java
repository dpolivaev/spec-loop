package wordle.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WordTest {
    @Test
    void wordNormalizesToUppercase() {
        var uut = new Word("apple");
        assertTrue(uut.value().equals("APPLE"));
    }

    @Test
    void wordRejectsWrongLength() {
        assertThrows(IllegalArgumentException.class, () -> new Word("TOO"));
    }

    @Test
    void wordRejectsNonLetters() {
        assertThrows(IllegalArgumentException.class, () -> new Word("AB1CD"));
    }

    @Test
    void wordAcceptsValidInput() {
        var uut = new Word("LEMON");
        assertTrue(uut.value().equals("LEMON"));
    }
}
