/**
 * This class is used for counting how many times a word appears in a file.
 * It stores both the word and how many times it appears
 */
public class IndivWordFreq {
    private String word;
    private int count;

    /**
     * Creates a new IndivWordFreq instance storing the word and the count, set to 1 at the start
     * @param enteredWord The word to be stored in this IndivWordFreq instance
     */
    public IndivWordFreq(String enteredWord) {
        word = enteredWord;
        count = 1;
    } 

    /**
     * Increases the count of this instance by one
     */
    public void incrementCount() {
        count++;
    }

    /**
     * Returns the count value of this instance
     * @return count value
     */
    public int getCount() {
        return count;
    }

    /**
     * Returns the word stored in this instance
     * @return stored word
     */
    public String getWord() {
        return word;
    }
}