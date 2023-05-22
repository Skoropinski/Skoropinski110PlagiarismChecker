import java.io.File;

/**
 * Driver class of the program that finds the file path of the folder the texts are in and calls the main menu
 */
public class PlagiarismChecker {

    public static void main(String[] args) {
        String filePath = new File("").getAbsolutePath();
        MainMenu menu = new MainMenu(filePath);
    }
}