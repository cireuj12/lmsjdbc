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
	//create session class?
	//Librarian Class
		//methods
			//initialize
			//log out
			//boolean for session active
	//Administrator Class
		//methods
	//Borrower Class
		//methods
	
	
	//super loop break/continue that goes back to previous menu?
	
	
	//while role selection == certain class

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Boolean loggedIn = false;
		Scanner scan = new Scanner(System.in);
		while (loggedIn == false) {
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
			}
			
			loggedIn = true;
			Boolean roled = true;
			
			while (roled != false) {
				/**
				 * LIBRARIAN SESSION
				 */
				if (roleSelection == 1) {
					//Initialize Librarian Session move
					//CREATE LIBRARIAN CLASS SESSION
					
					//LIB1
					System.out.println("1) Enter Branch you manage");
					System.out.println("2) Quit to previous menu");
					Integer choice = Integer.parseInt(scan.nextLine());
					
					if (choice == 2) { //DONT WORK
						roled = false;
//						loggedIn = false; //goes back to Main
					};
					
					Boolean session = true;
					
					while(session == true) {
						if (choice == 2) {
							session = false;
						} else {
							//LIB2
							//LIST BRANCHES
							BranchDAO branchdao = new BranchDAO();
							List<Branch> branchs = branchdao.readBranchs();
							for (Branch a: branchs) {
								Integer id = a.getBranchId();
								String branchName = a.getBranchName();
								String branchAddress = a.getBranchAddress();
								System.out.println(id + ") "+branchName+", "+branchAddress);
							}
							System.out.println("enter 0 to go to previous");
							
							Integer branchId = Integer.parseInt(scan.nextLine());
							System.out.println("");
							
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
								System.out.println("");
								
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
									branchdao.updateBranch(branchs.get(branchId-1)); //updating the branch with new parameters
									
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
										String author = a.getAuthor();
										System.out.println(id + ") "+title+ " by "+author);
										//then get copies for next menu
										//branchId = null;
										//branched = false;
										//actually this goes back to LIB2 NOT LIB3
									}
									
									
									//UPDATE BOOK NO
									Integer bookToSearch = Integer.parseInt(scan.nextLine());
									System.out.println("");
									//which book are you updating?
									//initialize access to BookCopy DB
									BookCopyDAO copiesofBook = new BookCopyDAO();
									BookCopy BookSelection = copiesofBook.readBookbyId(bookToSearch).get(0);
									Integer numOfCopies = BookSelection.getNoOfCopies();
									//You got the Book you want to change and how many copies it has
									
									System.out.println("Existing number of copies: "+numOfCopies);
									System.out.println("Enter new number of copies:");
									Integer newCopies = Integer.parseInt(scan.nextLine());
									
									BookSelection.setNoOfCopies(newCopies); //set new copy in model
									copiesofBook.updateBookCopy(BookSelection); //set new copy in DB from model
									
									numOfCopies = BookSelection.getNoOfCopies(); //show new result
									System.out.println("New copies changed to: "+numOfCopies);
									//back to LIB3
									branchId = null;
									
									/**DONE
									 * get book copies DAO for # of copies
									show number of copies for that book
									take input enter new number of copies
									update taken
									*/
								
								}
							}
							//break or continue
						}
					}
					
					/**END 
					 * 
					 * OF 
					 * 
					 * LIBRARIAN 
					 * 
					 * SESSION
					 * 
					*/
					
					
					
					
					

					
					
				} else if (roleSelection == 3) {
					/**
					 * BORROWER SESSION
					 */
					
						System.out.println("Enter your Card Number: ");
						
						Integer cardNo = Integer.parseInt(scan.nextLine());
						System.out.println("");
						
						BorrowerDAO borrowers = new BorrowerDAO();
						Borrower borrowerSelected = borrowers.readBorrowerById(cardNo).get(0);
						
						if (borrowerSelected == null) { //DOES NOT LET THE USER PASS WITH INCORRECT CARD NO
							System.out.println("This is not a valid Number, please enter a new number or quit: ");
							cardNo = Integer.parseInt(scan.nextLine());
							borrowerSelected = borrowers.readBorrowerById(cardNo).get(0);
						}
						
						Boolean validBorrower = true; //use this properly
						
						while (validBorrower) {
							String action = null;
							//BORR1
							System.out.println("1) Check out a book");
							System.out.println("2) Return a book");
							System.out.println("3) Quit to previous");
							
							int selection = Integer.parseInt(scan.nextLine());
							System.out.println("");
							
							if (selection == 3)  {
								roled = false; // GOES BACK TO MAIN
							}
							action = selection == 1 ? "check" : "return";
							Boolean actioned = true;
							
							while (actioned) {
								
								//check
								
								//LIST BRANCHES
								BranchDAO branchdao = new BranchDAO();
								List<Branch> branchs = branchdao.readBranchs();
								if (action == "check") {
									
									for (Branch a: branchs) {
										Integer id = a.getBranchId();
										String branchName = a.getBranchName();
										String branchAddress = a.getBranchAddress();
										System.out.println(id + ") "+branchName+", "+branchAddress);
									}
									System.out.println("enter 0 to go to previous");
									
									Integer branchId = Integer.parseInt(scan.nextLine());
									System.out.println("");
									
									if (branchId == 0) {
										actioned = false; // goes back to BORR1
										
										//dont work
									}
									
									Boolean branched = true;
									
									while (branched) {
										//get all books at that branch that have more than one copy(tbl_book_copies where branchId = selected), with author and title(from getBooks);
										
										//showing books in that branch
										BookDAO bookdao = new BookDAO();
										List<Book> books = bookdao.readBooksbyBranch(branchId);
										for (Book a: books) {
											Integer id = a.getBookId();
											String title = a.getTitle();
											String author = a.getAuthor();
											System.out.println(id + ") "+title+ " by "+author);
										}
										System.out.println("Please Select a book to checkout");
										Integer bookToSearch = Integer.parseInt(scan.nextLine());
										System.out.println("");
										
										
										//UPDATE BOOK NO
										BookCopyDAO copiesofBook = new BookCopyDAO();
										BookCopy BookSelection = copiesofBook.readBookbyId(bookToSearch).get(0);
										Integer numOfCopies = BookSelection.getNoOfCopies();
										//You got the Book you want to change and how many copies it has
										
										System.out.println("Existing number of copies: "+numOfCopies);
										
										if (numOfCopies == 0) { //so borrower can't check out empty books
											System.out.println("Sorry there are no copies of this book to checkout");
											branchId = null;
											branched = false;
										} else {
																					
											BookSelection.setNoOfCopies(numOfCopies - 1); // subtract 1 a and set new copy in model
											copiesofBook.updateBookCopy(BookSelection); //set new copy in DB from model
											
											numOfCopies = BookSelection.getNoOfCopies(); //show new result
											System.out.println("Now there are: "+numOfCopies);
											
											
											/**NOW 
											 * ADD 
											 * BOOK_LOANS_DAO
											 * BOOK_LOANS_TABLE
								
											*/
											
											BookLoanDAO bookloanDao = new BookLoanDAO();
											BookLoan addLoan = new BookLoan();
											addLoan.setBookId(bookToSearch);
											addLoan.setBranchId(branchId);
											addLoan.setCardNo(cardNo);
											addLoan.setDateOut(new Timestamp(System.currentTimeMillis())); // consider Java Util Time
											
											//7 days
											Calendar cal = Calendar.getInstance();
											cal.add(Calendar.DAY_OF_WEEK, 7);
											Timestamp ts = new Timestamp(cal.getTime().getTime());
											
											addLoan.setDueDate(ts);
											
//											System.out.println(addLoan.getBookId());
//											System.out.println(addLoan.getBranchId());
//											System.out.println(addLoan.getCardNo());
//											System.out.println(addLoan.getDateOut());
//											System.out.println(addLoan.getDueDate());
											
											bookloanDao.addBookLoan(addLoan);
											
											System.out.println("The book has been checked out and logged. ");
											System.out.println("\n");
									
													//date out
													//due date = date out + 1 week
											
											//back to LIB3
											branchId = null;
											branched = false;
												
										}
									}
								} else if (action == "return") {
									
									//can only return a book he has
									
									//Pick the branch you want to return to:
									for (Branch a: branchs) {
										Integer id = a.getBranchId();
										String branchName = a.getBranchName();
										String branchAddress = a.getBranchAddress();
										System.out.println(id + ") "+branchName+", "+branchAddress);
									}
									System.out.println("enter 0 to go to previous");
									Integer branchId = Integer.parseInt(scan.nextLine());
									if (branchId == 0) {
										action = null; // goes back to BORR1
									}
									
									Boolean branched = true;
									
									while (branched) {
										//get all books at that branch that have even 0 copies
											//choose book
											//DELETE book in book_loans

									}
								}
								
								
								
								
								//return
								
							}
						}
					
					
					//now access BorrowerDAO
					
					//access LoanDAO
					
				} else if (roleSelection == 2) {
					/**
					 * ADMIN SESSION
					 */
				}
				else {
					System.out.println("Please pick a valid choice");
					roled = false;
					loggedIn = false; 
				}
			}
			scan.close();
			System.out.close();

		}
	}

}

//My approach was to build out the DAOs/Models as I built out the interface functionality of the console
//Load up the DB with filler data to test
//Built out Librarian and all the requiste clasess fro the Methods in the Console Interface
//Then Borrower
//Then Admin
