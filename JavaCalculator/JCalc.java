/* @Author: Saif Shahin
* This is a test program to ensure that the implementation of the postFix class works successfully.
*/

import acm.program.ConsoleProgram;

public class JCalc extends ConsoleProgram {
	public void run() {

		print("Infix to Postfix conversion, enter expression of blank line to exit.");

		while (true) { // Takes in user input, terminates program via break statement if necessary.

			String text1 = readLine("\nexpr: ");

			text1.replaceAll(" ", ""); // Replaces any spaces in the user input with no space. This is for ease of
										// tokenization.

			if (text1.equals("")) { // Checks for program termination.

				println("Program terminated.");
				break;

			} else {
				postFix pf1 = new postFix();
				Queue QPrime = new Queue();
				Queue QInput = new Queue();

				QInput = pf1.parse(text1);// Parses user input String into a Queue

				QPrime = pf1.In2Post(QInput.toString()); // Queue gets converted to a String and inputed in the Int2Post
															// method.

				String s = QPrime.toString(); // Converts QPrime from a Queue to a String called 's'

				double d = pf1.PostEval(QPrime); // Equates 'd' to the computed expression.

				println(text1 + " => " + s); // Outputs the user input and its respective form in reverse-Polish
												// notation.

				print(" " + s + "evaluates to " + d); // Outputs the expression in reverse-Polish notation its computed
														// form.
			}
		}
	}
}
