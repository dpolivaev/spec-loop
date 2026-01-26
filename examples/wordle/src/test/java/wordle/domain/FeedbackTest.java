package wordle.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FeedbackTest {
    @Test
    void feedbackPreservesEntries() {
        var entry = new LetterFeedback(0, 'A', LetterStatus.CORRECT);
        var uut = new Feedback(List.of(entry));
        assertTrue(uut.entries().get(0).equals(entry));
    }

    @Test
    void letterFeedbackAccessorsExposeValues() {
        var uut = new LetterFeedback(2, 'Z', LetterStatus.ABSENT);
        assertTrue(uut.position() == 2);
        assertTrue(uut.letter() == 'Z');
        assertTrue(uut.status() == LetterStatus.ABSENT);
    }
}
