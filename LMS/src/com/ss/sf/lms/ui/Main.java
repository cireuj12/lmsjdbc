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
		do {
			System.out.println("Welcome to the SS Library Management System. Which category of a user are you: \n");
			System.out.println("1) Librarian");
			System.out.println("2) Administrator(Functionality still in Development)");
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
				/**
				 * LIBRARIAN SESSION
				 * 
				 * 
				 */
				switch (roleSelection) {
				case 1: { //if (roleSelection == 1) 
					//Initialize Librarian Session move
					//CREATE LIBRARIAN CLASS SESSION
					
					//LIB1
					System.out.println("1) Enter Branch you manage");
					System.out.println("2) Quit to previous menu");
					Integer choice = Integer.parseInt(scan.nextLine());
					
					if (choice == 2) { 
						roled = false;
						break;

					};
					
					Boolean session = true;
					
					do {
						if (choice == 2) {
//							session = false;
//							continue;
						} 
						else {
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
							System.out.println("Enter 0 to go to previous menu");
							
							Integer branchId = Integer.parseInt(scan.nextLine());
							System.out.println("");
							
							if (branchId == 0) {
								session = false;
								break;
//								continue;// goes back to LIB1
							}
							
							Boolean branched = true;
						
							//LIB3
							while (branched == true) {
								System.out.println("1) Update the details of the Library");
								System.out.println("2) Add copies of Book to the Branch");
								System.out.println("3) Quit to previous menu");
								
								Integer action = Integer.parseInt(scan.nextLine());
								System.out.println("");
								
								
								if (action == 3) {
									branched = false;
									break; //goes back to LIB3
								}
								
								if (action == 1) {
									System.out.println("You have chosen to update the Branch with Branch Id: "
											+ branchId
											+ " and Branch Name: "
											+ branchs.get(branchId-1).getBranchName()
											+ ".\n "
											+ "Enter ‘quit’ at any prompt to cancel operation.");
									
									System.out.println("Please enter new branch name or enter N/A for no change: ");
									String newName = scan.nextLine();
									if (newName == "quit") {
										branched = false;
										break;
									}
									System.out.println("Please enter new branch address or enter N/A for no change: ");
									String newAddress = scan.nextLine();
									if (newAddress == "quit") {
										branched = false;
										break;
									}
		
									
									branchs.get(branchId-1).setBranchName(newName);
									branchs.get(branchId-1).setBranchAddress(newAddress);						
									branchdao.updateBranch(branchs.get(branchId-1)); //updating the branch with new parameters
									
									System.out.println("Branch info has been updated");
									branchId = null;
									branched = false;
									//then go back to LIB3
									
								} else if (action == 2) {
									BookDAO books = new BookDAO();
									List<Book> booklist = books.readBooksbyBranch(branchId); //need to specify by branch Id
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
									
									
									//if null I have to add
									
									
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
					} while(session == true) ;
					
					/**END 
					 * 
					 * OF 
					 * 
					 * LIBRARIAN 
					 * 
					 * SESSION
					 * 
					*/
					break;
					
					
					
					

					
					
				} case 3:  {
					/**
					 * BORROWER SESSION
					 */
					
						System.out.println("Enter your Card Number: ");
						
						Integer cardNo = Integer.parseInt(scan.nextLine());
						System.out.println("");
						
						BorrowerDAO borrowers = new BorrowerDAO();
						Borrower borrowerSelected;
						
						try {
							borrowerSelected = borrowers.readBorrowerById(cardNo).get(0);
						}  catch (SQLException e) {
							System.out.println("This is not a valid Number, please enter a new number or quit: ");
							cardNo = Integer.parseInt(scan.nextLine());
							borrowerSelected = borrowers.readBorrowerById(cardNo).get(0);
						} catch (IndexOutOfBoundsException e) {
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
							System.out.println("3) Quit to previous menu");
							
							int selection = Integer.parseInt(scan.nextLine());
							System.out.println("");
							
							if (selection == 3)  {
								roled = false; // GOES BACK TO MAIN
								validBorrower = false;
								break;
							}
							
							action = selection == 1 ? "check" : "return";
							Boolean actioned = true;
							
							
							//This loop does not rerun
							while (actioned) {
								
								//check
								
								//LIST BRANCHES
								BranchDAO branchdao = new BranchDAO();
								List<Branch> branchs = branchdao.readBranchs();
								
								
								//CHECKING OUT
								if (action == "check") {
									
									for (Branch a: branchs) {
										Integer id = a.getBranchId();
										String branchName = a.getBranchName();
										String branchAddress = a.getBranchAddress();
										System.out.println(id + ") "+branchName+", "+branchAddress);
									}
									System.out.println("Enter 0 to go to previous menu");
									
									Integer branchId = Integer.parseInt(scan.nextLine());
									System.out.println("");
									
									if (branchId == 0) {
										actioned = false; // goes back to BORR1
										break;
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
										System.out.println("Enter 0 to go to previous menu");
										System.out.println("Please Select a book to checkout");
										Integer bookToSearch = Integer.parseInt(scan.nextLine());
										System.out.println("");
										
										if (bookToSearch == 0) {
											branched = false; // goes back to BORR1
											break;
										}
										
										
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
											System.out.println("");
									
													//date out
													//due date = date out + 1 week
											
											//back to LIB3
											branchId = null;
											branched = false;
											actioned = false;
												
										}
									}
								} else if (action == "return") {
									
									//can only return a book he has select * from tbl_book_loans where cardNo = borrowerId;
									//create instance of book copy to reduce by 1
									
									//show his book copies
									//click return
									//+ increase number of copies
									// delete loan
									
									//Pick the branch you want to return to - might not be neccesary cause youre just deleting book loan and copy and branchId in book loan
									//unless youre returning a book to any branch
									//assuming you return book to same branch
									
									BookLoanDAO bookLoans = new BookLoanDAO();
									List<BookLoan> borrowerBooks = bookLoans.readBookLoansCardNo(cardNo);
									

									for (BookLoan a: borrowerBooks) {
										Integer book = a.getBookId();
										Integer branch = a.getBranchId();
										Integer borrowerCard = a.getCardNo();
										Timestamp dateOut = a.getDateOut();
										Timestamp dueDate = a.getDueDate();
										System.out.println(book + " taken out on " + dateOut + " due on " +dueDate); //add left join for title
									}
									
									System.out.println("\n Which book would you like to return? Type 0 to return to previous menu.");
									
									Integer bookToSearch = Integer.parseInt(scan.nextLine());
										
									if (bookToSearch == 0) {
										actioned = false;
										break;
									}
									
									//update copy
									
									BookCopyDAO bookUpdate = new BookCopyDAO();
									BookCopy BookSelection = bookUpdate.readBookbyId(bookToSearch).get(0);
									Integer numOfCopies = BookSelection.getNoOfCopies();
									Integer branching = BookSelection.getBranchId();
									//You got the Book you want to change and how many copies it has
									
												
									BookSelection.setNoOfCopies(numOfCopies + 1); // subtract 1 a and set new copy in model
									bookUpdate.updateBookCopy(BookSelection); //set new copy in DB from model
									
									numOfCopies = BookSelection.getNoOfCopies(); //show new result
									
									
									//delete from bookLoan table based on parameters
									BookLoan toDelete = new BookLoan();
									toDelete.setBookId(bookToSearch);
									toDelete.setCardNo(cardNo);
									toDelete.setBranchId(branching);
									bookLoans.deleteBookLoan(toDelete);
									
									System.out.println("The book has been returned. Now there are: "+numOfCopies+"\n");
									
									//to return to borrower menu
									
									actioned = false;
									//don't work
								}
								
								
								
								//return
								
							}
						}
					
					
					//now access BorrowerDAO
					
					//access LoanDAO
					break;
				} case 2:  {//(roleSelection == 2) 
					/**
					 * ADMIN SESSION
					 * Add/Update/Delete/Read Book and Author
											Add/Update/Delete/Read Genres
											Add/Update/Delete/Read Publishers
											Add/Update/Delete/Read Library Branches
											Add/Update/Delete/Read Borrowers
											Over-ride Due Date for a Book Loan
					 */
					break;
				}
				default: {
					System.out.println("Please pick a valid choice");
					roled = false;
//					loggedIn = false; //this doesn't go back either
//					continue;
					break;
				}
			}//end of switch;

			} while (roled != false);// roled!= false;
			
//			scan.close();
//			System.out.close();
			continue;
		} while (true); // loop should always run

	}

}

//My approach was to build out the DAOs/Models as I built out the interface functionality of the console
//Load up the DB with filler data to test
//Built out Librarian and all the requiste clasess fro the Methods in the Console Interface
//Then Borrower
//Then Admin
