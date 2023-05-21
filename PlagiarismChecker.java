import java.io.File;
public class PlagiarismChecker {

    public static void main(String[] args) {
        String filePath = new File("").getAbsolutePath();
        WordList fileOne = new WordList(filePath + "/test1.txt");
        WordList fileTwo = new WordList(filePath + "/test5.txt");
        // for (int i = 0; i < fileOne.getUnalteredListLength(); i++) {
        //     System.out.print(fileOne.getUnalteredListPos(i) + " ");
        // }
        // System.out.println("\n");

        fileOne.cleanWordList();
         for (int i = 0; i < fileOne.getAlteredListLength(); i++) {
             System.out.print(fileOne.getAlteredListPos(i).getWord() + " ");
         }
         System.out.println();

        fileOne.wordFrequencyCounter(true);
        for (int i = 0; i < fileOne.getFrequencyListLength(); i++) {
            //System.out.println(fileOne.getFrequencyListPos(i).getWord() + " " + fileOne.getFrequencyListPos(i).getCount());
        }
        PhraseMatcher test = new PhraseMatcher(fileOne, fileTwo, 2);
        System.out.println(test.MatchFunction());
        
        for (int i = 0; i < fileTwo.getAlteredListLength(); i++) {
            if (fileTwo.getAlteredListPos(i).getHighlighted() == true) {
                System.out.print("|" + fileTwo.getAlteredListPos(i).getWord() + "| ");
            } else {
                System.out.print(fileTwo.getAlteredListPos(i).getWord() + " ");
            }
        }
        //System.out.println();

        MainMenu menu = new MainMenu(filePath);
    }
}