package Assignment2;

import java.util.Scanner;
import java.util.Stack;

public class Match {
	boolean match1[][];
	boolean match2[][];
	static boolean engaged1[];
	static boolean engaged2[];
	int count[];
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String row;
		String number = scan.nextLine();
		int numberOfLines = Integer.parseInt(number);
		int preference1[][] = new int[numberOfLines][numberOfLines];
		int preference2[][] = new int[numberOfLines][numberOfLines];
		Match m1 = new Match();
		
		m1.match1 = new boolean[numberOfLines][numberOfLines];
		m1.match2 = new boolean[numberOfLines][numberOfLines];
		m1.engaged1 = new boolean[numberOfLines];
		m1.engaged2 = new boolean[numberOfLines];
		m1.count = new int[numberOfLines];
		
		for (int j = 0; j < numberOfLines; j++) {

			row = scan.nextLine();
			int[] temp = m1.convert(row);
			preference1[j] = temp;

		}

		for (int j = 0; j < numberOfLines; j++) {

			row = scan.nextLine();
			int[] temp = m1.convert(row);
			preference2[j] = temp;

		}
		scan.close();
		
		
		
	}

	public int[] convert(String row) {

		String[] data = row.split(" ");

		int[] intdata = new int[data.length];

		for (int i = 0; i < data.length; i++) {

			intdata[i] = Integer.parseInt(data[i]);
		}

		return intdata;
	}
	
	public void firstmatch(int[][] preference1 ,int [][]preference2, Match m){
		
		Stack<Integer> stack = new Stack<Integer>();
		
		for(int i = preference1[0].length -1 ; i >=0  ; i--){
			
			stack.push(i);
		}
		
		while(!stack.isEmpty()){
			
			int man = stack.pop();
			
			
			if(engaged1[preference1[man][count[man]]] == false){
				
				m.match1[man][preference1[man][count[man]]] = true;
				count[man]++;
				}
			
			else{
				
				int[] reversewoman = m.reverseprefrence(preference2[preference1[man][count[man]]]);
//				Edit required here
				if(reversewoman[0] > preference2[man][preference1[man][count[man]]] ){
					engaged2[reversewoman[man]] = true;
				}
			}
		}
		
	}
	
	public int[] reverseprefrence(int[] preference){
		
		int[] modifiedpreference = new int[preference.length];
		
		for (int i = 0; i < preference.length; i++) {
			
			modifiedpreference[preference[i]] = i;
		}
		
		return modifiedpreference;
	}
	
	public int getrow(int[] match){
		
		
		return 0;
	} 
}