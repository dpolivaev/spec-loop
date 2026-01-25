package wordle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import wordle.domain.Feedback;
import wordle.domain.LetterFeedback;
import wordle.domain.LetterStatus;
import wordle.domain.Word;
import wordle.domain.WordleRules;
import java.util.List;

class MainTest {
    @Test
    void placeholderTest() {
        assertTrue(true);
    }

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

    @Test
    void compareAllCorrect() {
        var uut = new WordleRules();
        var feedback = uut.compare(new Word("CRANE"), new Word("CRANE"));
        assertTrue(feedback.entries().stream().allMatch(entry -> entry.status() == LetterStatus.CORRECT));
    }

    @Test
    void compareAllAbsent() {
        var uut = new WordleRules();
        var feedback = uut.compare(new Word("CRANE"), new Word("BOLDY"));
        assertTrue(feedback.entries().stream().allMatch(entry -> entry.status() == LetterStatus.ABSENT));
    }

    @Test
    void comparePresentLetters() {
        var uut = new WordleRules();
        var feedback = uut.compare(new Word("CRANE"), new Word("REACT"));
        assertTrue(feedback.entries().stream().anyMatch(entry -> entry.status() == LetterStatus.PRESENT));
    }

    @Test
    void compareDuplicateLetters() {
        var uut = new WordleRules();
        var feedback = uut.compare(new Word("LEVEL"), new Word("LELEE"));
        long presentCount = feedback.entries().stream()
                .filter(entry -> entry.status() == LetterStatus.PRESENT)
                .count();
        assertTrue(presentCount >= 1);
    }

    @Test
    void compareDuplicateLettersWithExtraGuessRepeats() {
        var uut = new WordleRules();
        var feedback = uut.compare(new Word("APPLE"), new Word("PPPPP"));
        long absentCount = feedback.entries().stream()
                .filter(entry -> entry.status() == LetterStatus.ABSENT)
                .count();
        assertTrue(absentCount >= 1);
    }
}
