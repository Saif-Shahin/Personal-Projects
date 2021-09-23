//*@author Saif Shahin 
import acm.program.ConsoleProgram;

public class SortBuffer extends ConsoleProgram {

	public void run() {
		// Introduction to program, instructs user.
		print("Text Sorting Program: (ECSE 202 - Assignment 2)\nEnter text to be sorted, line by line. A blank line terminates.\nYou can cut and paste text into this window:\n");

		bTree b = new bTree();// Creates an instance of bTree class
		b.setDisplay(this);// Calls the setDisplay method in the bTree class for successful applet
							// representation.
		while (true) { // Takes in user input, terminates program via break statement if necessary.
			String text1 = readLine("");

			if (text1.equals(" ") || text1.equals("")) {
				break;
			} else {
				b.addNode(text1); // Adds user input to node in bTree
			}
		}
		println("Text in sort order:\n");
		b.displayInOrder(b.root); // Runs displayInOrder method from bTree class

		println("\nText in reverse sort order:\n");
		b.displayInReverseOrder(b.root); // Runs displayInReverseOrder method from bTree class

		println("\nProgram terminated."); // Terminates program at blank input.
	}
}
