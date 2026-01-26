package wordle.ui;

import wordle.cli.CliGameRunner;
import wordle.cli.FeedbackRenderer;
import wordle.engine.GameEngine;
import wordle.engine.GameState;
import wordle.engine.GameStatus;
import wordle.input.GuessInputHandler;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.charset.StandardCharsets;

public class GameUiController {
    private final GameEngine engine;
    private final String wordlistSource;
    private final GuessInputHandler inputHandler;
    private final FeedbackRenderer renderer;
    private final JPanel historyPanel;
    private final JLabel statusLabel;
    private final JLabel messageLabel;
    private final JTextField inputField;
    private final JButton submitButton;
    private final PipedOutputStream inputWriter;

    private GameState state;

    public GameUiController(GameEngine engine, String wordlistSource, GuessInputHandler inputHandler,
                            FeedbackRenderer renderer, JPanel historyPanel, JLabel statusLabel, JLabel messageLabel,
                            JTextField inputField, JButton submitButton) {
        this.engine = engine;
        this.wordlistSource = wordlistSource;
        this.inputHandler = inputHandler;
        this.renderer = renderer;
        this.historyPanel = historyPanel;
        this.statusLabel = statusLabel;
        this.messageLabel = messageLabel;
        this.inputField = inputField;
        this.submitButton = submitButton;
        try {
            var inputStream = new PipedInputStream();
            this.inputWriter = new PipedOutputStream(inputStream);
            var runner = new CliGameRunner(inputStream, inputHandler);
            this.state = startGame();
            Thread thread = new Thread(() -> runLoop(runner));
            thread.setDaemon(true);
            thread.start();
        } catch (Exception exception) {
            throw new IllegalStateException("Failed to initialize UI input stream", exception);
        }
    }

    public void start() {
        updateStatus("Attempts remaining: " + state.attemptsRemaining());
    }

    public void submitInput(String rawInput) {
        if (!inputField.isEnabled()) {
            return;
        }
        inputField.setText("");
        try {
            inputWriter.write((rawInput + "\n").getBytes(StandardCharsets.UTF_8));
            inputWriter.flush();
        } catch (Exception exception) {
            updateMessage("Failed to submit input.");
        }
    }

    private void runLoop(CliGameRunner runner) {
        state = runner.run(engine, state, new UiListener());
        SwingUtilities.invokeLater(() -> {
            if (state.status() == GameStatus.WON) {
                updateStatus("Result: WON");
            } else if (state.status() == GameStatus.LOST) {
                updateStatus("Result: LOST");
            } else {
                updateStatus("Result: INTERRUPTED");
            }
            inputField.setEnabled(false);
            submitButton.setEnabled(false);
        });
    }

    private GameState startGame() {
        if (wordlistSource == null || wordlistSource.isBlank()) {
            return engine.startGame("wordlist.txt");
        }
        return engine.startGameExternal(wordlistSource);
    }

    private void updateMessage(String message) {
        SwingUtilities.invokeLater(() -> messageLabel.setText(message));
    }

    private void updateStatus(String status) {
        SwingUtilities.invokeLater(() -> statusLabel.setText(status));
    }

    private void addFeedbackRow(String feedback) {
        SwingUtilities.invokeLater(() -> {
            if (!feedback.isBlank()) {
                historyPanel.add(new JLabel(feedback));
                historyPanel.revalidate();
                historyPanel.repaint();
            }
        });
    }

    private class UiListener implements CliGameRunner.GameLoopListener {
        @Override
        public void onPrompt(GameState state) {
            updateStatus("Attempts remaining: " + state.attemptsRemaining());
        }

        @Override
        public void onInvalidInput(String message) {
            updateMessage(message);
        }

        @Override
        public void onFeedback(GameState state) {
            addFeedbackRow(renderer.render(state));
        }

        @Override
        public void onStatus(GameState state) {
            if (state.status() != GameStatus.IN_PROGRESS) {
                updateStatus("Status: " + state.status());
            }
        }
    }
}
