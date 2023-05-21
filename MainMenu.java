import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu implements ActionListener{
    private String filePath;
    private JFrame frame = new JFrame();
    private JPanel mainPanel = new JPanel(), lengthEntryPanel = new JPanel();
    private JPanel[] tablePanels = new JPanel[6];
    private GridLayout tableLayout = new GridLayout(5, 2);
    private JButton[][] filePairButtons = new JButton[6][6];
    private JButton lengthButton = new JButton("Submit length");
    private JLabel[][] filePairLabels = new JLabel[6][6];
    private int[][] filePairValues = new int[5][5];
    private int phraseCheckerMin = 4;
    private JTextField lengthEntry = new JTextField();

    private void setFrame() {
        frame.setVisible(true);
        frame.setTitle("Plagiarism Checker");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setFilePairs() {
        WordList fileOne, fileTwo;
        PhraseMatcher matcher;
        for (int i = 0; i < 5; i++) {
            fileOne = new WordList(filePath + "/test" + (i + 1) + ".txt");
            for (int j = 0; j < 5; j++) {
                fileTwo = new WordList(filePath + "/test" + (j + 1) + ".txt");
                matcher = new PhraseMatcher(fileOne, fileTwo, phraseCheckerMin);
                filePairButtons[i][j].setText("File " + (i + 1) + " - File " + (j + 1));
                filePairLabels[i][j].setText(matcher.MatchFunction() + "%");
                filePairValues[i][j] = matcher.MatchFunction();
            }
        }
    }

    private void findTopFour() {
        int[] topFour = {0,0,0,0}, a = {0,0,0,0}, b = {0,0,0,0};
        int pos = -1;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if ( i != j) {
                pos = -1;
                if (filePairValues[i][j] > topFour[0]) {
                    pos = 0;
                } else if (filePairValues[i][j] > topFour[1]) {
                    pos = 1;
                } else if (filePairValues[i][j] > topFour[2]) {
                    pos = 2;
                } else if (filePairValues[i][j] > topFour[3]) {
                    pos = 3;
                }

                if (pos > -1 && pos < 3) {
                    topFour[3] = topFour[2];
                    a[3] = a[2];
                    b[3] = b[2];
                }
                if (pos > -1 && pos < 2) {
                    topFour[2] = topFour[1];
                    a[2] = a[1];
                    b[2] = b[1];
                } 
                if (pos == 0) {
                    topFour[1] = topFour[0];
                    a[1] = a[0];
                    b[1] = b[0];
                }
                if (pos != -1) {
                    a[pos] = i;
                    b[pos] = j;
                    topFour[pos] = filePairValues[i][j];
                }
            }
            }
        }
        for (int y = 0; y < 4; y++) {
            filePairButtons[5][y].setText("File " + (a[y] + 1) + " - File " + (b[y] + 1));
            filePairLabels[5][y].setText(filePairValues[a[y]][b[y]] + "%");
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == lengthButton) {
            try {
                phraseCheckerMin = Integer.parseInt(lengthEntry.getText());
                mainPanel.removeAll();
                for (int i = 0; i < 6; i++) {
                    tablePanels[i].removeAll();
                }
                setFilePairs();
                setTablesPanel();
                mainPanel.add(lengthEntryPanel);
                for (int i = 0; i < 6; i++) {
                    mainPanel.add(tablePanels[i]);
                }
                frame.repaint();
                frame.setSize(frame.getWidth(), frame.getHeight() + 1);
            }
            catch (Exception ex) {

            }
        }
        else if (e.getSource() instanceof JButton) {
            String text = ((JButton) e.getSource()).getText();
            SubMenu newSubMenu = new SubMenu((int) text.charAt(5) - 48, (int) text.charAt(14) - 48, filePath, phraseCheckerMin);
        }
    }

    private void setTablesPanel() {
        JLabel pairTitle, pairPercentage;
        int[] sortedOrder;
        findTopFour();
        tablePanels[5].setLayout(tableLayout);
        for (int i = 0; i < 6; i++) {
            pairTitle = new JLabel("Pair: ");
            pairPercentage = new JLabel("Phrase Match:");
            tablePanels[i].setSize(100, 500);
            tablePanels[i].add(pairTitle);
            tablePanels[i].add(pairPercentage);
            if (i != 5) {
                sortedOrder = sortPairsArray(i);
                for (int j = 3; j >= 0; j--) {
                    tablePanels[i].add(filePairButtons[i][sortedOrder[j]]);
                    tablePanels[i].add(filePairLabels[i][sortedOrder[j]]);
                }
            } else {
                for (int j = 0; j < 4; j ++) {
                    tablePanels[i].add(filePairButtons[i][j]);
                    tablePanels[i].add(filePairLabels[i][j]);
                }
            }
        }
    }

    private int[] sortPairsArray(int i) {
        int k, tmp;
        int[] returnArray = {0,1,2,3,4}, valueArray = {filePairValues[i][0], filePairValues[i][1],filePairValues[i][2],filePairValues[i][3],filePairValues[i][4]};
            for (int j = 0; j < 5; j++) {
                k = j;
                while (k > 0 && valueArray[k-1] > valueArray[k]) {
                    tmp = valueArray[k-1];
                    valueArray[k-1] = valueArray[k];
                    valueArray[k] = tmp;

                    tmp = returnArray[k-1];
                    returnArray[k-1] = returnArray[k];
                    returnArray[k] = tmp;

                    k--;
                }
            }
        return returnArray;
    }

    public MainMenu(String givenFilePath) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                filePairButtons[i][j] = new JButton("");
                filePairButtons[i][j].addActionListener(this);
                filePairLabels[i][j] = new JLabel("");
            }
            tablePanels[i] = new JPanel(tableLayout);
        }
        filePath = givenFilePath;
        setFrame();
        frame.setContentPane(mainPanel);
        FlowLayout l = new FlowLayout();
        BorderLayout b = new BorderLayout();
        mainPanel.setLayout(l);
        lengthEntryPanel.setLayout(b);

        JLabel instructions = new JLabel("<html>Welcome to the plagiarism checker, on <br/>the right is all the phrase match percentages <br/>of the file pairs, click on a pair for further <br/>details. You can adjust the minimum phrase match <br/>length through the box below (set to 4 by default).</html>", SwingConstants.CENTER);
        lengthEntryPanel.add("North", instructions);
        lengthEntry.setSize(100, 10);
        lengthEntryPanel.add("Center", lengthEntry);
        lengthEntryPanel.add("South", lengthButton);
        lengthButton.addActionListener(this);

        mainPanel.add(lengthEntryPanel);
        setFilePairs();
        setTablesPanel();
        for (int i = 0; i < 6; i++) {
            mainPanel.add(tablePanels[i]);
        }
        frame.pack();
    }
}