package wordle.engine;

import org.junit.jupiter.api.Test;
import wordle.domain.Word;
import wordle.domain.WordleRules;
import wordle.words.WordListLoader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class GameEngineTest {
    @Test
    void startGameInitializesState() {
        var uut = new GameEngine(new WordListLoader(), new WordleRules(), 6);
        var state = uut.startGame("wordlist-single.txt");
        assertTrue(state.attemptsRemaining() == 6);
        assertTrue(state.history().isEmpty());
        assertTrue(state.status() == GameStatus.IN_PROGRESS);
    }

    @Test
    void correctGuessWinsWithoutDecrement() {
        var uut = new GameEngine(new WordListLoader(), new WordleRules(), 6);
        var state = uut.startGame("wordlist-single.txt");
        var updated = uut.submitGuess(state, "DELTA");
        assertTrue(updated.status() == GameStatus.WON);
        assertTrue(updated.attemptsRemaining() == 6);
        assertTrue(updated.history().size() == 1);
    }

    @Test
    void incorrectGuessDecrementsAttempts() {
        var uut = new GameEngine(new WordListLoader(), new WordleRules(), 2);
        var state = uut.startGame("wordlist-single.txt");
        var updated = uut.submitGuess(state, "CRANE");
        assertTrue(updated.status() == GameStatus.IN_PROGRESS);
        assertTrue(updated.attemptsRemaining() == 1);
        assertTrue(updated.history().size() == 1);
    }

    @Test
    void incorrectGuessEndsGameAtZeroAttempts() {
        var uut = new GameEngine(new WordListLoader(), new WordleRules(), 1);
        var state = uut.startGame("wordlist-single.txt");
        var updated = uut.submitGuess(state, "CRANE");
        assertTrue(updated.status() == GameStatus.LOST);
        assertTrue(updated.attemptsRemaining() == 0);
    }

    @Test
    void submitGuessAfterWonReturnsSameState() {
        var uut = new GameEngine(new WordListLoader(), new WordleRules(), 6);
        var state = uut.startGame("wordlist-single.txt");
        var won = uut.submitGuess(state, "DELTA");
        var updated = uut.submitGuess(won, "CRANE");
        assertTrue(updated == won);
    }

    @Test
    void submitGuessAfterLostReturnsSameState() {
        var uut = new GameEngine(new WordListLoader(), new WordleRules(), 1);
        var state = uut.startGame("wordlist-single.txt");
        var lost = uut.submitGuess(state, "CRANE");
        var updated = uut.submitGuess(lost, "DELTA");
        assertTrue(updated == lost);
    }

    @Test
    void submitGuessUsesProvidedSolution() {
        var uut = new GameEngine(new WordListLoader(), new WordleRules(), 6);
        var state = new GameState(new Word("CRANE"), 6, List.of(), GameStatus.IN_PROGRESS);
        var updated = uut.submitGuess(state, "CRANE");
        assertTrue(updated.status() == GameStatus.WON);
    }
}
