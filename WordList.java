import java.io.FileReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.util.*;

/**
 * This class stored and uses the text from the text files entered into the checker.
 * It stores 3 lists, one a list of the original text file parsed at each space,
 * another stores the previous list with all punctation removed and set to all lower case,
 * and the final list contains each unique word in the text and how many times it appears.
 * The functions to read from the text file and transform it to these list is also contained here.
 */
public class WordList {
    private List<String> unalteredList = new ArrayList<String>();
    private List<ParaWord> alteredList = new ArrayList<ParaWord>();
    private List<IndivWordFreq> frequencyList = new ArrayList<IndivWordFreq>();

    /**
     * Creates an instance of the WordList class, taking in a filename, reading
     * the file and parsing the raw text into a list
     * @param filePath String containing to the file this instance will store the information of
     */
    public WordList(String filePath) {
        String line;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            while ((line = reader.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, " ");
                while (st.hasMoreTokens()) {
                    unalteredList.add(st.nextToken());
                }
            }
            reader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
    }

    /**
     * Creates a list based on the raw parsed text from the document to remove all punctuation (aside from mid word hyphens)
     * and turning it to lower case, including to choice to either include words within quotes or not
     * @param includeQuotes Boolean value as to whether words within quotes should be included
     */
    public void cleanWordList(boolean includeQuotes) {
        alteredList.clear();
        boolean inQuotes = false, endQuotes = false;
            for (String element:unalteredList) {
                StringBuilder correctedWord = new StringBuilder(element.toLowerCase());
                for (int i = 0; i < correctedWord.length(); i++) {
                    if (!Character.isLetterOrDigit(correctedWord.charAt(i)) && !(correctedWord.charAt(i) == '-' && i != 0)) {
                        if((correctedWord.charAt(i) == '"') && (inQuotes == false) && (includeQuotes == false)) {
                            inQuotes = true;
                        } else {
                            if ((correctedWord.charAt(i) == '"') && (inQuotes == true))
                                endQuotes = true;
                        }
                        correctedWord.deleteCharAt(i);
                        i = i - 1;
                    }

                }
                if (inQuotes == false) {
                    alteredList.add(new ParaWord(correctedWord.toString()));
                }
                if (endQuotes == true) {
                    inQuotes = false;
                    endQuotes = false;
                }
            }
    }

    /**
     * Creates the list for the word frequency counter, looping through the list of altered words
     * and checking that word against every other word in the list, counting each time that word appears
     * and marking them as checked for future loops through the text, resulting in a list with the 
     * number of times each unique word appears.
     */
    public void wordFrequencyCounter() {
        IndivWordFreq itemTemp;
        frequencyList.clear();

        for (ParaWord element:alteredList) {
            element.setChecked(false);
        }

        for (ParaWord listElement:alteredList) {
            if (listElement.getChecked() == false ) {
                listElement.setChecked(true);
                itemTemp = new IndivWordFreq(listElement.getWord());
                for (ParaWord checkElement:alteredList) {
                    if (checkElement.getWord().equals(listElement.getWord()) && checkElement.getChecked() == false) {
                        itemTemp.incrementCount();
                        checkElement.setChecked(true);
                    }
                }
                if (itemTemp.getWord().length() > 0) {
                    frequencyList.add(itemTemp);
                }
            }
        }

        for (ParaWord element:alteredList) {
            element.setChecked(false);
        }
    }

    /**
     * Returns the word count of the file stored in this list
     * @return Int containing the word count of the file
     */
    public int getWordCount() {
        int wordCount = 0;
        for (IndivWordFreq element:frequencyList) {
            wordCount += element.getCount();
        }
        return wordCount;
    }

    /**
     * Returns the ParaWord at the provided index in the list containing the altered parsed text
     * @param index The index within the list you would like to call from
     * @return The ParaWord found at that index
     */
    public ParaWord getAlteredListPos(int index) {
        return alteredList.get(index);
    }

    /**
     * Returns the length of the list containing the altered parsed text
     * @return Length of the altered text array
     */
    public int getAlteredListLength() {
        return alteredList.size();
    }

    /**
     * Returns the string at the provided index in the list containing the unaltered parsed text
     * @param index The index within the list you would like to call from
     * @return The string found at that index
     */
    public String getUnalteredListPos(int index) {
        return unalteredList.get(index);
    }

    /**
     * Returns the length of the list containing the unaltered parsed text
     * @return Length of the unaltered text array
     */
    public int getUnalteredListLength() {
        return unalteredList.size();
    }

    /**
     * Returns the IndivWordFreq at the provided index in the list containing the frequency of each unique word
     * @param index The index within the list you would like to call from
     * @return The IndivWordFreq found at that index
     */
    public IndivWordFreq getFrequencyListPos(int index) {
        return frequencyList.get(index);
    }

    /**
     * Returns the length of the list containing the frequency of each unique word
     * @return Length of the frequency array
     */
    public int getFrequencyListLength() {
        return frequencyList.size();
    }
}