
/**
 * 
 * @author Siddharth Tarey(st2476@rit.edu) Batch 665-01
 * @author Pavan Bhat(pxb8715@rit.edu) Batch 665-01
 * 
 */
import java.util.ArrayList;
import java.util.Scanner;

public class AlignPoints {
	double xCoordinate[];
	double yCoordinate[];
//	ArrayList<Double> midX = new ArrayList<Double>();
//	ArrayList<Double> midY = new ArrayList<Double>();
	double midX;
	double midY;
//	ArrayList<Double> xIntercept = new ArrayList<Double>();
//	ArrayList<Double> yIntercept = new ArrayList<Double>();
//	ArrayList<Double> perpendicularSlope = new ArrayList<Double>();
	double perpendicularSlope = 0.0;
	double xIntercept = 0.0;
	double yIntercept = 0.0;
	
	
	
	public static void main(String[] args) {
	AlignPoints alignPoints = new AlignPoints();
	Scanner input = new Scanner(System.in);
	int numberOfPoints = Integer.parseInt(input.next());
	alignPoints.xCoordinate = new double[numberOfPoints];
	alignPoints.yCoordinate = new double[numberOfPoints];

	alignPoints.getInput(input, alignPoints, numberOfPoints);
	double tempSlope;
	ArrayList<AlignPoints> lines = new ArrayList<AlignPoints>();
	
	for (int i = 0; i < (numberOfPoints - 1); i++) {
		for (int j = i+1; j < numberOfPoints; j++) {
			
			AlignPoints alignPoints2 = new AlignPoints(); 
			 alignPoints.midX = ((alignPoints.xCoordinate[i] + alignPoints.xCoordinate[j])/2); 
			 alignPoints.midY = ((alignPoints.yCoordinate[i] + alignPoints.yCoordinate[j])/2); 
			 
			 if(alignPoints.xCoordinate[j] - alignPoints.xCoordinate[i] == 0){
					alignPoints2.perpendicularSlope = 0;
					alignPoints2.yIntercept = alignPoints.midY;
					alignPoints2.xIntercept = ((-1) *(alignPoints2.yIntercept) / (alignPoints2.perpendicularSlope));
					
					lines.add(alignPoints2);
				}
			 else{
				 tempSlope = ((alignPoints.yCoordinate[j] - alignPoints.yCoordinate[i])/(alignPoints.xCoordinate[j] - alignPoints.xCoordinate[i]));
				 if(tempSlope == 0){
					 alignPoints2.perpendicularSlope = 1/0.0;
						alignPoints2.xIntercept = alignPoints.midX;
						alignPoints2.yIntercept = alignPoints.midY;
						lines.add(alignPoints2);
				 }
				 else{
				 alignPoints2.perpendicularSlope = (-1 / (tempSlope));
				 alignPoints2.yIntercept = alignPoints.midY-(alignPoints2.perpendicularSlope * alignPoints.midX);
				 alignPoints2.xIntercept = ((-1) *(alignPoints2.yIntercept) / (alignPoints2.perpendicularSlope));
//				 System.out.println(alignPoints2.perpendicularSlope+ " and "+alignPoints2.yIntercept);
				 lines.add(alignPoints2);
				 }
			 }
			 
			 
		}		
	}
	
	ArrayList<AlignPoints> tempLines = new ArrayList<AlignPoints>();
//	for (int i = 0; i < tempLines.length; i++) {
////		if(lines.get(i).yIntercept)
//		tempLines[i] = lines.get(i).perpendicularSlope;
//	}
//	
	tempLines = alignPoints.mergeSort(lines);
//	System.out.println("size" + tempLines.size());
//	for (int j = 0; j < tempLines.size(); j++) {
//		System.out.println(tempLines.get(j).perpendicularSlope+", " + tempLines.get(j).yIntercept);
//	}
		
	int greaterCount = 0;
	int temporaryCount = 1;
	
	for(int i = 0; i < tempLines.size() - 1; i++){
		if(tempLines.get(i).perpendicularSlope == tempLines.get(i+1).perpendicularSlope){
			if (tempLines.get(i).yIntercept == tempLines.get(i+1).yIntercept) {
				temporaryCount++;
				if(temporaryCount > greaterCount){
					greaterCount = temporaryCount;
//					System.out.println(greaterCount);s
					temporaryCount =1;
				}
			}
		}
		}
	
	
	System.out.println(greaterCount);
	
	
//	for (int i = 0; i < numberOfPoints		; i++) {
//		System.out.println(alignPoints1.xCoordinate[i]+" and "+alignPoints.yCoordinate[i]);
//	}
	
}


