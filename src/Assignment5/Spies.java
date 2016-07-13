package Assignment5;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * This program forms a connections between reliable and unreliable spies
 * @author Siddharth Tarey(st2476@rit.edu)
 * @author Pavan Bhat(pxb8715@rit.edu)
 *
 */
public class Spies {
	// array of cost of going to each vertice
	int[] cost;
	// array of enreliable nodes
	int[] unreliable;
	// adjacency matrix of the graph
	int[][] reliable;
	// list of reliable and visited nodes
	ArrayList<Integer> visited;

	Spies(int vertex, int un) {

		unreliable = new int[un];
		this.cost = new int[vertex];
		// initialize the cost to infinity
		for (int i = 0; i < cost.length; i++) {
			this.cost[i] = 9999;
		}
		reliable = new int[vertex][vertex];
		visited = new ArrayList<Integer>();

		// populate all the vertices in the visited arraylist
		for (int i = 0; i < vertex; i++) {
			visited.add(i);
		}
		// initialize the adjacency matrix to infinity
		for (int i = 0; i < cost.length; i++) {
			for (int j = 0; j < cost.length; j++) {

				reliable[i][j] = 9999;

			}
		}

	}

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		int vertex = scan.nextInt();

		int edges = scan.nextInt();
		int unreliable = scan.nextInt();
		Spies s = new Spies(vertex, unreliable);
		// unreliable nodes
		for (int i = 0; i < unreliable; i++) {

			s.unreliable[i] = scan.nextInt();
		}
		// remove the unreliable nodes from the visited array list
		for (int i = 0; i < s.unreliable.length; i++) {

			s.visited.remove(new Integer(s.unreliable[i]));
		}

		for (int i = 0; i < edges; i++) {
			// take the inputs for the nodes and edges
			int node = scan.nextInt();
			int nextnode = scan.nextInt();
			int weight = scan.nextInt();
			// make the adjacency matrix a bidirectional graph
			if (s.reliable[node][nextnode] > weight) {
				s.reliable[node][nextnode] = weight;
			}
			if (s.reliable[nextnode][node] > weight) {
				s.reliable[nextnode][node] = weight;
			}
		}

		ArrayList<Integer> dummy = new ArrayList<Integer>();
		// create a dummy array list of visited node, whic has all the graph vertices 
		for (Integer I : s.visited) {

			dummy.add(I);
		}

		// process the first node of the graph
		int first = s.visited.get(0);
		// update the cost of the first node
		s.cost[first] = 0;
		// update the cost of the remaining nodes
		s.update(first);
		// remove the first node from the list
		s.visited.remove(new Integer(first));

		for (int i = 1; i < vertex; i++) {
			// extract the vertex with minimum cost from the graph
			int min = s.extractmin();
			// update the nodes that are connected to min
			s.update(min);
			// remove the min the vertex list
			s.visited.remove(new Integer(min));

		}
		// connect the unreliable spies to the MST
		s.updateunreliable(dummy);

		String result = s.print();
		System.out.println(result);
	}

	/**
	 * Returns the result that has to be printed
	 * @return result
	 */
	public String print() {
		String result = "";
		int sum = 0;
		for (int i = 0; i < cost.length; i++) {

			if (this.cost[i] == 9999) {
				result = "NONE";
				break;
			} else {

				sum += cost[i];
			}

		}

		if (!result.equals("NONE")) {

			result = Integer.toString(sum);
		}

		return result;
	}

	/**
	 * extracts the minimum cost node which has not been visited yet.
	 * @return vertex of the graph
	 */
	public int extractmin() {
		int min = 9999;
		int minnode = 0;
		// scan through the cost array to find the minimum cost node
		for (int i = 0; i < cost.length; i++) {
			// extract the node which is not yet vsited and has the minimum cost
			if (cost[i] < min && this.visited.contains(i)) {

				min = cost[i];
				minnode = i;

			}
		}

		return minnode;
	}

	/**
	 * Update the neighbouring nodes of the spcified vertex in the argument
	 * @param node: the vertex whose neighbours have to be updated
	 */
	public void update(int node) {
		int i;
		for (i = 0; i < this.visited.size(); i++) {

			// updates the minimum cost
			if (this.cost[this.visited.get(i)] > this.reliable[node][this.visited.get(i)]
					&& node != this.visited.get(i)) {

				this.cost[this.visited.get(i)] = this.reliable[node][this.visited.get(i)];
			}

		}
	}
/**
 * connects the unreliable nodes to the MST
 * @param dummy
 */
	public void updateunreliable(ArrayList<Integer> dummy) {

		for (Integer un : this.unreliable) {
			int min = 9999;
			// scans through all the vertices
			for (Integer d : dummy) {
				// uptades the least cost
				if (this.reliable[un][d] < min) {
					min = this.reliable[un][d];
				}
			}
			this.cost[un] = min;
		}

	}
}
