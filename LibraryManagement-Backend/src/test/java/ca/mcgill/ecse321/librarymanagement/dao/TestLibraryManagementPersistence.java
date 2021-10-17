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
import ca.mcgill.ecse321.librarymanagement.model.Movie;
import ca.mcgill.ecse321.librarymanagement.model.MusicAlbum;
import ca.mcgill.ecse321.librarymanagement.model.Newspaper;
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









