package wordle.domain;

public record LetterFeedback(int position, char letter, LetterStatus status) {
}
