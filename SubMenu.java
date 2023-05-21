import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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

    private void setFrame() {
        frame.setVisible(true);
        frame.setTitle("File " + fileOneName + " & File " + fileTwoName + " Information");
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

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

    private void formatPrintedText() {
        PhraseMatcher matcher = new PhraseMatcher(fileOne, fileTwo, phraseLength);
        matcher.MatchFunction();
        printedStrings = "<html><br/>File " + fileOneName + ":<br/>";
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
        System.out.println(fileTwo.getUnalteredListLength() + " " + fileTwo.getAlteredListLength());
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

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == wordButton) {
            mainPanel.remove(findWordPanel);
            findWordPanel.removeAll();
            setFindWordPanel();
            mainPanel.add("North", findWordPanel);
            frame.repaint();
        }
    }

    public SubMenu(int fileOneNo, int fileTwoNo, String givenFilePath, int enteredPhraseLength) {
        System.out.println(fileOneNo + " " + fileTwoNo);
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