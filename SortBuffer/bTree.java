
//*@author Saif Shahin 

import acm.program.ConsoleProgram;

//Note: This code was inspired by McGIll ECSE 202 Lecture and tutorial notes,

public class bTree {
	ConsoleProgram display;

	public void setDisplay(ConsoleProgram display) { // Link to the acm console program for successful representation in
														// applet
		this.display = display;
		display.println();
	}

	public class bNode { // Helper class, used for structure only
		String nodename;
		bNode leftchild;
		bNode rightchild;

		public bNode(String text1) { // This is the constructor
			nodename = text1;
		}
	}

	bNode root; // Creating a variable that could house a bTree node

	public void addNode(String newName) { // Adds a new String object to the bTree

		bNode newNode = new bNode(newName); // Creates a node
		bNode currentNode = root;
		if (currentNode == null) {
			root = newNode;
			return;
		}

		while (true) { // This while loop assigns Strings to bTree in appropriate order and compares
						// inputs.
			int i = newName.compareTo(currentNode.nodename); // Compares user input and assigns int value to comparison.
			if (i < 0) {
				if (currentNode.leftchild == null) {
					currentNode.leftchild = newNode;
					break;
				} else {
					currentNode = currentNode.leftchild;
				}
			} else {
				if (currentNode.rightchild == null) {
					currentNode.rightchild = newNode;
					break;
				} else {
					currentNode = currentNode.rightchild;
				}
			}
		}
	}

	public void displayInOrder(bNode startNode) { // Traverses bTree in order via recursion
		if (startNode.leftchild != null) {
			displayInOrder(startNode.leftchild);
		}
		display.println(startNode.nodename);
		if (startNode.rightchild != null)
			displayInOrder(startNode.rightchild);
	}

	public void displayInReverseOrder(bNode startNode) { // Traverses bTree in reverse order via recursion
		if (startNode.rightchild != null) {
			displayInReverseOrder(startNode.rightchild);
		}
		display.println(startNode.nodename);
		if (startNode.leftchild != null) {
			displayInReverseOrder(startNode.leftchild);
		}
	}
}
