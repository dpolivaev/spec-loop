package wordle.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WordleRulesTest {
    @Test
    void compareAllCorrect() {
        var uut = new WordleRules();
        var feedback = uut.compare(new Word("CRANE"), new Word("CRANE"));
        assertThat(feedback.entries())
                .allMatch(entry -> entry.status() == LetterStatus.CORRECT);
    }

    @Test
    void compareAllAbsent() {
        var uut = new WordleRules();
        var feedback = uut.compare(new Word("CRANE"), new Word("BOLDY"));
        assertThat(feedback.entries())
                .allMatch(entry -> entry.status() == LetterStatus.ABSENT);
    }

    @Test
    void comparePresentLetters() {
        var uut = new WordleRules();
        var feedback = uut.compare(new Word("CRANE"), new Word("REACT"));
        assertThat(feedback.entries())
                .anyMatch(entry -> entry.status() == LetterStatus.PRESENT);
    }

    @Test
    void compareDuplicateLetters() {
        var uut = new WordleRules();
        var feedback = uut.compare(new Word("LEVEL"), new Word("LELEE"));
        long presentCount = feedback.entries().stream()
                .filter(entry -> entry.status() == LetterStatus.PRESENT)
                .count();
        assertThat(presentCount).isGreaterThanOrEqualTo(1);
    }

    @Test
    void compareDuplicateLettersWithExtraGuessRepeats() {
        var uut = new WordleRules();
        var feedback = uut.compare(new Word("APPLE"), new Word("PPPPP"));
        long absentCount = feedback.entries().stream()
                .filter(entry -> entry.status() == LetterStatus.ABSENT)
                .count();
        assertThat(absentCount).isGreaterThanOrEqualTo(1);
    }
}
