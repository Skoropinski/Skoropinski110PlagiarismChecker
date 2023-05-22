import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * This class is for printing the sub menu of a pair of files.
 * It prints out the list of word frequencies of every word in both file compared to the other file,
 * the text of the files where the matched phrases are highlighted and
 * a text field for users to search for word frequencies within the two files
 */
public class SubMenu implements ActionListener{
    private WordList fileOne, fileTwo;
    private int fileOneName, fileTwoName, phraseLength;
    private JFrame frame = new JFrame();
    private JPanel mainPanel = new JPanel(), frequencyPanel = new JPanel(), findWordPanel = new JPanel();
    private JButton wordButton = new JButton("Find word");
    private JTextField wordInput = new JTextField("Plagiarism");
    private GridLayout frequencyLayout;
    private String wordToFind, printedStrings;
    private JLabel wordFindResults = new JLabel("", SwingConstants.CENTER), wordFindExplan = new JLabel("Please enter a word to find its frequency", SwingConstants.CENTER), printedText = new JLabel("");

    /**
     * Sets up the frame of the sub menu, including title, close action, size and making it visible
     */
    private void setFrame() {
        frame.setVisible(true);
        frame.setTitle("File " + fileOneName + " & File " + fileTwoName + " Information");
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * Sets up the panel for the finding word frequency feature, placing the instruction text, button, text field and
     * results in the correct place
     */
    private void setFindWordPanel() {
        findWordPanel.setLayout(new BorderLayout());
        findWordPanel.add("North", wordFindExplan);
        wordInput.setPreferredSize(new Dimension(150, 30));
        wordButton.setPreferredSize(new Dimension(150, 30));
        findWordPanel.add("Center", wordInput);
        findWordPanel.add("East", wordButton);
        wordToFind = wordInput.getText().toLowerCase();
        String foundLabelText = "<html>File " +  fileOneName + ": " + fileOne.findWordCount(wordToFind) + "<br/>File " + fileTwoName + ": " + fileTwo.findWordCount(wordToFind) + "</html>";
        wordFindResults.setText(foundLabelText);
        findWordPanel.add("South", wordFindResults);
    }

    /**
     * Formats the text when printing out the text files so that phrased matched text is red and text in quotes
     * is blue, as well as new lines for ease of reading using html
     */
    private void formatPrintedText() {
        PhraseMatcher matcher = new PhraseMatcher(fileOne, fileTwo, phraseLength);
        matcher.MatchFunction();
        printedStrings = "<html><br/><font color = 'red'>Red</font> text shows matched phrases<br/><font color = 'blue'>Blue</font> text shows text in quotes (not checked during phrase matching)<br/><br/>File " + fileOneName + ":<br/>";
        for (int i = 0; i < fileOne.getUnalteredListLength(); i++) {
            if (fileOne.getAlteredListPos(i).getInQuotes() == true) {
                printedStrings = printedStrings + "<font color = 'blue'>";
            } else if (fileOne.getAlteredListPos(i).getHighlighted() == true){
                printedStrings = printedStrings + "<font color = 'red'>";
            } else {
                printedStrings = printedStrings + "<font color = 'black'>";
            }
            printedStrings = printedStrings + fileOne.getUnalteredListPos(i) + "</font> ";
            if (i % 8 == 0 && i != 0) {
                printedStrings = printedStrings + "<br/>";
            }
        }

        printedStrings = printedStrings + "<br/><br/>File " + fileTwoName + ":<br/>";
        for (int i = 0; i < fileTwo.getUnalteredListLength(); i++) {
            if (fileTwo.getAlteredListPos(i).getInQuotes() == true) {
                printedStrings = printedStrings + "<font color = 'blue'>";
            } else if (fileTwo.getAlteredListPos(i).getHighlighted() == true) {
                printedStrings = printedStrings + "<font color = 'red'>";
            } else {
                printedStrings = printedStrings + "<font color = 'black'>";
            }
            printedStrings = printedStrings + fileTwo.getUnalteredListPos(i) + "</font> ";
            if (i % 8 == 0  && i != 0) {
                printedStrings = printedStrings + "<br/>";
            }
        }

        printedStrings = printedStrings + "</html>";
        printedText.setText(printedStrings);
        printedText.setVerticalAlignment(SwingConstants.TOP);
        printedText.setHorizontalAlignment(SwingConstants.LEFT);
    }

    /**
     * Sets the table which contains each word frequency, adding the titles to the dedicated panel,
     * then the word and word frequency from both files in three columns, adding all the words from
     * the first file then all from second file which weren't already included in the first file's list
     */
    private void setFrequencyTable() {
        frequencyLayout = new GridLayout(0, 3);
        JLabel wordTitle = new JLabel("Word: "), fileOneTitle = new JLabel("File " + fileOneName + ":"), fileTwoTitle = new JLabel("File " + fileTwoName + ":");
        JLabel wordLabel, fileOneCount, fileTwoCount;
        frequencyPanel.setLayout(frequencyLayout); 
        frequencyPanel.add(wordTitle);
        frequencyPanel.add(fileOneTitle);
        frequencyPanel.add(fileTwoTitle);
        for (int i = 0; i < fileOne.getFrequencyListLength(); i++) {
            wordLabel = new JLabel(fileOne.getFrequencyListPos(i).getWord());
            fileOneCount = new JLabel();
            fileOneCount.setText(fileOne.getFrequencyListPos(i).getCount() + "");
            fileTwoCount = new JLabel();
            fileTwoCount.setText(fileTwo.findWordCount(fileOne.getFrequencyListPos(i).getWord()) + "");
            frequencyPanel.add(wordLabel);
            frequencyPanel.add(fileOneCount);
            frequencyPanel.add(fileTwoCount);
        }
        for (int i = 0; i < fileTwo.getFrequencyListLength(); i++) {
            if (fileOne.findWordCount(fileTwo.getFrequencyListPos(i).getWord()) == 0) {
                wordLabel = new JLabel(fileTwo.getFrequencyListPos(i).getWord());
                fileOneCount = new JLabel("0");
                fileTwoCount = new JLabel();
                fileTwoCount.setText(fileTwo.getFrequencyListPos(i).getCount() + "");
                frequencyPanel.add(wordLabel);
                frequencyPanel.add(fileOneCount);
                frequencyPanel.add(fileTwoCount);
            }
        }
    }

    /**
     * Accepts the input of the text field via the confirm button, repainting the frame
     * to show the frequencies of the word currently input in the text field
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == wordButton) {
            mainPanel.remove(findWordPanel);
            findWordPanel.removeAll();
            setFindWordPanel();
            mainPanel.add("North", findWordPanel);
            frame.repaint();
        }
    }

    /**
     * Main function to produce the submenu, setting up the files and frame elements
     * for display
     * @param fileOneNo number of the first file
     * @param fileTwoNo number of the second file
     * @param givenFilePath file path to the folder containing the text files
     * @param enteredPhraseLength the length used during phrase matching used in the main menu when this was called
     */
    public SubMenu(int fileOneNo, int fileTwoNo, String givenFilePath, int enteredPhraseLength) {
        fileOne = new WordList(givenFilePath + "/test" + fileOneNo + ".txt");
        fileOne.cleanWordList();
        fileOne.wordFrequencyCounter(true);
        fileOneName = fileOneNo;
        fileTwo = new WordList(givenFilePath + "/test" + fileTwoNo + ".txt");
        fileTwo.cleanWordList();
        fileTwo.wordFrequencyCounter(true);
        fileTwoName = fileTwoNo;
        phraseLength = enteredPhraseLength;

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add("West", frequencyPanel);
        setFrame();
        setFrequencyTable();
        wordButton.addActionListener(this);
        setFindWordPanel();
        mainPanel.add("North", findWordPanel);
        formatPrintedText();
        mainPanel.add("Center", printedText);
        frame.add(new JScrollPane(mainPanel));
    }
}