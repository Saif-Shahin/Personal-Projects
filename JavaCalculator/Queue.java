/* @Author: Saif Shahin
*  This code implements the Queue class; a FIFO data structure.
*/

public class Queue {
//must export a method to convert the set of output tokens into a string for output.
	String emptyString = "";
	// These are the listNode instance variables.
	listNode lead;
	listNode rear;
	listNode leadbeta;

	// Constructor class that takes in String input and assigns the instance
	// variables to the String.

	public void enqueue(String arg) {
		// This sets the data instance variables in the listNode class to arg.
		listNode lna = new listNode(arg);
		if (rear == null) { // If the end of the Queue is null, this if statement basically creates a
							// listNode at the end of the queue,and thus equates the end and start of the
							// queue to the inputed data.
			rear = lna;
			lead = lna;

		} // If the end of the Queue is not null, it will attach a new node (and hence
			// the inputed data) onto the end of the Queue.
		else {
			lna.next = rear;
			rear.last = lna;
			rear = lna;

		}

	}

	public String dequeue() {
		
		String j= lead.data;
		
		if (lead.last!=null) { // If the Queue is not empty and this is the last node, set rear to null
			listNode ln3= lead.last;
			ln3.next=null;
			lead=ln3;
			
			
			
		}
		else { //Otherwise, set the lead and rear of the Queue to null
			rear =null;
			lead=null;
		}
		return j;
	}
	
	// The two classes below are constructor classes.
	public Queue(String token) {
		lead = new listNode(token);
		rear = lead;
	}

	public Queue() {

		lead = null;
		rear = null;

	}

	// converts the input queue into a String; Returns a string which is the
	// concatenation of all tokens in the queue, separated by one space.
	public String toString() {

		StringBuilder stringbuild = new StringBuilder(); // String builder to append the strings.

		String fq1; // Initializes String fq1.

		leadbeta = lead;
		while (leadbeta != null) { // If the lead of the Queue is not empty, the String builder appends the data
									// stored in the lead of the Queue and a space.

			stringbuild.append(leadbeta.data + " ");

			leadbeta = leadbeta.last; // Sets the lead of the Queue to the data stored before it.
		}
		fq1 = stringbuild.toString();// Converts the final String builder to a String and then returns it.
		return fq1;

	}
}
