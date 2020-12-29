/**
 * 
 */
package com.ss.sf.lms.ui;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.ss.sf.lms.dao.BookDAO;
import com.ss.sf.lms.domain.Book;

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
			System.out.println("1) Books");
			System.out.println("2) Authors");
			System.out.println("3) Publishers");
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
									Book bookToChange = allBooks.get(bookSelection - 1 );
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

							case 2: //Add book
								
								break;

							}
						}
						break;
					case 2:
						//Authors UPDATE/DELETE /ADD 1hr
						break;
					case 3:
						//Publishers UPDATE/DELETE /ADD 1hr 
						break;
					case 4:
						//Library Branches UPDATE/DELETE /ADD 1hr
						break;
					case 5:
						//Borrowers UPDATE/DELETE /ADD 1hr
						break;
					case 6:
						//Override Due Date UPDATE
						break;
					
					}
				}
			}
			

			
//		this.active = false;
	}

}
