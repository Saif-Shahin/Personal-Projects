/* @Author: Saif Shahin
*  
*  This class implements a GUI to create a calculator. The infix to postfix conversion, and the postfix 
*  evaluation takes place in the postFix class.
*  This code was inspired by the example used on page 395 of The Art and Science of Java (Roberts, 2007)
*
*/

import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import acm.program.Program;
import acm.gui.TableLayout;

public class JCalcGUI extends Program implements ActionListener, KeyListener {

	postFix pf1 = new postFix();
	Queue QPrime = new Queue();
	Queue QInput = new Queue();

	// This initializes the GUI user interface
	JTextField inputDisplay = new JTextField();
	JTextField outputDisplay = new JTextField();
	JTextField instructions = new JTextField();
	JTextArea n = new JTextArea();
	double outputresult = 0.0; // Initializes output result.

	int Buttonsize = 70; // Sets button size to 70 pixels.
	int Buttonwidth = 60; // sets button width to 60 pixels.

	public void init() {
		setSize(700, 700); // Sets the size of the applet
		setBackground(Color.DARK_GRAY); // Sets the background color of the applet.

		setLayout(new TableLayout(9, 4)); // Sets the grid layout for the calculator.
		n.setEditable(false);
		add(n, NORTH); // Adds instructions to GUI.
		n.setText(
				"Welcome to JCalc! Enter an expression for it to be computed!\nThe Escape key and the Quit button terminate the program.\n"
						+ "Please click on the topmost textfeild if you wish to enter keyboard inputs after button inputs.");

		inputDisplay = CalcDisplay(inputDisplay); // Creates an input display.

		outputDisplay = CalcDisplay(outputDisplay);// Creates an output display.

		inputDisplay.addKeyListener(new keyListener()); // Adds key-listening method to the input display.

		add(inputDisplay, "gridwidth= 4 height= " + Buttonsize); // Adds the input box where user inputs expression to
																	// the applet.
		add(outputDisplay, "gridwidth= 4 height= " + Buttonsize);// Adds the output box where the expression result is
																	// displayed to the applet.
		addButtons(); // Adds the calculator buttons.
		addActionListeners();

	}

//This method adds the calculator buttons to the display.
	public void addButtons() {
		String parameter = "width=" + Buttonwidth + " height=" + Buttonsize;
		String Buttons[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "+", "-", "*", "/", "%", "^", "(", ")",
				".", "=", "C", "DEL", "Quit" };

		for (int p = 0; p < Buttons.length; p++) {
			JButton button = new JButton(Buttons[p]);
			add(button, parameter);
		}

	}

//Keyboard function for the input display
	class keyListener implements KeyListener {
		@Override
		public void keyTyped(KeyEvent x) {

			Character C = x.getKeyChar();

			if (C.equals('\b')) { // If input is backspace, same function as "DEL" action.
				int inti = inputDisplay.getText().length() - 1;
				String s = inputDisplay.getText().substring(0, inti);
				inputDisplay.setText(s);
			}
			try {
				if (C.equals('\b')) { // This is done so backspace key won't be evaluated twice due to the try
										// statement

				} else if (C.equals('C') || C.equals('c')) { // Resets the input and output displays if the user hits
																// the "C" or 'c' key.
					reset();

				} else if (x.getKeyChar() == KeyEvent.VK_ESCAPE) { // Terminates program if user hits the escape key.
					reset();
					System.exit(0);

				} else if (C.equals('\n') || C.equals('=')) { // If input is enter key, same functionality as equals
																// sign.
					System.out.println("Enter key: " + x.getKeyChar());
					QInput = pf1.parse(inputDisplay.getText());// Parses user input String into a Queue.
					QPrime = pf1.In2Post(QInput.toString());// Converts user input into postfix expression.
					outputresult = pf1.PostEval(QPrime);// Evaluates the postfix expression.
					Double o = outputresult;
					String o2 = o.toString();// Converts the output double to a String.
					outputDisplay.setText(o2);// Displays the output on the output display.
				}

				else if (betweenRange(x)) { // If the user enters a key corresponding to a number, set the input display
											// to
											// that key.
					String output = Character.toString(x.getKeyChar());
					System.out.println("line 95 output is: " + output);
					inputDisplay.setText(inputDisplay.getText() + output);
				}
			} catch (NumberFormatException | NullPointerException | StringIndexOutOfBoundsException c) { // Outputs
																											// "ERROR!"
																											// if user
																											// inputs
																											// invalid
				// expression.
				outputDisplay.setText("ERROR!");
			}
		}

//The next two methods override the keyPressed and keyReleased methods in the KeyListener interface.
		@Override
		public void keyPressed(KeyEvent e) {
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
	}

//Checks if the key typed is a valid key (a number or valid operator).
	public boolean betweenRange(KeyEvent x) {
		Character range = x.getKeyChar();
		if (range.equals('0') || range.equals('1') || range.equals('2') || range.equals('3') || range.equals('4')
				|| range.equals('5') || range.equals('6') || range.equals('7') || range.equals('8') || range.equals('9')
				|| range.equals('+') || range.equals('-') || range.equals('*') || range.equals('/') || range.equals('^')
				|| range.equals('%') || range.equals('(') || range.equals(')') || range.equals('C')
				|| range.equals('.')) {
			return true;
		} else {
			return false;
		}

	}

//Sets the input and output displays when called.
	public JTextField CalcDisplay(JTextField JText) {
		JText.setEditable(false);
		return JText;
	}

//Resets the calculator to its initial values. 
	public void reset() {
		inputDisplay.setText("");
		outputDisplay.setText(null);
		outputresult = 0.0;

	}

	// Method for the actions taken when a button is pressed.
	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();

		if (command.equals("DEL")) { // Deletes last operator or operand inputed by the user if the user hits the
										// "DEL" button.
			int inti = inputDisplay.getText().length() - 1;
			String s = inputDisplay.getText().substring(0, inti);
			inputDisplay.setText(s);

		}
		try {
			if (command.equals("DEL")) { // This is done so "DEL" won't be evaluated twice due to the try statement
			} else if (command.equals("C")) { // Makes the two text fields blank if user hits the "C" button.
				reset();

			}

			else if (command.equals("Quit")) { // Terminates the program if user hits "Quit" button.
				reset();
				System.exit(0);

			}

			else if (command.equals("=")) {
				QInput = pf1.parse(inputDisplay.getText());// Parses user input String into a Queue.
				QPrime = pf1.In2Post(QInput.toString()); // Converts user input into postfix expression.
				outputresult = pf1.PostEval(QPrime); // Evaluates the postfix expression.
				Double o = outputresult;
				String o2 = o.toString(); // Converts the output double to a String.
				outputDisplay.setText(o2); // Displays the output on the output display.
			}

			else {
				inputDisplay.setText(inputDisplay.getText() + command);

			}
		} catch (NumberFormatException | NullPointerException | StringIndexOutOfBoundsException b) {// Outputs "ERROR!"
																									// if user inputs
																									// invalid
																									// expression.
			outputDisplay.setText("ERROR!");
		}

	}

}
