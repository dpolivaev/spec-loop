package wordle.input;

public class GuessInputHandler {
    public GuessInputResult validate(String rawInput) {
        if (rawInput == null) {
            return GuessInputResult.invalid("Empty guess. Try again.");
        }
        var trimmed = rawInput.trim();
        if (trimmed.isEmpty()) {
            return GuessInputResult.invalid("Empty guess. Try again.");
        }
        if (trimmed.length() != 5) {
            return GuessInputResult.invalid("Invalid guess. Try again.");
        }
        return GuessInputResult.valid(trimmed);
    }
}
