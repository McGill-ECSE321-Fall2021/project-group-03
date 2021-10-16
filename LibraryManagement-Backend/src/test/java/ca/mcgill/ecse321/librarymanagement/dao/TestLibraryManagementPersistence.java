package ca.mcgill.ecse321.librarymanagement.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.LMS.LibraryManagementApplication;
import ca.mcgill.ecse321.librarymanagement.model.Book;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestLibraryManagementPersistence {

	@Autowired
	private BookRepository bookRepository;
	
	@AfterEach
	public void clearDatabase() {
		// Delete any DB used in the tests
		// Start by deleting databases that depend on other databases. Ex: Registrations. 2.4.3
		bookRepository.deleteAll();
	}
	
	//This test was written by lee and vass and is to be used as example for other tests.
	// we just want to test a Persist and load (saved after closed) to make sure the back end is set up properly.
	// follow the same steps for every other class
	@Test
	public void testPersistAndLoadBook() {
		//Start by creating a primary key.
		String bookId = "TestBook";
		
		//Create all constructor fields
		Date date = new Date(2001, 5, 1);	//Lees birthday in case you wanna get him a gift. 
		String image = "imageLink";
		String bookName = "bookName";
		String author = "author";
		String synopsis = "synopsis";
		String genre = "genre";
	
		
		//Create Book object with parameters. ^^
		Book book = new Book(date, image, bookName, bookId, author, synopsis, genre);
		
		//Save the book to the DB.
		bookRepository.save(book);
		
		//Get rid of the book.
		book = null;
		
		//Use the CRUD method to query the book from the DB. 
		book = bookRepository.findBookById(bookId);
		
		//Check that the object was properly queried from DB.
		assertNotNull(book);
		
		//Test that all the data was properly saved.
		assertEquals(date, book.getReleaseDate());
		assertEquals(image, book.getImage());
		assertEquals(bookName, book.getName());
		assertEquals(author, book.getAuthor());
		assertEquals(synopsis, book.getSynopsis());
		assertEquals(genre, book.getGenre());
		
	}
}









