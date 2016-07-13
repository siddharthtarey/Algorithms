
/**

 * Homework 2-5: 
 * Given an array of integers A[0..n-1]. 
 * Case a: The task is to determine whether there exists an integer x that occurs in A more than n/2 times 
 * (i.e., whether A has a majority element). 
 * 
 * Case b: The task is to determine whether there exists an integer x that occurs in A more than n/3 times.
 * Design an algorithm that runs in O(n) time for the above 2 cases.
 *  
 *   
 * @author Pavan Prabhakar Bhat (pxb8715@rit.edu)
 * @author Siddharth Tarey (st2476@rit.edu)
 */

// All imports here
import java.util.Scanner;
import java.util.*;
public class Majority {
	// Stores the given input of integers A[0..n-1]
	int[] numbers;
	int j1 = 0, j2 = 0;
	/**
	 * The main program required to find whether a given array has the majority
	 * element.
	 * 
	 * @param args
	 *            Unused
	 */
	public static void main(String[] args) {
		// Object of the given class
		Majority majority = new Majority();

		Scanner input = new Scanner(System.in);
		// numberOfElements stores the number of elements in a given array.
		int numberOfElements = input.nextInt();
		majority.numbers = new int[numberOfElements];
		majority.getInput(numberOfElements, majority, input);

		String highestNumber = majority.findHighestNumber(majority.numbers, numberOfElements);
		String highestNumber2 = majority.findHighestNumber2(majority.numbers, numberOfElements);
		System.out.println(highestNumber);
		System.out.println(highestNumber2);
//		majority.hasMajorityElement(majority, highestNumber, numberOfElements);
//		if(highestNumber.equals("No")){
////			System.out.println("hello");
//		}

	}

	/**
	 * Finds the highest number in a given array in O(n) time.
	 * 
	 * @param numbers
	 *            Input array in which the highest element needs to be found.
	 * @param numberOfElements
	 *            The number of elements in the given array.
	 * @return Returns an integer value that is the highest number in the array.
	 */
	private String findHighestNumber(int[] numbers, int numberOfElements) {
		String n = new String("NO");
//		System.out.println(j2 +" "+j1);
		if((this.j2 - this.j1 + 1) > numberOfElements/2){
			n = "YES";
		}
		else{
		Random random = new Random();
		int arrow = random.nextInt(numberOfElements);
		int []temp = new int[numberOfElements];
		
		int leftCount = 0, rightCount = numberOfElements-1;
		for(int i = 0; i < numberOfElements; i++){
			if(numbers[i]<arrow){
				temp[leftCount] = numbers[i];
				leftCount++;
			}
			else if(numbers[i] > arrow){
				temp[rightCount] = numbers[i];
				rightCount--;
			}
		}
		
		this.j1 = leftCount;
		this.j2 = rightCount;
		
		
		
		if((numberOfElements - this.j1) > numberOfElements/2){
			
			int []temp2 = new int[(numberOfElements - this.j1)];
			for(int i = 0; i < this.j1; i++){
				temp2[i] = temp[i];
			}
			findHighestNumber(temp2, this.j1+1);
		}
		else if(this.j2 < numberOfElements/2){
			
			int []temp2 = new int[this.j2];
			for(int i = 0; i < temp2.length; i++){
				temp2[i] = temp[this.j2+i];
			}
			findHighestNumber(temp2, this.j1+1);
		}
		
		}
		
		return n;
	}
	
	
	private String findHighestNumber2(int[] numbers, int numberOfElements) {
		String n = new String("NO");
//		System.out.println(j2 +" "+j1);
		if((this.j2 - this.j1 + 1) > numberOfElements/3){
			n = "YES";
		}
		else{
		Random random = new Random();
		int arrow = random.nextInt(numberOfElements);
		int []temp = new int[numberOfElements];
		
		int leftCount = 0, rightCount = numberOfElements-1;
		for(int i = 0; i < numberOfElements; i++){
			if(numbers[i]<arrow){
				temp[leftCount] = numbers[i];
				leftCount++;
			}
			else if(numbers[i] > arrow){
				temp[rightCount] = numbers[i];
				rightCount--;
			}
		}
		
		this.j1 = leftCount;
		this.j2 = rightCount;
		
		
		if((numberOfElements - this.j1) > numberOfElements/3){
			
			int []temp2 = new int[(numberOfElements - this.j1)];
			for(int i = 0; i < this.j1; i++){
				temp2[i] = temp[i];
			}
			findHighestNumber(temp2, this.j1+1);
		}
		else if(this.j2 < numberOfElements/3){
			
			int []temp2 = new int[this.j2];
			for(int i = 0; i < temp2.length; i++){
				temp2[i] = temp[this.j2+i];
			}
			findHighestNumber(temp2, this.j1+1);
		}
		
		}
		
		return n;
	}

	/**
	 * Finds whether a given array has a majority element in it or not.
	 * 
	 * @param majority
	 *            Object of class
	 * @param highestNumber
	 *            Highest number in the input array
	 * @param numberOfElements
	 *            The number of elements in the given array.
	 */
//	private void hasMajorityElement(Majority majority, int highestNumber, int numberOfElements) {
//		int[] tempNumbers = new int[highestNumber + 1];
//		// Creating a temporary array to hold the count of the occurrence of
//		// each element
//		for (int i = 0; i < majority.numbers.length; i++) {
//			tempNumbers[majority.numbers[i]]++;
//		}
//		// finalAns contains the count of the number that has occurred maximum
//		// number of times in the array.
//		double finalAns = majority.findHighestNumber(tempNumbers, tempNumbers.length);
//		// Checks for case a in the question
//		if (finalAns > (double) (numberOfElements / 2)) {
//			System.out.println("YES");
//		} else {
//			System.out.println("NO");
//		}
//		// Checks for case b in the question
//		if (finalAns > (double) (numberOfElements / 3)) {
//			System.out.println("YES");
//		} else {
//			System.out.println("NO");
//		}
//
//	}

	/**
	 * Gets the input array from the user
	 * 
	 * @param numberOfElements
	 *            The number of elements in the given array.
	 * @param majority
	 *            Object of class
	 * @param input
	 *            Scanner object
	 */
	public void getInput(int numberOfElements, Majority majority, Scanner input) {
		for (int i = 0; i < numbers.length; i++) {
			majority.numbers[i] = input.nextInt();
		}
		input.close();
	}
}
