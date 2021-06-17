import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Util {

    /**
     * Deletes all white spaces that occur more than once,
     * converts text to lower case and removes all the punctuation
     */
    private static final Function<String,String> normalizeTextFunction = line ->
            line.trim()
                    .toLowerCase()
                    .replaceAll("\\p{Punct}", "")
                    .replaceAll("( )+", " ");

    /**
     * Extracts words from a sentence
     * Because the text normalized first, the delimeter is white space
     *
     * @param   sentence One line of a file
     * @return  List of words that extracted
     */
    protected static List<String> createWords(String sentence) {
        return Arrays.asList(normalizeTextFunction.apply(sentence).split(" "));
    }

    /**
     * Calculates the percentage
     *
     * @param obtained number of sequence words that can be found on a file
     * @param total    number of words of searched sentence
     * @return         matching percentage
     */
    protected static short calculatePercentage(short obtained, short total) {
        return (short) (obtained * 100.0f / total);
    }

    /**
     * @param text The text that will be normalized
     * @return     The normalized text according to normalizeTextFunction
     */
    protected static String normalizeText(String text) {
        return normalizeTextFunction.apply(text);
    }
}
