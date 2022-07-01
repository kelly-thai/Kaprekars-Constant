import java.util.Scanner;
/*
 *  KAPREKAR'S CONSTANT
 *  A program that explores kaprekar's constant (6174)
 *  
 *  1) Start with a random four digit number
 *    - At least two of the digits must be different (1110 is okay) - initial
 *  2) Arrange the digits in descending order
 *  3) Subtract the above number in reverse
 *  4) Repeat with the new number
 *  5) If you do this long enough, you will hit 6174
 */

public class kaprekar {

	//global variables
	static int count = 0;
	static boolean print = false;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to Kaprekar's Constant Simulator!");
		
		System.out.println();
		System.out.println("This program will count how many times it takes to reach 6174.");
		System.out.println();
		System.out.println("Would you like to print out the process?");
		System.out.print("Yes/No: ");
		//Verify user input
		String printInput = sc.nextLine();
		while (!printInput.equalsIgnoreCase("Yes") && !printInput.equalsIgnoreCase("No")) {
			//if it makes it into this loop the input is INCORRECT
			System.out.println("Please respond with either \"Yes\" or \"No\"");
			System.out.print("Would you like to print out the process? ");
			printInput = sc.nextLine();
		}

		//update global
		if (printInput.equalsIgnoreCase("Yes")) {
			print = true;
			System.out.println("Okay! The process will be printed out for you.");
		} else {
			print = false;
			System.out.println("Got it, the process will not be printed.");
		}
		System.out.println();
		
		String input = "yes";
		while (input.equalsIgnoreCase("yes")) {
			count = 0;
			intro(sc);
			System.out.println();
			System.out.print("Would you like to run the simulation again? ");
			input = sc.next();
			System.out.println();
		}
		sc.close();
	}


	private static void intro(Scanner sc) {
		
		System.out.println("Please enter a four-digit number.");
		System.out.println("NOTE: no more than 3 of the digits may be the same.");
		System.out.print("Starting Number: ");
		//TODO: verify user input
		//TODO: check to see if it is even an int in the first place
		//for now, just assuming it is
		int input = sc.nextInt(); //for time's sake i'm gonna assume this is valid input
		System.out.println();
		calculate(input);
		System.out.println();
		System.out.println("It took "+count+" times to reach Kaprekar's constant.");
	}

	//recursive?
	private static void calculate(int input) {
		if (input == 6174) {
			return;
		}

		count++;
		if (print) {
			System.out.println("CALCULATION #"+ count + ": input = " + input);
		}
		int sortedNumber = 0;
		//sort the number
		//ngl got this from stack overflow
		for (int i = 9; i >= 0; i--) {
			int tmpNumber = input;
			while (tmpNumber > 0) {
				int digit = tmpNumber % 10;
				// Check for the greatest digit in the given number
				if (digit == i) {
					sortedNumber *= 10;
					sortedNumber += digit;
				}
				tmpNumber /= 10;
			}
		}
		//here sortedNumber is good
		//reverse it
		int reversed = 0;
		int num = sortedNumber;
		while(num != 0) {
			// get last digit from num
			int digit = num % 10;
			reversed = reversed * 10 + digit;
			// remove the last digit from num
			num /= 10;
		}
		int result = sortedNumber - reversed;
		if (print) {
			System.out.println(sortedNumber +" - " + reversed + " = " + result);
		}
		calculate(result);
	}

}

