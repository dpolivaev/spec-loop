package wordle.words;

import org.junit.jupiter.api.Test;
import wordle.domain.Word;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WordListLoaderTest {
    @Test
    void randomWordReturnsNonNull() {
        var uut = new WordListLoader();
        Word word = uut.randomWord("wordlist-test.txt");
        assertNotNull(word);
    }

    @Test
    void randomWordIsUppercase() {
        var uut = new WordListLoader();
        var word = uut.randomWord("wordlist-test.txt");
        assertTrue(word.value().equals(word.value().toUpperCase()));
    }

    @Test
    void randomWordReturnsDifferentValues() {
        var uut = new WordListLoader();
        var values = new HashSet<String>();
        for (int i = 0; i < 100; i++) {
            values.add(uut.randomWord("wordlist-test.txt").value());
        }
        assertTrue(values.size() > 1);
    }

    @Test
    void randomWordSelectsOnlyEntry() {
        var uut = new WordListLoader();
        var word = uut.randomWord("wordlist-single.txt");
        assertTrue(word.value().equals("DELTA"));
    }
}
