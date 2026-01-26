package wordle.input;

public record GuessInputResult(String normalizedGuess, String errorMessage) {
    public static GuessInputResult valid(String normalizedGuess) {
        return new GuessInputResult(normalizedGuess, null);
    }

    public static GuessInputResult invalid(String errorMessage) {
        return new GuessInputResult(null, errorMessage);
    }

    public boolean isValid() {
        return normalizedGuess != null;
    }
}
