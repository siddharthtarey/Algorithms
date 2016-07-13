package Assignment6;

/**
 * This program counts the number of edges which are not used in any of the shortest path of the graph
 * @author Siddharth Tarey(st2476@rit.edu)
 * @author Pavan Prabhakar Bhat(pxb8715@rit.edu) 
 * 
 */
import java.util.Scanner;

public class CountUseless {
	double[][][] S;
	int test = 0;

	CountUseless() {
	}

	CountUseless(int vertex) {

		// initialize the dynamic array.
		this.S = new double[vertex][vertex][vertex + 1];

	}

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int vertex = scan.nextInt();
		int edges = scan.nextInt();
		int count = 0;

		CountUseless c = new CountUseless(vertex);

		// initialize the diagonal elements to 0 and non diagonal elements to
		// 99999 in every plane.
		for (int k = 0; k < vertex; k++) {
			for (int i = 0; i < vertex; i++) {
				for (int j = 0; j < vertex; j++) {

					if (i == j) {
						c.S[i][j][k] = 0;
					} else {
						c.S[i][j][k] = 99999;
					}
				}
			}
		}
		// fill the data into the matrix
		for (int i = 0; i < edges; i++) {

			int a = scan.nextInt();
			int b = scan.nextInt();
			double data = scan.nextDouble();
			// ignores the self edges, and the useless edge count is incresed to
			// 1
			if (a == b) {
				c.S[a][b][0] = 0;
				count++;
			} else {
				c.S[a][b][0] = data;
			}

		}

		// solve the dynamic matrix
		c.findmin(vertex);

		for (int i = 0; i < vertex; i++) {
			for (int j = 0; j < vertex; j++) {
				// considers only the edges that are present in the graph
				if (c.S[i][j][0] != (double) 99999) {
					// if the edges in the orignal graph are not used in the
					// final solution, then the edge is useless
					if (c.S[i][j][vertex] != c.S[i][j][0])
						count++;
				}
			}
		}
		// print the result
		System.out.println(count);

	}

	/**
	 * This function solves the dynamic array for floyd warshall
	 * 
	 * @param vertex
	 */
	public void findmin(int vertex) {
		for (int k = 1; k < vertex + 1; k++) {

			for (int i = 0; i < vertex; i++) {

				for (int j = 0; j < vertex; j++) {
					// this is the recursive equation for floyd warshall
					if (this.S[i][j][k - 1] > (this.S[i][k - 1][k - 1] + this.S[k - 1][j][k - 1])) {
						this.S[i][j][k] = (this.S[i][k - 1][k - 1] + this.S[k - 1][j][k - 1]);
					} else {
						this.S[i][j][k] = this.S[i][j][k - 1];
					}

				}
			}
		}

	}

}
