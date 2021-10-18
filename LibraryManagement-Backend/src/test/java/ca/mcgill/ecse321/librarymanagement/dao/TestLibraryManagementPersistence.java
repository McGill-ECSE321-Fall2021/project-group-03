package ca.mcgill.ecse321.librarymanagement.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.librarymanagement.LibraryManagementApplication;
import ca.mcgill.ecse321.librarymanagement.model.Book;
import ca.mcgill.ecse321.librarymanagement.model.Library;
import ca.mcgill.ecse321.librarymanagement.model.LibrarySchedule;
import ca.mcgill.ecse321.librarymanagement.model.Movie;
import ca.mcgill.ecse321.librarymanagement.model.MusicAlbum;
import ca.mcgill.ecse321.librarymanagement.model.Newspaper;
import ca.mcgill.ecse321.librarymanagement.model.Room;
import ca.mcgill.ecse321.librarymanagement.model.RoomSchedule;
import ca.mcgill.ecse321.librarymanagement.model.Schedule;
import ca.mcgill.ecse321.librarymanagement.model.StaffSchedule;
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
		
		//Create all constructor fields
		Date date = new Date(2001, 5, 1); //Lees birthday in case you wanna get him a gift.
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
		
		//Create all constructor fields
		Date date = new Date(2001, 5, 1);
		String image = "imageLink";
		String musicAlbumName = "musicAlbumName";
		String artist = "artist";
		int duration = 60;
		String genre = "genre";
		
		//Create album object with parameters. ^^
		MusicAlbum album = new MusicAlbum(date, image, musicAlbumName, artist, duration, genre);
		
		//Save the album to the DB.
		MusicAlbum savedAlbum = musicAlbumRepository.save(album);
		int albumId = savedAlbum.getTitleId();
			
		//Get rid of the album.
		album = null;
		
		//Use the CRUD method to query the album from the DB. 
		album = musicAlbumRepository.findMusicAlbumByTitleId(albumId);
						
		//Check that the object was properly queried from DB.
		assertNotNull(album);
		
		//Test that all the data was properly saved.
		assertEquals(date, album.getReleaseDate());
		assertEquals(image, album.getImage());
		assertEquals(musicAlbumName, album.getName());
		assertEquals(artist, album.getArtist());
		assertEquals(duration, album.getDuration());
		assertEquals(genre, album.getGenre());
	}
	
	@Test
	public void testPersistAndLoadMovie() {
		
		//Create all constructor fields
		Date date = new Date(2001, 5, 1);
		String image = "imageLink";
		String movieName = "movieName";
		String director = "director";
		int duration = 60;
		String synopsis = "synopsis";
		String genre = "genre";
		
		//Create movie object with parameters. ^^
		Movie movie = new Movie(date, image, movieName, director, genre, synopsis, duration);
				
		//Save the movie to the DB.
		Movie savedMovie = movieRepository.save(movie);
		int movieId = savedMovie.getTitleId();
	
		//Get rid of the movie.
		movie = null;
		
		//Use the CRUD method to query the book from the DB. 
		movie = movieRepository.findMovieByTitleId(movieId);
						
		//Check that the object was properly queried from DB.
		assertNotNull(movie);
		
		//Test that all the data was properly saved.
		assertEquals(date, movie.getReleaseDate());
		assertEquals(image, movie.getImage());
		assertEquals(movieName, movie.getName());
		assertEquals(director, movie.getDirector());
		assertEquals(duration, movie.getDuration());
		assertEquals(genre, movie.getGenre());
		assertEquals(synopsis, movie.getSynopsis());
		
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
		
		StaffSchedule staffSchedule = new StaffSchedule();
		
		StaffSchedule savedStaffSchedule = staffScheduleRepository.save(staffSchedule);
		int savedStaffScheduleId = savedStaffSchedule.getScheduleId();
		
		
		staffSchedule = null;
		
 
		staffSchedule = staffScheduleRepository.findStaffScheduleByScheduleId(savedStaffScheduleId);
	
		assertNotNull(staffSchedule);
		
		
	}
	
	
	@Test
	public void testPersistAndLoadHeadLibrarian() {
		
		LibrarySchedule libSchedule = new LibrarySchedule();
		StaffSchedule staffSchedule = new StaffSchedule();
		Library library = new Library(libSchedule);
		
		String aUsername = "aUsername";
		String aPassword  = "aPassword";
		String aEmailaddress = "aEmailaddress";
		String aFullName  = "aFullName";
		String aResAddress = "aResAddress";
		boolean aIsResident  = true;
		

		
		HeadLibrarian headLibrarian = new HeadLibrarian(aUsername,aPassword, aEmailaddress, aFullName, aResAddress,aIsResident,library, staffSchedule );
		
		LibrarySchedule savedLibrarySchedule = libraryScheduleRepository.save(libSchedule);
		Library savedLibrary = libraryRepository.save(library);
		StaffSchedule savedStaffSchedule = staffScheduleRepository.save(staffSchedule);
		HeadLibrarian savedHeadLibrarian = headLibrarianRepository.save(headLibrarian);
		
		
		int savedLibraryScheduleId = savedLibrarySchedule.getScheduleId();
		int savedLibraryId = savedLibrary.getLibraryId();
		int savedstaffScheduleId = savedStaffSchedule.getScheduleId();
		int savedHeadLibrarianId = savedHeadLibrarian.getUserId();

		libSchedule = null;
		library = null;
		staffSchedule =  null;
		headLibrarian = null;
		
		libSchedule = libraryScheduleRepository.findLibraryScheduleByScheduleId(savedLibraryScheduleId);
		library = libraryRepository.findLibraryByLibraryId(savedLibraryId);
		staffSchedule = staffScheduleRepository.findStaffScheduleByScheduleId(savedstaffScheduleId);
		headLibrarian = headLibrarianRepository.findHeadLibrarianByUserId(savedHeadLibrarianId);
		
		assertNotNull(headLibrarian);
		
		assertEquals(aUsername, headLibrarian.getUsername());
		assertEquals(aPassword, headLibrarian.getPassword());
		assertEquals(aEmailaddress, headLibrarian.getEmailaddress());
		assertEquals(aFullName, headLibrarian.getFullName());
		assertEquals(aResAddress, headLibrarian.getResAddress());
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
	@Transactional
	public void testPersistAndLoadLibrary() {
		
		 //make and save library schdule
		 LibrarySchedule librarySchedule = new LibrarySchedule();
		 LibrarySchedule savedLibrarySchedule = libraryScheduleRepository.save(librarySchedule);
		 
		 
		 //make and save library and get the libraryID
		 Library library = new Library(librarySchedule);
		 Library savedLibrary = libraryRepository.save(library);
		 int libraryId = savedLibrary.getLibraryId();
		 
		 //make and save 2 room schedules
		 RoomSchedule roomSchedule1 = new RoomSchedule();
		 RoomSchedule savedRoomSchedule1 = roomScheduleRepository.save(roomSchedule1);
		 RoomSchedule roomSchedule2 = new RoomSchedule();
		 RoomSchedule savedRoomSchedule2 = roomScheduleRepository.save(roomSchedule2);
		 
		 
		 //make and save 2 rooms and add them to the library
		 Room room1 = new Room(roomSchedule1,library);
		 Room savedRoom1 = roomRepository.save(room1);
		 Room room2 = new Room(roomSchedule2,library);
		 Room savedRoom2 = roomRepository.save(room2);
		 library.addRoom(room1);
		 library.addRoom(room2);
		 
		 
		 //make and save 2 users and add them to the library
		 User user1 = new User("Adam", "password", "hi@yahoo.com", "Abraham", "avenue", true,library );
		 User savedUser1 = userRepository.save(user1);
		 User user2 = new User("Sam", "password1", "hi1@yahoo.com", "Samuel", "avenue1", true,library );
		 User savedUser2 = userRepository.save(user2);
		 library.addUser(user1);
		 library.addUser(user2);
		 
		 
		 //make and save 2 titles and add them to the library
		 Date date1 = new Date(2001, 2, 15);
		 Date date2 = new Date(2001, 7, 10);
		 Newspaper title1 = new Newspaper(date1, "image1", "SamV", "headline1");
		 Newspaper savedNewspaper1 = newspaperRepository.save(title1);
		 
		 Newspaper title2 = new Newspaper(date2, "image2", "AdamG", "headline2");
		 Newspaper savedNewspaper2 = newspaperRepository.save(title2);
		 //MusicAlbum title2 = new MusicAlbum(date2, "image2", "Music Album", "Adam Geenen", 5, "Horror");
		 //MusicAlbum savedMovie = musicAlbumRepository.save(title2);
		 library.addTitle(title1);
		 library.addTitle(title2);

		
		 //make the library null and then retrieve it from the database
		 library = null;
		 library = libraryRepository.findLibraryByLibraryId(libraryId);
		 
		 //assert that the library is not null
		 assertNotNull(library);
		 
		 //assert that the retrieved library has the correct library schedule
		 assertEquals(libraryId, library.getLibraryId());
		 
		 //assert that the retrieve library has the 2 room schedules in respective rooms
		 assertEquals(roomSchedule1.getScheduleId(), library.getRoom(0).getRoomSchedule().getScheduleId());
		 assertEquals(roomSchedule2.getScheduleId(), library.getRoom(1).getRoomSchedule().getScheduleId());
		 
		 //assert that the retrieved library has the correct rooms
		 assertEquals(room1.getRoomId(), library.getRoom(0).getRoomId());
		 assertEquals(room2.getRoomId(), library.getRoom(1).getRoomId());
		 
		 //assert that the retrieved library has the correct users with the corresponding fields
		 //user 1
		 assertEquals(user1.getUserId(), library.getUser(0).getUserId());
		 assertEquals("Adam", library.getUser(0).getUsername());
		 assertEquals("password", library.getUser(0).getPassword());
		 assertEquals("hi@yahoo.com", library.getUser(0).getEmailaddress());
		 assertEquals("Abraham", library.getUser(0).getFullName());
		 assertEquals("avenue", library.getUser(0).getResAddress());
		 assertEquals(true, library.getUser(0).getIsResident());
		 assertEquals(library, library.getUser(0).getLibrary());
		 
		 //user 2
		 assertEquals(user2.getUserId(), library.getUser(1).getUserId());
		 assertEquals("Sam", library.getUser(1).getUsername());
		 assertEquals("password1", library.getUser(1).getPassword());
		 assertEquals("hi1@yahoo.com", library.getUser(1).getEmailaddress());
		 assertEquals("Samuel", library.getUser(1).getFullName());
		 assertEquals("avenue1", library.getUser(1).getResAddress());
		 assertEquals(true, library.getUser(1).getIsResident());
		 assertEquals(library, library.getUser(1).getLibrary());
		 
		// assert that the retrieved library has the two titles and their fields
		 
		 //title1 (newspaper)
		 assertEquals(title1.getTitleId(), library.getTitle(0).getTitleId());
		 assertEquals(date1, library.getTitle(0).getReleaseDate());
		 assertEquals("image1", library.getTitle(0).getImage());
		 assertEquals("SamV", library.getTitle(0).getName());
		 assertEquals("headline1", ((Newspaper) library.getTitle(0)).getHeadline());
 
		 //title2 (newspaper)
		 assertEquals(title2.getTitleId(), library.getTitle(1).getTitleId());
		 assertEquals(date2, library.getTitle(1).getReleaseDate());
		 assertEquals("image2", library.getTitle(1).getImage());
		 assertEquals("AdamG", library.getTitle(1).getName());
		 assertEquals("headline2", ((Newspaper) library.getTitle(1)).getHeadline());
	}
	
	@Test
	@Transactional
	public void testPersistAndLoadLibrarySchedule() {
		 LibrarySchedule librarySchedule = new LibrarySchedule();
		 
		 //create a first timeslot and add it to the library
		 Time startTime1 = new Time(4, 30, 30);
		 Time endTime1 = new Time(8, 30, 30);
		 int dayOfWeek1 = 5;
		 Date date1 = new Date(1999, 2, 26);
		 librarySchedule.addTimeSlot(new TimeSlot(startTime1, endTime1, dayOfWeek1, date1, librarySchedule));
		
		 //create a first timeslot and add it to the library
		 Time startTime2 = new Time(9, 30, 30);
		 Time endTime2 = new Time(11, 30, 30);
		 int dayOfWeek2 = 6;
		 Date date2 = new Date(2001, 7, 10);
		 librarySchedule.addTimeSlot(new TimeSlot(startTime2, endTime2, dayOfWeek2, date2, librarySchedule));
		 
		 //save the library schedule and gets its ID
		 LibrarySchedule savedLibrarySchedule = libraryScheduleRepository.save(librarySchedule);
		 int libraryScheduleId = savedLibrarySchedule.getScheduleId();
		 
		 librarySchedule = null;
		 librarySchedule = libraryScheduleRepository.findLibraryScheduleByScheduleId(libraryScheduleId);
		 
		 assertNotNull(librarySchedule);
		 
		 //assert the schedule retrieved is the same schedule save
		 assertEquals(libraryScheduleId, librarySchedule.getScheduleId());
		 
		 //assert the first timeslot is the same as well as its fields
		 assertEquals(startTime1, librarySchedule.getTimeSlot(0).getStartTime());
		 assertEquals(endTime1, librarySchedule.getTimeSlot(0).getEndTime());
		 assertEquals(dayOfWeek1, librarySchedule.getTimeSlot(0).getDayOfWeek());
		 assertEquals(date1, librarySchedule.getTimeSlot(0).getDate());
		 
		 //assert the second timeslot is the same as well as its fields
		 assertEquals(startTime2, librarySchedule.getTimeSlot(1).getStartTime());
		 assertEquals(endTime2, librarySchedule.getTimeSlot(1).getEndTime());
		 assertEquals(dayOfWeek2, librarySchedule.getTimeSlot(1).getDayOfWeek());
		 assertEquals(date2, librarySchedule.getTimeSlot(1).getDate());
	}
	
	@Test
	public void testPersistAndLoadTimeSlot() {
		 //create the library schedule and save it
		 LibrarySchedule librarySchedule = new LibrarySchedule();
		 LibrarySchedule savedLibrarySchedule = libraryScheduleRepository.save(librarySchedule);

		 //create the TimeSlot as well as its field	
		 Time startTime = new Time(4, 30, 30);
		 Time endTime = new Time(8, 30, 30);
		 int dayOfWeek = 5;
		 Date date = new Date(1999, 2, 26);
		 TimeSlot timeSlot = new TimeSlot(startTime, endTime, dayOfWeek, date, librarySchedule);
		 
		 TimeSlot savedTimeSlot = timeSlotRepository.save(timeSlot);
		 int timeSlotId = savedTimeSlot.getTimeSlotId();
		 
		 timeSlot = null;
		 timeSlot = timeSlotRepository.findTimeSlotByTimeSlotId(timeSlotId);
		 
		 assertNotNull(timeSlot);
		 
		 assertEquals(timeSlotId,timeSlot.getTimeSlotId());
		 assertEquals(startTime, timeSlot.getStartTime());
		 assertEquals(endTime, timeSlot.getEndTime());
		 assertEquals(dayOfWeek, timeSlot.getDayOfWeek());
		 assertEquals(date, timeSlot.getDate());
		 
		 assertEquals(librarySchedule.getScheduleId(), timeSlot.getSchedule().getScheduleId());
	}
	
	
	
}