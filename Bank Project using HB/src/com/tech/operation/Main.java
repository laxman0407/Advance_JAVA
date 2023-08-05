package com.tech.operation;

import java.util.Scanner;

public class Main {
// main method
	public static void main(String[] args) {
		while(true) {
		Operation op = new Operation();
		Scanner sc = new Scanner(System.in);
		System.out.println(
				" 1.create account \n 2.show accounts details \n 3.deposit money \n 4.balance enquiry \n 5.withdraw balance \n 6.transfer money \n");
		int x = sc.nextInt();
		switch (x) {

		case 1:
			op.createAccount();
			break;
		case 2:
			op.showDetails();
			break;
		case 3:
			op.deposit();
			break;
		case 4:
			op.balanceEnquiry();
			break;
		case 5:
			op.withdrawMoney();
			break;
		case 6:
			op.transferMoney();
			break;

		default:
			System.out.println("enter valid input");
			break;

		}
		
		}

	}

}
