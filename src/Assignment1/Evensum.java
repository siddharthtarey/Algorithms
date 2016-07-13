package Assignment1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Evensum {

	public static void main(String[] args) {
		
		int sum = 0 ;
		ArrayList<Integer> list = new ArrayList<Integer>();
		Scanner scan = new Scanner(System.in);
		int count = scan.nextInt();
		while(count >0){
	
			int number = scan.nextInt();
			if(number >= 0){
			list.add(number);
			count--;
			}
			
		}
		
		for(int i : list){
			
			if( i%2 == 0){
			sum = sum + i;
			}
		}
		
		System.out.println(sum);
	}
}
