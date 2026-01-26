package wordle.cli;

import org.junit.jupiter.api.Test;
import picocli.CommandLine;
import wordle.domain.WordleRules;
import wordle.words.WordListLoader;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class CliRunnerTest {
    @Test
    void defaultsApplyWhenNoArgsProvided() {
        var runner = new CliRunner(new WordListLoader(), new WordleRules(),
                new ByteArrayInputStream(new byte[0]), new PrintStream(new ByteArrayOutputStream()));
        var commandLine = new CommandLine(runner);
        commandLine.parseArgs();
        assertThat(runner.wordlistSource()).isNull();
        assertThat(runner.maxAttempts()).isEqualTo(6);
    }

    @Test
    void emptyInputLineIsRejectedAndGameContinues() {
        var input = "\nDELTA\n";
        var outputBuffer = new ByteArrayOutputStream();
        var filePath = writeTempWordList();
        var runner = new CliRunner(new WordListLoader(), new WordleRules(),
                new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)), new PrintStream(outputBuffer));
        var commandLine = new CommandLine(runner);
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
        var runner = new CliRunner(new WordListLoader(), new WordleRules(),
                new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)), new PrintStream(outputBuffer));
        var commandLine = new CommandLine(runner);
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
        var runner = new CliRunner(new WordListLoader(), new WordleRules(),
                new ByteArrayInputStream("DELTA\n".getBytes(StandardCharsets.UTF_8)), new PrintStream(outputBuffer));
        var commandLine = new CommandLine(runner);
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
        var runner = new CliRunner(new WordListLoader(), new WordleRules(),
                new ByteArrayInputStream(new byte[0]), new PrintStream(outputBuffer));
        var commandLine = new CommandLine(runner);
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
