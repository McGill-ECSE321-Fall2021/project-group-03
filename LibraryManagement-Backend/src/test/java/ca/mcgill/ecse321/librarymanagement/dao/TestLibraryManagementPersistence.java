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
import ca.mcgill.ecse321.librarymanagement.model.Room;
import ca.mcgill.ecse321.librarymanagement.model.RoomSchedule;
import ca.mcgill.ecse321.librarymanagement.model.Schedule;
import ca.mcgill.ecse321.librarymanagement.model.TimeSlot;
import ca.mcgill.ecse321.librarymanagement.model.User;
import ca.mcgill.ecse321.librarymanagement.model.Librarian;
import ca.mcgill.ecse321.librarymanagement.model.HeadLibrarian;
import ca.mcgill.ecse321.librarymanagement.model.MusicAlbum;
import ca.mcgill.ecse321.librarymanagement.model.Movie;
import ca.mcgill.ecse321.librarymanagement.model.Newspaper;
import ca.mcgill.ecse321.librarymanagement.model.StaffSchedule;
import ca.mcgill.ecse321.librarymanagement.model.Title;

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
		//libraryRepository.deleteAll();
		//timeSlotRepository.deleteAll();
		//libraryScheduleRepository.deleteAll();

		//bookRepository.deleteAll();
		//movieRepository.deleteAll();
		//musicAlbumRepository.deleteAll();
		//newspaperRepository.deleteAll();
		//roomRepository.deleteAll();
		//headLibrarianRepository.deleteAll();
		//librarianRepository.deleteAll();
		//userRepository.deleteAll();
		//roomScheduleRepository.deleteAll();
		//staffScheduleRepository.deleteAll();
	}
	
	/*
	 * 
	 * sam
	 * 
	 * 
	 * */
	
//	@Test
//	public void testPersistAndLoadBook() {
//		
//		//Create all constructor fields
//		Date date = new Date(2001, 5, 1);	//Lees birthday in case you wanna get him a gift. 
//		String image = "imageLink";
//		String bookName = "bookName";
//		String author = "author";
//		String synopsis = "synopsis";
//		String genre = "genre";
//		
//		//Create Book object with parameters. ^^
//		Book book = new Book(date, image, bookName, author, synopsis, genre);
//				
//		//Save the book to the DB.
//		Book savedBook = bookRepository.save(book);
//		int savedBookId = savedBook.getTitleId();
//	
//		//Get rid of the book.
//		book = null;
//		
//		//Use the CRUD method to query the book from the DB. 
//		book = bookRepository.findBookByTitleId(savedBookId);
//						
//		//Check that the object was properly queried from DB.
//		assertNotNull(book);
//		
//		//Test that all the data was properly saved.
//		assertEquals(date, book.getReleaseDate());
//		assertEquals(image, book.getImage());
//		assertEquals(bookName, book.getName());
//		assertEquals(author, book.getAuthor());
//		assertEquals(synopsis, book.getSynopsis());
//		assertEquals(genre, book.getGenre());
//
//	}
//	
//	@Test
//	public void testPersistAndLoadNewspaper() {
//		
//	}
//	
//	@Test
//	public void testPersistAndLoadMusicAlbum() {
//		
//	}
//	
//	@Test
//	public void testPersistAndLoadMovie() {
//		
//	}
//	
//	
//	/*
//	 * 
//	 * adam
//	 * 
//	 * 
//	 * */
//	
//	@Test
//	public void testPersistAndLoadUser() {
//		
//	}
//	
//	@Test
//	public void testPersistAndLoadLibrarian() {
//		
//	}
//	
//
//	
//	/*
//	 * 
//	 * liam
//	 * 
//	 * 
//	 * */
//	
//	
//	@Test
//	public void testPersistAndLoadStaffSchedule() {
//		
//	}
//	
//	@Test
//	public void testPersistAndLoadHeadLibrarian() {
//		
//	}
//	
//	/*
//	 * 
//	 * tara
//	 * 
//	 * 
//	 * */
//	
//	@Test
//	public void testPersistAndLoadRoom() {
//		
//		RoomSchedule roomSchedule = new RoomSchedule();
//		LibrarySchedule librarySchedule = new LibrarySchedule();
//		Library library = new Library(librarySchedule);
//		
//		Room room = new Room(roomSchedule, library);
//		
//		
//		
//	}
//	
//	@Test
//	public void testPersistAndLoadRoomSchedule() {
//		LibrarySchedule librarySchedule = new LibrarySchedule();
//		
//		LibrarySchedule savedLibrarySchedule = libraryScheduleRepository.save(librarySchedule);
//		int libraryScheduleID = savedLibrarySchedule.getScheduleId();
//		
//		librarySchedule = null;
//		
//		//a library shcedule has time slots
//		//so you want to check that the list of time slots is the same
//	}
	
	/*
	 * 
	 * avi
	 * 
	 * 
	 * */
//	@Test
//	public void testPersistAndLoadLibrarySchedule() {
//		
//	}
//	
//	@Test
//	public void testPersistAndLoadLibrary() {
//		//Library needs Library Schedule
//	}

	
	@Test
	public void testPersistAndLoadTimeSlot() {
		LibrarySchedule librarySchedule = new LibrarySchedule();
		
		 Time startTime = new Time(4, 30, 30);
		 Time endTime = new Time(8, 30, 30);
		 int dayOfWeek = 5;
		 Date date = new Date(1999, 2, 26);
		 
		 TimeSlot timeSlot = new TimeSlot(startTime, endTime, dayOfWeek, date, librarySchedule);
		 
		// LibrarySchedule savedLibrarySchedule = libraryScheduleRepository.save(librarySchedule);

		 TimeSlot savedTimeSlot = timeSlotRepository.save(timeSlot);
		 int timeSlotId = savedTimeSlot.getTimeSlotId();
		 
		 timeSlot = null;
		 timeSlot = timeSlotRepository.findTimeSlotByTimeSlotId(timeSlotId);
		 
		// int libraryScheduleId = savedLibrarySchedule.getScheduleId();
		 
		// librarySchedule = null;
		 
	//	 librarySchedule = libraryScheduleRepository.findLibraryScheduleByScheduleId(libraryScheduleId);
		 

		 
		 assertNotNull(timeSlot);
		 
		 assertEquals(startTime, timeSlot.getStartTime());
		 assertEquals(endTime, timeSlot.getEndTime());
		 assertEquals(dayOfWeek, timeSlot.getDayOfWeek());
		 assertEquals(date, timeSlot.getDate());
		 assertEquals(startTime, timeSlot.getStartTime());
		 assertEquals(librarySchedule, timeSlot.getSchedule());


		 
		 
	
	}
	
	
	
}









