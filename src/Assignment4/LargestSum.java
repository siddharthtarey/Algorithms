package Assignment4;
import java.util.Scanner;
/**
 * this program finds the largest possible sum of elements in an increasing subsequence of a1, a2, . . . , an
 * @author Siddharth Tarey(st2476@rit.edu)
 * @author Pavan Bhat(pxb8715@rit.edu)
 *
 */
public class LargestSum {
	int[] a;
	int[] S;
	int[] actualSequence;
	int n = 0;

	public static void main(String[] args) {
		LargestSum largestSum = new LargestSum();
		largestSum.getInput();
		int result = largestSum.calculateMaxSum();
		System.out.println(result);
	}

	/**
	 *  this function returns the maximum sum in a subsequence
	 * @return
	 */
	private int calculateMaxSum() {
		// scan from the first element in the array
		for (int i = 1; i < n; i++) {
			// scan the elemt with every other elemnt in the array
			for (int j = 0; j < i; j++) {
				// if the order is increasing this if condition exeutes
				if (a[i] > a[j]) {
					S[i] = maximum(S[i], S[j] + a[i]);
					actualSequence[i] = j;
				}
			}
		}
		//returns the maximum in S 
		return maxSum(S);
	}

	/**
	 * this function finds the greater between the two number
	 * @param i
	 * @param j
	 * @return
	 */
	private int maximum(int i, int j) {
		if (i > j) {
			return i;
		} else {
			return j;
		}
	}

	/**
	 * this function finds the maximum number in an array.
	 * @param s2
	 * @return
	 */
	private int maxSum(int[] s2) {
		int max = S[0];
		for (int i = 1; i < S.length; i++) {
			if (max < S[i]) {
				max = S[i];
			}
		}
		return max;
	}
/**
 * this function is used to get input from the user.
 */
	private void getInput() {
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		a = new int[n];
		S = new int[n];
		actualSequence = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = input.nextInt();
			actualSequence[i] = i;
		}
		for (int j = 0; j < n; j++) {
			S[j] = a[j];
		}
		input.close();
	}
}
