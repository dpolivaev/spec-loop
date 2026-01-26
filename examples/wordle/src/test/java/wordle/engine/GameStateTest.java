package wordle.engine;

import org.junit.jupiter.api.Test;
import wordle.domain.Feedback;
import wordle.domain.Word;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class GameStateTest {
    @Test
    void gameStateStoresProvidedValues() {
        var solution = new Word("CRANE");
        var history = List.<Feedback>of();
        var uut = new GameState(solution, 6, history, GameStatus.IN_PROGRESS);
        assertTrue(uut.solution().equals(solution));
        assertTrue(uut.attemptsRemaining() == 6);
        assertTrue(uut.history().equals(history));
        assertTrue(uut.status() == GameStatus.IN_PROGRESS);
    }
}
