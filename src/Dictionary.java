import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;
/***************************************************************************************************************
 * Class:			Dictionary
 * Purpose:			This class manages the TreeMap<String, Integer> data structure used for storing words, where 
 * 						String is the word and Integer is the number of times it exists in the TreeMap. 
 * 						Includes the logic for adding from a keyboard or file, searching, or displaying the number of nodes in the tree.
 * Author:			Patrick Griffith
 * Course:	  		CST8130_300 Data Structures
 * Created:   		November 17, 2016
 * Last Edited: 	December 6, 2016
 * 
 * Data Members:	tree: TreeMap<String, Integer> - TreeMap of words and the number of times they have been added 
 * 
 * Methods:			Dictionary() - default constructor - allocates a new TreeMap<String, Integer>
 * 					toString() : String - returns the number of nodes that tree contains
 * 					clear() - erases all mappings within tree
 * 					readFromFile() : boolean - opens a file if found and adds each word to the tree with addToTree(String) method
 * 					readFromKeyboard(Scanner) - prompts the user to enter some text and adds each word to the tree with addToTree(String) method
 * 					addToTree(String) - strips each passed in word for special characters and inserts into tree with an updated count
 * 					openFile() : Scanner - attempts to open a file and returns an open Scanner if it exists
 * 					search(String) : int - returns the number of times a word exists within tree
 ***************************************************************************************************************/
public class Dictionary {
	/* CONSTRUCTORS	---------------------------------------------------------	*/	
	public Dictionary() {
		tree = new TreeMap<String, Integer>();
	}


	/* NORMAL BEHAVIOR	-----------------------------------------------------	*/	
	@Override
	public String toString() { //Returns number of nodes in tree since it doesn't make sense to have the description of Dictionary be the keys or values, as the TreeMap can potentially contain millions of elements
		return "There are " + tree.size() + " nodes"; 
	}

	public void clear() {
		tree.clear();
	}

	public boolean readFromFile() {
		Scanner input = openFile();		
		if (input == null)
			return false;

		while(input.hasNext()) {
			addToTree(input.next());
		}

		return true;
	}

	public void readFromKeyboard(Scanner input) {
		System.out.print("Enter text to add to the dictionary (space delimiter): ");
		input.nextLine(); //removes the last line buffered from input
		String[] words = input.nextLine().split(" "); //check for multiple words being added

		for (int i = 0; i < words.length; i++) {
			addToTree(words[i]);
		}
	}
	
	public void addToTree(String word) {
		word = word.toLowerCase().replaceAll("\\W", ""); //removes all non-word characters
		
		int count = search(word) + 1; //retrieve the current word count and increase it
		tree.put(word, count);
	}
	
	
	/* HELPER METHODS	-----------------------------------------------------	*/	
	public Scanner openFile() {
		Scanner fileReader = new Scanner(System.in);

		System.out.print("Enter name of file to process: ");
		File file = new File(fileReader.nextLine());

		try {
			return fileReader = new Scanner(file);	
		}
		catch(IOException ioe) {
			System.out.println(ioe.getMessage() + "\n");
			return null;
		}
	}

	public int search(String word) {
		return (tree.containsKey(word)) ? tree.get(word) : 0; //return the word count, if it doesn't exist return 0 since it will be added for the first time
	}


	/* ATTRIBUTES	---------------------------------------------------------	*/	
	private TreeMap<String, Integer> tree;
}