//*@author Saif Shahin 
//This file acts the same way as bTree.java, however it is written in c.
//It also prints from an array rather than directly to console output.
//Date Created: 16/4/2021

#include <stdio.h>
#include <stdlib.h>
#include "bTree.h" //it doesn’t have the usual form as it is surrounded by double quotes, meaning it is a user defined include file.
#include <string.h> //gives us strcpy and strcasecmp (case insensitive string compare) methods.
#include <strings.h>

bNode *root = NULL; // bTree root node.

int numNodes = 0; // Number of nodes in tree.

int recCount = 0; // Count variable for use in recursions.

char **indexArray; // index array for tree sort.

void initTree() { // Function that initializes the bTree.
	root = NULL;
	numNodes = 0;
}

bNode* makeNode(char *data) { //function that allocates a bNode.
	numNodes++; //Increments the numNodes counter.

	bNode *node = (bNode*) malloc(sizeof(bNode)); //Creates new bNode object.
	//Sets the values of the variables in the bNode struct.
	node->data = data;
	node->left = NULL;
	node->right = NULL;
	return node;
}

void addNode(char *data) {

	bNode *currentnode;	//initializes a pointer to bNode data type.

	currentnode = root;

	if (currentnode == NULL) { //Checks if root=null (if input is the first input in the bTree).
		root = makeNode(data);
		return;
	} else {

		while (true) { // This while loop assigns Strings to bTree in appropriate order and compares
					   // inputs.
			int i = strcasecmp(data, currentnode->data); // Compares user input and assigns int value to comparison.

			if (i < 0) {

				if (currentnode->left == NULL) { //if the leftchild of the current node is not pointing to anything,
					currentnode->left = makeNode(data); //Set the leftchild of the current node equal to the node housing the input.

					break;
				} else {
					currentnode = currentnode->left; //if not, move on down tree.

				}

			} else {
				if (currentnode->right == NULL) { //if the rightchild of the current node is not pointing to anything,
					currentnode->right = makeNode(data); //Set the rightchild of the current node equal to the node housing the input.

					break;
				} else {
					currentnode = currentnode->right; //if not, move on down tree/

				}
			}
		}
	}
}

void inOrder(bNode *root) { //recursive inOrder traversal function for the bTree.

	if (root->left != NULL) {
		inOrder(root->left);
	}
//Sets the index of the index array at the value of recCount to the String (array of chars) stored in the according root data.
	indexArray[recCount++] = root->data;
	printf("\n%s", root->data); //Prints the according root data.

	if (root->right != NULL) {
		inOrder(root->right);
	}

}

void inReverseOrder(bNode *root) { //recursive inReverseOrder traversal function for the bTree.

	if (root->right != NULL) {
		inReverseOrder(root->right);
	}
	//Sets the index of the index array at the value of recCount to the String (array of chars) stored in the according root data.
	indexArray[recCount++] = root->data;
	printf("\n%s", root->data); //Prints the according root data.

	if (root->left != NULL) {
		inReverseOrder(root->left);
	}
}

void postOrder(bNode *root) { //recursive postOrder traversal function for the bTree, used to delete the bTree.
	if (root->left != NULL) {
		postOrder(root->left);
	}

	indexArray[recCount++] = root->data; //Sets the index of the index array at the value of recCount to the String (array of chars) stored in the according root data.

	if (root->right != NULL) {
		postOrder(root->right);
	}

}

char** makeSortIndex(char arg) { //returns an array of pointers to strings in sort order.

	numNodes = getNumNodes();
	indexArray = (char**) malloc(sizeof(char*) * numNodes); //Allocates an array that is the size of a pointer to char * the value of numNodes.
	recCount = 0;

	//Calls on the according traversal method as per the char arg.
	if (arg == 'd' || arg == 'D') {
		inReverseOrder(root);
	}

	else {
		inOrder(root);

	}

	return indexArray;
}

void deleteTree() { //deletes nodes as it goes along the postOrder traversal.

	while (root->data != NULL) {

		postOrder(root);
		free(root->data); // Deallocate buffer
		free(root); // Deallocate bNode

	}
}

int getNumNodes() { //function to get the value of numNodes.
	return numNodes;
}

