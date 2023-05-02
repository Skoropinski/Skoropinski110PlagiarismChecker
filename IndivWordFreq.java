public class IndivWordFreq {
    private String word;
    private int count;

    public IndivWordFreq(String enteredWord) {
        word = enteredWord;
    }

    public void incrementCount() {
        count++;
    }

    public int getCount() {
        return count;
    }

    public String getWord() {
        return word;
    }
}