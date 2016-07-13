package Assignment4;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This program determines the maximum possible value that can be obtained from
 * the expression by fully parenthesizing it using Dynamic programming. The only operators used are '+' and '-'
 * 
 * @author Siddharth Tarey(st2476@rit.edu)
 *@author  Pavan Bhat(pxb8715@rit.edu)
 */
public class ParenthesesDP {
	ArrayList<Integer> num = new ArrayList<Integer>();
	ArrayList<Character> operator = new ArrayList<Character>();
	int[][] maxArray;
	int[][] minArray;
	int[] minMax = new int[2];

	public static void main(String[] args) {
		ParenthesesDP par = new ParenthesesDP();
		par.getInput();
		int result = par.bracketsDP();
		System.out.println(result);
	}

	/**
	 *  this function determies the maximum output from a given equation
	 * @return
	 */
	private int bracketsDP() {
		// initializes the diagonal elemts of the matrix..
		for (int i = 1; i < num.size(); i++) {
			maxArray[i][i] = num.get(i);
			minArray[i][i] = num.get(i);
		}

		// solves for the elemnts above the diagonal, the elemnts below the diagonal are ignored
		for (int s = 1; s < num.size() - 1; s++) {
			for (int i = 1; i < (num.size() - s); i++) {
				int j = i + s;
				int[] temp = maxInput(i, j);
				maxArray[i][j] = temp[1];
				minArray[i][j] = temp[0];
			}
		}
		return maxArray[1][num.size() - 1];

	}
/**
 *  Compares various cases for gaining the maximum value at that element in the mmatrix
 * @param i
 * @param j
 * @return
 */
	private int[] maxInput(int i, int j) {
		double max = Double.NEGATIVE_INFINITY, min = Double.POSITIVE_INFINITY;
		double caseA = 0, caseB = 0, caseC = 0, caseD = 0;
		for (int k = i; k <= j - 1; k++) {
			if (operator.get(k).equals('+')) {
				caseA = maxArray[i][k] + maxArray[k + 1][j];
				caseB = maxArray[i][k] + minArray[k + 1][j];
				caseC = minArray[i][k] + maxArray[k + 1][j];
				caseD = minArray[i][k] + minArray[k + 1][j];
				min = minimum(min, caseA, caseB, caseC, caseD);
				max = maximum(max, caseA, caseB, caseC, caseD);

			} else {
				caseA = maxArray[i][k] - maxArray[k + 1][j];
				caseB = maxArray[i][k] - minArray[k + 1][j];
				caseC = minArray[i][k] - maxArray[k + 1][j];
				caseD = minArray[i][k] - minArray[k + 1][j];
				min = minimum(min, caseA, caseB, caseC, caseD);
				max = maximum(max, caseA, caseB, caseC, caseD);
			}
		}
		minMax[0] = (int) min;
		minMax[1] = (int) max;
		return minMax;
	}

	/**
	 * determibes the minimum value between the four double vaues in the parameters 
	 * @param min
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @return
	 */
	private double minimum(double min, double a, double b, double c, double d) {
		if (a <= b && a <= c && a <= d && a <= min) {
			return a;
		} else if (b <= c && b <= d && b <= a && b <= min) {
			return b;
		} else if (c <= d && c <= b && c <= a && c <= min) {
			return c;
		} else if (d <= c && d <= b && d <= a && d <= min) {
			return d;
		} else {
			return min;
		}
	}

	/**
	 * determines the largest value between the values mentioned in the arguments
	 * @param max
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @return
	 */
	private double maximum(double max, double a, double b, double c, double d) {
		if (a >= b && a >= c && a >= d && a >= max) {
			return a;
		} else if (b >= c && b >= d && b >= a && b >= max) {
			return b;
		} else if (c >= d && c >= b && c >= a && c >= max) {
			return c;
		} else if (d >= max && d >= b && d >= a && d >= c) {
			return d;
		} else {
			return max;
		}
	}
/**
 * gets the input from the user.
 */
	private void getInput() {
		Scanner input = new Scanner(System.in);
		String temp = input.nextLine();
		String[] temp2 = temp.split(" ");
		num.add(0);
		operator.add('|');
		for (int i = 0; i < temp2.length; i++) {
			if (Character.isDigit(temp2[i].charAt(0))) {
				num.add(Integer.parseInt(temp2[i]));
			} else {
				operator.add(temp2[i].charAt(0));
			}
		}
		maxArray = new int[num.size()][num.size()];
		minArray = new int[num.size()][num.size()];
		input.close();

	}

}
