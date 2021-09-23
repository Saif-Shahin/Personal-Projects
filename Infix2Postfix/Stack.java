//Author: Saif Shahin.
//This is the Stack class; a LIFO data structure.

public class Stack {

	listNode top;

	public void push(String operator) { // inputs data onto the Stack
		listNode lnx = new listNode(operator); // Creates a new entry
		if (!(top == null)) { // If the stack is not empty, it adds the entry onto the top of the stack.

			top.next = lnx;
			lnx.last = top;
		}

		top = lnx; // Equates the top of the stack to the entry.

	}

	String pop() { // returns the last inputed data that was inputed onto the Stack.

		if (!(top == null)) { // If the stack is not empty (This checks this condition)
			String data = top.data;
			if (!(top.last == null)) { // If the entry 'under' the current entry us not null, make the new top the
										// entry below it.
				listNode lnb = top.last;
				lnb.next = null;
				top = lnb;
			} else { // If the entry 'under' the current entry is null, set the top to null.
				top = null;
			}
			return data;
		} else { // If the stack is empty, return an empty string

			return "";
		}
	}

//The two classes underneath this note are constructors.
	public Stack() {

		top = null;
	}

	public Stack(String operator) {
		top = new listNode(operator);
	}

}
