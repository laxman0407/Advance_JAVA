package com.tech.operation;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Operation op = new Operation();
		Scanner sc = new Scanner(System.in);
		System.out.println(" 1.Save Data \n 2.Update Data \n 3.Get Data \n 4.Delete Data \n");
		int x = sc.nextInt();
		switch (x) {

		case 1:
			op.saveData();
			break;

		case 2:
			op.updateData();
			break;

		case 3:
			op.getAllData();
			break;

		case 4:
			op.deleteData();
			break;

		default:
			System.out.println("enter valid input");
			break;
		}
	}

}
