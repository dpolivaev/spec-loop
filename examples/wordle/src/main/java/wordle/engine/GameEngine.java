package wordle.engine;

import wordle.domain.Word;
import wordle.domain.LetterStatus;
import wordle.domain.WordleRules;
import wordle.words.WordListLoader;

import java.util.ArrayList;
import java.util.List;

public class GameEngine {
    private final WordListLoader wordListLoader;
    private final WordleRules rules;
    private final int maxAttempts;

    public GameEngine(WordListLoader wordListLoader, WordleRules rules, int maxAttempts) {
        this.wordListLoader = wordListLoader;
        this.rules = rules;
        this.maxAttempts = maxAttempts;
    }

    public GameState startGame(String resourcePath) {
        var solution = wordListLoader.randomWord(resourcePath);
        return new GameState(solution, maxAttempts, List.of(), GameStatus.IN_PROGRESS);
    }

    public GameState submitGuess(GameState state, String guessRaw) {
        if (state.status() != GameStatus.IN_PROGRESS) {
            return state;
        }

        var guess = new Word(guessRaw);
        var feedback = rules.compare(state.solution(), guess);
        var updatedHistory = new ArrayList<>(state.history());
        updatedHistory.add(feedback);

        if (feedback.entries().stream().allMatch(entry -> entry.status() == LetterStatus.CORRECT)) {
            return new GameState(state.solution(), state.attemptsRemaining(), updatedHistory, GameStatus.WON);
        }

        int remaining = state.attemptsRemaining() - 1;
        if (remaining <= 0) {
            return new GameState(state.solution(), 0, updatedHistory, GameStatus.LOST);
        }

        return new GameState(state.solution(), remaining, updatedHistory, GameStatus.IN_PROGRESS);
    }
}
