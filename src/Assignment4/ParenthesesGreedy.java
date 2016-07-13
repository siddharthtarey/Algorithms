package Assignment4;

import java.util.Scanner;
import java.util.Stack;

/**
 * this program determines the maximum possible value that can be obtained from
 * the expression by fully parenthesizing it. The only operator involved are '+' and '*'. Uses a greedy solution for it
 * 
 * @author Sid
 *
 */
public class ParenthesesGreedy {
	// stack for the operands
	Stack<Integer> num = new Stack<Integer>();
	// stack for the operators
	Stack<Character> operator = new Stack<Character>();
	// stack for the final elements that have to be multiplied togeather to obtain maximum output 
	Stack<Integer> newNum = new Stack<Integer>();

	public static void main(String[] args) {
		ParenthesesGreedy greed = new ParenthesesGreedy();
		greed.getInput();
		greed.brackets();
		int total = 1;
		// multiples all the elements in newNum to obtain maximum output  
		while (!greed.newNum.isEmpty()) {
			total *= greed.newNum.pop();
		}
		System.out.println(total);
	}

	/**
	 * this function partially solves the equation and modifies the newNum stack
	 */
	private void brackets() {
		while (!operator.isEmpty()) {
			// if operator is '*' pop it and discard it
			if (operator.peek() == '*') {
				operator.pop();
				// pop the operand and push it onto newNum
				newNum.push(num.pop());
			} else {
				if (!num.isEmpty()) {
					
					if (num.size() == 1) {
						newNum.push(num.pop());
						break;
					}
					// if the operator is '+' add the numbers and push the total agian on the num stack
					operator.pop();
					int temp = num.pop() + num.pop();
					num.push(temp);
				}
			}
		}
		// pops the num stack if there are any operands left and pushes on the newNum stack.
		while (!num.isEmpty()) {
			newNum.push(num.pop());

		}
	}
/**
 * this functions gets the input from the user.
 */
	private void getInput() {
		Scanner input = new Scanner(System.in);
		String temp = input.nextLine();
		String[] temp2 = temp.split(" ");
		for (int i = 0; i < temp2.length; i++) {
			if (Character.isDigit(temp2[i].charAt(0))) {
				num.push(Integer.parseInt(temp2[i]));
			} else {
				operator.push(temp2[i].charAt(0));
			}
		}

		input.close();
	}

}
