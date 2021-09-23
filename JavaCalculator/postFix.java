/** 
 * @author: Saif Shahin
 * This class implements Infix to Postfix conversion and it evaluates the Postfix expression. 
 *
 */

import java.util.StringTokenizer;

public class postFix { // Contains methods that convert the input string from the user into a Queue and
						// converts the user input from Infix to Postfix notation via the Shunting- yard
						// algorithm. It also contians the postEval class, which evaluates the postFix
						// expression.

	public double PostEval(Queue PostFix) {

		Stack output = new Stack();

		double j = 0;
		// While the postfix queue is not empty.
		while (PostFix.lead != null) {

			// Dequeue token from postFix queue.
			String A = PostFix.dequeue();

			if (A.equals("+") || A.equals("-") || A.equals("/") // Checks if the current token is an operator.

					|| A.equals("%") || A.equals("*")

					|| A.equals("^") || A.equals("ß")) {

				// Check if operator is unary of binary.
				if (bianaryCheck(A)) { // the token is binary, hence pop two operands off of the stack.
					String v = output.pop();
					String x = output.pop();

					// convert operands v and x from Strings to doubles.
					Double Vd = Double.parseDouble(v);
					Double Xd = Double.parseDouble(x);

					// Computing the operands per the operator.
					switch (A) {
					case "+":
						j = Vd + Xd;
						break;
					case "-":
						j = Xd - Vd;
						break;
					case "/":
						j = Xd / Vd;
						break;
					case "%":
						j = Xd % Vd;
						break;
					case "*":
						j = Xd * Vd;
						break;
					case "^":
						j = Math.pow(Xd, Vd);
						break;
					}

					// Push computed result onto stack.
					output.push(Double.toString(j));

				} else if (unaryCheck(A)) { // If token is unary, pop one operand off of the stack.
					if (output.top == null) { // Check if this is a negative sign at the start of the function.
						output.push("ß");

					} else {

						String v = output.pop();
						Double Vd = Double.parseDouble(v);

						// Add more cases for more unique operators
						switch (A) {
						case "ß":
							j = -Vd;
							break;
						}
						output.push(Double.toString(j));

					}
				}

			}

			// if token is an operand, push it onto stack
			else {

				output.push(A);

			}

		}
		// Return the item remaining in the stack.
		String out = output.pop();
		Double jo = Double.parseDouble(out);

		// If the last token in the stack is unary minus operator, negate the output.
		if (output.pop() == "ß") {
			jo = -jo;
		}

		return jo;
	}

	// Converts unary minus to 'ß'
	public String unaryConverter(String str) {

		StringBuilder strb = new StringBuilder(str);
		for (int i = 0; i < str.length(); i++) {
			if (i == 0 && str.charAt(0) == '-') {
				strb.setCharAt(i, 'ß');
				str = strb.toString();

			} else if (str.charAt(i) == '+' || str.charAt(i) == '-' || str.charAt(i) == '*' || str.charAt(i) == '/'
					|| str.charAt(i) == '^' || str.charAt(i) == '(') {

				if (str.charAt(i + 1) == '-') {
					strb.setCharAt(i + 1, 'ß');
					str = strb.toString();
				}
			}

		}

		return str;

	}

	// Method to check for unary minus.
	public boolean unaryCheck(String operator) {

		if (operator.equals("ß")) {// || operator.equals("+")) {
			return true;
		} else {
			return false;
		}

	}

//Method to check for binary operators. 
	public boolean bianaryCheck(String operator) {

		if (operator.equals("+") || operator.equals("-") || operator.equals("/") // Checks if the current token is an
																					// operator

				|| operator.equals("%") || operator.equals("*")

				|| operator.equals("^")) {// || operator.equals("(") || operator.equals(")")) {
			return true;
		} else {
			return false;
		}

	}

