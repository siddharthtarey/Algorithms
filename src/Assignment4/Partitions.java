package Assignment4;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author Siddharth Tarey(st2476@rit.edu)
 * @author Pavan Bhat (pxb8715@rit.edu)
 *
 */
public class Partitions {
	// total size of the input.
	int total = 0;
	// data contained in the array list
	ArrayList<Integer> num = new ArrayList<Integer>();
	int []S1;
	int []S;
	
	public static void main(String[] args) {
		Partitions part = new Partitions();
		part.getInput();
		int result = part.getPartitions();
		int result2 = part.getPartitionsDP();
		System.out.println((int)Math.pow(2,(result - 1)));
		System.out.println(result2);
	}
	/**
	 * this function partition a sequence into consecutive subsequences such that the sum of each subsequence is odd
	 * this function solves the above problem in O(n) time complexity
	 * @return returns the number of partitions that can be made.
	 */
	public int getPartitionsDP() {
		int pointer = 0;
		// traverses through the input arraylist
		for(int i = 0; i < total; i++){
			
			// copies the previous elemsnt if number is even
			if(num.get(i)%2 == 0){
				S1[i+1] = S1[i];
			}
			// for the first odd element encountered increments S1 at that poistion to 1
			else if(num.get(i)%2 == 1 && S1[i] == 0){
				S1[i+1] += 1;
				pointer = i+1;
			}
			// for more odd values in the list, the values in S1 are added till the previous odd number.
			else if(i >= 1 && num.get(i)%2 == 1 && pointer >= 1){
				S1[i+1] = (i-pointer) * S1[i]+S1[pointer-1]+ S1[pointer];
				pointer = i+1;
			}
			
		}
		
				
		return S1[total];
	}

	/**
	 * this function partition a sequence into consecutive subsequences such that the sum of each subsequence is odd
	 * this function solves the above problem in O(n^2) time complexity
	 * @return returns the number of partitions that can be made.
	 */
	private int getPartitionsDP2() {
		int pointer = 0;
		for(int i = 0; i < total; i++){
			//copies the previous elemsnt if number is even
			if(num.get(i)%2 == 0){
				S1[i+1] = S1[i];
			}
			// for the first odd element encountered increments S1 at that poistion to 1
			else if(num.get(i)%2 == 1 && S1[i] == 0){
				S1[i+1] += 1;
				pointer = i+1;
			}
			// for more odd values in the list, the values in S1 are added till the previous odd number.
			else if(i >= 1 && num.get(i)%2 == 1 && pointer >= 1){
				for(int j = i+1; j >= pointer-1; j--){
					S1[i+1] +=S1[j];
				}
				pointer = i+1;
			}
			
			
		}
		
		return S1[total];
	}
	
	/**
	 * this function partitions a subsequence into non-empty consecutive subsequences such that the sum of each subsequence is even
	 * this uses a greedy approach to solve the problem
	 * @return the number of even partitions in one iteration
	 */
	private int getPartitions() {
		int oddCount = 0;
		int totalCount = 0;
		boolean flag = false;
		for(int i = 0; i < num.size(); i++){
			// increases he totalCount if only even number is encountered 
			if(num.get(i)%2 == 0 && flag == false){
				totalCount += 1;
			}
			// case for odd numbers
			else if(num.get(i)%2 == 1){
				oddCount += 1;
				flag = true;
				// increases the totalCount if 2 odd values are encountered since odd+odd = even
				if(oddCount ==2){
					totalCount += 1;
					oddCount = 0;
					flag = false;
				}
			}
			
		}
		return totalCount;
	}

	/**
	 * this function gets the input from the user.
	 */
	private void getInput() {
		Scanner input = new Scanner(System.in);
		total = input.nextInt();
		for(int i = 0; i <  total; i++){
			num.add(input.nextInt());
		}
		S = new int[total];
		S1 = new int[total+1];
		S1[0] = 0;
		input.close();
	}
}
