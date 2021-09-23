/* @Author: Saif Shahin
*  This class implements the linked list data structure.
*  This code was inspired by ECSE 202 lecture notes (McGill University)
*/

public class listNode {

//Instance variables of the listNode class
	String data;
	listNode next;
	listNode last;

//Constructor class that will allow for assigning the input Strings to the listNode 'data' instance variable. 
//I chose to put it here instead of in the Stack and Queue classes as this would void the necessity to repeat the same code 
//over again. 
	public listNode(String operator) {
		data = operator;
	}
}
