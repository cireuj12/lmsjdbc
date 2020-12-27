package com.ss.sf.lms.ui;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.ss.sf.lms.dao.BookDAO;
import com.ss.sf.lms.dao.BranchDAO;
import com.ss.sf.lms.dao.BookCopyDAO;
import com.ss.sf.lms.domain.Book;
import com.ss.sf.lms.domain.Branch;
import com.ss.sf.lms.domain.BookCopy;

public class Main {
	//create session class?
	//Librarian Class
		//methods
	//Administrator Class
		//methods
	//Borrower Class
		//methods
	
	
	//super loop break/continue that goes back to previous menu?
	
	
	//while role selection == certain class
	static Boolean loggedIn = false;
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
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
			Boolean roled = true;
			
			while (roled) {
				/**
				 * LIBRARIAN SESSION
				 */
				if (roleSelection == 1) {
					//Initialize Librarian Session move
					
					//LIB1
					System.out.println("1) Enter Branch you manage");
					System.out.println("2) Quit to previous menu");
					Integer choice = Integer.parseInt(scan.nextLine());
					
					if (choice == 2) {
						roled = false;
						loggedIn = false; //goes back to Main
					};
					Boolean session = true;
					
					while(session == true) {
						if (choice == 2) {
							session = false;
						} else {
							//LIB2
							BranchDAO branches = new BranchDAO();
							List<Branch> branchs = branches.readBranchs();
							for (Branch a: branchs) {
								Integer id = a.getBranchId();
								String branchName = a.getBranchName();
								String branchAddress = a.getBranchAddress();
								System.out.println(id + ") "+branchName+", "+branchAddress);
							}
							System.out.println("enter 0 to go to previous");
							
							Integer branchId = Integer.parseInt(scan.nextLine());
							
							if (branchId == 0) {
								session = false; // goes back to LIB1
							}
							
							Boolean branched = true;
						
							//LIB3
							while (branched == true) {
								System.out.println("1) Update the details of the Library");
								System.out.println("2) Add copies of Book to the Branch");
								System.out.println("3) Quit to Previous");
								
								Integer action = Integer.parseInt(scan.nextLine());
								
								if (action == 3) {
									branched = false; //goes back to LIB3
								}
								
								if (action == 1) {
									System.out.println("You have chosen to update the Branch with Branch Id: X and Branch Name: ABCD.\n "
											+ "Enter ‘quit’ at any prompt to cancel operation.");
									
									System.out.println("Please enter new branch name or enter N/A for no change: ");
									String newName = scan.nextLine();
									System.out.println("Please enter new branch address or enter N/A for no change: ");
									String newAddress = scan.nextLine();
									
									branchs.get(branchId-1).setBranchName(newName);
									branchs.get(branchId-1).setBranchAddress(newAddress);						
									branches.updateBranch(branchs.get(branchId-1));
									
									System.out.println("Branch info has been updated");
									branchId = null;
									branched = false;
									//then go back to LIB3
									
								} else if (action == 2) {
									BookDAO books = new BookDAO();
									List<Book> booklist = books.readBooksAuthor();
									for (Book a: booklist) {
										Integer id = a.getBookId();
										String title = a.getTitle();
										
										//need to join to get author
										//then get list of books
										String author = a.getAuthor();
										System.out.println(id + ") "+title+ " by "+author);
										//then get copies for next menu
										//branchId = null;
										//branched = false;
										//actually this goes back to LIB2 NOT LIB3
									}
									
									Integer book = Integer.parseInt(scan.nextLine());
									
									//add copies to book Book
									
									//DONT CREATE NEW BOOKDAO
									//CopyDao copiesofBook = new BookCopyDao();
									
									
									//get book copies DAO for # of copies
									//show number of copies for that book
									//take inputenter new number of copies
									//update taken
								
								}
							}
							//break or continue
							//update details of library
							//add copies of Book to Branch
						}
					}
					
					/**END 
					 * OF 
					 * LIBRARIAN 
					 * SESSION
					*/
					
				} else if (roleSelection == 2) {
					/**
					 * ADMIN SESSION
					 */
				} else if (roleSelection == 3) {
					/**
					 * BORROWER SESSION
					 */
				} else {
					System.out.println("Please pick a valid choice");
					roled = false;
					loggedIn = false; 
				}
			}
		
		}
	}

}
