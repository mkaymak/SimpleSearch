import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SearcherTest {
    @Test
    void searchForSentence() {
        HashMap<String, HashMap<String, LinkedList<Integer>>> contentOfFiles = mockContentOfFileHashmap();

        Searcher.setWordsOfSearchedSentence(Arrays.asList("to", "be", "or"));

        assertEquals(100, Searcher.getPercentage(contentOfFiles.get("file1.txt")));
        assertEquals(33, Searcher.getPercentage(contentOfFiles.get("file2.txt")));
        assertEquals(66, Searcher.getPercentage(contentOfFiles.get("file3.txt")));
        assertEquals(0, Searcher.getPercentage(contentOfFiles.get("file4.txt")));
    }

    private HashMap<String, HashMap<String, LinkedList<Integer>>> mockContentOfFileHashmap() {
        HashMap<String, HashMap<String, LinkedList<Integer>>> contentOfFiles = new HashMap<>();

        contentOfFiles.put("file1.txt", new HashMap<String, LinkedList<Integer>>() {{
                    put("hi", new LinkedList<Integer>(Arrays.asList(1,35)));
                    put("to", new LinkedList<Integer>(Arrays.asList(5,7,16,20)));
                    put("be", new LinkedList<Integer>(Arrays.asList(9,17,21)));
                    put("or", new LinkedList<Integer>(Arrays.asList(15,18)));
                    put("not", new LinkedList<Integer>(Arrays.asList(6,19)));
                    put("adevinta", new LinkedList<Integer>(Arrays.asList(32,74)));
                }}
        );

        contentOfFiles.put("file2.txt", new HashMap<String, LinkedList<Integer>>() {{
                    put("love", new LinkedList<Integer>(Arrays.asList(1,76)));
                    put("spain", new LinkedList<Integer>(Arrays.asList(5,7,16,20)));
                    put("to", new LinkedList<Integer>(Arrays.asList(9,17,21)));
                    put("work", new LinkedList<Integer>(Arrays.asList(93,105)));
                    put("hard", new LinkedList<Integer>(Arrays.asList(8,42)));
                }}
        );

        contentOfFiles.put("file3.txt", new HashMap<String, LinkedList<Integer>>() {{
                    put("java", new LinkedList<Integer>(Arrays.asList(1,76)));
                    put("best", new LinkedList<Integer>(Arrays.asList(5,7,16,20)));
                    put("to", new LinkedList<Integer>(Arrays.asList(5,7,16,20)));
                    put("be", new LinkedList<Integer>(Arrays.asList(9,17,21)));
                    put("barcelona", new LinkedList<Integer>(Arrays.asList(8,42)));
                }}
        );

        contentOfFiles.put("file4.txt", new HashMap<String, LinkedList<Integer>>() {{
                    put("hi", new LinkedList<Integer>(Arrays.asList(1,35)));
                    put("or", new LinkedList<Integer>(Arrays.asList(15,18)));
                    put("not", new LinkedList<Integer>(Arrays.asList(6,19)));
                    put("adevinta", new LinkedList<Integer>(Arrays.asList(32,74)));
                }}
        );
        return contentOfFiles;
    }
}