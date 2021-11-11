package ca.mcgill.ecse321.librarymanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;

import ca.mcgill.ecse321.librarymanagement.dao.ClientRepository;
import ca.mcgill.ecse321.librarymanagement.dao.LibrarianRepository;
import ca.mcgill.ecse321.librarymanagement.dao.RoomRepository;
import ca.mcgill.ecse321.librarymanagement.dao.RoomReservationRepository;
import ca.mcgill.ecse321.librarymanagement.dao.TimeslotRepository;
import ca.mcgill.ecse321.librarymanagement.dao.TitleRepository;
import ca.mcgill.ecse321.librarymanagement.dao.TitleReservationRepository;
import ca.mcgill.ecse321.librarymanagement.model.Client;
import ca.mcgill.ecse321.librarymanagement.model.Librarian;
import ca.mcgill.ecse321.librarymanagement.model.Library;
import ca.mcgill.ecse321.librarymanagement.model.Room;
import ca.mcgill.ecse321.librarymanagement.model.Room.RoomType;
import ca.mcgill.ecse321.librarymanagement.model.RoomReservation;
import ca.mcgill.ecse321.librarymanagement.model.Timeslot;
import ca.mcgill.ecse321.librarymanagement.model.Title;
import ca.mcgill.ecse321.librarymanagement.model.Title.TitleType;
import ca.mcgill.ecse321.librarymanagement.model.TitleReservation;

@ExtendWith(MockitoExtension.class)
public class TestLibraryManagementService {

	@Mock
	private TitleRepository titleRepository;

	@Mock
	private ClientRepository clientRepository;

	@Mock
	private LibrarianRepository librarianRepository;

	@Mock
	private RoomRepository roomRepository;

	@Mock
	private TimeslotRepository timeslotRepository;

	@Mock
	private TitleReservationRepository titleReservationRepository;

	@Mock
	private RoomReservationRepository roomReservationRepository;

	@InjectMocks
	private LibraryManagementService service;

	private static final int TITLE_KEY = 123;
	private static final int CLIENT_KEY = 1234;
	private static final int LIBRARIAN_KEY = 1234;
	private static final int ROOM_KEY = 1234;
	private static final int TIMESLOT_KEY = 1234;
	private static final int TITLE_RESERVATION_KEY = 1234;
	private static final int ROOM_RESERVATION_KEY = 1234;

	// Title tests

	@BeforeEach
	public void setMockOutput() {
		lenient().when(titleRepository.findTitleByTitleId(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(TITLE_KEY)) {
				String name = "moby dick";
				String description = "whale eats guy";
				String genre = "adventure";
				TitleType titleType = TitleType.Book;
				Title title = new Title(name, description, genre, true, titleType);
				title.setTitleId(TITLE_KEY);
				return title;
			} else {
				return null;
			}
		});

