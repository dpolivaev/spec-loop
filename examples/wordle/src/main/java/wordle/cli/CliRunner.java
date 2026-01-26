package wordle.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import wordle.engine.GameEngine;
import wordle.engine.GameState;
import wordle.engine.GameStatus;
import wordle.domain.WordleRules;
import wordle.words.WordListLoader;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Callable;

@Command(name = "wordle", description = "Play Wordle in the terminal.", mixinStandardHelpOptions = true)
public class CliRunner implements Callable<Integer> {
    @Option(names = "--wordlist", description = "Word list file path or URL.")
    private String wordlistSource;

    @Option(names = "--attempts", description = "Number of attempts before losing.", defaultValue = "6")
    private int maxAttempts;

    private final WordListLoader wordListLoader;
    private final WordleRules rules;
    private final InputStream input;
    private final PrintStream output;

    public CliRunner(WordListLoader wordListLoader, WordleRules rules, InputStream input, PrintStream output) {
        this.wordListLoader = wordListLoader;
        this.rules = rules;
        this.input = input;
        this.output = output;
    }

    @Override
    public Integer call() {
        var engine = new GameEngine(wordListLoader, rules, maxAttempts);
        var state = startGame(engine);
        var reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
        output.println("Wordle started. Enter guesses:");
        while (state.status() == GameStatus.IN_PROGRESS) {
            output.println("Attempts remaining: " + state.attemptsRemaining());
            String line;
            try {
                line = reader.readLine();
            } catch (Exception exception) {
                throw new IllegalStateException("Failed to read input", exception);
            }
            if (line == null) {
                break;
            }
            var trimmed = line.trim();
            if (trimmed.isEmpty()) {
                output.println("Empty guess. Try again.");
                continue;
            }
            try {
                state = engine.submitGuess(state, trimmed);
            } catch (IllegalArgumentException exception) {
                output.println("Invalid guess. Try again.");
                continue;
            }
            output.println("Status: " + state.status());
        }
        if (state.status() == GameStatus.WON) {
            output.println("Result: WON");
        } else if (state.status() == GameStatus.LOST) {
            output.println("Result: LOST");
        } else {
            output.println("Result: INTERRUPTED");
        }
        return 0;
    }

    String wordlistSource() {
        return wordlistSource;
    }

    int maxAttempts() {
        return maxAttempts;
    }

    private GameState startGame(GameEngine engine) {
        if (wordlistSource == null || wordlistSource.isBlank()) {
            return engine.startGame("wordlist.txt");
        }
        return engine.startGameExternal(wordlistSource);
    }
}
