package wordle;

import picocli.CommandLine;
import wordle.cli.CliOptions;
import wordle.cli.FeedbackRenderer;
import wordle.domain.WordleRules;
import wordle.input.GuessInputHandler;
import wordle.words.WordListLoader;

public class Main {
    public static void main(String[] args) {
        var runner = new CliOptions(new WordListLoader(), new WordleRules(), System.in, System.out,
                new GuessInputHandler(), new FeedbackRenderer());
        int exitCode = new CommandLine(runner).execute(args);
        System.exit(exitCode);
    }
}
