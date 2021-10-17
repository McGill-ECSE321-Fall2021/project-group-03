package ca.mcgill.ecse321.librarymanagement.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.librarymanagement.LibraryManagementApplication;
import ca.mcgill.ecse321.librarymanagement.model.Book;
import ca.mcgill.ecse321.librarymanagement.model.Library;
import ca.mcgill.ecse321.librarymanagement.model.LibrarySchedule;
import ca.mcgill.ecse321.librarymanagement.model.Newspaper;
import ca.mcgill.ecse321.librarymanagement.model.Schedule;
import ca.mcgill.ecse321.librarymanagement.model.TimeSlot;
import ca.mcgill.ecse321.librarymanagement.model.User;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestLibraryManagementPersistence {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private MusicAlbumRepository musicAlbumRepository;
	
	@Autowired
	private NewspaperRepository newspaperRepository;
	
	@Autowired
	private LibraryScheduleRepository libraryScheduleRepository;
	
	@Autowired
	private RoomScheduleRepository roomScheduleRepository;
	
	@Autowired
	private StaffScheduleRepository staffScheduleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private HeadLibrarianRepository headLibrarianRepository;
	
	@Autowired
	private LibrarianRepository librarianRepository;
	
	@Autowired
	private TimeSlotRepository timeSlotRepository;
	
	@Autowired
	private LibraryRepository libraryRepository;
	
	@Autowired
	private RoomRepository roomRepository;
	
	
	@BeforeEach
	@AfterEach
	public void clearDatabase() {
		bookRepository.deleteAll();
	}
	
	/*
	 * 
	 * sam
	 * 
	 * 
	 * */
	
	@Test
	public void testPersistAndLoadBook() {
		
		//Create all constructor fields
		Date date = new Date(2001, 5, 1);
		String image = "imageLink";
		String bookName = "bookName";
		String author = "author";
		String synopsis = "synopsis";
		String genre = "genre";
		
		//Create Book object with parameters. ^^
		Book book = new Book(date, image, bookName, author, synopsis, genre);
				
		//Save the book to the DB.
		Book savedBook = bookRepository.save(book);
		int bookId = savedBook.getTitleId();
	
		//Get rid of the book.
		book = null;
		
		//Use the CRUD method to query the book from the DB. 
		book = bookRepository.findBookByTitleId(bookId);
						
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
	
	@Test
	public void testPersistAndLoadNewspaper() {
		
				//Create all constructor fields
				Date date = new Date(2001, 5, 1);
				String image = "imageLink";
				String newspaperName = "newspaperName";
				String headline = "headline";
				
				//Create newspaper object with parameters. ^^
				Newspaper newspaper = new Newspaper(date, image, newspaperName, headline);
						
				//Save the newspaper to the DB.
				Newspaper savedNewspaper = newspaperRepository.save(newspaper);
				int newspaperId = savedNewspaper.getTitleId();
			
				//Get rid of the book.
				newspaper = null;
				
				//Use the CRUD method to query the book from the DB. 
				newspaper = newspaperRepository.findNewspaperByTitleId(newspaperId);
								
				//Check that the object was properly queried from DB.
				assertNotNull(newspaper);
				
				//Test that all the data was properly saved.
				assertEquals(date, newspaper.getReleaseDate());
				assertEquals(image, newspaper.getImage());
				assertEquals(newspaperName, newspaper.getName());
				assertEquals(headline, newspaper.getHeadline());
		
	}
	
	@Test
	public void testPersistAndLoadMusicAlbum() {
		
	}
	
	@Test
	public void testPersistAndLoadMovie() {
		
	}
	
	
	/*
	 * 
	 * adam
	 * 
	 * 
	 * */
	
	@Test
	public void testPersistAndLoadUser() {
		
	}
	
	@Test
	public void testPersistAndLoadLibrarian() {
		
	}
	

	
	/*
	 * 
	 * liam
	 * 
	 * 
	 * */
	
	
	@Test
	public void testPersistAndLoadStaffSchedule() {
		
	}
	
	@Test
	public void testPersistAndLoadHeadLibrarian() {
		
	}
	
	/*
	 * 
	 * tara
	 * 
	 * 
	 * */
	
	@Test
	public void testPersistAndLoadRoom() {
		
	}
	
	@Test
	public void testPersistAndLoadRoomSchedule() {
		
	}
	
	/*
	 * 
	 * avi
	 * 
	 * 
	 * */
	
	@Test
	public void testPersistAndLoadLibrary() {
		
	}
	
	@Test
	public void testPersistAndLoadLibrarySchedule() {
		
	}
	
	@Test
	public void testPersistAndLoadTimeSlot() {
		
	}
	
	
	
}









