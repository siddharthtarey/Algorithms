package Assignment3;
/**
 * Foundation of Algorithms, Homework 3, Question 4
 * 
 *   @author Siddharth Tarey(st2476@rit.edu)
 *   @author Pavan Bhat (pxb8715@rit.edu)
 * 
 */

import java.util.Scanner;

public class MinimumGap {
	double startime;
	double endtime;

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		int no = scan.nextInt();
		// input for start and finish time.
		double start = scan.nextDouble();
		double finish = scan.nextDouble();
		MinimumGap[] min = new MinimumGap[no];

		// Input for gathering data from the user.
		for (int i = 0; i < no; i++) {

			MinimumGap data = new MinimumGap();
			data.startime = scan.nextDouble();
			data.endtime = scan.nextDouble();
			min[i] = data;

		}

		double gap = mingap(min);
		// calculate the minimum gap in the interval
		gap = (finish-start) - gap;
		System.out.println((int)gap);
	}

	/**
	 * Selects the pair of non-overlapping intervals that have the smallest gap between them.
	 * @param min: the intervals that have been provided by the user
	 * @return the maximum time that is calculated
	 */
	public static double mingap(MinimumGap[] min) {

		double[] s = new double[min.length + 1];
		s[0] = 0;

		MinimumGap[] sorted = sort(min);
		
		// this loop is to start filling the dynamic array S
		for (int i = 1; i <= sorted.length; i++) {

			double weight = sorted[i - 1].endtime - sorted[i - 1].startime;
			int p = 0;
			// checks for the non overlapping interval. 
			for (int j = i-1; j >= 0; j--) {
				
				if(sorted[i-1].startime >= sorted[j].endtime){
					
					p = j+1;
					
					break;
				}
			
			}
			// recurrance relation, to fill the max length of interval in the dynamic array..
			s[i] = max(s[i-1],weight+s[p]);
			
		}
		// returns the maximum length of interval from the dynamic array S.
		return (s[s.length-1]);
	}
/**
 * Sorts the interval array, given by the user.
 * Sorts the array in ascending order of the start time. 
 * @param min: array given as input by the user
 * @return: the sorted array
 */
	public static MinimumGap[] sort(MinimumGap[] min) {

		// use bubble sort for sortig the array
		for (int i = 0; i < min.length; i++) {

			for (int j = i + 1; j < min.length; j++) {

				if (min[i].endtime >= min[j].endtime) {

					MinimumGap temp;
					temp = min[i];
					min[i] = min[j];
					min[j] = temp;
				}
			}
		}

		return min;
	}
	/**
	 * Return the maximum number between the two double values
	 * @param s: first double value
	 * @param weight: second double value
	 * @return: the geater double value between 's' and 'weight' 
	 */
	public static double max(double s, double weight){
		
		
		if(s>weight){
			return s;
		}
		else{
			
			return weight;
		}
		
	}
}
