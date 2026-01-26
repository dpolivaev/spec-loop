package wordle.cli;

import wordle.engine.GameEngine;
import wordle.engine.GameState;
import wordle.engine.GameStatus;
import wordle.input.GuessInputHandler;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class CliGameRunner {
    public interface GameLoopListener {
        void onPrompt(GameState state);

        void onInvalidInput(String message);

        void onFeedback(GameState state);

        void onStatus(GameState state);
    }

    private final InputStream input;
    private final GuessInputHandler inputHandler;

    public CliGameRunner(InputStream input, GuessInputHandler inputHandler) {
        this.input = input;
        this.inputHandler = inputHandler;
    }

    public GameState run(GameEngine engine, GameState initialState, GameLoopListener listener) {
        var reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
        var state = initialState;
        while (state.status() == GameStatus.IN_PROGRESS) {
            listener.onPrompt(state);
            String line;
            try {
                line = reader.readLine();
            } catch (Exception exception) {
                throw new IllegalStateException("Failed to read input", exception);
            }
            if (line == null) {
                break;
            }
            var inputResult = inputHandler.validate(line);
            if (!inputResult.isValid()) {
                listener.onInvalidInput(inputResult.errorMessage());
                continue;
            }
            try {
                state = engine.submitGuess(state, inputResult.normalizedGuess());
            } catch (IllegalArgumentException exception) {
                listener.onInvalidInput("Invalid guess. Try again.");
                continue;
            }
            listener.onFeedback(state);
            listener.onStatus(state);
        }
        return state;
    }
}