		lenient().when(clientRepository.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(CLIENT_KEY)) {
				String residentialAddress = "514 marwan road";
				String email = "email@123.com";
				boolean isResident = true;
				boolean isOnline = true;
				String username = "big shot";
				String password = "spaghetti_noodles";
				String fullName = "John Doe";
				Client client = new Client(username, password, fullName, residentialAddress, email, isResident,
						isOnline);
				client.setUserId(CLIENT_KEY);
				return client;
			} else {
				return null;
			}
		});

		lenient().when(librarianRepository.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(LIBRARIAN_KEY)) {
				String residentialAddress = "514 marwan road";
				String email = "email@123.com";
				boolean isResident = true;
				boolean isOnline = true;
				String username = "big shot";
				String password = "spaghetti_noodles";
				String fullName = "John Doe";
				Client client = new Client(username, password, fullName, residentialAddress, email, isResident,
						isOnline);
				client.setUserId(LIBRARIAN_KEY);
				return client;
			} else {
				return null;
			}
		});

		lenient().when(roomRepository.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(ROOM_KEY)) {
				int capacity = 10;
				boolean isAvailable = true;
				RoomType roomType = RoomType.Event;
				Room room = new Room(capacity, isAvailable, roomType);
				room.setRoomId(ROOM_KEY);
				return room;
			} else {
				return null;
			}
		});

		lenient().when(timeslotRepository.findById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(TIMESLOT_KEY)) {
				Date date = new Date(2021, 11, 11);
				Time startTime = new Time(10, 0, 0);
				Time endTime = new Time(20, 0, 0);
				Timeslot timeslot = new Timeslot(startTime, endTime, date);
				timeslot.setTimeslotId(TIMESLOT_KEY);
				return timeslot;
			} else {
				return null;
			}
		});

		lenient().when(titleReservationRepository.findTitleReservationByTitleReservationId(anyInt()))
				.thenAnswer((InvocationOnMock invocation) -> {
					if (invocation.getArgument(0).equals(TITLE_RESERVATION_KEY)) {
						Date date = new Date(Calendar.getInstance().getTime().getTime());
						Date returnDate = sqlDatePlusDays(date);
						boolean isCheckedOut = false;

						String name = "moby dick";
						String description = "whale eats guy";
						String genre = "adventure";
						TitleType titleType = TitleType.Book;
						Title title = new Title(name, description, genre, true, titleType);

						String residentialAddress = "514 marwan road";
						String email = "email@123.com";
						boolean isResident = true;
						boolean isOnline = true;
						String username = "big shot";
						String password = "spaghetti_noodles";
						String fullName = "John Doe";
						Client client = new Client(username, password, fullName, residentialAddress, email, isResident,
								isOnline);

						TitleReservation titleReservation = new TitleReservation(returnDate, isCheckedOut, title,
								client);
						titleReservation.setTitleReservationId(TITLE_KEY);
						return titleReservation;
					} else {
						return null;
					}
				});

	}

	private Date sqlDatePlusDays(Date date) {
		return Date.valueOf(date.toLocalDate().plusDays(14));
	}

	// SAM

	// title tests

	// valid input

	@Test
	public void createTitle() {

		String name = "moby dick";
		String description = "whale eats guy";
		String genre = "adventure";
		boolean isAvailable = true;
		TitleType titleType = TitleType.Book;
		Library library = new Library();
		Title title = null;

		try {
			title = service.createTitle(name, description, genre, isAvailable, titleType, library);
		}

		catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(title);
		assertEquals(name, title.getName());
		assertEquals(description, title.getDescription());
		assertEquals(genre, title.getGenre());
		assertEquals(isAvailable, title.getIsAvailable());
		assertEquals(titleType, title.getTitleType());

	}

	// invalid input

	@Test
	public void createTitleNull() {
		String name = null;
		String description = null;
		String genre = null;
		boolean isAvailable = true;
		TitleType titleType = null;
		Library library = null;
		Title title = null;
		String error = "";

		try {
			title = service.createTitle(name, description, genre, isAvailable, titleType, library);
		}

		catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(title);
		assertEquals("Title cannot be empty", error);

	}

	// client tests

	// valid input

	@Test
	public void createClient() {

		String username = "moby dick";
		String password = "whale eats guy";
		String fullname = "adventure";
		String residentialAddress = "123 avenue";
		String email = "adventure@gmail.com";
		boolean isResident = true;
		boolean isOnline = true;
		Library library = null;
		Client client = null;

		try {
			client = service.createClient(username, password, fullname, residentialAddress, email, isResident, isOnline,
					library);
		}

		catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(client);
		assertEquals(username, client.getUsername());
		assertEquals(password, client.getPassword());
		assertEquals(fullname, client.getFullname());
		assertEquals(residentialAddress, client.getResidentialAddress());
		assertEquals(email, client.getEmail());
		assertEquals(isResident, client.getIsResident());
		assertEquals(isOnline, client.getIsOnline());

	}

	// invalid input

	@Test
	public void createClientNull() {

		String username = null;
		String password = null;
		String fullname = null;
		String residentialAddress = null;
		String email = null;
		boolean isResident = true;
		boolean isOnline = true;
		Library library = null;
		Client client = null;
		String error = "";

		try {
			client = service.createClient(username, password, fullname, residentialAddress, email, isResident, isOnline,
					library);
		}

		catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(client);
		assertEquals("Client cannot be empty", error);

	}
	
	@Test
	public void getExistingClient() {
		assertEquals(CLIENT_KEY, service.getClient(CLIENT_KEY).getUserId());
	}
	@Test
	public void getNonExistingClient() {
		assertNull(service.getClient(4321));
	}

	// valid input
	@Test
	public void createLibrarian() {

		String username = "moby dick";
		String password = "whale eats guy";
		String fullname = "adventure";
		boolean isheadlibrarian = true;
		Librarian librarian = null;
		Library library = new Library();

		try {
			librarian = service.createLibrarian(username, password, fullname, isheadlibrarian, library);
		}

		catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(librarian);
		assertEquals(username, librarian.getUsername());
		assertEquals(password, librarian.getPassword());
		assertEquals(fullname, librarian.getFullname());
		assertEquals(isheadlibrarian, librarian.getIsHeadLibrarian());

	}

	// invalid input

	@Test
	public void createLibrarianNull() {

		String username = null;
		String password = null;
		String fullname = null;
		boolean isHeadLibrarian = true;
		Library library = null;
		Librarian librarian = null;
		String error = "";

		try {
			librarian = service.createLibrarian(username, password, fullname, isHeadLibrarian, library);
		}

		catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(librarian);
		assertEquals("Librarian cannot be empty", error);

	}
	@Test
	public void getExistingLibrarian() {
		assertEquals(LIBRARIAN_KEY, service.getLibrarian(LIBRARIAN_KEY).getUserId());
	}
	@Test
	public void getNonExistingLibrarian() {
		assertNull(service.getLibrarian(43210));
	}

	// valid input
	@Test
	public void createRoom() {

		int capacity = 4;
		boolean isAvailable = true;
		RoomType roomType = RoomType.Study;
		Room room = null;
		Library library = new Library();

		try {
			room = service.createRoom(capacity, isAvailable, roomType, library);
		}

		catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(room);
		assertEquals(capacity, room.getCapacity());
		assertEquals(isAvailable, room.getIsAvailable());
		assertEquals(roomType, room.getRoomType());

	}

	// invalid input

	@Test
	public void createRoomNull() {

		int capacity = 4;
		boolean isAvailable = true;
		RoomType roomType = RoomType.Study;
		Room room = null;
		Library library = new Library();
		String error = "";

		try {
			room = service.createRoom(capacity, isAvailable, roomType, library);
		}

		catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(room);
		assertEquals("Room cannot be empty", error);

	}
	
	@Test
	public void getExistingRoom() {
		assertEquals(ROOM_KEY, service.getRoom(ROOM_KEY).getRoomId());
	}
	@Test
	public void getNonExistingRoom() {
		assertNull(service.getRoom(4321));
	}

	// valid input
	@Test
	public void createLibraryTimeslot() {

		Date date = new Date(2021, 11, 11);
		Time start = new Time(10, 0, 0);
		Time end = new Time(20, 0, 0);

		Timeslot timeslot = null;
		Library library = new Library();

		try {
			timeslot = service.createLibraryTimeslot(start, end, date, library.getLibrarySchedule(), library);
		}

		catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(timeslot);
		assertEquals(date, timeslot.getDate());
		assertEquals(start, timeslot.getStartTime());
		assertEquals(end, timeslot.getEndTime());

	}

	// invalid input

	@Test
	public void createLibraryTimeslotNull() {

		Date date = null;
		Time start = null;
		Time end = null;
		Timeslot timeslot = null;
		Library library = new Library();
		String error = "";

		try {
			timeslot = service.createLibraryTimeslot(start, end, date, library.getLibrarySchedule(), library);
		}

		catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(timeslot);
		assertEquals("Library time slot cannot be empty", error);

	}
	
	

	// invalid input

	@Test
	public void createLibraryTimeslotOverlapping() {

		// try to add another timeslot at the same time
		Date date = new Date(2021, 11, 11);
		Time start = new Time(10, 0, 0);
		Time end = new Time(20, 0, 0);
		Timeslot timeslot = null;
		Timeslot timeslot2 = null;
		Library library = new Library();
		String error = "";

		try {
			timeslot = service.createLibraryTimeslot(start, end, date, library.getLibrarySchedule(), library);
			timeslot2 = service.createLibraryTimeslot(start, end, date, library.getLibrarySchedule(), library);
		}

		catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(timeslot);
		assertEquals("Overlapping library time slots", error);

	}
	

	// valid input
	@Test
	public void createStaffScheduleTimeslot() {

		Date date = new Date(2021, 11, 11);
		Time start = new Time(10, 0, 0);
		Time end = new Time(20, 0, 0);

		Timeslot timeslot = null;
		Library library = new Library();
		Librarian librarian = (Librarian) library.getUser(LIBRARIAN_KEY);

		try {
			timeslot = service.createStaffScheduleTimeslot(start, end, date, library, librarian);
		}

		catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(timeslot);
		assertEquals(date, timeslot.getDate());
		assertEquals(start, timeslot.getStartTime());
		assertEquals(end, timeslot.getEndTime());

	}

	// invalid input

	@Test
	public void createStaffScheduleTimeslotNull() {

		Date date = null;
		Time start = null;
		Time end = null;
		Timeslot timeslot = null;
		Library library = new Library();
		Librarian librarian = null;
		String error = "";

		try {
			timeslot = service.createStaffScheduleTimeslot(start, end, date, library, librarian);
		}

		catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(timeslot);
		assertEquals("Staff Schedule time slot cannot be empty", error);

	}

	// invalid input

	@Test
	public void createStaffScheduleTimeslotOverlapping() {

		Date date = new Date(2021, 11, 11);
		Time start = new Time(10, 0, 0);
		Time end = new Time(20, 0, 0);
		Timeslot timeslot = null;
		Library library = new Library();
		Librarian librarian = (Librarian) library.getUser(LIBRARIAN_KEY);
		String error = "";

		try {
			timeslot = service.createStaffScheduleTimeslot(start, end, date, library, librarian);
			timeslot = service.createStaffScheduleTimeslot(start, end, date, library, librarian);
		}

		catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(timeslot);
		assertEquals("Staff Schedule time slot cannot overlap for the same librarian", error);

	}
	
	@Test
	public void getExistingTimeSlot() {
		assertEquals(TIMESLOT_KEY, service.getTimeSlot(TIMESLOT_KEY).getTimeslotId());
	}
	@Test
	public void getNonExistingTimeSlot() {
		assertNull(service.getTimeSlot(4321));
	}

