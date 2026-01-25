package wordle.domain;

import java.util.List;

public record Feedback(List<LetterFeedback> entries) {
}
