package wordle.words;

import org.junit.jupiter.api.Test;
import wordle.domain.Word;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

class WordListLoaderTest {
    @Test
    void randomWordReturnsNonNull() {
        var uut = new WordListLoader();
        Word word = uut.randomWord("wordlist-test.txt");
        assertThat(word).isNotNull();
    }

    @Test
    void randomWordIsUppercase() {
        var uut = new WordListLoader();
        var word = uut.randomWord("wordlist-test.txt");
        assertThat(word.value()).isEqualTo(word.value().toUpperCase());
    }

    @Test
    void randomWordReturnsDifferentValues() {
        var uut = new WordListLoader();
        var values = new HashSet<String>();
        for (int i = 0; i < 100; i++) {
            values.add(uut.randomWord("wordlist-test.txt").value());
        }
        assertThat(values.size()).isGreaterThan(1);
    }

    @Test
    void randomWordSelectsOnlyEntry() {
        var uut = new WordListLoader();
        var word = uut.randomWord("wordlist-single.txt");
        assertThat(word.value()).isEqualTo("DELTA");
    }
}