//	@Transactional
//	public Title createTitle(String name, String description, String genre, boolean isAvailable, TitleType titleType) {
//		
//		if (name == null || name.trim().length() == 0) {
//			throw new IllegalArgumentException("Title name cannot be empty!");
//		}
//		
//		if (description == null || description.trim().length() == 0) {
//			throw new IllegalArgumentException("Title description cannot be empty!");
//		}
//		
//		if (genre == null || description.trim().length() == 0) {
//			throw new IllegalArgumentException("Title genre cannot be empty!");
//		}
//		
//		if (isAvailable == false) {
//			throw new IllegalArgumentException("Title availability cannot be false when first created!");
//		}
//		
//		if (titleType == null) {
//			throw new IllegalArgumentException("Title type cannot be null!");
//		}
//		
//		Title title = new Title(name, description, genre, true, titleType);
//		
//		titleRepository.save(title);
//		
//		return title;
//		
//	}
//	
//	@Transactional
//	public Title getTitle(String name, String description, String genre, boolean isAvailable, TitleType titleType) {
//		
//		if (name == null || name.trim().length() == 0) {
//			throw new IllegalArgumentException("Title name cannot be empty!");
//		}
//		
//		if (description == null || description.trim().length() == 0) {
//			throw new IllegalArgumentException("Title description cannot be empty!");
//		}
//		
//		if (genre == null || description.trim().length() == 0) {
//			throw new IllegalArgumentException("Title genre cannot be empty!");
//		}
//		
//		if (isAvailable == false) {
//			throw new IllegalArgumentException("Title availability cannot be false when first created!");
//		}
//		
//		if (titleType == null) {
//			throw new IllegalArgumentException("Title type cannot be null!");
//		}
//		
//		Title title = titleRepository.findTitleByTitleId(TITLE_KEY);
//				
//		return title;
//	}
//	
//	@Transactional
//	public Client createClient(String username, String password, String fullname, String residentialAddress, String email, boolean isResident, boolean isOnline) {
//		
//		if (username == null || username.trim().length() == 0) {
//			throw new IllegalArgumentException("username cannot be empty!");
//		}
//		
//		if (password == null || password.trim().length() == 0) {
//			throw new IllegalArgumentException("password description cannot be empty!");
//		}
//		
//		if (fullname == null || fullname.trim().length() == 0) {
//			throw new IllegalArgumentException("full name cannot be empty!");
//		}
//		
//		Client client = new Client(username, password, fullname, residentialAddress, email, isResident, isOnline);
//		
//		clientRepository.save(client);
//		
//		return client;
//		
//	}
//	
//	
//	@Transactional
//	public Client getClient(String username, String password, String fullname, String residentialAddress, String email, boolean isResident, boolean isOnline) {
//		
//		if (username == null || username.trim().length() == 0) {
//			throw new IllegalArgumentException("username cannot be empty!");
//		}
//		
//		if (password == null || password.trim().length() == 0) {
//			throw new IllegalArgumentException("password description cannot be empty!");
//		}
//		
//		if (fullname == null || fullname.trim().length() == 0) {
//			throw new IllegalArgumentException("full name cannot be empty!");
//		}
//		
//		Client client = clientRepository.findClientByUserId(CLIENT_KEY);
//				
//		return client;
//		
//	}

	// TARA
	// valid input
	@Test
	public void createTitleReservation() {
		Date date = new Date(Calendar.getInstance().getTime().getTime());
		Date returnDate = sqlDatePlusDays(date);
		boolean isCheckedOut = false;

		String name = "moby dick";
		String description = "whale eats guy";
		String genre = "adventure";
		TitleType titleType = TitleType.Book;
		Title title = new Title(name, description, genre, true, titleType);

		String residentialAddress = "514 marwan road";
		String email = "email@123.com";
		boolean isResident = true;
		boolean isOnline = true;
		String username = "big shot";
		String password = "spaghetti_noodles";
		String fullName = "John Doe";
		Client client = new Client(username, password, fullName, residentialAddress, email, isResident, isOnline);
		TitleReservation titleReservation = null;
		Library library = new Library();

		try {
			titleReservation = service.createTitleReservation(returnDate, isCheckedOut, title, client, library);
		}

		catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(titleReservation);
		assertEquals(returnDate, titleReservation.getReturnDate());
		assertEquals(isCheckedOut, titleReservation.getIsCheckedOut());
		assertEquals(title.getTitleId(), titleReservation.getTitle().getTitleId());
		assertEquals(client.getUserId(), titleReservation.getClient().getUserId());

	}

	// invalid input
	@Test
	public void createTitleReservationNull() {
		Date returnDate = null;
		boolean isCheckedOut = false;
		Title title = null;
		Client client = null;
		TitleReservation titleReservation = null;
		Library library = new Library();
		String error = "";

		try {
			titleReservation = service.createTitleReservation(returnDate, isCheckedOut, title, client, library);
		}

		catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(titleReservation);
		assertEquals("Title reservation cannot be empty", error);
	}
	
	//Title and Client do not exist
	@Test
	public void createTitleReservationTitleAndClientDoNotExist() {
		Date date = new Date(Calendar.getInstance().getTime().getTime());
		Date returnDate = sqlDatePlusDays(date);
		
		boolean isCheckedOut=false;
		
		String residentialAddress = "514 marwan street";
		String email = "email@gmail.com";
		boolean isResident = true;
		boolean isOnline = true;
		String username = "tara";
		String password = "spaghetti";
		String fullName = "Tara Doe";
		Client client = new Client(username, password, fullName, residentialAddress, email, isResident, isOnline);
	
		
		assertEquals(0, service.getAllClients().size());
		
		String name = "great gatsby";
		String description = "rich guy";
		String genre = "party";
		TitleType titleType = TitleType.Book;
		Title title = new Title(name, description, genre, true, titleType);
		
		assertEquals(0, service.getAllTitles().size());
		String error = null;
		TitleReservation titleReservation = null;
		try {
			titleReservation = service.createTitleReservation(returnDate,isCheckedOut,title,client,library);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(titleReservation);
		// check error
		assertEquals("Client does not exist! Title does not exist!", error);		
	}
	
	@Test
	public void getExistingTitleReservation() {
		assertEquals(TITLE_RESERVATION_KEY, service.getTitleReservation(TITLE_RESERVATION_KEY).getTitleReservationId());
	}
	@Test
	public void getNonExistingTitleReservation() {
		assertNull(service.getTitleReservation(4321));
	}

	// Valid input
	@Test
	public void createRoomReservation() {
		Time startTime = new Time(5, 0, 0);
		Time endTime = new Time(7, 0, 0);
		Date date = new Date(2021, 7, 12);

		int capacity = 10;
		boolean isAvailable = true;
		RoomType roomtype = RoomType.Study;
		Room room = new Room(capacity, isAvailable, roomtype);

		String residentialAddress = "514 marwan road";
		String email = "email@123.com";
		boolean isResident = true;
		boolean isOnline = true;
		String username = "big shot";
		String password = "spaghetti_noodles";
		String fullName = "John Doe";
		Client client = new Client(username, password, fullName, residentialAddress, email, isResident, isOnline);

		Library library = new Library();
		RoomReservation roomReservation = null;

		try {
			roomReservation = service.createRoomReservation(startTime, endTime, date, room, client, library);
		}

		catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(roomReservation);
		assertEquals(startTime, roomReservation.getStartTime());
		assertEquals(endTime, roomReservation.getEndTime());
		assertEquals(date, roomReservation.getDate());
		assertEquals(room.getRoomId(), roomReservation.getRoom().getRoomId());
		assertEquals(client.getUserId(), roomReservation.getClient().getUserId());
	}

	//invalid input
	@Test
	public void createRoomReservationNull() {

		Time startTime = null;
		Time endTime = null;
		Date date = null;
		Room room = null;
		Client client = null;
		Library library = new Library();
		RoomReservation roomReservation = null;
		String error = "";

		try {
			roomReservation = service.createRoomReservation(startTime, endTime, date, room, client, library);
		}

		catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(roomReservation);
		assertEquals("Room reservation cannot be empty", error);

	}
	
	//Client and Room do not exist
	@Test
	public void createRoomReservationTitleAndRoomDoNotExist() {
		Date date = new Date(2021,5,2);
		Time startTime = new Time(6,0,0);
		Time endTime = new Time(9,0,0);

		
		String residentialAddress = "514 marwan street";
		String email = "email@gmail.com";
		boolean isResident = true;
		boolean isOnline = true;
		String username = "tara";
		String password = "spaghetti";
		String fullName = "Tara Doe";
		Client client = new Client(username, password, fullName, residentialAddress, email, isResident, isOnline);
	
		assertEquals(0, service.getAllClients().size());
		
		int capacity = 1;
		boolean isAvailable = true;
		RoomType roomType = RoomType.Event;
		Room room = new Room(capacity,isAvailable,roomType);
		assertEquals(0,service.getAllRooms().size());
		
		Library library = new Library();
		
		String error = null;
		RoomReservation roomReservation = null;
		
		try {
			roomReservation = service.createRoomReservation(startTime, endTime,date,room, client, library);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(roomReservation);
		// check error
		assertEquals("Client does not exist! Room does not exist!", error);		
	}
	
	@Test
	public void getExistingRoomReservation() {
		assertEquals(ROOM_RESERVATION_KEY, service.getRoomReservation(ROOM_RESERVATION_KEY).getRoomReservationId());
	}
	@Test
	public void getNonExistingRoomReservation() {
		assertNull(service.getRoomReservation(4321));
	}

}
