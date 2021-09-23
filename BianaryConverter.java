//*@author Saif Shahin (McGill Student ID: 260964749)

import java.util.Scanner;

public class BianaryConverterAssignment1 {
	public static void main(String[] args) {
		run();
	}

	public static void run() {
		// The code from lines 14-16, 33, 41-42, and 51-57 is taken from ECSE 202,
		// Winter 2021, Assignment 1.
		// Introduction and instructions:
		System.out.println("Base conversion program; converts +ve integers to a target base.");
		System.out.println("Enter number to be converted and target base on separate lines following the prompts.");
		System.out.println("A blank entry for either input terminates the program.");
		// Read user input/ check for program termination or input error
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("Number >");
			String input = sc.nextLine();
			if (input.equals(" ")) {
				break;
			}
			boolean check = false;
			for (int i = 0; i < input.length() - 1; i++) {
				char temp = input.charAt(i);
				if ((temp >= 'a' && temp <= 'z') || (temp >= 'A' && temp <= 'Z')) {
					check = true;
				}
			}
			if (check) {
				System.out.println("Error! " + input + " does not correspond to a positive integer.");
			} else if (input.equals(" ") || input.equals("")) {
				break;
				// If user input is a corresponds to a positive integer, this converts the
				// String input to an integer.
			} else {
				int number = String2Int(input);

				if (number < 0) {
					System.out.println("Error! " + input + " does not correspond to a positive integer.");
					// If user input is corresponds to a positive integer, program continues and
					// prompts user to enter target base.
				} else {
					System.out.println("Target base >");
					input = sc.nextLine();
					if (input.equals("") || input.equals(" ") || input.equals("  ")) { // Check for break.
						break;
					}
					int base = String2Int(input);
					if (base < 2 || base > 16) { // Check if user input for the target base follows the aforementioned
													// parameters
						System.out.println("The base must be between 2 and 16 inclusive.");
					} else { // Convert the user number into its equivalent form in the target base.
						String result = baseConv(number, base);
						System.out.println(number + " expressed in base " + base + " is " + result);
					}
				}
			}

		}
		sc.close();
		//Program termination
		System.out.println("Program terminated.");
	}

//Converts the user's input from a String to an integer.
	private static int String2Int(String input) {
		int total = 0;
		int counter = input.length() - 1;
		for (int i = 0; i <= input.length() - 1; i++) {
			char temp = input.charAt(i);
			int local = Character.getNumericValue(temp);
			int power = 1;
			for (int j = counter; j > 0; j--) {
				power = power * 10;
			}
			local = local * power;
			counter--;
			total += local;
		}
		return total;
	}

//Converts the corresponding integer value of the user input into its value represented in the target base
	private static String baseConv(int number, int base) {
		String values[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };

		String converted;
		converted = "";
		int digit;
		boolean tf = true;
		while (tf) {
			digit = number % base;
			number = number / base;
			String temp = values[digit];
			converted = temp + converted;
			if (number == 0) {
				tf = !tf;
			}
		}
		return converted;
	}
}