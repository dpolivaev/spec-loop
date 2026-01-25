package wordle.domain;

import java.util.List;

public record Word(String value) {
    public Word {
        if (value == null) {
            throw new IllegalArgumentException("Word value must not be null");
        }
        var normalized = value.trim().toUpperCase();
        if (normalized.length() != 5) {
            throw new IllegalArgumentException("Word must be exactly 5 letters");
        }
        for (int i = 0; i < normalized.length(); i++) {
            char ch = normalized.charAt(i);
            if (ch < 'A' || ch > 'Z') {
                throw new IllegalArgumentException("Word must contain only A-Z letters");
            }
        }
        value = normalized;
    }

    public List<Character> letters() {
        return value.chars()
                .mapToObj(ch -> (char) ch)
                .toList();
    }
}
