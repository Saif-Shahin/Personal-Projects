/*
 *Author: Saif Shahin 
 *This file acts the same way as sortBuffer.java, but written in c.
 *Date Created: 16/4/2021
 *
 */

#include <stdio.h>
#include <stdlib.h>
#include "bTree.h"
#include <string.h>
#define LENGTH 80

//Function to emulate the readLine method in ACM Java.
char* readLine() {

	char *buffer = malloc(LENGTH); //Allocates memory to store pointer to buffer.
	if (buffer == NULL) { //Terminates program if input string is too large.
		printf("Can't allocate buffer.\n");
		exit(true);
	}
	fgets(buffer, LENGTH, stdin);
	buffer[strlen(buffer) - 1] = '\0'; //Makes the last letter of the input \0.
	return buffer;
}

int main() {
	printf(
			"Text Sorting Program: (ECSE 202 - Assignment 2)\nEnter text to be sorted, line by line. A blank line terminates.\nYou can cut and paste text into this window:\n\n");

	initTree(); //initializes bTree.

	while (true) {

		char *input = readLine(); //reads user input.

		if (strlen(input) == 0) { //if input is 'return key'.

			break;
		}

		else {

			addNode(input); //passes input into the bTree.

		}
	}

//Calls on the necessary traversal routine to traverse the sortIndex.
	printf("Text in sort order:\n");
	makeSortIndex('a');

	printf("\n\nText in reverse sort order:\n");
	makeSortIndex('d');

	printf("\n\nProgram terminated.");

//Deletes the bTree generated in this program.
	deleteTree();

}
