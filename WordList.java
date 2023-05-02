import java.io.FileReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.util.*;

public class WordList {
    private List<String> unalteredList = new ArrayList<String>();
    private List<ParaWord> alteredList = new ArrayList<ParaWord>();

    public WordList(String filePath) {
        String line;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            while ((line = reader.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, " ");
                while (st.hasMoreTokens()) {
                    unalteredList.add(st.nextToken());
                }
            }
            reader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 
    }

    public void cleanWordList(boolean includeQuotes) {
        boolean inQuotes = false, endQuotes = false;
            for (String element:unalteredList) {
                StringBuilder correctedWord = new StringBuilder(element.toLowerCase());
                for (int i = 0; i < correctedWord.length(); i++) {
                    if (!Character.isLetterOrDigit(correctedWord.charAt(i)) && !(correctedWord.charAt(i) == '-' && i != 0)) {
                        if((correctedWord.charAt(i) == '"') && (inQuotes == false) && (includeQuotes == false)) {
                            inQuotes = true;
                        } else {
                            if ((correctedWord.charAt(i) == '"') && (inQuotes == true))
                                endQuotes = true;
                        }
                        correctedWord.deleteCharAt(i);
                        i = i - 1;
                    }

                }
                if ((correctedWord.length() > 0) && (inQuotes == false)) {
                    alteredList.add(new ParaWord(correctedWord.toString()));
                }
                if (endQuotes == true) {
                    inQuotes = false;
                    endQuotes = false;
                }
            }
    }

    public String getWordListPos(int index) {
        return alteredList.get(index);
    }
}