	/**
	 * This function does merge sort on the input array
	 * 
	 * @param data2:
	 *            the array that needs to be sorted
	 * @return returns an array of type double
	 */
	public ArrayList<AlignPoints> mergeSort(ArrayList<AlignPoints> lines) {
		ArrayList<AlignPoints> left = new ArrayList<AlignPoints>();
		ArrayList<AlignPoints> right = new ArrayList<AlignPoints>();
		if (lines.size() == 1) {
			return lines;
		} else {
			int leftIndex = (int) Math.floor(lines.size() / 2);
//			Place holders for left and right sections of the entire data.
//			left = new double[leftIndex];
//			right = new double[lines.size() - leftIndex];

			for (int i = 0; i < lines.size(); i++) {
				if (i < leftIndex) {
//					left.set(i, lines.get(i));
					left.add(i, lines.get(i));
				} else {
					right.add(i - leftIndex, lines.get(i));
				}
			}
		}
//		recursive calls to sort the left and right section.
		left = mergeSort(left);
		right = mergeSort(right);
//		System.out.println("left "+left.size());
//		System.out.println("right "+right.size());
//		required to merge the left and the right sections of the array.
		ArrayList<AlignPoints> result = Merge(left, right);
		return result;
	}

	/**
	 * This function merges the data that has been split.
	 * 
	 * @param left
	 * @param right
	 * @return returns a sorted array of type double.
	 */
	public ArrayList<AlignPoints> Merge(ArrayList<AlignPoints> left, ArrayList<AlignPoints> right) {
		// TODO Auto-generated method stub
		ArrayList<AlignPoints> temp = new ArrayList<AlignPoints>();
		int leftIndex = 0, rightIndex = 0;
		
		for (int i = 0; i < (left.size() + right.size()); i++) {
		
			//System.out.println("left right"+(left.size() + right.size()));
//			required for adding the elements in the right section if there are no elements left in the left section 
			if (leftIndex == left.size()) {
				temp.add(i,right.get(rightIndex));
				rightIndex++;
			} 
//			required for adding the elements in the left section if there are no elements left in the right section 
			else if (rightIndex == right.size()) {
				temp.add(i,left.get(leftIndex));
				leftIndex++;
			} else if (left.get(leftIndex).perpendicularSlope < right.get(rightIndex).perpendicularSlope) {
					temp.add(i,left.get(leftIndex));
					leftIndex++;
			}
			else if (left.get(leftIndex).perpendicularSlope == right.get(rightIndex).perpendicularSlope)  {
				if (left.get(leftIndex).yIntercept < right.get(rightIndex).yIntercept) {
					temp.add(i,left.get(leftIndex));
					leftIndex++;
				}
				else{
					temp.add(i,right.get(rightIndex));
					rightIndex++;
				}
			}
			else {
				temp.add(i,right.get(rightIndex));
				rightIndex++;
			}
		}
		return temp;
	}
	 
	
	
public void getInput(Scanner input, AlignPoints alignPoints, int numberOfPoints){
	for(int i = 0; i < numberOfPoints; i++){
		alignPoints.xCoordinate[i] = input.nextInt();
		alignPoints.yCoordinate[i] = input.nextInt();
	}
}
}
