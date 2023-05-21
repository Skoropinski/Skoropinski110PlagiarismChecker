import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SubMenu implements ActionListener{
    private WordList fileOne, fileTwo;
    private int fileOneName, fileTwoName;
    private JFrame frame = new JFrame();
    private JPanel mainPanel = new JPanel(), frequencyPanel = new JPanel();
    private GridLayout frequencyLayout;

    private void setFrame() {
        frame.setVisible(true);
        frame.setTitle("Plagiarism Checker");
        frame.setSize(1200, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

    }

    public SubMenu(int fileOneNo, int fileTwoNo, String givenFilePath) {
        System.out.println(fileOneNo + " " + fileTwoNo);
        fileOne = new WordList(givenFilePath + "/test" + fileOneNo + ".txt");
        fileOne.cleanWordList(true);
        fileOne.wordFrequencyCounter();
        fileOneName = fileOneNo;
        fileTwo = new WordList(givenFilePath + "/test" + fileTwoNo + ".txt");
        fileTwo.cleanWordList(true);
        fileTwo.wordFrequencyCounter();
        fileTwoName = fileTwoNo;

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add("West", frequencyPanel);
        setFrame();
        setFrequencyTable();
        frame.add(new JScrollPane(mainPanel));
    }
}