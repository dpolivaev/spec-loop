package wordle;

import picocli.CommandLine;
import wordle.cli.CliRunner;
import wordle.domain.WordleRules;
import wordle.words.WordListLoader;

public class Main {
    public static void main(String[] args) {
        var runner = new CliRunner(new WordListLoader(), new WordleRules(), System.in, System.out);
        int exitCode = new CommandLine(runner).execute(args);
        System.exit(exitCode);
    }
}
