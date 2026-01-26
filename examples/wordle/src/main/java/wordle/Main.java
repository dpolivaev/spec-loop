package wordle;

import picocli.CommandLine;
import wordle.cli.CliOptions;
import wordle.cli.FeedbackRenderer;
import wordle.domain.WordleRules;
import wordle.input.GuessInputHandler;
import wordle.ui.WordleApp;
import wordle.words.WordListLoader;

import java.awt.GraphicsEnvironment;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        var runner = new CliOptions(new WordListLoader(), new WordleRules(), System.in, System.out,
                new GuessInputHandler(), new FeedbackRenderer());
        var commandLine = new CommandLine(runner);
        commandLine.parseArgs(args);
        boolean runsInTerminal = runner.runsInTerminal();
        if (!runsInTerminal && !GraphicsEnvironment.isHeadless()) {
            var app = new WordleApp(new WordListLoader(), new WordleRules(), runner.maxAttempts(),
                    runner.wordlistSource());
            app.start();
            return;
        }
        int exitCode = commandLine.execute(args);
        System.exit(exitCode);
    }
}
