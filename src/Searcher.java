import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class searched for given user input in dictionary of the files
 * and calculate the percentage of the match for each file
 * Because the sentences consist of words, user input is divided into words as file contents
 * And existence of the sequence positions of the words indicates that there is a match.
 *
 */
public class Searcher {
    private static List<String> wordsOfSearchedSentence = null;

    /**
     * Convert user input to array of words
     * and calculate the matching percentage to print
     *
     * @param searchedUserInput User input to search in the files
     * @param contentOfFiles    File Dictionary
     */
    protected static void searchForSentence(String searchedUserInput,
                                            HashMap<String, HashMap<String, LinkedList<Integer>>> contentOfFiles) {
        wordsOfSearchedSentence = Util.createWords(searchedUserInput);
        short percentage;
        for(String fileName : contentOfFiles.keySet()) {
            HashMap<String, LinkedList<Integer>> fileContent = contentOfFiles.get(fileName);
            percentage = getPercentage(fileContent);
            System.out.println(fileName + ":%" + percentage);
        }
    }

    /**
     * Calculates the matching percentage
     * Number of ordered sequence from the beginning of the sentence
     * / total number of words in user input
     * gives the matching percentage
     *
     * @param fileContent Map of the words of file
     * @return            Matching percentage
     */
    protected static short getPercentage(HashMap<String, LinkedList<Integer>> fileContent) {
        List<Integer> temp = fileContent.get(wordsOfSearchedSentence.get(0));

        if (temp == null) {
            return 0;
        }

        short lengthOfFile = (short) wordsOfSearchedSentence.size();

        List<Integer> current;
        for(short i=1; i< lengthOfFile; i++) {
            current = fileContent.get(wordsOfSearchedSentence.get(i));
            if (current == null) {
                return Util.calculatePercentage(i, lengthOfFile);
            }
            temp = searchForSequence(temp, current);
            if (temp == null || temp.size() == 0) {
                return Util.calculatePercentage(i, lengthOfFile);
            }
        }
        return 100;
    }


    /**
     * Finds whether there is a sequence between two lists
     * If there is a sequence, some elements of List of positions2 is one more of some of the elements of position1
     *
     * @param positions1 List of prior words positions
     * @param positions2 List of latter words positions
     * @return           List of positions2 if there is a sequence between positions1
     */
    private static List<Integer> searchForSequence(List<Integer> positions1, List<Integer> positions2) {
        return positions2.stream()
                .filter(position2 -> positions1.stream().anyMatch(position1 -> position1 == position2-1))
                .collect(Collectors.toList());
    }

    public static void setWordsOfSearchedSentence(List<String> wordsOfSearchedSentence) {
        Searcher.wordsOfSearchedSentence = wordsOfSearchedSentence;
    }

}
