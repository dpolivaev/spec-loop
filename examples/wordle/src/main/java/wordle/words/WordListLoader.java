package wordle.words;

import wordle.domain.Word;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ThreadLocalRandom;

public class WordListLoader {
    public Word randomWord(String resourcePath) {
        return randomWordFromResource(resourcePath);
    }

    public Word randomWordFromResource(String resourcePath) {
        var loader = Thread.currentThread().getContextClassLoader();
        var stream = loader.getResourceAsStream(resourcePath);

        if (stream == null) {
            throw new IllegalStateException("Missing word list resource: " + resourcePath);
        }
        return randomWordFromStream(stream, "resource " + resourcePath);
    }

    public Word randomWordFromPath(Path path) {
        try (var stream = Files.newInputStream(path)) {
            return randomWordFromStream(stream, "file " + path);
        } catch (Exception exception) {
            if (exception instanceof RuntimeException runtimeException) {
                throw runtimeException;
            }
            throw new IllegalStateException("Failed to read word list file: " + path, exception);
        }
    }

    public Word randomWordFromUrl(URL url) {
        try (var stream = url.openStream()) {
            return randomWordFromStream(stream, "url " + url);
        } catch (Exception exception) {
            if (exception instanceof RuntimeException runtimeException) {
                throw runtimeException;
            }
            throw new IllegalStateException("Failed to read word list url: " + url, exception);
        }
    }

    private Word randomWordFromStream(InputStream stream, String source) {
        try (var reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {
            var header = reader.readLine();
            if (header == null) {
                throw new IllegalStateException("Word list is empty: " + source);
            }
            int count = parseCount(header);
            int target = ThreadLocalRandom.current().nextInt(count);
            for (int index = 0; index < count; index++) {
                var line = reader.readLine();
                if (line == null) {
                    throw new IllegalStateException("Word list shorter than expected: " + source);
                }
                var trimmed = line.trim();
                if (trimmed.isEmpty()) {
                    throw new IllegalStateException("Word list contains empty line: " + source);
                }
                if (index == target) {
                    return new Word(trimmed);
                }
            }
            throw new IllegalStateException("Word list selection failed: " + source);
        } catch (IOException exception) {
            throw new IllegalStateException("Failed to read word list: " + source, exception);
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
