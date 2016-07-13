package Assignment4;

import java.util.Scanner;

/**
 * This program determibes the cost of converting string X to a string Y
 * @author Siddharth Tarey(st2476@rit.edu)
 * @author Pavan Bhat(pxb8715@rit.edu)
 *
 */
public class StringConvert {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		// takes the input from the user
		String f = scan.nextLine();
		String s = scan.nextLine();
		char[] second = f.toCharArray();
		char[] first = s.toCharArray();
		// costs defined
		int removecost = 3;
		int addcost = 4;
		int replacecost = 5;
		StringConvert object = new StringConvert();
		int S[][] = new int[first.length+1][second.length+1];

		// base case along the row of the matrix
		for (int i = 0; i < S[0].length; i++) {

			S[0][i] = removecost * i;

		}
		// base case along the coulm of the matrix
		for (int i = 0; i < S.length; i++) {

			S[i][0] = addcost * i;

		}

		for (int j = 0; j < S.length; j++) {
			for (int i = 0; i < S[j].length; i++) {

				// this condition executes for matrix elements other than the base case
				if (i != 0 && j != 0) {
					// if the letters are same, the diagonally below values are copied
					if(second[i-1] == first[j-1]){
						
						S[j][i] = S[j-1][i-1];
					}
					// else the costs are compared with different elemets of the matrix
					else{
					S[j][i] = object.min(S, j, i, removecost, addcost, replacecost);
					}
				}

			}

		}
		
		System.out.println(S[S.length-1][S[0].length-1]);
	}

	/**
	 * This method returns the minimum value between S[j-1][i], S[j][i-1] and S[j-1][i-2] 
	 * @param S the dynamic proogram matrix
	 * @param i row of the matrix element under consideration 
	 * @param j column of the matrix elemnt under consideration
	 * @param removecost
	 * @param addcost
	 * @param replacecost
	 * @return
	 */
	public int min(int[][] S, int i, int j, int removecost, int addcost, int replacecost) {
		int min = 0;
		// executes if the value of j-2 >= 0 soas to compare S[i-1][j-2] with other values 
		if (j - 2 >= 0) {
			// compares vallues and sends the minimum value
			if ((S[i - 1][j - 2] + replacecost) <= (S[i - 1][j] + addcost)) {

				if ((S[i - 1][j - 2] + replacecost) <= (S[i][j - 1] + removecost)) {

					min = (S[i - 1][j - 2] + replacecost);
				} else {

					min = (S[i][j - 1] + removecost);
				}
			}

			else if ((S[i - 1][j] + addcost) <= (S[i][j - 1] + removecost)) {
				min = (S[i - 1][j] + addcost);
			} else {

				min = (S[i][j - 1] + removecost);
			}
		} 
		// this executes if the value of j-2 is not >=0
		else {

			if ((S[i - 1][j] + addcost) <= (S[i][j - 1] + removecost)) {
				min = (S[i - 1][j] + addcost);
			} else {

				min = (S[i][j - 1] + removecost);
			}

		}

		return min;
	}
}
