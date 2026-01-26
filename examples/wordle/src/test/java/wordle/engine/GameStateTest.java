package wordle.engine;

import org.junit.jupiter.api.Test;
import wordle.domain.Feedback;
import wordle.domain.Word;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GameStateTest {
    @Test
    void gameStateStoresProvidedValues() {
        var solution = new Word("CRANE");
        var history = List.<Feedback>of();
        var uut = new GameState(solution, 6, history, GameStatus.IN_PROGRESS);
        assertThat(uut.solution()).isEqualTo(solution);
        assertThat(uut.attemptsRemaining()).isEqualTo(6);
        assertThat(uut.history()).isEqualTo(history);
        assertThat(uut.status()).isEqualTo(GameStatus.IN_PROGRESS);
    }
}
