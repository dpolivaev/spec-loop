package wordle.engine;

import org.junit.jupiter.api.Test;
import wordle.domain.Word;
import wordle.domain.WordleRules;
import wordle.words.WordListLoader;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GameEngineTest {
    @Test
    void startGameInitializesState() {
        var uut = new GameEngine(new WordListLoader(), new WordleRules(), 6);
        var state = uut.startGame("wordlist-single.txt");
        assertThat(state.attemptsRemaining()).isEqualTo(6);
        assertThat(state.history()).isEmpty();
        assertThat(state.status()).isEqualTo(GameStatus.IN_PROGRESS);
    }

    @Test
    void correctGuessWinsWithoutDecrement() {
        var uut = new GameEngine(new WordListLoader(), new WordleRules(), 6);
        var state = uut.startGame("wordlist-single.txt");
        var updated = uut.submitGuess(state, "DELTA");
        assertThat(updated.status()).isEqualTo(GameStatus.WON);
        assertThat(updated.attemptsRemaining()).isEqualTo(6);
        assertThat(updated.history()).hasSize(1);
    }

    @Test
    void incorrectGuessDecrementsAttempts() {
        var uut = new GameEngine(new WordListLoader(), new WordleRules(), 2);
        var state = uut.startGame("wordlist-single.txt");
        var updated = uut.submitGuess(state, "CRANE");
        assertThat(updated.status()).isEqualTo(GameStatus.IN_PROGRESS);
        assertThat(updated.attemptsRemaining()).isEqualTo(1);
        assertThat(updated.history()).hasSize(1);
    }

    @Test
    void incorrectGuessEndsGameAtZeroAttempts() {
        var uut = new GameEngine(new WordListLoader(), new WordleRules(), 1);
        var state = uut.startGame("wordlist-single.txt");
        var updated = uut.submitGuess(state, "CRANE");
        assertThat(updated.status()).isEqualTo(GameStatus.LOST);
        assertThat(updated.attemptsRemaining()).isZero();
    }

    @Test
    void submitGuessAfterWonReturnsSameState() {
        var uut = new GameEngine(new WordListLoader(), new WordleRules(), 6);
        var state = uut.startGame("wordlist-single.txt");
        var won = uut.submitGuess(state, "DELTA");
        var updated = uut.submitGuess(won, "CRANE");
        assertThat(updated).isSameAs(won);
    }

    @Test
    void submitGuessAfterLostReturnsSameState() {
        var uut = new GameEngine(new WordListLoader(), new WordleRules(), 1);
        var state = uut.startGame("wordlist-single.txt");
        var lost = uut.submitGuess(state, "CRANE");
        var updated = uut.submitGuess(lost, "DELTA");
        assertThat(updated).isSameAs(lost);
    }

    @Test
    void submitGuessUsesProvidedSolution() {
        var uut = new GameEngine(new WordListLoader(), new WordleRules(), 6);
        var state = new GameState(new Word("CRANE"), 6, List.of(), GameStatus.IN_PROGRESS);
        var updated = uut.submitGuess(state, "CRANE");
        assertThat(updated.status()).isEqualTo(GameStatus.WON);
    }
}
