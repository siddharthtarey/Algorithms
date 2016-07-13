/**
 * 
 * @author Siddharth Tarey(st2476@rit.edu) Batch 665-01
 * @author Pavan Bhat(pxb8715@rit.edu) Batch 665-01
 * 
 */



import java.util.Scanner;

public class Picture {

	int age;
	double height;
	Picture[] picture;
	int count = 0;

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		int persons = scan.nextInt();
		Picture p = new Picture();
		p.picture = new Picture[persons];
		
		for (int i = 0; i < persons; i++) {
			p.picture[i] = new Picture();
			p.picture[i].age = scan.nextInt();
			p.picture[i].height = scan.nextDouble();

		}
		for (int i = 0; i < p.picture.length; i++) {
			p.sort(p.picture);
		}

		System.out.println(p.count);
	}

	public void sort(Picture[] picture) {

		this.split(0, picture.length - 1);
	}

	public void split(int left, int right) {

		if (left < right) {

			int middle = (left + right) / 2;

			this.split(left, middle);

			this.split(middle + 1, right);

			this.merge(middle);
		}

	}

	public void merge(int middle) {

		if (this.picture[middle].age == 8 && this.picture[middle + 1].age == 7) {

			Picture temp = this.picture[middle];
			this.picture[middle] = this.picture[middle + 1];
			this.picture[middle + 1] = temp;
			this.count++;
		} else if (this.picture[middle].age == 7 && this.picture[middle + 1].age == 7) {

			if (this.picture[middle].height > this.picture[middle + 1].height) {

				Picture temp = this.picture[middle];
				this.picture[middle] = this.picture[middle + 1];
				this.picture[middle + 1] = temp;
				this.count++;
			}
		} else if (this.picture[middle].age == 8 && this.picture[middle + 1].age == 8) {

			if (this.picture[middle].height < this.picture[middle + 1].height) {

				Picture temp = this.picture[middle];
				this.picture[middle] = this.picture[middle + 1];
				this.picture[middle + 1] = temp;
				this.count++;
			}
		}
		// if middle is prof and right is 7
		else if ((this.picture[middle].age != 8 && this.picture[middle].age != 7)
				&& this.picture[middle + 1].age == 7) {
			Picture temp = this.picture[middle];
			this.picture[middle] = this.picture[middle + 1];
			this.picture[middle + 1] = temp;
			this.count++;

		}
		// if middle is prof and left is 8
		else if (this.picture[middle].age == 8
				&& (this.picture[middle + 1].age != 8 && this.picture[middle + 1].age != 7)) {
			Picture temp = this.picture[middle];
			this.picture[middle] = this.picture[middle + 1];
			this.picture[middle + 1] = temp;
			this.count++;

		}
	}
}
