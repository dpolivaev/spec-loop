package wordle.cli;

import org.junit.jupiter.api.Test;
import wordle.domain.WordleRules;
import wordle.engine.GameEngine;
import wordle.engine.GameState;
import wordle.engine.GameStatus;
import wordle.input.GuessInputHandler;
import wordle.words.WordListLoader;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

class CliGameRunnerTest {
    @Test
    void runsLoopAndEmitsCallbacks() {
        var input = new ByteArrayInputStream("DELTA\n".getBytes(StandardCharsets.UTF_8));
        var filePath = writeTempWordList();
        var engine = new GameEngine(new WordListLoader(), new WordleRules(), 1);
        var initialState = engine.startGameExternal(filePath.toString());
        var prompts = new AtomicInteger();
        var feedbacks = new AtomicInteger();
        var statuses = new AtomicInteger();
        var invalids = new AtomicInteger();
        var uut = new CliGameRunner(input, new GuessInputHandler());

        GameState result = uut.run(engine, initialState, new CliGameRunner.GameLoopListener() {
            @Override
            public void onPrompt(GameState state) {
                prompts.incrementAndGet();
            }

            @Override
            public void onInvalidInput(String message) {
                invalids.incrementAndGet();
            }

            @Override
            public void onFeedback(GameState state) {
                feedbacks.incrementAndGet();
            }

            @Override
            public void onStatus(GameState state) {
                statuses.incrementAndGet();
            }
        });

        assertThat(result.status()).isEqualTo(GameStatus.WON);
        assertThat(prompts.get()).isEqualTo(1);
        assertThat(feedbacks.get()).isEqualTo(1);
        assertThat(statuses.get()).isEqualTo(1);
        assertThat(invalids.get()).isZero();
    }

    private Path writeTempWordList() {
        try {
            var path = Files.createTempFile("wordlist", ".txt");
            var content = "1 words\nDELTA\n";
            Files.writeString(path, content, StandardCharsets.UTF_8);
            path.toFile().deleteOnExit();
            return path;
        } catch (Exception exception) {
            throw new IllegalStateException("Failed to create temp word list", exception);
        }
    }
}
