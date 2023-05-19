import java.util.*;

public class PhraseMatcher {
    private WordList firstFile, secondFile;
    int minMatchLen;

    public PhraseMatcher(WordList firstEntered, WordList secondEntered, int enteredLength) {
        firstFile = firstEntered;
        secondFile = secondEntered;
        minMatchLen = enteredLength;
    }

    public int MatchFunction() {
        int totalLen, totalMatchedWords = 0, phraseLen = 0, tempi;
        double returnPercentage;
        boolean continueChecking = true;
        List<Integer> firstHighlightedIndex = new ArrayList<Integer>(), secondHighlightedIndex = new ArrayList<Integer>(), tempOne = new ArrayList<Integer>(), tempTwo = new ArrayList<Integer>();

        firstFile.cleanWordList(false);
        firstFile.wordFrequencyCounter();
        totalLen = firstFile.getWordCount();
        secondFile.cleanWordList(false);
        secondFile.wordFrequencyCounter();

        for (int i = 0; i < firstFile.getAlteredListLength(); i++) {
            tempi = i;
            if (firstFile.getAlteredListPos(i).getChecked() == false) {
                for (int j = 0; j < secondFile.getAlteredListLength(); j++) {
                    if (secondFile.getAlteredListPos(j).getChecked() == false) {
                        while (firstFile.getAlteredListPos(i).getWord().equals(secondFile.getAlteredListPos(j).getWord()) && continueChecking && secondFile.getAlteredListPos(j).getChecked() == false && firstFile.getAlteredListPos(i).getChecked() == false) {
                            phraseLen++;
                            tempOne.add(i);
                            tempTwo.add(j);
                            firstHighlightedIndex.add(i);
                            secondHighlightedIndex.add(j);
                            if (i + 1 < firstFile.getAlteredListLength() && j + 1 < secondFile.getAlteredListLength()) {
                                i++;
                                j++;
                            } else {
                                continueChecking = false;
                            }
                            }
                        if (phraseLen >= minMatchLen) {
                            totalMatchedWords += phraseLen;
                            for (int element:tempOne) {
                                firstFile.getAlteredListPos(element).setChecked(true);
                            }
                           
                            for (int element:tempTwo) {
                                secondFile.getAlteredListPos(element).setChecked(true);
                            }
                        }
                        tempOne.clear();
                        tempTwo.clear();
                        i = tempi;
                        phraseLen = 0;
                        continueChecking = true;
                    }
                }  
            }
        }

        for (int element:firstHighlightedIndex){
            firstFile.getAlteredListPos(element).setHighlighted(true);
        }
        for (int element:secondHighlightedIndex){
            secondFile.getAlteredListPos(element).setHighlighted(true);
        }

        returnPercentage = ( (double)totalMatchedWords / (double) totalLen) * 100;
        return (int) Math.round(returnPercentage);
    }


}