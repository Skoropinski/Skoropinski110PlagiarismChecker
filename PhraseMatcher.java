import java.util.*;

public class PhraseMatcher {
    private WordList firstFile, secondFile;
    int minMatchLen;

    public PhraseMatcher(WordList firstEntered, WordList secondEntered, int enteredLength) {
        firstFile = firstEntered;
        secondFile = secondEntered;
        minMatchLen = enteredLength;
    }

    public double MatchFunction() {
        int totalLen, totalMatchedWords = 0, phraseLen = 0, tempi;
        double returnPercentage;
        boolean continueChecking = true;
        List<Integer> firstHighlightedIndex = new ArrayList<Integer>(), secondHighlightedIndex = new ArrayList<Integer>();

        firstFile.cleanWordList(true);
        firstFile.wordFrequencyCounter();
        secondFile.cleanWordList(true);
        secondFile.wordFrequencyCounter();
        totalLen = firstFile.getWordCount() + secondFile.getWordCount();

        firstFile.cleanWordList(false);
        firstFile.wordFrequencyCounter();
        secondFile.cleanWordList(false);
        secondFile.wordFrequencyCounter();

        for (int i = 0; i < firstFile.getAlteredListLength(); i++) {
            tempi = i;
            if (firstFile.getAlteredListPos(i).getChecked() == false) {
                for (int j = 0; j < secondFile.getAlteredListLength(); j++) {
                    if (secondFile.getAlteredListPos(j).getChecked() == false) {
                        while (firstFile.getAlteredListPos(i).getWord().equals(secondFile.getAlteredListPos(j).getWord()) && continueChecking) {
                            phraseLen = phraseLen + 2;
                            firstFile.getAlteredListPos(i).setChecked(true);
                            secondFile.getAlteredListPos(j).setChecked(true);
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
                        }
                        i = tempi;
                        phraseLen = 0;
                        continueChecking = true;
                    }
                }  
            }
        }
        System.out.println(totalMatchedWords + " " + totalLen);
        returnPercentage = ( (double)totalMatchedWords / (double) totalLen) * 100;
        return returnPercentage;
    }


}