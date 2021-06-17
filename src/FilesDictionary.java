import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class finds files in a given directory
 * Then creates a dictionary from the words that extracted from each file
 */
public class FilesDictionary {
    private final HashMap<String, HashMap<String, LinkedList<Integer>>> filesDictionary = new HashMap<>();

    /**
     * Creates in memory representation of the content of the files
     *
     * @param directoryName The directory name provided by the user to search files in it
     * @return              In memory representation of the content of the files based on words
     */
    protected HashMap<String, HashMap<String, LinkedList<Integer>>> createDictionaryOfFiles(String directoryName) {
        try (Stream<Path> files = Files.walk(Paths.get(directoryName))) {
            List<Path> paths = findFilesInDirectory(files, directoryName);
            setFilesDictionary(paths);
        } catch (IOException exception) {
            System.out.println("Error reading files from " + directoryName);
        }
        return filesDictionary;
    }

    /**
     * Search for .txt files in a given directory
     *
     * @param files         Path of files
     * @param directoryName The directory name provided by the user to search files in it
     * @return              List of files path
     */
    private List<Path> findFilesInDirectory(Stream<Path> files, String directoryName) {
        List<Path> paths = files
                .filter(file -> file.getFileName().toString().endsWith(".txt"))
                .collect(Collectors.toList());
        System.out.println(paths.size() + " files read in directory " + directoryName);
        return paths;
    }

    /**
     * Creates in-memory representation of the content of the files and set it to the contentOfFiles field
     * Key is fileName and value is map of words and their positions
     *
     * @param paths of the files
     */
    private void setFilesDictionary(List<Path> paths){
        for(Path path : paths) {
            HashMap<String, LinkedList<Integer>> wordPositionsInFileMap = createPositionsOfWordsMap(path);
            if(wordPositionsInFileMap != null) {
                filesDictionary.put(path.getFileName().toString(), wordPositionsInFileMap);
            }
        }
    }

    /**
     * Creates a Map of words positions for a given path of a file
     *
     * @param path Path of the file
     * @return     Positions of words Map
     */
    private HashMap<String, LinkedList<Integer>> createPositionsOfWordsMap(Path path) {
        try (Stream<String> lines = Files.lines(path)) {
            return findPositionsOfWords(lines);
        }
        catch (IOException ex) {
            System.out.println("IOException while creating dictionary " + ex);
            return null;
        }
    }

    /**
     * Creates a positions of words Map
     *
     * If a word cannot found in the Map, put a new element to Map with key of word and value with a singletonList
     * that holds the first occurrence of that word in the file
     *
     * If found, get the list of positions for that word and add the occurrence position to the list
     *
     * @param lines Stream of lines of a file
     * @return      Map of words positions; Key: Word that found in a file, Value: List of positions of that word
     */
    private HashMap<String, LinkedList<Integer>> findPositionsOfWords(Stream<String> lines) {
        // Position of the first word in the file is 1, then increasing position by one for each word.
        int position = 1;
        LinkedList<String> words = createWordsFromSentences(lines);
        String fistWord = words.getFirst();
        // In order to not check whether wordPositionsInFileMap is null in the below for loop
        HashMap<String, LinkedList<Integer>> wordPositionsInFileMap = createPositionMapWithFirstWord(fistWord, position);
        // Because of the first word stored in the map in the above line
        words.removeFirst();
        for(String word : words) {
            position++;
            if (wordPositionsInFileMap.get(word) == null) {
                wordPositionsInFileMap.put(word, new LinkedList<>(Collections.singletonList(position)));
            }
            else {
                wordPositionsInFileMap.get(word).add(position);
            }
        }
        return wordPositionsInFileMap;
    }

    /**
     * Extract words from lines of a file
     *
     * @param lines Stream of lines of a file
     * @return      Created list of words
     */
    private LinkedList<String> createWordsFromSentences(Stream<String> lines) {
        LinkedList<String> wordsOfFile = new LinkedList<>();
        LinkedList<String> sentences = getSentencesFromFile(lines);
        sentences.forEach(sentence ->
                    wordsOfFile.addAll(Util.createWords(sentence))
        );
        return wordsOfFile;
    }

    /**
     * Creates sentences as a list of String from a file
     *
     * @param sentences Lines that read from a file
     * @return
     */
    private LinkedList<String> getSentencesFromFile(Stream<String> sentences) {
        return sentences.collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Creates a map that stores positions of words in a file with first word and its first occurrence position
     * So that in the findPositionsOfWords method, the for loop does not have to check whether the position map is null
     *
     * @param firstWord the first key of words positions map
     * @param position  the first occurrence of first word in a file
     * @return          map of positions
     */
    private HashMap<String, LinkedList<Integer>> createPositionMapWithFirstWord(String firstWord, int position) {
        HashMap<String, LinkedList<Integer>> wordPositionInFileMap = new HashMap<>();
        wordPositionInFileMap.put(firstWord, new LinkedList<>(Collections.singletonList(position)));
        return wordPositionInFileMap;
    }
}
