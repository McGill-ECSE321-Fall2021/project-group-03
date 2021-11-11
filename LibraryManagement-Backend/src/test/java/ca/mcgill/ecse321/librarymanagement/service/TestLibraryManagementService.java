package ca.mcgill.ecse321.librarymanagement.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;

import java.sql.Date;
import java.sql.Time;

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
import ca.mcgill.ecse321.librarymanagement.dao.TimeslotRepository;
import ca.mcgill.ecse321.librarymanagement.dao.TitleRepository;
import ca.mcgill.ecse321.librarymanagement.model.Client;
import ca.mcgill.ecse321.librarymanagement.model.Librarian;
import ca.mcgill.ecse321.librarymanagement.model.Library;
import ca.mcgill.ecse321.librarymanagement.model.Room;
import ca.mcgill.ecse321.librarymanagement.model.Room.RoomType;
import ca.mcgill.ecse321.librarymanagement.model.Timeslot;
import ca.mcgill.ecse321.librarymanagement.model.Title;
import ca.mcgill.ecse321.librarymanagement.model.Title.TitleType;

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
	
	@InjectMocks
	private LibraryManagementService service;
	
	private static final int TITLE_KEY = 123;
	private static final int CLIENT_KEY = 1234;
	private static final int LIBRARIAN_KEY = 1234;
	private static final int ROOM_KEY = 1234;
	private static final int TIMESLOT_KEY = 1234;
	
	// Title tests
	
	@BeforeEach
	public void setMockOutput() {
		lenient().when(titleRepository.findTitleByTitleId(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
	        if(invocation.getArgument(0).equals(TITLE_KEY)) {
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
		
		lenient().when(clientRepository.findById(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
	        if(invocation.getArgument(0).equals(CLIENT_KEY)) {
	        	String residentialAddress = "514 marwan road";
	        	String email = "email@123.com";
	        	boolean isResident = true;
	        	boolean isOnline = true;
	        	String username = "big shot";
	        	String password = "spaghetti_noodles";
	        	String fullName = "John Doe";
	    		Client client = new Client(username, password, fullName, residentialAddress, email, isResident, isOnline);
	            client.setUserId(CLIENT_KEY);
	            return client;
	        } else {
	            return null;
	        }
	    });
		
		lenient().when(librarianRepository.findById(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
	        if(invocation.getArgument(0).equals(LIBRARIAN_KEY)) {
	        	String residentialAddress = "514 marwan road";
	        	String email = "email@123.com";
	        	boolean isResident = true;
	        	boolean isOnline = true;
	        	String username = "big shot";
	        	String password = "spaghetti_noodles";
	        	String fullName = "John Doe";
	    		Client client = new Client(username, password, fullName, residentialAddress, email, isResident, isOnline);
	            client.setUserId(LIBRARIAN_KEY);
	            return client;
	        } else {
	            return null;
	        }
	    });
		
		lenient().when(roomRepository.findById(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
	        if(invocation.getArgument(0).equals(ROOM_KEY)) {
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
		
		lenient().when(timeslotRepository.findById(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
	        if(invocation.getArgument(0).equals(TIMESLOT_KEY)) {
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
			client = service.createClient(username, password, fullname, residentialAddress, email, isResident, isOnline, library);
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
			client = service.createClient(username, password, fullname, residentialAddress, email, isResident, isOnline, library);
		}
		
		catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(client);
		assertEquals("Client cannot be empty", error);
		
	}
	
	//valid input
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
	
	//valid input
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
	
	//valid input
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
	
	//valid input
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

}
