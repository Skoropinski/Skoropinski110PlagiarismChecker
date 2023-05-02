public class ParaWord {
    private String word;
    private boolean checked;
    private boolean highlighted;

    public ParaWord(String enteredWord) {
        checked = false;
        highlighted = false;
        word = enteredWord;
    }   

    public String getWord() {
        return word;
    }

    public boolean getChecked() {
        return checked;
    }

    public void setChecked(boolean enteredChecked) {
        checked = enteredChecked;
    }

    public boolean getHighlighted() {
        return highlighted;
    }

    public void setHighlighted(boolean enteredHighlighted) {
        highlighted = enteredHighlighted;
    }
}