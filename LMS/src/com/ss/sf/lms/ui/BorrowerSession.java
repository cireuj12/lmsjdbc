/**
 * 
 */
package com.ss.sf.lms.ui;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import com.ss.sf.lms.dao.BookCopyDAO;
import com.ss.sf.lms.dao.BookDAO;
import com.ss.sf.lms.dao.BookLoanDAO;
import com.ss.sf.lms.dao.BorrowerDAO;
import com.ss.sf.lms.dao.BranchDAO;
import com.ss.sf.lms.domain.Book;
import com.ss.sf.lms.domain.BookCopy;
import com.ss.sf.lms.domain.BookLoan;
import com.ss.sf.lms.domain.Borrower;
import com.ss.sf.lms.domain.Branch;

/**
 * @author ericju
 *
 */
public class BorrowerSession {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public Boolean active;
	
	public void launch() throws ClassNotFoundException, SQLException {
		
		System.out.println("Enter your Card Number: ");
		
		Scanner scan = new Scanner(System.in);
		Integer cardNo = Integer.parseInt(scan.nextLine());
		System.out.println("");
		
		BorrowerDAO borrowers = new BorrowerDAO();
		Borrower borrowerSelected;
		
		if (cardNo == 0) {
			//exit
			active = false; 
		} else {
			try {
				borrowerSelected = borrowers.readBorrowerById(cardNo).get(0);
			}  catch (SQLException e) {
				System.out.println("This is not a valid Number, please enter a new number or quit(type 0): ");
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
					active = false; // GOES BACK TO MAIN
					validBorrower = false;
					break;
				}
				
				action = selection == 1 ? "check" : "return";
				Boolean actioned = true;
				
			
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
						System.out.println("0) Quit to previous menu");
						
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
							System.out.println("0) Quit to previous menu");
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
								System.out.println("Sorry there are no copies of this book to checkout\n");
								branchId = null;
								branched = false;
							} else {
																		
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
								
								try {
									bookloanDao.addBookLoan(addLoan);
								}  catch (SQLException e) {
									System.out.println("You already have a copy of this book, please select another. \n");
									branched = false;
									break;
								}
								
								BookSelection.setNoOfCopies(numOfCopies - 1); // subtract 1 a and set new copy in model
								copiesofBook.updateBookCopy(BookSelection); //set new copy in DB from model
								numOfCopies = BookSelection.getNoOfCopies(); //show new result
								System.out.println("Now there are: "+numOfCopies);
								System.out.println("The book has been checked out and logged. ");
								System.out.println("");
							 
								
								//back to LIB3
								branchId = null;
								branched = false;
								actioned = false;
									
							}
						}
					} else if (action == "return") {
						
						BookLoanDAO bookLoans = new BookLoanDAO();
						List<BookLoan> borrowerBooks = bookLoans.readBookLoansCardNo(cardNo);
						

						for (BookLoan a: borrowerBooks) {
							Integer book = a.getBookId();
							String title = a.getTitle();
							Timestamp dateOut = a.getDateOut();
							Timestamp dueDate = a.getDueDate();
							System.out.println(book + ") " +title+ " taken out on " + dateOut + " due on " +dueDate); //add left join for title
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
					}			
					//return
					
				}
			}
		}
		

		
	}

}
