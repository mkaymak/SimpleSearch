import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class UtilTest {

    @Test
    void createWords() {
        String text = "TO BE OR   ,,, Not To be....";
        List<String> words = Arrays.asList("to","be","or","not","to","be");
        assertEquals(words, Util.createWords(text));
    }

    @Test
    void findPercentage() {
        short obtained = 4;
        short total = 5;
        assertEquals(80, Util.calculatePercentage(obtained, total));
    }

    @Test
    void normalizeText() {
        String text = "TO BE OR   ,,, Not To be....";
        String normalizedText = "to be or not to be";
        assertEquals(normalizedText, Util.normalizeText(text));
    }
}