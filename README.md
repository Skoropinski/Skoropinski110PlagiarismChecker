# Skoropinski110PlagiarismChecker
110 coursework submisson for the third project option (Plagiarism Checker) by Joshua Skoropinski

Instructions:
Press the buttons of a file pair to open a submenu containing additional information about that pair, as well as change the 
minimum phrase length for phrase matching on the main menu by entering the length and clicking the button, as well as
search for the frequency of a given word in a pair of files by entering the corrisponding sub menu, entering the word into the 
text field and pressing the button to display it

Files:
- IndivWordFreq.java and ParaWord.java are classes which contain variables of different types, used in lists to save related
data (e.g. a word and its frequency in the files)
- WordList.java stores the information about a specific file, including 3 lists, one of the unaltered text parsed by each space,
one of the altered text with punctuation and capital letters removed and one which stores how frequently each unique word
appears in the file, with it also containing the methods / functions to create the 3 lists from the file name passed into it
- PhraseMatcher.java finds the percentage phrase match between two files (the number of words in phrases shared between the
two files passed into it divided by the number of words in the first file). This doesn't count words in quotations for either
aforementioned value and has a minimum phrase length passed into it to only count phrases between the files being the same
if they are a certain number of words long
- MainMenu.java is a GUI class which displays a GUI that shows the phrase match percentage between every pair of files, the
percentage of the top 4 files, introductory text, a text field to change the minimum phrase length, a button to enact that
change and repaint the frame and a button for each file pair to open a sub menu containing extra information about that
specific file pair
-SubMenu.java controls the GUI for sub menus, with it printing the word frequency of all unique words of both files passed into
it, the text of both files printed out with words that are apart of a matched phrase being red and those within quotation marks 
being blue and a text field with a button to find the word frequency of an entered word (with the results printed in the GUI)