package wordle.ui;

import org.junit.jupiter.api.Test;
import wordle.cli.FeedbackRenderer;
import wordle.domain.WordleRules;
import wordle.engine.GameEngine;
import wordle.input.GuessInputHandler;
import wordle.words.WordListLoader;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.BooleanSupplier;

import static org.assertj.core.api.Assertions.assertThat;

class GameUiControllerTest {
    @Test
    void emptyInputIsRejectedWithoutConsumingAttempt() {
        var fixture = createFixture();
        fixture.controller.start();

        fixture.controller.submitInput("   ");

        waitUntil(() -> fixture.messageLabel.getText().contains("Empty guess"));
        waitUntil(() -> fixture.statusLabel.getText().contains("Attempts remaining: 1"));
        assertThat(fixture.messageLabel.getText()).contains("Empty guess");
        assertThat(fixture.statusLabel.getText()).contains("Attempts remaining: 1");
    }

    @Test
    void invalidLengthIsRejectedWithoutConsumingAttempt() {
        var fixture = createFixture();
        fixture.controller.start();

        fixture.controller.submitInput("BAD");

        waitUntil(() -> fixture.messageLabel.getText().contains("Invalid guess"));
        waitUntil(() -> fixture.statusLabel.getText().contains("Attempts remaining: 1"));
        assertThat(fixture.messageLabel.getText()).contains("Invalid guess");
        assertThat(fixture.statusLabel.getText()).contains("Attempts remaining: 1");
    }

    @Test
    void validGuessAppendsFeedbackAndDisablesInputOnWin() {
        var fixture = createFixture();
        fixture.controller.start();

        fixture.controller.submitInput("DELTA");

        waitUntil(() -> fixture.historyPanel.getComponentCount() == 1);
        waitUntil(() -> fixture.statusLabel.getText().startsWith("Result:"));
        assertThat(fixture.historyPanel.getComponentCount()).isEqualTo(1);
        assertThat(fixture.statusLabel.getText()).contains("WON");
        assertThat(fixture.inputField.isEnabled()).isFalse();
        assertThat(fixture.submitButton.isEnabled()).isFalse();
    }

    private static Fixture createFixture() {
        var rules = new WordleRules();
        var engine = new GameEngine(new WordListLoader(), rules, 1);
        var historyPanel = new JPanel();
        var statusLabel = new JLabel();
        var messageLabel = new JLabel();
        var inputField = new JTextField();
        var submitButton = new JButton();
        var wordListPath = writeTempWordList();
        var controller = new GameUiController(engine, wordListPath.toString(), new GuessInputHandler(),
                new FeedbackRenderer(), historyPanel, statusLabel, messageLabel, inputField, submitButton);
        return new Fixture(controller, historyPanel, statusLabel, messageLabel, inputField, submitButton);
    }

    private static void waitUntil(BooleanSupplier condition) {
        var deadline = System.currentTimeMillis() + 1000;
        while (System.currentTimeMillis() < deadline) {
            if (condition.getAsBoolean()) {
                return;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException interruptedException) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    private static Path writeTempWordList() {
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

    private record Fixture(
            GameUiController controller,
            JPanel historyPanel,
            JLabel statusLabel,
            JLabel messageLabel,
            JTextField inputField,
            JButton submitButton
    ) {
    }
}
