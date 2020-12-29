package com.ss.sf.lms.ui;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.ss.sf.lms.dao.BookDAO;
import com.ss.sf.lms.dao.BookLoanDAO;
import com.ss.sf.lms.dao.BorrowerDAO;
import com.ss.sf.lms.dao.BranchDAO;
import com.ss.sf.lms.dao.BookCopyDAO;
import com.ss.sf.lms.domain.Book;
import com.ss.sf.lms.domain.Branch;
import com.ss.sf.lms.domain.BookCopy;
import com.ss.sf.lms.domain.BookLoan;
import com.ss.sf.lms.domain.Borrower;

public class Main {
	

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Boolean loggedIn = false;
		Scanner scan = new Scanner(System.in);
		//Implement: if quit is typed in any time System.out.close();
		do {
			//**Main Menu**
			System.out.println("Welcome to the SS Library Management System. Which category of a user are you: \n");
			System.out.println("1) Librarian");
			System.out.println("2) Administrator");
			System.out.println("3) Borrower");
			System.out.println("4) Close");
			
			
			Integer roleSelection = Integer.parseInt(scan.nextLine());
			System.out.println("");
			
			
			if(roleSelection == 4) {
				System.out.println("Shutting off...");
				System.out.close();
				break;
			}
//			loggedIn = true;
			Boolean roled = true;
			
			do {

				switch (roleSelection) {
				case 1: { 
					/**
					 * LIBRARIAN SESSION
					 */
					Librarian librarian = new Librarian();
					
					librarian.active = true;
					
					while(librarian.active) {
						librarian.launch();
					}
					
					if (librarian.active == false) {
						System.out.println("Main Menu");
						roled = false;
					}
								
					break;				
					
				} case 3:  {
					/**
					 * BORROWER SESSION
					 */
					BorrowerSession borrowersession = new BorrowerSession();
					
					borrowersession.active = true;
					
					while(borrowersession.active) {
						borrowersession.launch();
					}
					
					if (borrowersession.active == false) {
						System.out.println("Main Menu");
						roled = false;
					}
					
					break;
					
				} case 2:  {
					/**
					 * Administrator SESSION
					 */
					Administrator session = new Administrator();
					
					session.active = true;
					
					while(session.active) {
						session.launch();
					}
					
					if (session.active == false) {
						System.out.println("Main Menu");
						roled = false;
					}
					
					break;
				}
				default: {
					System.out.println("Please pick a valid choice");
					roled = false;
					break;
				}
			}//end of switch;

			} while (roled != false);// roled!= false;
			
			continue;
		} while (true); // loop should always run
		

	}

}

//My approach was to build out the DAOs/Models as I built out the interface functionality of the console
//Load up the DB with filler data to test
//Built out Librarian and all the requiste clasess fro the Methods in the Console Interface
//Then Borrower
//Then Admin
