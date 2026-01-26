package wordle.engine;

import wordle.domain.Feedback;
import wordle.domain.Word;

import java.util.List;

public record GameState(
        Word solution,
        int attemptsRemaining,
        List<Feedback> history,
        GameStatus status
) {
}
