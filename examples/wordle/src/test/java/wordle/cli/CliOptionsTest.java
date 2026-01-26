package wordle.cli;

import org.junit.jupiter.api.Test;
import picocli.CommandLine;
import wordle.domain.WordleRules;
import wordle.input.GuessInputHandler;
import wordle.words.WordListLoader;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class CliOptionsTest {
    @Test
    void defaultsApplyWhenNoArgsProvided() {
        var options = new CliOptions(new WordListLoader(), new WordleRules(),
                new ByteArrayInputStream(new byte[0]), new PrintStream(new ByteArrayOutputStream()),
                new GuessInputHandler(), new FeedbackRenderer());
        var commandLine = new CommandLine(options);
        commandLine.parseArgs();
        assertThat(options.wordlistSource()).isNull();
        assertThat(options.maxAttempts()).isEqualTo(6);
        assertThat(options.runsInTerminal()).isFalse();
    }

    @Test
    void cliOptionIsParsed() {
        var options = new CliOptions(new WordListLoader(), new WordleRules(),
                new ByteArrayInputStream(new byte[0]), new PrintStream(new ByteArrayOutputStream()),
                new GuessInputHandler(), new FeedbackRenderer());
        var commandLine = new CommandLine(options);
        commandLine.parseArgs("--cli");

        assertThat(options.runsInTerminal()).isTrue();
    }

    @Test
    void emptyInputLineIsRejectedAndGameContinues() {
        var input = "\nDELTA\n";
        var outputBuffer = new ByteArrayOutputStream();
        var filePath = writeTempWordList();
        var options = new CliOptions(new WordListLoader(), new WordleRules(),
                new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)), new PrintStream(outputBuffer),
                new GuessInputHandler(), new FeedbackRenderer());
        var commandLine = new CommandLine(options);
        int exitCode = commandLine.execute("--wordlist", filePath.toString(), "--attempts", "1");
        var output = new String(outputBuffer.toByteArray(), StandardCharsets.UTF_8);
        assertThat(exitCode).isEqualTo(0);
        assertThat(output).contains("Empty guess");
        assertThat(output).contains("Attempts remaining: 1");
        assertThat(output).contains("Status: WON");
        assertThat(output).contains("Result: WON");
    }

    @Test
    void invalidGuessIsRejectedAndDoesNotConsumeAttempt() {
        var input = "BAD\nDELTA\n";
        var outputBuffer = new ByteArrayOutputStream();
        var filePath = writeTempWordList();
        var options = new CliOptions(new WordListLoader(), new WordleRules(),
                new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)), new PrintStream(outputBuffer),
                new GuessInputHandler(), new FeedbackRenderer());
        var commandLine = new CommandLine(options);
        int exitCode = commandLine.execute("--wordlist", filePath.toString(), "--attempts", "1");
        var output = new String(outputBuffer.toByteArray(), StandardCharsets.UTF_8);
        assertThat(exitCode).isEqualTo(0);
        assertThat(output).contains("Invalid guess");
        assertThat(output).contains("Attempts remaining: 1");
        assertThat(output).contains("Result: WON");
    }

    @Test
    void wordlistUrlIsAccepted() {
        var outputBuffer = new ByteArrayOutputStream();
        var filePath = writeTempWordList();
        var options = new CliOptions(new WordListLoader(), new WordleRules(),
                new ByteArrayInputStream("DELTA\n".getBytes(StandardCharsets.UTF_8)), new PrintStream(outputBuffer),
                new GuessInputHandler(), new FeedbackRenderer());
        var commandLine = new CommandLine(options);
        int exitCode = commandLine.execute("--wordlist", filePath.toUri().toString(), "--attempts", "1");
        assertThat(exitCode).isEqualTo(0);
        var output = new String(outputBuffer.toByteArray(), StandardCharsets.UTF_8);
        assertThat(output).contains("Attempts remaining: 1");
        assertThat(output).contains("Result: WON");
    }

    @Test
    void inputEndIsReportedAsInterrupted() {
        var outputBuffer = new ByteArrayOutputStream();
        var filePath = writeTempWordList();
        var options = new CliOptions(new WordListLoader(), new WordleRules(),
                new ByteArrayInputStream(new byte[0]), new PrintStream(outputBuffer),
                new GuessInputHandler(), new FeedbackRenderer());
        var commandLine = new CommandLine(options);
        int exitCode = commandLine.execute("--wordlist", filePath.toString(), "--attempts", "1");
        assertThat(exitCode).isEqualTo(0);
        var output = new String(outputBuffer.toByteArray(), StandardCharsets.UTF_8);
        assertThat(output).contains("Attempts remaining: 1");
        assertThat(output).contains("Result: INTERRUPTED");
    }

    private Path writeTempWordList() {
        try {
            var path = Files.createTempFile("wordlist", ".txt");
            var content = "1 words\nDELTA\n";
            Files.writeString(path, content, StandardCharsets.UTF_8);
            path.toFile().deleteOnExit();
            return path;
        } catch (Exception exception) {
            throw new IllegalStateException("Failed to create temp word list", exception);
        }
    }
}
