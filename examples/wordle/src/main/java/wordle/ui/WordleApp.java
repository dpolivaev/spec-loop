package wordle.ui;

import wordle.domain.WordleRules;
import wordle.engine.GameEngine;
import wordle.input.GuessInputHandler;
import wordle.words.WordListLoader;
import wordle.cli.FeedbackRenderer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;

public class WordleApp {
    private final WordListLoader wordListLoader;
    private final WordleRules rules;
    private final int maxAttempts;
    private final String wordlistSource;

    public WordleApp(WordListLoader wordListLoader, WordleRules rules, int maxAttempts, String wordlistSource) {
        this.wordListLoader = wordListLoader;
        this.rules = rules;
        this.maxAttempts = maxAttempts;
        this.wordlistSource = wordlistSource;
    }

    public void start() {
        SwingUtilities.invokeLater(this::buildUi);
    }

    private void buildUi() {
        var frame = new JFrame("Wordle");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(8, 8));

        var statusLabel = new JLabel("Wordle started.");
        var messageLabel = new JLabel(" ");
        var statusPanel = new JPanel(new BorderLayout());
        statusPanel.add(statusLabel, BorderLayout.NORTH);
        statusPanel.add(messageLabel, BorderLayout.SOUTH);

        var historyPanel = new JPanel();
        historyPanel.setLayout(new BoxLayout(historyPanel, BoxLayout.Y_AXIS));
        var historyScroll = new JScrollPane(historyPanel);

        var inputField = new JTextField();
        var submitButton = new JButton("Submit");
        var inputPanel = new JPanel(new BorderLayout(8, 8));
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(submitButton, BorderLayout.EAST);

        frame.add(statusPanel, BorderLayout.NORTH);
        frame.add(historyScroll, BorderLayout.CENTER);
        frame.add(inputPanel, BorderLayout.SOUTH);

        var engine = new GameEngine(wordListLoader, rules, maxAttempts);
        var controller = new GameUiController(engine, wordlistSource, new GuessInputHandler(),
                new FeedbackRenderer(), historyPanel, statusLabel, messageLabel, inputField, submitButton);
        controller.start();

        submitButton.addActionListener(event -> controller.submitInput(inputField.getText()));
        inputField.addActionListener(event -> controller.submitInput(inputField.getText()));

        frame.setSize(420, 320);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
