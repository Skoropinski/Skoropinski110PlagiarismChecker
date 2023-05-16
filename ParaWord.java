/**
 * This class is used for both counting words and phrase matching.
 * It uses the checked value to store whether this instance of a word in a text has already been checked,
 * and the highlighted value is used for when the word is displayed for phrase matching
 */
public class ParaWord {
    private String word;
    private boolean checked;
    private boolean highlighted;

    /**
     * This creates a new instance of the ParaWord class, setting both checked and highlighted to their default false state
     * @param enteredWord The word to be stored in this ParaWord instance
     */
    public ParaWord(String enteredWord) {
        checked = false;
        highlighted = false;
        word = enteredWord;
    }   

    /**
     * Returns the word stored in this instance
     * @return Stored word
     */
    public String getWord() {
        return word;
    }

    /**
     * Returns the checked value of this instance
     * @return Stored checked value
     */
    public boolean getChecked() {
        return checked;
    }

    /**
     * Sets the checked value of this instance
     * @param enteredChecked Entered checked value
     */
    public void setChecked(boolean enteredChecked) {
        checked = enteredChecked;
    }

    /**
     * Returns the highlighted value of this instance
     * @return Stored highlighted value
     */
    public boolean getHighlighted() {
        return highlighted;
    }

    /**
     * Sets the highlighted value of this instance
     * @param enteredHighlighted Entered highlighted value
     */
    public void setHighlighted(boolean enteredHighlighted) {
        highlighted = enteredHighlighted;
    }
}