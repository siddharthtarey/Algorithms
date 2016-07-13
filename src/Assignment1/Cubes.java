package Assignment1;

import java.util.Scanner;

public class Cubes {

	public static void main(String[] args) {
		int input,result = 0,calc = 0;
		
			System.out.println("Enter a number between 1 and 1,000,000");
			Scanner s = new Scanner(System.in);
			input = s.nextInt();

			if (input >= 1 && input <= 1000000) {
				while((result = calc*calc*calc) <= input){
					
					calc++;
					System.out.println(result);
				}
			
			}
		
		
			s.close();
	}
}
