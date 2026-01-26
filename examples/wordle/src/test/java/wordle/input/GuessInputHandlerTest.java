package wordle.input;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GuessInputHandlerTest {
    @Test
    void rejectsEmptyInput() {
        var uut = new GuessInputHandler();

        var result = uut.validate("   ");

        assertThat(result.isValid()).isFalse();
        assertThat(result.errorMessage()).isEqualTo("Empty guess. Try again.");
    }

    @Test
    void rejectsInvalidLength() {
        var uut = new GuessInputHandler();

        var result = uut.validate("BAD");

        assertThat(result.isValid()).isFalse();
        assertThat(result.errorMessage()).isEqualTo("Invalid guess. Try again.");
    }

    @Test
    void acceptsValidLength() {
        var uut = new GuessInputHandler();

        var result = uut.validate("delta");

        assertThat(result.isValid()).isTrue();
        assertThat(result.normalizedGuess()).isEqualTo("delta");
    }
}
