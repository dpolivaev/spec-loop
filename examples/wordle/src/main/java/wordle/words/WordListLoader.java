package wordle.words;

import wordle.domain.Word;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ThreadLocalRandom;

public class WordListLoader {
    public Word randomWord(String resourcePath) {
        var loader = Thread.currentThread().getContextClassLoader();
        var stream = loader.getResourceAsStream(resourcePath);

        try (var reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {
            var header = reader.readLine();
            if (header == null) {
                throw new IllegalStateException("Word list is empty: " + resourcePath);
            }
            int count = parseCount(header);
            int target = ThreadLocalRandom.current().nextInt(count);
            for (int index = 0; index < count; index++) {
                var line = reader.readLine();
                if (line == null) {
                    throw new IllegalStateException("Word list shorter than expected: " + resourcePath);
                }
                var trimmed = line.trim();
                if (trimmed.isEmpty()) {
                    throw new IllegalStateException("Word list contains empty line: " + resourcePath);
                }
                if (index == target) {
                    return new Word(trimmed);
                }
            }
            throw new IllegalStateException("Word list selection failed: " + resourcePath);
        } catch (Exception exception) {
            if (exception instanceof RuntimeException runtimeException) {
                throw runtimeException;
            }
            throw new IllegalStateException("Failed to read word list: " + resourcePath, exception);
        }
    }

    private int parseCount(String header) {
        var trimmed = header.trim();
        var parts = trimmed.split("\\s+");
        if (parts.length == 0) {
            throw new IllegalStateException("Invalid word list header: " + header);
        }
        try {
            return Integer.parseInt(parts[0]);
        } catch (NumberFormatException exception) {
            throw new IllegalStateException("Invalid word list header: " + header, exception);
        }
    }
}
