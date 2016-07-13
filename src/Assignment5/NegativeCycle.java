package Assignment5;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This program Detects Negative cycle in the graph
 * @author Siddharth Tarey(st2476@rit.edu)
 * @author Pavan Bhat(pxb8715@rit.edu)
 *
 */
public class NegativeCycle {
	// adjacency matrix for graph
	int[][] matrix;
	// start node of negative edge
	int negative;
	// end node for the negative edge
	int nextnegative;
	// weight of the negative edge
	int nweight;
	// distance array
	int[] dist;
	// list of vertices in the graph
	boolean[] vertices;

	public NegativeCycle() {

	}

	NegativeCycle(int vertex) {
		matrix = new int[vertex][vertex];
		dist = new int[vertex];
		vertices = new boolean[vertex];
		for (int i = 0; i < vertex; i++) {
			vertices[i] = false;
			dist[i] = 999999;
		}
		
		for (int i = 0; i < vertex; i++) {
			for (int j = 0; j < vertex; j++) {
				
				matrix[i][j] = 999999;
			}
		}
		
	}

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		// sacn the input
		int vertex = scan.nextInt();
		int edges = scan.nextInt();
		NegativeCycle n = new NegativeCycle(vertex);
		for (int i = 0; i < edges; i++) {

			int node = scan.nextInt();
			int nextnode = scan.nextInt();
			int weight = scan.nextInt();

			n.matrix[node][nextnode] = weight;
			if (weight < 0) {

				n.negative = node;
				n.nextnegative = nextnode;
				n.nweight = weight;
			}

		}
		
		int tcost =0;
		// initiate the distance of the end node of the negative edge
		n.dist[n.nextnegative] = 0;
		// update the neighbours of the end node
		n.update(n.nextnegative); 
		// remove the end node from the vertex list
		n.vertices[n.nextnegative] = true;
		
		for (int i = 0; i < n.vertices.length; i++) 
		{
			// extract the minimum cost node
			int minnode = n.extractmin();
			// update the neighbours of the minnode
			n.update(minnode);
			// update the total cost
			tcost = n.dist[minnode];
			// remove the minode from the vertex list
			n.vertices[minnode]= true;
			// break if you reach the start node of the negative edge
			if(minnode == n.negative){
				
				break;
			}
		}
		// calculate the cycle cost and print yes if negative value found
		if((tcost+n.nweight) <0){	
			System.out.println("YES");
		
		}
		else{
			System.out.println("NO");
		}
	}
	/**
	 * Update the neighboring nodes of the specified vertex in the argument
	 * @param node: the vertex whose neighbors have to be updated
	 */
	public void update(int node){
		for(int i = 0 ; i<this.vertices.length;i++){
			
			if(this.dist[i] > (this.dist[node] + this.matrix[node][i]) && this.vertices[i] == false ){
				
				dist[i] = this.dist[node] + this.matrix[node][i];
				
			}
		}
		
	}
	/**
	 * extracts the minimum cost node which has not been visited yet.
	 * @return vertex of the graph
	 */
	public int extractmin() {
		int min = 999999;
		int minnode = 0;

		for (int i = 0; i < this.dist.length; i++) {

			if (this.dist[i] < min && this.vertices[i] == false) {

				min = this.dist[i];
				minnode = i;

			}
		}
		
		return minnode;
	}
}