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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.librarymanagement.model.Client;
import ca.mcgill.ecse321.librarymanagement.model.Librarian;
import ca.mcgill.ecse321.librarymanagement.model.Library;
import ca.mcgill.ecse321.librarymanagement.model.Room;
import ca.mcgill.ecse321.librarymanagement.model.Room.RoomType;
import ca.mcgill.ecse321.librarymanagement.model.RoomReservation;
import ca.mcgill.ecse321.librarymanagement.model.Schedule;
import ca.mcgill.ecse321.librarymanagement.model.Timeslot;
import ca.mcgill.ecse321.librarymanagement.model.Title;
import ca.mcgill.ecse321.librarymanagement.model.Title.TitleType;
import ca.mcgill.ecse321.librarymanagement.model.TitleReservation;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestLibraryManagementPersistence {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private LibrarianRepository librarianRepository;

	@Autowired
	private TimeslotRepository timeslotRepository;

	@Autowired
	private LibraryRepository libraryRepository;

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private RoomReservationRepository roomReservationRepository;

	@Autowired
	private TitleReservationRepository titleReservationRepository;

	@Autowired
	private ScheduleRepository scheduleRepository;

	@Autowired
	private TitleRepository titleRepository;

	@BeforeEach
	@AfterEach
	public void clearDatabase() {
		
		titleReservationRepository.deleteAll();
		libraryRepository.deleteAll();
		roomReservationRepository.deleteAll();
		clientRepository.deleteAll();
		librarianRepository.deleteAll();
		titleRepository.deleteAll();
		scheduleRepository.deleteAll();
		timeslotRepository.deleteAll();
		roomRepository.deleteAll();
	}

	@Test
	public void testPersistAndLoadBook() {

		// Create all constructor fields
		String name = "moby dick";
		String description = "whale eats guy";
		String genre = "adventure";
		TitleType titleType = TitleType.Book;
		Library library = new Library();

		// Create Title object with parameters. ^^
		Title book = new Title(name, description, genre, true, titleType);
		library.addTitle(book);
		// Save the title to the DB.
		libraryRepository.save(library);
		Title savedBook = titleRepository.save(book);
		int bookId = savedBook.getTitleId();

		// Get rid of the title.
		book = null;

		// Use the CRUD method to query the title from the DB.
		book = titleRepository.findTitleByTitleId(bookId);

		// Check that the object was properly queried from DB.
		assertNotNull(book);

		// Test that all the data was properly saved.
		assertEquals(name, book.getName());
		assertEquals(description, book.getDescription());
		assertEquals(genre, book.getGenre());
		assertEquals(titleType, book.getTitleType());

	}

	@Test
	public void testPersistAndLoadNewspaper() {

		// Create all constructor fields
		String name = "moby dick";
		String description = "whale eats guy";
		String genre = "adventure";
		TitleType titleType = TitleType.Newspaper;
		Library library = new Library();

		// Create Title object with parameters. ^^
		Title newspaper = new Title(name, description, genre, true, titleType);
		library.addTitle(newspaper);
		// Save the title to the DB.
		libraryRepository.save(library);
		Title savedNewspaper = titleRepository.save(newspaper);
		int newspaperId = savedNewspaper.getTitleId();

		// Get rid of the title.
		newspaper = null;

		// Use the CRUD method to query the title from the DB.
		newspaper = titleRepository.findTitleByTitleId(newspaperId);

		// Check that the object was properly queried from DB.
		assertNotNull(newspaper);

		// Test that all the data was properly saved.
		assertEquals(name, newspaper.getName());
		assertEquals(description, newspaper.getDescription());
		assertEquals(genre, newspaper.getGenre());
		assertEquals(titleType, newspaper.getTitleType());

	}

	@Test
	public void testPersistAndLoadMusicAlbum() {

		// Create all constructor fields
		String name = "moby dick";
		String description = "whale eats guy";
		String genre = "adventure";
		TitleType titleType = TitleType.MusicAlbum;
		Library library = new Library();

		// Create Title object with parameters. ^^
		Title musicAlbum = new Title(name, description, genre, true, titleType);
		library.addTitle(musicAlbum);


		// Save the title to the DB.
		libraryRepository.save(library);
		Title savedMusicAlbum = titleRepository.save(musicAlbum);
		int musicAlbumId = savedMusicAlbum.getTitleId();

		// Get rid of the title.
		musicAlbum = null;

		// Use the CRUD method to query the title from the DB.
		musicAlbum = titleRepository.findTitleByTitleId(musicAlbumId);

		// Check that the object was properly queried from DB.
		assertNotNull(musicAlbum);

		// Test that all the data was properly saved.
		assertEquals(name, musicAlbum.getName());
		assertEquals(description, musicAlbum.getDescription());
		assertEquals(genre, musicAlbum.getGenre());
		assertEquals(titleType, musicAlbum.getTitleType());
	}

	@Test
	public void testPersistAndLoadMovie() {

		// Create all constructor fields
		String name = "moby dick";
		String description = "whale eats guy";
		String genre = "adventure";
		TitleType titleType = TitleType.Movie;
		Library library = new Library();

		// Create Title object with parameters. ^^
		Title movie = new Title(name, description, genre, true, titleType);
		library.addTitle(movie);


		// Save the title to the DB.
		libraryRepository.save(library);
		Title savedMovie = titleRepository.save(movie);
		int movieId = savedMovie.getTitleId();

		// Get rid of the title.
		movie = null;

		// Use the CRUD method to query the title from the DB.
		movie = titleRepository.findTitleByTitleId(movieId);

		// Check that the object was properly queried from DB.
		assertNotNull(movie);

		// Test that all the data was properly saved.
		assertEquals(name, movie.getName());
		assertEquals(description, movie.getDescription());
		assertEquals(genre, movie.getGenre());
		assertEquals(titleType, movie.getTitleType());

	}

	@Test
	public void testPersistAndLoadArchive() {

		// Create all constructor fields
		String name = "moby dick";
		String description = "whale eats guy";
		String genre = "adventure";
		TitleType titleType = TitleType.Movie;
		Library library = new Library();

		// Create Title object with parameters. ^^
		Title archive = new Title(name, description, genre, true, titleType);
		library.addTitle(archive);

		// Save the title to the DB.
		libraryRepository.save(library);
		Title savedArchive = titleRepository.save(archive);
		int archiveId = savedArchive.getTitleId();

		// Get rid of the title.
		archive = null;

		// Use the CRUD method to query the title from the DB.
		archive = titleRepository.findTitleByTitleId(archiveId);

		// Check that the object was properly queried from DB.
		assertNotNull(archive);

		// Test that all the data was properly saved.
		assertEquals(name, archive.getName());
		assertEquals(description, archive.getDescription());
		assertEquals(genre, archive.getGenre());
		assertEquals(titleType, archive.getTitleType());

	}

	@Test
	public void testPersistAndLoadClient() {
		String userUsername = "geener";
		String userPassword = "pass";
		String userEmailaddress = "adam@gmail.com";
		String userFullName = "Adam Geenen";
		String userResAddress = "kildare";
		boolean isResident = true;
		Library library = new Library();
		boolean isOnline = true;

		Client client = new Client(userUsername, userPassword, userFullName, userResAddress, userEmailaddress,
				isResident, isOnline);

		library.addUser(client);
		libraryRepository.save(library);
		Client savedClient = clientRepository.save(client);

		int savedClientId = savedClient.getUserId();

		client = null;

		client = clientRepository.findClientByUserId(savedClientId);

		assertNotNull(client);

		assertEquals(userUsername, client.getUsername());
		assertEquals(userPassword, client.getPassword());
		assertEquals(userFullName, client.getFullname());
		assertEquals(userResAddress, client.getResidentialAddress());
		assertEquals(userEmailaddress, client.getEmail());
		assertEquals(isResident, client.getIsResident());
		assertEquals(isOnline, client.getIsOnline());

	}

	@Test
	public void testPersistAndLoadLibrarian() {
		String librarianUsername = "geener";
		String librarianPassword = "pass";
		String librarianFullName = "Adam Geenen";
		boolean isHeadLibrarian = false;

		Library library = new Library();
		Librarian librarian = new Librarian(librarianUsername, librarianPassword, librarianFullName,
				isHeadLibrarian);

		library.addUser(librarian);
		libraryRepository.save(library);
		Librarian savedLibrarian = librarianRepository.save(librarian);

		int savedLibrarianId = savedLibrarian.getUserId();

		librarian = null;

		librarian = librarianRepository.findLibrarianByUserId(savedLibrarianId);

		assertNotNull(librarian);

		assertEquals(librarianUsername, librarian.getUsername());
		assertEquals(librarianPassword, librarian.getPassword());
		assertEquals(librarianFullName, librarian.getFullname());
		assertEquals(isHeadLibrarian, librarian.getIsHeadLibrarian());
	}

	@Test
	public void testPersistAndLoadHeadLibrarian() {
		String librarianUsername = "geener";
		String librarianPassword = "pass";
		String librarianFullName = "Adam Geenen";
		boolean isHeadLibrarian = true;

		Library library = new Library();
		Librarian librarian = new Librarian(librarianUsername, librarianPassword, librarianFullName,
				isHeadLibrarian);

		library.addUser(librarian);
		libraryRepository.save(library);
		Librarian savedLibrarian = librarianRepository.save(librarian);

		int savedLibrarianId = savedLibrarian.getUserId();

		librarian = null;

		librarian = librarianRepository.findLibrarianByUserId(savedLibrarianId);

		assertNotNull(librarian);

		assertEquals(librarianUsername, librarian.getUsername());
		assertEquals(librarianPassword, librarian.getPassword());
		assertEquals(librarianFullName, librarian.getFullname());
		assertEquals(isHeadLibrarian, librarian.getIsHeadLibrarian());
	}

	@Test
	public void testPersistAndLoadSchedule() {

		// create a schedule. Save it to persistence. Then make it null and retrieve it.
		Library library = new Library();
		Schedule schedule = new Schedule();
		library.setLibrarySchedule(schedule);

		libraryRepository.save(library);
		Schedule savedSchedule = scheduleRepository.save(schedule);

		int savedScheduleId = savedSchedule.getScheduleId();

		schedule = null;

		schedule = scheduleRepository.findScheduleByScheduleId(savedScheduleId);

		assertNotNull(schedule);
	}

	@Test
	public void testPersistAndLoadRoom() {

		// Create room

		Library library = new Library();
		int capacity = 10;
		boolean isAvailable = true;
		RoomType roomType = RoomType.Study;
		Room room = new Room(capacity, isAvailable, roomType);

		// create a room and save it to persistence
		library.addRoom(room);
		libraryRepository.save(library);
		Room savedRoom = roomRepository.save(room);

		int savedRoomId = savedRoom.getRoomId();

		room = null;

		room = roomRepository.findRoomByRoomId(savedRoomId);

		assertNotNull(room);

		assertEquals(capacity, room.getCapacity());
		assertEquals(isAvailable, room.getIsAvailable());
		assertEquals(roomType, room.getRoomType());
	}

	@Test
	public void testPersistAndLoadLibrary() {
		// make and save library and get the libraryID
		Library library = new Library();
		int libraryId = libraryRepository.save(library).getLibraryId();

		library = null;

		Library savedLibrary = libraryRepository.findLibraryByLibraryId(libraryId);

		assertNotNull(savedLibrary);
		assertEquals(libraryId, savedLibrary.getLibraryId());

	}

	@Test
	public void testPersistAndLoadTimeSlot() {

		Time startTime = new Time(4, 30, 30);
		Time endTime = new Time(8, 30, 30);
		Date date = new Date(1999, 2, 26);
		Timeslot timeSlot = new Timeslot(startTime, endTime, date);

		Timeslot savedTimeSlot = timeslotRepository.save(timeSlot);
		int timeSlotId = savedTimeSlot.getTimeSlotId();

		timeSlot = null;
		timeSlot = timeslotRepository.findTimeslotByTimeslotId(timeSlotId);

		assertNotNull(timeSlot);

		assertEquals(timeSlotId, timeSlot.getTimeSlotId());
		assertEquals(startTime, timeSlot.getStartTime());
		assertEquals(endTime, timeSlot.getEndTime());
		assertEquals(date, timeSlot.getDate());
	}

	@Test
	public void testPersistAndLoadTitleReservation() {
		Date returnDate = new Date(2021, 2, 4);
		boolean isCheckedOut = false;
		String name = "moby dick";
		String description = "whale eats guy";
		String genre = "adventure";
		boolean isAvailable = false;
		TitleType titleType = TitleType.Movie;
		Library library = new Library();
		
		Title title = new Title(name, description, genre, isAvailable, titleType);
		String username = "tara";
		String password = "hello";
		String fullname = "tara ginsberg";
		String residentialAddress = "123 hello street";
		String email = "tara@gmail.com";
		boolean isResident = true;
		boolean isOnline = false;
		
		Client client = new Client(username, password, fullname, residentialAddress, email, isResident,
				isOnline);

		
		TitleReservation titleReservation = new TitleReservation(returnDate, isCheckedOut, title, client);

		
		library.addTitle(title);
		library.addUser(client);
		libraryRepository.save(library);
		TitleReservation savedTitleReservation = titleReservationRepository.save(titleReservation);

		int savedTitleReservationId = savedTitleReservation.getTitleReservationId();

		titleReservation = null;
		titleReservation = titleReservationRepository.findTitleReservationByTitleReservationId(savedTitleReservationId);

		assertNotNull(titleReservation);
		assertEquals(returnDate, titleReservation.getReturnDate());
		assertEquals(isCheckedOut, titleReservation.getIsCheckedOut());
		assertEquals(title.getTitleId(), titleReservation.getTitle().getTitleId());
		assertEquals(client.getUserId(), titleReservation.getClient().getUserId());

	}

	@Test
	public void testPersistAndLoadRoomReservation() {

		// Create room reservation
		Time startTime = new Time(2, 0, 0);
		Time endTime = new Time(5, 0, 0);
		Date date = new Date(2021, 1, 2);
		Library library = new Library();
		int capacity = 5;
		boolean isAvailable = true;
		RoomType roomType = RoomType.Event;
		String username = "tara";
		String password = "hello";
		String fullname = "tara ginsberg";
		String residentialAddress = "123 hello street";
		String email = "tara@gmail.com";
		boolean isResident = true;
		boolean isOnline = false;

		Room room = new Room(capacity, isAvailable, roomType);
		Client client = new Client(username, password, fullname, residentialAddress, email, isResident,
				isOnline);
		
		RoomReservation roomReservation = new RoomReservation(startTime, endTime, date, room, client);

		library.addUser(client);
		library.addRoom(room);
		libraryRepository.save(library);
		RoomReservation savedRoomReservation = roomReservationRepository.save(roomReservation);

		int savedRoomReservationId = savedRoomReservation.getTimeSlotId();

		roomReservation = null;
		roomReservation = roomReservationRepository.findRoomReservationByTimeslotId(savedRoomReservationId);

		assertNotNull(roomReservation);
		assertEquals(startTime, roomReservation.getStartTime());
		assertEquals(endTime, roomReservation.getEndTime());
		assertEquals(date, roomReservation.getDate());
		assertEquals(room.getRoomId(), roomReservation.getRoom().getRoomId());
		assertEquals(client.getUserId(), roomReservation.getClient().getUserId());

	}

}