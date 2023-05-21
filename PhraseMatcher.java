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
        List<Integer> tempOne = new ArrayList<Integer>(), tempTwo = new ArrayList<Integer>();

        firstFile.cleanWordList();
        firstFile.wordFrequencyCounter(false);
        totalLen = firstFile.getWordCount();
        secondFile.cleanWordList();
        secondFile.wordFrequencyCounter(false);

        for (int i = 0; i < firstFile.getAlteredListLength(); i++) {
            tempi = i;
            if (firstFile.getAlteredListPos(i).getChecked() == false && firstFile.getAlteredListPos(i).getWord().length() != 0 && !firstFile.getAlteredListPos(i).getInQuotes()) {
                for (int j = 0; j < secondFile.getAlteredListLength(); j++) {
                    if (secondFile.getAlteredListPos(j).getChecked() == false && secondFile.getAlteredListPos(j).getWord().length() != 0 && !secondFile.getAlteredListPos(j).getInQuotes()) {
                        while (firstFile.getAlteredListPos(i).getWord().equals(secondFile.getAlteredListPos(j).getWord()) && continueChecking && secondFile.getAlteredListPos(j).getChecked() == false && firstFile.getAlteredListPos(i).getChecked() == false && !firstFile.getAlteredListPos(i).getInQuotes() && !secondFile.getAlteredListPos(j).getInQuotes()) {
                            if (firstFile.getAlteredListPos(i).getWord().length() != 0 && secondFile.getAlteredListPos(j).getWord().length() != 0) {
                                phraseLen++;
                                tempOne.add(i);
                                tempTwo.add(j);
                            }
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
                                firstFile.getAlteredListPos(element).setHighlighted(true);
                            }
                           
                            for (int element:tempTwo) {
                                secondFile.getAlteredListPos(element).setChecked(true);
                                secondFile.getAlteredListPos(element).setHighlighted(true);
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


        returnPercentage = ( (double)totalMatchedWords / (double) totalLen) * 100;
        return (int) Math.round(returnPercentage);
    }


}