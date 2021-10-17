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
		timeSlotRepository.deleteAll();
		bookRepository.deleteAll();
		movieRepository.deleteAll();
		musicAlbumRepository.deleteAll();
		newspaperRepository.deleteAll();
		roomRepository.deleteAll();
		headLibrarianRepository.deleteAll();
		librarianRepository.deleteAll();
		userRepository.deleteAll();
		libraryRepository.deleteAll();
		roomScheduleRepository.deleteAll();
		staffScheduleRepository.deleteAll();
		libraryScheduleRepository.deleteAll();
	}
	
	/*
	 * 
	 * sam
	 * 
	 * 
	 * */
	
	@Test
	public void testPersistAndLoadBook() {
		
//		//Create all constructor fields
		Date date = new Date(2001, 5, 1);	//Lees birthday in case you wanna get him a gift. 
		String image = "imageLink";
		String bookName = "bookName";
		String author = "author";
		String synopsis = "synopsis";
		String genre = "genre";
		
		//Create Book object with parameters. ^^
		Book book = new Book(date, image, bookName, author, synopsis, genre);
				
		//Save the book to the DB.
		Book savedBook = bookRepository.save(book);
		int savedBookId = savedBook.getTitleId();
	
		//Get rid of the book.
		book = null;
		
		//Use the CRUD method to query the book from the DB. 
		book = bookRepository.findBookByTitleId(savedBookId);
						
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
		String userUsername = "geener";
		String userPassword = "pass";
		String userEmailaddress = "adam@gmail.com";
		String userFullName = "Adam Geenen";
		String userResAddress = "kildare";
		boolean resident = true;
		
		LibrarySchedule librarySchedule = new LibrarySchedule();
		Library library = new Library(librarySchedule);
		User user = new User(userUsername, userPassword, userEmailaddress, userFullName, userResAddress, resident, library);
		
		LibrarySchedule savedLibrarySchedule = libraryScheduleRepository.save(librarySchedule);
		Library savedLibrary = libraryRepository.save(library);
		User savedUser = userRepository.save(user);
		
		int savedLibraryScheduleId = savedLibrarySchedule.getScheduleId();
		int savedLibraryId = savedLibrary.getLibraryId();
		int savedUserId = savedUser.getUserId();

		librarySchedule = null;
		library = null;
		user = null;
		
		librarySchedule = libraryScheduleRepository.findLibraryScheduleByScheduleId(savedLibraryScheduleId);
		library = libraryRepository.findLibraryByLibraryId(savedLibraryId);
		user = userRepository.findUserByUserId(savedUserId);
				
		System.out.println(user);
		
		assertNotNull(user);
		
		assertEquals(userUsername, user.getUsername());
		assertEquals(userPassword, user.getPassword());
		assertEquals(userEmailaddress, user.getEmailaddress());
		assertEquals(userFullName, user.getFullName());
		assertEquals(userResAddress, user.getResAddress());
	}
	
	@Test
	public void testPersistAndLoadLibrarian() {
		String librarianUsername = "geener";
		String librarianPassword = "pass";
		String librarianEmailaddress = "adam@gmail.com";
		String librarianFullName = "Adam Geenen";
		String librarianResAddress = "kildare";
		boolean resident = true;
		
		LibrarySchedule librarySchedule = new LibrarySchedule();
		Library library = new Library(librarySchedule);
		StaffSchedule staffSchedule = new StaffSchedule();
		Librarian librarian = new Librarian(librarianUsername, librarianPassword, librarianEmailaddress, librarianFullName, librarianResAddress, resident, library, staffSchedule);
		
		LibrarySchedule savedLibrarySchedule = libraryScheduleRepository.save(librarySchedule);
		Library savedLibrary = libraryRepository.save(library);
		StaffSchedule savedStaffSchedule = staffScheduleRepository.save(staffSchedule);
		Librarian savedLibrarian = userRepository.save(librarian);
		
		int savedLibraryScheduleId = savedLibrarySchedule.getScheduleId();
		int savedLibraryId = savedLibrary.getLibraryId();
		int savedstaffScheduleId = savedStaffSchedule.getScheduleId();
		int savedLibrarianId = savedLibrarian.getUserId();

		librarySchedule = null;
		library = null;
		staffSchedule =  null;
		librarian = null;
		
		librarySchedule = libraryScheduleRepository.findLibraryScheduleByScheduleId(savedLibraryScheduleId);
		library = libraryRepository.findLibraryByLibraryId(savedLibraryId);
		staffSchedule = staffScheduleRepository.findStaffScheduleByScheduleId(savedstaffScheduleId);
		librarian = librarianRepository.findLibrarianByUserId(savedLibrarianId);
				
		System.out.println(librarian);
		
		assertNotNull(librarian);
		
		assertEquals(librarianUsername, librarian.getUsername());
		assertEquals(librarianPassword, librarian.getPassword());
		assertEquals(librarianEmailaddress, librarian.getEmailaddress());
		assertEquals(librarianFullName, librarian.getFullName());
		assertEquals(librarianResAddress, librarian.getResAddress());
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
		
		LibrarySchedule librarySchedule = new LibrarySchedule();
		Library library = new Library(librarySchedule);
		RoomSchedule roomSchedule = new RoomSchedule();
		Room room = new Room(roomSchedule, library);
		
		LibrarySchedule savedLibrarySchedule = libraryScheduleRepository.save(librarySchedule);
		Library savedLibrary = libraryRepository.save(library);
		RoomSchedule savedRoomSchedule = roomScheduleRepository.save(roomSchedule);
		Room savedRoom = roomRepository.save(room);

		int savedLibraryScheduleId = savedLibrarySchedule.getScheduleId();
		int savedLibraryId = savedLibrary.getLibraryId();
		int savedRoomScheduleId = savedRoomSchedule.getScheduleId();
		int savedRoomId = savedRoom.getRoomId();
		
		librarySchedule = null;
		library = null;
		roomSchedule = null;
		room = null;
		
		librarySchedule = libraryScheduleRepository.findLibraryScheduleByScheduleId(savedLibraryScheduleId);
		library = libraryRepository.findLibraryByLibraryId(savedLibraryId);
		roomSchedule = roomScheduleRepository.findRoomScheduleByScheduleId(savedRoomScheduleId);
		room = roomRepository.findRoomByRoomId(savedRoomId);
		
		assertNotNull(librarySchedule);
		assertNotNull(library);
		assertNotNull(roomSchedule);
		assertNotNull(room);
		
		assertEquals(roomSchedule.getScheduleId(), room.getRoomSchedule().getScheduleId());
		assertEquals(library.getLibraryId(), room.getLibrary().getLibraryId());			
	}
	
	@Test
	public void testPersistAndLoadRoomSchedule() {
		
		RoomSchedule roomSchedule = new RoomSchedule();
		RoomSchedule savedRoomSchedule = roomScheduleRepository.save(roomSchedule);
		int savedRoomScheduleId = savedRoomSchedule.getScheduleId();
		
		roomSchedule = null;
		
		roomSchedule = roomScheduleRepository.findRoomScheduleByScheduleId(savedRoomScheduleId);
		
		assertNotNull(roomSchedule);
		assertEquals(savedRoomScheduleId, roomSchedule.getScheduleId());
		
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









