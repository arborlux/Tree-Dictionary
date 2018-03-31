import java.util.Scanner;
/***************************************************************************************************************
 * Class:			Assign4
 * Purpose:			This is the driver class for Assignment #4 - Dictionary. Displays a menu to a user to add words 
 * 						either from the keyboard or from a file to a TreeMap and tracks how many of each word has been added.
 * Author:			Patrick Griffith
 * Course:	  		CST8130_300 Data Structures
 * Created:   		November 17, 2016
 * Last Edited: 	December 6, 2016
 ***************************************************************************************************************/
public class Assign4 {
	/* ENTRY POINT for STAND-ALONE OPERATION --------------------------------	*/
	public static void main(String[] args) {
		Dictionary dictionary = new Dictionary();
		Scanner input = new Scanner(System.in);

		int choice = 0;
		String[] words;
		do {
			System.out.print("Enter 1 to clear dictionary,\n2 to add text from the keyboard,\n3 to add text from a file,\n4 to search for a word count,\n5 to display number of nodes,\n6 to quit: ");

			if (input.hasNextInt())
				choice = input.nextInt();
			else {
				choice = -1;
				input.next();
			}

			switch (choice) {
			case 1:
				dictionary.clear();
				System.out.println();
				break;
			case 2:
				dictionary.readFromKeyboard(input);
				System.out.println();
				break;
			case 3:
				dictionary.readFromFile();
				System.out.println();
				break;
			case 4:
				System.out.print("Enter the word(s) to search for (space delimiter): ");
				input.nextLine(); //clears last line from input
				words = input.nextLine().split(" "); //allows searching for uppercases, and non-word characters for testing and error checking purposes. Can search for multiple words
				for (int i = 0; i < words.length; i++) {
					System.out.println(words[i] + " occurs " + dictionary.search(words[i]) + " times");
				}
				System.out.println();
				break;
			case 5:
				System.out.println(dictionary + "\n");
				break;
			case 6:
				System.out.print("Exiting Program");
				break;
			default:
				System.out.println("Not a valid option\n");
			}		
		} while (choice != 6);

		input.close();
	}
}