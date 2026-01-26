package wordle.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WordTest {
    @Test
    void wordNormalizesToUppercase() {
        var uut = new Word("apple");
        assertThat(uut.value()).isEqualTo("APPLE");
    }

    @Test
    void wordRejectsWrongLength() {
        assertThatThrownBy(() -> new Word("TOO"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void wordRejectsNonLetters() {
        assertThatThrownBy(() -> new Word("AB1CD"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void wordAcceptsValidInput() {
        var uut = new Word("LEMON");
        assertThat(uut.value()).isEqualTo("LEMON");
    }
}
