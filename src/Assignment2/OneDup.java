package Assignment2;

/**
 * This program finds repeated elements in a sorted array
 * @author Siddharth Tarey(st2476@rit.edu)
 * @author Pavan Bhat(pxb8715@rit.edu)
 * 
 */

import java.util.Scanner;

public class OneDup {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		// takes the size of the array
		int size = scan.nextInt();
		int[] series = new int[size + 2];
		int low = 0;
		int high = size - 1;

		// takes input from the user
		for (int i = 0; i <= size + 1; i++) {

			series[i] = scan.nextInt();
		}
		/*
		 * This uses binary search algorithm to search the sorted for duplicate
		 * elements.
		 */
		while (high >= low) {

			int middle = (high + low) / 2;
			/*
			 * compares the index with the data, if the index is not equal to the
			 * data, there has to be a repeated number on the left side of the
			 * array.
			 * 
			 * if the number and index match, it moves to the right to check for mismatches
			 */
			if (series[middle] == middle) {

				low = middle + 1;
			} else {
				high = middle - 1;
			}
		}
		//prints the repeated elements
		System.out.println(series[high]);
		scan.close();
	}

}
