package wordle.cli;

import org.junit.jupiter.api.Test;
import wordle.domain.Feedback;
import wordle.domain.LetterFeedback;
import wordle.domain.LetterStatus;
import wordle.domain.Word;
import wordle.engine.GameState;
import wordle.engine.GameStatus;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FeedbackRendererTest {
    @Test
    void renderLastFeedbackWithMarkers() {
        var entries = List.of(
                new LetterFeedback(0, 'C', LetterStatus.CORRECT),
                new LetterFeedback(1, 'R', LetterStatus.PRESENT),
                new LetterFeedback(2, 'A', LetterStatus.ABSENT),
                new LetterFeedback(3, 'N', LetterStatus.ABSENT),
                new LetterFeedback(4, 'E', LetterStatus.PRESENT)
        );
        var feedback = new Feedback(entries);
        var state = new GameState(new Word("CRANE"), 3, List.of(feedback), GameStatus.IN_PROGRESS);
        var uut = new FeedbackRenderer();

        assertThat(uut.render(state)).isEqualTo("C= R~ A. N. E~");
    }

    @Test
    void renderEmptyHistoryReturnsEmptyString() {
        var state = new GameState(new Word("CRANE"), 3, List.of(), GameStatus.IN_PROGRESS);
        var uut = new FeedbackRenderer();

        assertThat(uut.render(state)).isEmpty();
    }
}
