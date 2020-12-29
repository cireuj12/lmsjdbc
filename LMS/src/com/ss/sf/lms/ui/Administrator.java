/**
 * 
 */
package com.ss.sf.lms.ui;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import com.ss.sf.lms.dao.AuthorDAO;
import com.ss.sf.lms.dao.BookDAO;
import com.ss.sf.lms.dao.BookLoanDAO;
import com.ss.sf.lms.dao.BorrowerDAO;
import com.ss.sf.lms.dao.BranchDAO;
import com.ss.sf.lms.dao.BookLoanDAO;
import com.ss.sf.lms.domain.Author;
import com.ss.sf.lms.domain.Book;
import com.ss.sf.lms.domain.Branch;
import com.ss.sf.lms.domain.Borrower;
import com.ss.sf.lms.domain.BookLoan;

/**
 * @author ericju
 *
 */
public class Administrator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	public Boolean active;
	
	public void launch() throws ClassNotFoundException, SQLException {
		
			System.out.println("Which table would you like to change?");
			System.out.println("1) Books(Incomplete)");
			System.out.println("2) Authors");
			System.out.println("3) Publishers(Incomplete)");
			System.out.println("4) Library Branches");
			System.out.println("5) Borrowers");
			System.out.println("6) Override Due Date");
			System.out.println("0) Return to previous menu\n");

			
			Scanner scan1 = new Scanner(System.in);
			
			Integer input = Integer.parseInt(scan1.nextLine());
			
			if (input == 0 ) {
				active = false;
			} else {
				Boolean selected = true;
				
				while (selected) {
					switch(input) {
					
					case 1:
						//Books 1
						BookDAO bookdao = new BookDAO();
						System.out.println("1) Find Book to Edit");
						System.out.println("2) Add Book");
						System.out.println("3) Return to previous menu\n");
						
						Integer bookInput = Integer.parseInt(scan1.nextLine());
						
						while (bookInput != null) {
							switch (bookInput) {
							//other option for menu:
							//add
							//show books to change
							//previous
							case 3: //previous menu
								selected = false;
								bookInput = null;
								break; //
							case 1: //Find all books;
								List<Book> allBooks = bookdao.readBooksAuthor();
								for (Book a : allBooks) {
									Integer id = a.getBookId();
									String title = a.getTitle();
									String author = a.getAuthor();
									System.out.println(id + ") " + title + " by " + author);
								}
								System.out.println(
										"\nSelect which book to change. Or type 0 to return to previous menu.");
								
								Integer bookSelection = Integer.parseInt(scan1.nextLine());
								
								if (bookSelection == 0) { //previous menu
									bookInput = null;
									break;
								} else {
									
									//add select book by id
									Book bookToChange = bookdao.readBookById(bookSelection).get(0);
									System.out.println("You've selected " + bookToChange.getTitle());
									System.out.println("Enter 1 to Update, 2 to delete, 0 to return to previous menu ");
									//updatebyBookID, delete by BookID
									Integer action = Integer.parseInt(scan1.nextLine());
									if (action == 0) { //previous menu
										bookInput = null;
										break;
									} else if (action == 1) { //UPDATE keep book ID=
										System.out.println("Currently:"+bookToChange.getTitle()+". What do you want to change the title to?\n");
										bookToChange.setTitle(scan1.nextLine());
										System.out.println("What do you want to change the Author ID to?\n");
										bookToChange.setAuthId(Integer.parseInt(scan1.nextLine()));
										System.out.println("What do you want to change the Publisher ID to?\n");
										bookToChange.setPubId(Integer.parseInt(scan1.nextLine()));
										
										bookdao.updateBook(bookToChange); //send update to database
										
										System.out.println("/n The book has been updated!");
										
									} else { //DELETE
										System.out.println("Type yes to confirm deletion");
										String confirm = scan1.nextLine();
										if (confirm.equals("yes")) { // compare string data not string object
											bookdao.deleteBook(bookToChange);
											System.out.println("This book has been deleted\n");
										} else {
											System.out.println("Reverting...\n");
											bookInput = null;
											break;
										}
									}
								}
								//done with update/delete go back to previous menu
								selected = false;
								bookInput = null;
								break;
							/**
							 * UPDATE/DELETE BOOK 
							 * COMPLETE
							 */
								
								
							case 2: //Add book
								Book bookToAdd = new Book();
								
								//NEED TO FIX DB TO AUTO INCREMENT
								System.out.println("What is the title of the book?");
								System.out.println("What is the authId of the book?"); //valid authId . Please add author first
								System.out.println("What is the pubId of the book?"); //valid pubId . Plese add pubisher first
								
								break;

							}
						}
						break;
					case 2:
						
						//Authors
						//CRUD
						AuthorDAO authordao = new AuthorDAO();
						System.out.println("1) Find Author to Edit");
						System.out.println("2) Add Author");
						System.out.println("3) Return to previous menu\n");
						
						Integer authorInput = Integer.parseInt(scan1.nextLine());
						
						while (authorInput != null) {
							switch (authorInput) {
							case 3: //previous menu
								selected = false;
								authorInput = null;
								break; //
							case 1: //Find all authors;
								List<Author> allAuthors = authordao.readAuthors();
								for (Author a : allAuthors) {
									Integer id = a.getAuthorId();
									String author = a.getAuthorName();
									System.out.println(id + ") " + author);
								}
								System.out.println(
										"\nSelect which author to change. Or type 0 to return to previous menu.");
								
								Integer authorSelection = Integer.parseInt(scan1.nextLine());
								
								if (authorSelection == 0) { //previous menu
									authorInput = null;
									break;
								} else {

									Author authorToChange = authordao.readAuthorById(authorSelection).get(0);
									System.out.println("You've selected " + authorToChange.getAuthorName());
									System.out.println("Enter 1 to Update, 2 to delete, 0 to return to previous menu ");
		
									Integer action1 = Integer.parseInt(scan1.nextLine());
									if (action1 == 0) { //previous menu
										authorInput = null;
										break;
									} else if (action1 == 1) { //UPDATE keep author ID=
										System.out.println("Currently:"+authorToChange.getAuthorName()+". What do you want to change the author to?\n");
										authorToChange.setAuthorName(scan1.nextLine());

										
										authordao.updateAuthor(authorToChange); //send update to database
										
										System.out.println("/n The author has been updated!");
										
									} else { //DELETE
										System.out.println("Type yes to confirm deletion");
										String confirm = scan1.nextLine();
										if (confirm.equals("yes")) { // compare string data not string object
											authordao.deleteAuthor(authorToChange);
											System.out.println("This author has been deleted\n");
										} else {
											System.out.println("Reverting...\n");
											authorInput = null;
											break;
										}
									}
								}
								//done with update/delete go back to previous menu
								selected = false;
								authorInput = null;
								break;
								
								
							case 2: //Add author
								Author authorToAdd = new Author();

								System.out.println("What is the name of the author?\n");
								String authorName = scan1.nextLine();
								authorToAdd.setAuthorName(authorName);
								
								authordao.addAuthor(authorToAdd);
								
								System.out.println("The author has been added\n");
								selected = false;
								authorInput = null;
								break;

							}
						}
						
						
						/**
						 * CRUD AUTHOR 
						 * COMPLETE
						 */
							
						break;
					case 3:
						//Publishers 
						//CRUD 1HR
						//auto increment for DB added
						
						//NEED TO CREATE PUBLISHER OBJECT AND DAO
						
						break;
					case 4:
						//Library Branches 
						// CRUD
						//auto increment for DB added
						
						BranchDAO branchdao = new BranchDAO();
						System.out.println("1) Find Branch to Edit");
						System.out.println("2) Add Branch");
						System.out.println("3) Return to previous menu\n");
						
						Integer branchInput = Integer.parseInt(scan1.nextLine());
						
						while (branchInput != null) {
							switch (branchInput) {
							case 3: //previous menu
								selected = false;
								branchInput = null;
								break; //
							case 1: //Find all branchs;
								List<Branch> allBranchs = branchdao.readBranchs();
								for (Branch a : allBranchs) {
									Integer id = a.getBranchId();
									String branch = a.getBranchName();
									String address = a.getBranchAddress();
									System.out.println(id + ") " + branch +" , " + address);
								}
								System.out.println(
										"\nSelect which branch to change. Or type 0 to return to previous menu.");
								
								Integer branchSelection = Integer.parseInt(scan1.nextLine());
								
								if (branchSelection == 0) { //previous menu
									branchInput = null;
									break;
								} else {
									//Author authorToChange = authordao.readAuthorById(authorSelection).get(0);
									Branch branchToChange = branchdao.readBranchById(branchSelection).get(0);
									System.out.println("You've selected " + branchToChange.getBranchName() + " , " + branchToChange.getBranchAddress());
									System.out.println("Enter 1 to Update, 2 to delete, 0 to return to previous menu ");
		
									Integer action2 = Integer.parseInt(scan1.nextLine());
									if (action2 == 0) { //previous menu
										branchInput = null;
										break;
									} else if (action2 == 1) { //UPDATE keep branch ID=
										System.out.println("Currently:"+branchToChange.getBranchName()+". What do you want to change the branch name to?\n");
										branchToChange.setBranchName(scan1.nextLine());
										System.out.println("Currently:"+branchToChange.getBranchAddress()+". What do you want to change the branch address to?\n");
										branchToChange.setBranchAddress(scan1.nextLine());

										
										branchdao.updateBranch(branchToChange); //send update to database
										
										System.out.println("/n The branch has been updated!");
										
									} else { //DELETE
										System.out.println("Type yes to confirm deletion");
										String confirm1 = scan1.nextLine();
										if (confirm1.equals("yes")) { // compare string data not string object
											branchdao.deleteBranch(branchToChange);
											System.out.println("This branch has been deleted\n");
										} else {
											System.out.println("Reverting...\n");
											branchInput = null;
											break;
										}
									}
								}
								//done with update/delete go back to previous menu
								selected = false;
								branchInput = null;
								break;
								
								
							case 2: //Add author
								Branch branchToAdd = new Branch();

								System.out.println("What is the name of the branch?\n");
								String branchName = scan1.nextLine();
								branchToAdd.setBranchName(branchName);
								
								System.out.println("What is the address of the branch?\n");
								String addressName = scan1.nextLine();
								branchToAdd.setBranchAddress(addressName);
								
								branchdao.addBranch(branchToAdd);
								
								System.out.println("The branch has been added.\n");
								selected = false;
								branchInput = null;
								break;

							}
						}
						
						
						/**
						 * CRUD BRANCH 
						 * COMPLETE
						 */
						break;
					case 5:
						//Borrowers
						//CRUD
						//db updated to autoincrement
						
						BorrowerDAO borrowerdao = new BorrowerDAO();
						System.out.println("1) Find Borrower to Edit");
						System.out.println("2) Add Borrower");
						System.out.println("3) Return to previous menu\n");
						
						Integer borrowerInput = Integer.parseInt(scan1.nextLine());
						
						while (borrowerInput != null) {
							switch (borrowerInput) {
							case 3: //previous menu
								selected = false;
								borrowerInput = null;
								break; //
							case 1: //Find all borrowers;
								List<Borrower> allBorrowers = borrowerdao.readBorrowers();
								for (Borrower a : allBorrowers) {
									Integer id = a.getCardNo();
									String name = a.getName();
									String address = a.getAddress();
									String phone = a.getPhone();
									System.out.println(id + ") " + name +" , " + address +" , " + phone);
								}
								System.out.println(
										"\nSelect which borrower to change. Or type 0 to return to previous menu.");
								
								Integer borrowerSelection = Integer.parseInt(scan1.nextLine());
								
								if (borrowerSelection == 0) { //previous menu
									borrowerInput = null;
									break;
								} else {
								
									Borrower borrowerToChange = borrowerdao.readBorrowerById(borrowerSelection).get(0);
									System.out.println("You've selected " + borrowerToChange.getName() + " , " + borrowerToChange.getAddress() + " , " + borrowerToChange.getPhone());
									System.out.println("Enter 1 to Update, 2 to delete, 0 to return to previous menu ");
		
									Integer action3 = Integer.parseInt(scan1.nextLine());
									if (action3 == 0) { //previous menu
										borrowerInput = null;
										break;
									} else if (action3 == 1) { 
										System.out.println("Currently:"+borrowerToChange.getName()+". What do you want to change the name to?\n");
										borrowerToChange.setName(scan1.nextLine());
										System.out.println("Currently:"+borrowerToChange.getAddress()+". What do you want to change the address to?\n");
										borrowerToChange.setAddress(scan1.nextLine());
										System.out.println("Currently:"+borrowerToChange.getPhone()+". What do you want to change the phone to?\n");
										borrowerToChange.setPhone(scan1.nextLine());

										
										borrowerdao.updateBorrower(borrowerToChange); //send update to database
										
										System.out.println("/n The borrower has been updated!");
										
									} else { //DELETE
										System.out.println("Type yes to confirm deletion");
										String confirm2 = scan1.nextLine();
										if (confirm2.equals("yes")) { // compare string data not string object
											borrowerdao.deleteBorrower(borrowerToChange);
											System.out.println("This borrower has been deleted\n");
										} else {
											System.out.println("Reverting...\n");
											borrowerInput = null;
											break;
										}
									}
								}
								//done with update/delete go back to previous menu
								selected = false;
								borrowerInput = null;
								break;
								
								
							case 2: //Add author
								Borrower borrowerToAdd = new Borrower();

								System.out.println("What is the name of the borrower?\n");
								String borrowerName = scan1.nextLine();
								borrowerToAdd.setName(borrowerName);
								
								System.out.println("What is the address of the borrower?\n");
								String addressName = scan1.nextLine();
								borrowerToAdd.setAddress(addressName);
								
								System.out.println("What is the phone # of the borrower?\n");
								String phone = scan1.nextLine();
								borrowerToAdd.setPhone(phone);
								
								borrowerdao.addBorrower(borrowerToAdd);
								
								System.out.println("The borrower has been added.\n");
								selected = false;
								borrowerInput = null;
								break;

							}//end of switch
						}
						
						/**
						 * CRUD BORROWER 
						 * COMPLETE
						 */
						break;
					case 6:
						//Override Due Date UPDATE
						
						BookLoanDAO bookloandao = new BookLoanDAO();
						System.out.println("1) Find Due Date to Override");
						System.out.println("2) Return to previous menu\n");
						
						Integer bookLoanInput = Integer.parseInt(scan1.nextLine());
						
						
						while(bookLoanInput != null) {
							
							switch (bookLoanInput) {
							case 2: //previous menu
								selected = false;
								bookLoanInput = null;
								break; //
							case 1: //Find all bookLoans;
								List<BookLoan> allLoans = bookloandao.readBookLoans();
								Integer selection = 1;
								for (BookLoan a : allLoans) {
									Integer id = selection;
									Integer cardNo = a.getCardNo();
									Integer branch = a.getBranchId();
									Integer book = a.getBookId();
									Timestamp dueDate = a.getDueDate();
									System.out.println(id +") " + cardNo + " took out " + book +" from " + branch +", due on " + dueDate);
									selection++;
								}
								selection = 1; // set it back to one after loop  
								//get that index
								System.out.println(
										"\nSelect which due date to change. Or type 0 to return to previous menu.");
								
								Integer changeDate = Integer.parseInt(scan1.nextLine());
								
								if (changeDate == 0) { //previous menu
									bookLoanInput = null;
									break;
								} else {
								
									BookLoan loanToChange = allLoans.get(changeDate - 1); //minus one is needed here because indexing into List not key
									System.out.println("You've selected book " + loanToChange.getBookId() 
														+ " , borrower:" 
														+ loanToChange.getCardNo() 
														+ " , " 
														+ loanToChange.getBranchId()
														+ " due on:"
														+ loanToChange.getDueDate());
									System.out.println("Enter 1 to Update 0 to return to previous menu ");
		
									Integer action4 = Integer.parseInt(scan1.nextLine());
									if (action4 == 0) { //previous menu
										bookLoanInput = null;
										break;
									} else if (action4 == 1) { 
										System.out.println("Currently:"+loanToChange.getDueDate()+". How many days do you want to add to the due date?\n");
										Integer addToTimestamp = Integer.parseInt(scan1.nextLine());
										
										//logic to add to current due date
										Timestamp currentDueDate = loanToChange.getDueDate();
										Calendar cal = Calendar.getInstance();
										cal.setTimeInMillis(currentDueDate.getTime());
										cal.add(Calendar.DAY_OF_WEEK, addToTimestamp);
										Timestamp newTime = new Timestamp(cal.getTime().getTime());
										
										loanToChange.setDueDate(newTime);

										
										bookloandao.updateBookLoan(loanToChange); //send update to database
										
										System.out.println("/n The due date has been updated!");
										
										//A better version would be to manually input due date
										
									} 
								}
								//done with update/delete go back to previous menu
								selected = false;
								bookLoanInput = null;
								break;
								

							}//end of switch
						}
						
						/**
						 * UPDATE BORROWER LOAN 
						 * COMPLETE
						 */
						
						break;
					
					}
				}
			}
			

			
//		this.active = false;
	}

}
