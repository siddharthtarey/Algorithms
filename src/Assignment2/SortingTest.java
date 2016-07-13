/**
 * 
 * @author Siddharth Tarey (st2476@rit.edu)
 * @author Pavan Bhat (pxb8715@rit.edu)
 * 
 * This program executes insertion, merge and bucket sort on the input size specified by the user.
 * The execution time is displlayed in the end.
 */
package Assignment2;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class SortingTest {

	double[] data;

	public static void main(String[] args) {
		SortingTest test = new SortingTest();
		Random rand = new Random();
		Scanner input = new Scanner(System.in);
		// if flag is true, uses the gaussian distribution else uses the uniform
		// distribution
		boolean gaussian = true;
		// The amount of data is taken from the user
		int arraySize = input.nextInt();
		input.close();
		test.data = new double[arraySize];
		// enters numbers of uniform distribution in the array.
		if (gaussian == false) {
			for (int i = 0; i < arraySize; i++) {
				test.data[i] = rand.nextDouble();
			}
		} else {

			for (int i = 0; i < arraySize; i++) {
				test.data[i] = 0.5 + rand.nextGaussian() * 0.001;
			}

		}
		// this section records the start time and end time of merge sort
		System.out.println();
		long startTime = System.currentTimeMillis();
		test.data = test.mergeSort(test.data);
		long endTIme = System.currentTimeMillis();
		long timeElapsed = endTIme - startTime;

		System.out.print("Merge Sort: ");
		// displays the time taken for merge sort
		System.out.println(timeElapsed);

		// enters numbers of uniform distribution in the array.
		if (gaussian == false) {
			for (int i = 0; i < arraySize; i++) {
				test.data[i] = rand.nextDouble();
			}
		} else {

			for (int i = 0; i < arraySize; i++) {
				test.data[i] = 0.5 + rand.nextGaussian() * 0.001;
			}

		}

		// this section records the start time and end time of insertion sort
		System.out.println();
		startTime = System.currentTimeMillis();
		test.data = test.insertionSort(test.data);
		endTIme = System.currentTimeMillis();
		timeElapsed = endTIme - startTime;

		System.out.print("Insertion Sort: ");
		// displays the time taken for insertion sort
		System.out.println(timeElapsed);
		System.out.println();

		// enters numbers of uniform distribution in the array.
		if (gaussian == false) {
			for (int i = 0; i < arraySize; i++) {
				test.data[i] = rand.nextDouble();
			}
		} else {

			for (int i = 0; i < arraySize; i++) {
				test.data[i] = 0.5 + rand.nextGaussian() * 0.001;
			}

		}

		// this section records the start time and end time of bucket sort
		startTime = System.currentTimeMillis();
		test.data = test.bucketSort(test.data);
		endTIme = System.currentTimeMillis();
		timeElapsed = endTIme - startTime;

		System.out.print("Bucket Sort: ");
		// displays the time taken for bucket sort
		System.out.println(timeElapsed);

	}

	/**
	 * This function executes bucket sort an array
	 * 
	 * @param data2:
	 *            the array that needs to be sorted
	 * @return returns sorted array back
	 */
	private double[] bucketSort(double[] data2) {
		// Create an arraylist of buckets.
		ArrayList<ArrayList<Double>> buckets = new ArrayList<ArrayList<Double>>();
		SortingTest test = new SortingTest();
		int index = 0;
		// add buckets to the above arraylist
		while (index < data2.length) {
			buckets.add(new ArrayList<Double>());
			index++;
		}
		// insert data into the buckets
		for (int i = 0; i < data2.length; i++) {
			int temp = (int) Math.floor(data2.length * data2[i]);

			buckets.get(temp).add(data2[i]);
		}

		// executes insertion sort on the buckets.
		for (int i = 0; i < buckets.size(); i++) {
			buckets.set(i, (test.insertionSort(buckets.get(i))));
		}

		int count = 0;
		// puts the sorted data into an array
		for (ArrayList<Double> bucket : buckets) {

			for (Double dat : bucket) {

				data2[count] = dat;
				count++;
			}
		}

		return data2;
	}

	/**
	 * This method executes insertion sort on the ArrayList
	 * 
	 * @param arrayList:
	 *            The arraylist that needs to be sorted
	 * @return
	 */
	private ArrayList<Double> insertionSort(ArrayList<Double> arrayList) {
		// TODO Auto-generated method stub
		int i, j;
		for (i = 0; i < arrayList.size(); i++) {
			double element = arrayList.get(i);
			j = i;
			while (j > 0 && (arrayList.get(j - 1) > element)) {
				arrayList.set(j, (arrayList.get(j - 1)));
				j = j - 1;
			}
			arrayList.set(j, element);
		}

		return arrayList;
	}

	/**
	 * This method performs insertion sort on primitive double array
	 * 
	 * @param data:
	 *            array that needs to be sorted
	 * @return
	 */
	private double[] insertionSort(double[] data) {
		// TODO Auto-generated method stub
		int i, j;
		for (i = 0; i < data.length; i++) {
			double element = data[i];
			j = i;
			while (j > 0 && (data[j - 1] > element)) {
				data[j] = data[j - 1];
				j = j - 1;
			}
			data[j] = element;
		}
		return data;
	}

	/**
	 * This function does merge sort on the input array
	 * 
	 * @param data2:
	 *            the array that needs to be sorted
	 * @return
	 */
	public double[] mergeSort(double[] data2) {
		double[] left;
		double[] right;
		if (data2.length == 1) {
			return data2;
		} else {
			int leftIndex = (int) Math.floor(data2.length / 2);
			left = new double[leftIndex];
			right = new double[data2.length - leftIndex];

			for (int i = 0; i < data2.length; i++) {
				if (i < leftIndex) {
					left[i] = data2[i];
				} else {
					right[i - leftIndex] = data2[i];
				}
			}
		}
		left = mergeSort(left);
		right = mergeSort(right);
		double[] result = Merge(left, right);
		return result;
	}

	/**
	 * This function splits
	 * 
	 * @param left
	 * @param right
	 * @return
	 */
	public double[] Merge(double[] left, double[] right) {
		// TODO Auto-generated method stub
		double[] temp = new double[left.length + right.length];
		int leftIndex = 0, rightIndex = 0;
		for (int i = 0; i < temp.length; i++) {
			if (leftIndex == left.length) {
				temp[i] = right[rightIndex];
				rightIndex++;
			} else if (rightIndex == right.length) {
				temp[i] = left[leftIndex];
				leftIndex++;
			} else if (left[leftIndex] < right[rightIndex]) {
				temp[i] = left[leftIndex];
				leftIndex++;
			} else {
				temp[i] = right[rightIndex];
				rightIndex++;
			}
		}
		return temp;
	}

}
