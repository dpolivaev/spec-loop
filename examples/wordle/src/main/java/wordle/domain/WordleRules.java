package wordle.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordleRules {
    public Feedback compare(Word solution, Word guess) {
        var solutionLetters = solution.letters();
        var guessLetters = guess.letters();
        var size = solutionLetters.size();
        var statuses = new LetterStatus[size];
        var remainingCounts = new HashMap<Character, Integer>();

        for (int i = 0; i < size; i++) {
            char solutionChar = solutionLetters.get(i);
            char guessChar = guessLetters.get(i);
            if (solutionChar == guessChar) {
                statuses[i] = LetterStatus.CORRECT;
            } else {
                remainingCounts.put(solutionChar, remainingCounts.getOrDefault(solutionChar, 0) + 1);
            }
        }

        for (int i = 0; i < size; i++) {
            if (statuses[i] != null) {
                continue;
            }
            char guessChar = guessLetters.get(i);
            int remaining = remainingCounts.getOrDefault(guessChar, 0);
            if (remaining > 0) {
                statuses[i] = LetterStatus.PRESENT;
                remainingCounts.put(guessChar, remaining - 1);
            } else {
                statuses[i] = LetterStatus.ABSENT;
            }
        }

        List<LetterFeedback> entries = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            entries.add(new LetterFeedback(i, guessLetters.get(i), statuses[i]));
        }
        return new Feedback(entries);
    }
}
