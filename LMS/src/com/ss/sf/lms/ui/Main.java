package com.ss.sf.lms.ui;

import java.util.Scanner;

public class Main {
	//create session class?
	//Librarian Class
		//methods
	//Administrator Class
		//methods
	//Borrower Class
		//methods
	
	
	
	//while role selection == certain class
	static Boolean loggedIn = false;
	public static void main(String[] args) {
		
		while (loggedIn == false) {
			System.out.println("Welcome to the SS Library Management System. Which category of a user are you: \n");
			System.out.println("1) Librarian");
			System.out.println("2) Administrator");
			System.out.println("3) Borrower");
			System.out.println("4) Close");
			Scanner scan = new Scanner(System.in);
			
			Integer roleSelection = Integer.parseInt(scan.nextLine());
			
			if(roleSelection == 4) {
				System.out.println("Shutting off...");
				System.out.close();
			}
			loggedIn = true;
			
			while (loggedIn) {
				if (roleSelection == 1) {
					//Initialize Librarian Session move
					Scanner scanAgain = new Scanner(System.in);
					System.out.println("1) Enter Branch you manage");
					System.out.println("2) Quit to previous menu");
					Integer choice = Integer.parseInt(scanAgain.nextLine());
					
					if (choice == 2) {
						loggedIn = false;
					} else {
						//run get branches query
						//print it out
					}
					
					
				} else if (roleSelection == 2) {
					//Initialize Administrator Session
				} else if (roleSelection == 3) {
					//Initialize Borrower Session
				}
			}
		
		}
	}

}