	public Queue parse(String arg) { // Takes a string containing an Infix or Postfix expression and parses it into
										// a set of tokens, numbers and operators. The tokens are then returned in a
										// queue.

		String arg1 = unaryConverter(arg); // Converts unary minus to identifying symbol

		StringTokenizer stok = new StringTokenizer(arg1, "+^%-*/()ß", true); // String tokenizer that tokenizes the
																				// inputed
																				// String, using '+-*/()' as the
																				// delimiters,
																				// and including the delimiters as
																				// tokens.
		String token = null; // Initializes the String.
		Queue queue2 = new Queue(); // Creates a new instance of the Queue class.

		while (stok.hasMoreTokens()) { // Enqueues the tokens created from the string by the StringTokenizer into a
										// Queue.
			token = stok.nextToken();
			queue2.enqueue(token);
		}

		return queue2;

	}

	public Queue In2Post(String Qin) { // This method takes in a String input containing the tokenized user input, then
										// it tokenizes the input again, with tokens separates by " ". It then applies
										// the Shunting-yard algorithm while there are more tokens in the string, and
										// returns a Queue containing the tokens in reverse-polish notation (prefix
										// notation).
		Queue queue2 = new Queue();
		Stack stack1 = new Stack();
		StringTokenizer STok = new StringTokenizer(Qin, " "); // tokenizes the String input into individual tokens
																// again. The tokens are separated by " ". This is done
																// so the algorithm can repeat for the number of tokens
																// contained in The String tokenizer, as shown in the
																// while loop below.

		while (STok.hasMoreTokens()) {

			String tkn1 = STok.nextToken();

			if (tkn1.equals("+") || tkn1.equals("-") || tkn1.equals("/") // Checks if the current token is an operator
																			// (excluding the open and closed
																			// parenthesis operators).

					|| tkn1.equals("%") || tkn1.equals("*")

					|| tkn1.equals("^")) {

				while ((stack1.top != null) && (precedence(stack1.top.data) > precedence(tkn1) || // Compares the
																									// operators in
																									// terms of their
																									// precedences.
						(precedence(stack1.top.data) == precedence(tkn1) && LA(stack1.top.data)))) {

					queue2.enqueue(stack1.pop()); // pops operators from the operator stack into the output queue.

				}

				stack1.push(tkn1); // push operator to the operator stack

			}

			else if (tkn1.compareTo("(") == 0) { // If the operator is a open parenthesis, push it into the operator
													// stack.
				stack1.push(tkn1);
			}

			else if (tkn1.compareTo(")") == 0) { // If the operator is a closed parenthesis, while the top of the stack
													// is not a closed parenthesis,

				while (stack1.top.data.compareTo("(") != 0) {
					queue2.enqueue(stack1.pop()); // pop the operator from the operator stack onto the output queue
				}

				if (stack1.top.data.compareTo("(") == 0) { // If the top of the operator stack is an open parenthesis,
															// pop the operator from the operator stack and discard it.
					stack1.pop();
				}
			} else { // The input is not a token, thus it is a number, so enqueue the number onto the
						// output queue.
				queue2.enqueue(tkn1);
			}

		}
		while (stack1.top != null) { // This pops the operators from the stack onto the output queue until the stack
										// is empty.

			queue2.enqueue(stack1.pop());

		}

		return queue2; // Returns the output queue, queue2.
	}

//Method for comparing the precedences of the respective operators. 
	public static int precedence(String data) {
		if (data.compareTo("+") == 0) {
			return 2;
		} else if (data.compareTo("-") == 0) {
			return 2;
		} else if (data.compareTo("*") == 0) {
			return 3;
		} else if (data.compareTo("/") == 0) {
			return 3;
		} else if (data.compareTo("^") == 0) {
			return 4;
		} else if (data.compareTo("%") == 0) {
			return 3;
		} else {

			return -1;
		}

	}

	// Method for determining whether a given operator is left associative.
	public static boolean LA(String tkn) {
		return tkn.equals("*") || tkn.equals("+") || tkn.equals("/") || tkn.equals("%") || tkn.equals("-")
				|| tkn.equals("(") || tkn.equals(")");
	}
}
