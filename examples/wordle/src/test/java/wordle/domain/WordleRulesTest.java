package wordle.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class WordleRulesTest {
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
