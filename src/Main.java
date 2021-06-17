import java.util.*;

public class Main {
    private static final String TERMINATION_WORD = "quit";
    private static final String SEARCH_WORD = "search>";
    private static final FilesDictionary filesDictionary = new FilesDictionary();
    private static HashMap<String, HashMap<String, LinkedList<Integer>>> contentOfFiles;

    /**
     * Main method of the script
     *
     */
    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in)) {
            if(args == null || args.length == 0 || args[0] == null) {
                throw new IllegalArgumentException("Please enter a directory");
            }
            setFileContents(args[0]);
            String userInput = getUserInput(scanner);
            searchForUserInputInFiles(scanner, userInput);
        }
        catch (IllegalArgumentException ex) {
            System.out.println(ex);
        }
    }

    /**
     * Searches for the user inputs in files in given directory
     * Also finds the percentage of match
     *
     * @param scanner   to get new userInput until user types TERMINATION_WORD
     * @param userInput the first user input provided by Main method
     */
    private static void searchForUserInputInFiles(Scanner scanner, String userInput) {
        while (!userInput.equalsIgnoreCase(TERMINATION_WORD)) {
            Searcher.searchForSentence(userInput, contentOfFiles);
            userInput = getUserInput(scanner);
        }
    }

    /**
     * Creates in memory representation of content of files based on words
     *
     * @param directoryName for reading the files in that directory
     */
    private static void setFileContents(String directoryName) {
        contentOfFiles = filesDictionary.createDictionaryOfFiles(directoryName);
    }

    /**
     * Gets user input to search on files in dictionary
     *
     * @param  scanner is provided from Main method
     * @return user input to search
     */
    private static String getUserInput(Scanner scanner) {
        System.out.print(SEARCH_WORD);
        return scanner.nextLine();
    }
}
