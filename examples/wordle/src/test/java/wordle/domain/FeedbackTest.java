package wordle.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FeedbackTest {
    @Test
    void feedbackPreservesEntries() {
        var entry = new LetterFeedback(0, 'A', LetterStatus.CORRECT);
        var uut = new Feedback(List.of(entry));
        assertThat(uut.entries()).containsExactly(entry);
    }

    @Test
    void letterFeedbackAccessorsExposeValues() {
        var uut = new LetterFeedback(2, 'Z', LetterStatus.ABSENT);
        assertThat(uut.position()).isEqualTo(2);
        assertThat(uut.letter()).isEqualTo('Z');
        assertThat(uut.status()).isEqualTo(LetterStatus.ABSENT);
    }
}
