public class IndivWordFreq {
    private String word;
    private int count;

    public IndivWordFreq(String enteredWord) {
        word = enteredWord;
        count = 1;
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