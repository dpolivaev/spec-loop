package wordle.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import wordle.engine.GameEngine;
import wordle.engine.GameState;
import wordle.engine.GameStatus;
import wordle.domain.WordleRules;
import wordle.input.GuessInputHandler;
import wordle.words.WordListLoader;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.concurrent.Callable;

@Command(name = "wordle", description = "Play Wordle in the terminal.", mixinStandardHelpOptions = true)
public class CliOptions implements Callable<Integer> {
    @Option(names = "--wordlist", description = "Word list file path or URL.")
    private String wordlistSource;

    @Option(names = "--attempts", description = "Number of attempts before losing.", defaultValue = "6")
    private int maxAttempts;

    @Option(names = "--cli", description = "Force CLI mode.")
    private boolean runsInTerminal;

    private final WordListLoader wordListLoader;
    private final WordleRules rules;
    private final InputStream input;
    private final PrintStream output;
    private final GuessInputHandler inputHandler;
    private final FeedbackRenderer renderer;

    public CliOptions(WordListLoader wordListLoader, WordleRules rules, InputStream input, PrintStream output,
                      GuessInputHandler inputHandler, FeedbackRenderer renderer) {
        this.wordListLoader = wordListLoader;
        this.rules = rules;
        this.input = input;
        this.output = output;
        this.inputHandler = inputHandler;
        this.renderer = renderer;
    }

    @Override
    public Integer call() {
        var engine = new GameEngine(wordListLoader, rules, maxAttempts);
        var state = startGame(engine);
        var runner = new CliGameRunner(input, inputHandler);
        output.println("Wordle started. Enter guesses:");
        state = runner.run(engine, state, new ConsoleListener(output, renderer));
        if (state.status() == GameStatus.WON) {
            output.println("Result: WON");
        } else if (state.status() == GameStatus.LOST) {
            output.println("Result: LOST");
        } else {
            output.println("Result: INTERRUPTED");
        }
        return 0;
    }

    public String wordlistSource() {
        return wordlistSource;
    }

    public int maxAttempts() {
        return maxAttempts;
    }

    public boolean runsInTerminal() {
        return runsInTerminal;
    }

    private GameState startGame(GameEngine engine) {
        if (wordlistSource == null || wordlistSource.isBlank()) {
            return engine.startGame("wordlist.txt");
        }
        return engine.startGameExternal(wordlistSource);
    }

    private static class ConsoleListener implements CliGameRunner.GameLoopListener {
        private final PrintStream output;
        private final FeedbackRenderer renderer;

        private ConsoleListener(PrintStream output, FeedbackRenderer renderer) {
            this.output = output;
            this.renderer = renderer;
        }

        @Override
        public void onPrompt(GameState state) {
            output.println("Attempts remaining: " + state.attemptsRemaining());
        }

        @Override
        public void onInvalidInput(String message) {
            output.println(message);
        }

        @Override
        public void onFeedback(GameState state) {
            var rendered = renderer.render(state);
            if (!rendered.isBlank()) {
                output.println(rendered);
            }
        }

        @Override
        public void onStatus(GameState state) {
            output.println("Status: " + state.status());
        }
    }
}
