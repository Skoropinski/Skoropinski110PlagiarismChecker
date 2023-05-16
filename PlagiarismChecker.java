import java.io.File;
public class PlagiarismChecker {

    public static void main(String[] args) {
        String filePath = new File("").getAbsolutePath();
        WordList fileOne = new WordList(filePath + "/test1.txt");
        WordList fileTwo = new WordList(filePath + "/test2.txt");
        // for (int i = 0; i < fileOne.getUnalteredListLength(); i++) {
        //     System.out.print(fileOne.getUnalteredListPos(i) + " ");
        // }
        // System.out.println("\n");

        // fileOne.cleanWordList(true);
        // for (int i = 0; i < fileOne.getAlteredListLength(); i++) {
        //     System.out.print(fileOne.getAlteredListPos(i).getWord() + " ");
        // }
        // System.out.println();

        // fileOne.wordFrequencyCounter();
        // for (int i = 0; i < fileOne.getFrequencyListLength(); i++) {
        //     System.out.println(fileOne.getFrequencyListPos(i).getWord() + " " + fileOne.getFrequencyListPos(i).getCount());
        // }
        PhraseMatcher test = new PhraseMatcher(fileOne, fileTwo, 5);
        System.out.println(test.MatchFunction());
    }
}