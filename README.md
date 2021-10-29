# SimpleSearch
Simple Search Coding Challenge

**Build The Code**

To create .class files:

`javac Main.java`

To create a jar:

`jar cfm SimpleSearch.jar MANIFEST.MF Main.class FilesDictionary.class Searcher.class Util.class`

**Run The Code**

To run the code:

`java -jar SimpleSearch.jar %DIRECTORY_PATH%`

DIRECTORY_PATH: e.g.: /Users/Documents/Searcher


**Assumptions**
* Searches are not case sensitive.
* Punctuations are considered as unimportant, so all punctuation marks removed.
* Multiple white spaces are considered as unimportant.
* Only .txt files are read
* All file names in given directory must be unique 
  -in any case all OS agree with it- because of storing the content of files in a HashMap.




