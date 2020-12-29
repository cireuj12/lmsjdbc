/**
 * 
 */
package com.ss.sf.lms.ui;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.ss.sf.lms.dao.BookCopyDAO;
import com.ss.sf.lms.dao.BookDAO;
import com.ss.sf.lms.dao.BranchDAO;
import com.ss.sf.lms.domain.Book;
import com.ss.sf.lms.domain.BookCopy;
import com.ss.sf.lms.domain.Branch;

/**
 * @author ericju
 *
 */
public class Librarian {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	public Boolean active;
	
	public void launch() throws ClassNotFoundException, SQLException {
		System.out.println("1) Enter Branch you manage");
		System.out.println("2) Quit to previous menu");
		
		Scanner scan = new Scanner(System.in);
		
		Integer choice;
		try {
			choice = Integer.parseInt(scan.nextLine());
		} catch (NumberFormatException e1) {
			System.out.println("Invalid choice, please try again."); //Insures Integer
			choice = Integer.parseInt(scan.nextLine());
		}
		
		if (choice == 2 || choice > 2) { 
			active = false;
		};
		
		Boolean session = true;
		
		do {
			if (choice == 2) {
				session = false;
				break;
			} 
			else if (choice == 1) {
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
				
				Integer branchId;
					try {
						branchId = Integer.parseInt(scan.nextLine());
					} catch (NumberFormatException e) {
						System.out.println("Invalid choice, please try again."); //Insures Integer
						branchId = Integer.parseInt(scan.nextLine());
					}
				
				System.out.println("");			
				if (branchId == 0) {
					session = false;
					break;
				} else if (branchId > branchs.size() || branchId < 0) {
					System.out.println("Invalid choice, please try again.");
					break;
				}
				
				Boolean branched = true;
			
				//LIB3
				while (branched == true) {
					System.out.println("1) Update the details of the Library");
					System.out.println("2) Add copies of Book to the Branch");
					System.out.println("3) Quit to previous menu");
					
					Integer action;
					try {
						action = Integer.parseInt(scan.nextLine());
					} catch (NumberFormatException e) {
						System.out.println("Invalid choice, please try again."); //Insures Integer
						action = Integer.parseInt(scan.nextLine());
					}
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
						
						Integer newCopies;
						try {
							newCopies = Integer.parseInt(scan.nextLine());
						} catch (NumberFormatException e) {
							System.out.println("Invalid choice, please try again."); //Insures Integer
							newCopies = Integer.parseInt(scan.nextLine());
						}
						
						BookSelection.setNoOfCopies(newCopies); //set new copy in model
						copiesofBook.updateBookCopy(BookSelection); //set new copy in DB from model
						
						numOfCopies = BookSelection.getNoOfCopies(); //show new result
						System.out.println("New copies changed to: "+numOfCopies);
						//back to LIB3
						branchId = null;
						
					
					}
				}
				//break or continue
			} else {
				System.out.println("Invalid choice, please try again. \n");
				break;
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
		
	}
}
