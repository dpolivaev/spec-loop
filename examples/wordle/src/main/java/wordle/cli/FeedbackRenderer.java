package wordle.cli;

import wordle.domain.LetterFeedback;
import wordle.domain.LetterStatus;
import wordle.engine.GameState;

import java.util.List;

public class FeedbackRenderer {
    public String render(GameState state) {
        var history = state.history();
        if (history.isEmpty()) {
            return "";
        }
        var entries = history.get(history.size() - 1).entries();
        return formatEntries(entries);
    }

    private String formatEntries(List<LetterFeedback> entries) {
        var builder = new StringBuilder();
        for (int index = 0; index < entries.size(); index++) {
            if (index > 0) {
                builder.append(' ');
            }
            var entry = entries.get(index);
            builder.append(entry.letter());
            builder.append(markerFor(entry.status()));
        }
        return builder.toString();
    }

    private char markerFor(LetterStatus status) {
        if (status == LetterStatus.CORRECT) {
            return '=';
        }
        if (status == LetterStatus.PRESENT) {
            return '~';
        }
        return '.';
    }
}
