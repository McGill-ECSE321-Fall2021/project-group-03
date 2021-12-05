package ca.mcgill.ecse321.librarymanagement.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.librarymanagement.dao.ClientRepository;
import ca.mcgill.ecse321.librarymanagement.dao.LibrarianRepository;
import ca.mcgill.ecse321.librarymanagement.dao.LibraryRepository;
import ca.mcgill.ecse321.librarymanagement.dao.RoomRepository;
import ca.mcgill.ecse321.librarymanagement.dao.RoomReservationRepository;
import ca.mcgill.ecse321.librarymanagement.dao.ScheduleRepository;
import ca.mcgill.ecse321.librarymanagement.dao.TimeslotRepository;
import ca.mcgill.ecse321.librarymanagement.dao.TitleRepository;
import ca.mcgill.ecse321.librarymanagement.dao.TitleReservationRepository;
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
import ca.mcgill.ecse321.librarymanagement.model.User;

@Service
public class LibraryManagementService {

	@Autowired
	private TitleRepository titleRepository;

	@Autowired
	private LibraryRepository libraryRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private LibrarianRepository librarianRepository;

	@Autowired
	private TimeslotRepository timeslotRepository;

	@Autowired
	private ScheduleRepository scheduleRepository;

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private RoomReservationRepository roomReservationRepository;

	@Autowired
	private TitleReservationRepository titleReservationRepository;

	/***
	 * Method to create a title.
	 * 
	 * @param name
	 * @param description
	 * @param genre
	 * @param isAvailable
	 * @param titleType
	 * @param library
	 * @return title
	 */

	@Transactional
	public Title createTitle(String name, String description, String genre, boolean isAvailable, TitleType titleType,
			Library library) {

		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("Title cannot be empty!");
		}

		if (description == null || description.trim().length() == 0) {
			throw new IllegalArgumentException("Description cannot be empty!");
		}

		if (genre == null || description.trim().length() == 0) {
			throw new IllegalArgumentException("Genre cannot be empty!");
		}

		if (titleType == null) {
			throw new IllegalArgumentException("TitleType cannot be empty!");
		}

		Title title = new Title(name, description, genre, isAvailable, titleType);
		library.addTitle(title);
		titleRepository.save(title);
		libraryRepository.save(library);
		return title;
	}

	/***
	 * Method to update a title.
	 * 
	 * @param name
	 * @param description
	 * @param genre
	 * @param isAvailable
	 * @param titleType
	 * @param library
	 * @return title
	 */

	@Transactional
	public Title updateTitle(String name, String description, String genre, TitleType titleType, Library library) {

		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("Title cannot be empty!");
		}

		if (description == null || description.trim().length() == 0) {
			throw new IllegalArgumentException("Description cannot be empty!");
		}

		if (genre == null || description.trim().length() == 0) {
			throw new IllegalArgumentException("Genre cannot be empty!");
		}

		if (titleType == null) {
			throw new IllegalArgumentException("TitleType cannot be empty!");
		}

		Title title = null;

		// update all copies of a title being updated.

		for (Title t : library.getTitles()) {
			if (t.getName().equals(name)) {
				t.setDescription(description);
				t.setGenre(genre);
				t.setName(name);
				t.setTitleType(titleType);
				title = t;
				titleRepository.save(t);
			}
		}

		if (title == null) {
			throw new IllegalArgumentException("Title does not exist!");
		}

		libraryRepository.save(library);
		return title;
	}

	/***
	 * Method to get a title.
	 * 
	 * @param titleID
	 * @return title
	 */
	@Transactional
	public Title getTitle(int titleId) {
		Title title = titleRepository.findTitleByTitleId(titleId);
		if (title == null) {
			throw new IllegalArgumentException("Title does not exist!");
		}
		return title;
	}

	/***
	 * Method to get all titles in the system.
	 * 
	 * @return List of titles.
	 */
	@Transactional
	public List<Title> getAllTitles() {
		return toList(titleRepository.findAll());
	}

	/***
	 * Method to convert to a list.
	 * 
	 * @return List.
	 */
	private <T> List<T> toList(Iterable<T> iterable) {
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}

	/***
	 * Method to get the library. If the library doesn't exist then the method
	 * creates one by calling the initialize library method.
	 * 
	 * @return Library.
	 */
	@Transactional
	public Library getLibrary() {
		try {
			Library library = toList(libraryRepository.findAll()).get(0);
		}

		catch (Exception e) {

			// This case will only happen if database is deleted.
			return initializeLibrary();
		}

		return toList(libraryRepository.findAll()).get(0);
	}

	/***
	 * Method to initialize the library. It creates a head librarian account by
	 * default. It also creates 3 rooms in the library as specified by the client.
	 * 
	 * @return Library.
	 */
	public Library initializeLibrary() {
		Library library = new Library();
		Librarian head = new Librarian("headLibrarian", "head", "Head Librarian", true);
		library.addUser(head);
		librarianRepository.save(head);

		// The client told us the library has 3 rooms.

		createRoom(10, true, Room.RoomType.Study, library);
		createRoom(5, true, Room.RoomType.Study, library);
		createRoom(50, true, Room.RoomType.Event, library);

		Schedule librarySchedule = new Schedule();
		library.setLibrarySchedule(librarySchedule);

		scheduleRepository.save(librarySchedule);
		libraryRepository.save(library);

		return library;
	}

	/***
	 * Method to delete the library.
	 * 
	 * @param library
	 */
	public void removeLibrary(Library library) {

		titleReservationRepository.deleteAll();
		roomReservationRepository.deleteAll();
		libraryRepository.deleteAll();

		clientRepository.deleteAll();
		librarianRepository.deleteAll();
		titleRepository.deleteAll();
		scheduleRepository.deleteAll();
		roomRepository.deleteAll();
		timeslotRepository.deleteAll();
	}

	/***
	 * Method to create a client.
	 * 
	 * @param aUsername
	 * @param aPassword
	 * @param aFullname
	 * @param aResidentialAddress
	 * @param aEmail
	 * @param aIsOnline
	 * @param library
	 * @return client
	 */
	@Transactional
	public Client createClient(String aUsername, String aPassword, String aFullname, String aResidentialAddress,
			String aEmail, boolean aIsResident, boolean aIsOnline, Library library)

	{
		if (aUsername == null || aUsername.trim().length() == 0) {
			throw new IllegalArgumentException("Client information cannot be empty!");
		}

		if (aPassword == null || aPassword.trim().length() == 0) {
			throw new IllegalArgumentException("Client information cannot be empty!");
		}

		if (aFullname == null || aFullname.trim().length() == 0) {
			throw new IllegalArgumentException("Client information cannot be empty!");
		}

		for (User u : library.getUsers()) {
			if (u.getUsername().equals(aUsername)) {
				throw new IllegalArgumentException("username already exists");
			}
		}

		for (User u : library.getUsers()) {
			if (u.getUsername() == aUsername) {
				throw new IllegalArgumentException("username already exists");
			}
		}

		Client client = new Client(aUsername, aPassword, aFullname, aResidentialAddress, aEmail, aIsResident,
				aIsOnline);
		library.addUser(client);
		clientRepository.save(client);
		libraryRepository.save(library);

		return client;
	}

	/***
	 * Method to get all clients in the system
	 * 
	 * @return List of clients.
	 */
	@Transactional
	public List<Client> getAllClients() {
		return toList(clientRepository.findAll());
	}

	/***
	 * Method to get a specific client.
	 * 
	 * @param client ID
	 * @return client
	 */
	@Transactional
	public Client getClient(int clientId) {
		Client client = clientRepository.findClientByUserId(clientId);

		if (client == null) {
			throw new IllegalArgumentException("Client does not exist!");
		}
		return client;
	}

	/***
	 * Method to create a librarian.
	 * 
	 * @param aUsername
	 * @param aPassword
	 * @param aFullname
	 * @param isHeadLibrarian
	 * @param Library
	 * @return librarian
	 */
	@Transactional
	public Librarian createLibrarian(String username, String password, String fullName, boolean isHeadLibrarian,
			Library library) {

		if (username == null || username.trim().length() == 0) {
			throw new IllegalArgumentException("Librarian username cannot be empty!");
		}

		if (password == null || password.trim().length() == 0) {
			throw new IllegalArgumentException("Librarian password cannot be empty!");
		}

		if (fullName == null || fullName.trim().length() == 0) {
			throw new IllegalArgumentException("Librarian Full Name cannot be empty!");
		}

		Librarian headLibrarian = null;

		for (User u : library.getUsers()) {
			if (u.getUsername().equals(username)) {
				throw new IllegalArgumentException("Username already exists");
			}
		}

		// To check if head librarian.
		for (User u : library.getUsers()) {
			if (u instanceof Librarian) {
				Librarian l = (Librarian) u;
				if (l.getIsHeadLibrarian()) {
					headLibrarian = l;
				}
			}
		}

		if (username == null || username.trim().length() == 0) {
			throw new IllegalArgumentException("Librarian information cannot be empty!");
		}

		if (password == null || password.trim().length() == 0) {
			throw new IllegalArgumentException("Librarian information cannot be empty!");
		}

		if (fullName == null || fullName.trim().length() == 0) {
			throw new IllegalArgumentException("Librarian information cannot be empty!");
		}

		if (isHeadLibrarian && headLibrarian != null) {
			throw new IllegalArgumentException("Head Librarian already exists!");
		}

		Librarian librarian = new Librarian(username, password, fullName, isHeadLibrarian);
		library.addUser(librarian);
		scheduleRepository.save(librarian.getStaffSchedule());
		librarianRepository.save(librarian);
		libraryRepository.save(library);
		return librarian;
	}

	/***
	 * Method to get all librarians in the system
	 * 
	 * @return List of librarians.
	 */
	@Transactional
	public List<Librarian> getAllLibrarians() {
		return toList(librarianRepository.findAll());
	}

	/***
	 * Method to create a timeslot in the library.
	 * 
	 * @param startTime
	 * @param endTime
	 * @param date
	 * @param librarySchedule,
	 * @param library
	 * @return the created timeslot.
	 */
	@Transactional
	public Timeslot createLibraryTimeslot(Time startTime, Time endTime, Date date, Schedule librarySchedule,
			Library library) {

		if (startTime == null || endTime == null || date == null) {
			throw new IllegalArgumentException(
					"Library time slot cannot be empty. Please fill in the year, month and day");
		}

		for (Timeslot t : library.getLibrarySchedule().getTimeslots()) {

			if (isOverlapping(t, date, startTime, endTime)) {
				throw new IllegalArgumentException("Library time slot cannot overlap existing time slot");
			}
		}

		if (startTime.after(endTime) || startTime.equals(endTime)) {
			throw new IllegalArgumentException("start time must be before end time");
		}

		Date today = new Date(Calendar.getInstance().getTime().getTime());
		if (today.after(date)) {
			throw new IllegalArgumentException("Library time slot cannot be created in the past");
		}

		// Used to fix java date internally.
		int year = date.getYear() - 1900;
		int month = date.getMonth() - 1;
		int day = date.getDate();
		Date date1 = new Date(year, month, day);
		Timeslot timeslot = new Timeslot(startTime, endTime, date1);

		librarySchedule.addTimeslot(timeslot);
		timeslotRepository.save(timeslot);
		scheduleRepository.save(librarySchedule);
		libraryRepository.save(library);

// Add this timeslot as possible room reservations for all rooms in the library.
		if (library.getRooms().size() != 0) {

			for (int i = 0; i < library.getRooms().size(); i++) {
				Room room = library.getRoom(i);
				addRoomReservations(timeslot, room, library);

			}
		}

		return timeslot;
	}

	/***
	 * Method to remove a timeslot from library.
	 * 
	 * @param timeslot Id
	 */

	@Transactional
	public void removeLibraryScheduleTimeslot(int timeslotId) {
		Library library = getLibrary();
		Schedule librarySchedule = library.getLibrarySchedule();
		Timeslot timeslot = null;

		// find time slot
		for (Timeslot t : library.getLibrarySchedule().getTimeslots()) {
			if (t.getTimeSlotId() == timeslotId) {
				timeslot = t;
			}
		}

		if (timeslot == null) {
			throw new IllegalArgumentException("Timeslot does not exist");
		}

		// Remove all possible room reservations from each room.
		for (RoomReservation roomReservation : library.getRoomReservations()) {
			if (roomReservation.getTimeSlotId() == timeslotId) {
				library.removeRoomReservation(roomReservation);
				roomReservationRepository.delete(roomReservation);

			}
		}

		librarySchedule.removeTimeslot(timeslot);
		timeslotRepository.delete(timeslot);
		scheduleRepository.save(librarySchedule);

	}

	/***
	 * Method to get all time slots in the library.
	 * 
	 * @return list of time slots
	 */
	@Transactional
	public List<Timeslot> getAllLibraryTimeslots() {

		Library library = getLibrary();

		return library.getLibrarySchedule().getTimeslots();
	}

	/***
	 * Method to get all time slots of a librarian.
	 * 
	 * @param librairan Username.
	 * @return list of time slots of that librarian.
	 */
	@Transactional
	public List<Timeslot> getAllLibrarianTimeslots(String librarianUsername) {
		// filter through only the library timeslots
		Librarian librarian = librarianRepository.findLibrarianByUsername(librarianUsername);
		return librarian.getStaffSchedule().getTimeslots();
	}

	/***
	 * Method to get all rooms in the library.
	 * 
	 * @param librarian Username.
	 * @return list of of rooms.
	 */
	@Transactional
	public List<Room> getAllRooms() {
		return toList(roomRepository.findAll());
	}

	/***
	 * Method to get a room by room id.
	 * 
	 * @param room id
	 * @return room
	 */
	@Transactional
	public Room getRoom(int roomId) {
		Room room = roomRepository.findRoomByRoomId(roomId);
		if (room == null) {
			throw new IllegalArgumentException("Room does not exist!");
		}
		return room;
	}

	/***
	 * Method to get all room reservations of a specific room
	 * 
	 * @param room id
	 * @return list of reservations.
	 */
	@Transactional
	public List<RoomReservation> getAllRoomReservations(int roomId) {
		List<RoomReservation> roomReservations = toList(roomReservationRepository.findAll());
		ArrayList<RoomReservation> curRoomReservations = new ArrayList<RoomReservation>();

		for (RoomReservation rr : roomReservations) {
			if (rr.getRoom().getRoomId() == roomId) {
				curRoomReservations.add(rr);
			}
		}

		if (curRoomReservations.size() == 0) {
			throw new IllegalArgumentException("This Room Reservation does not exist");
		}

		return curRoomReservations;
	}

	/***
	 * Method to create a room.
	 * 
	 * @param capacity
	 * @param isAvailable
	 * @param roomType
	 * @param library
	 * @return new room
	 */
	@Transactional
	public Room createRoom(int capacity, boolean isAvailable, RoomType roomType, Library library) {

		if (capacity <= 0) {
			throw new IllegalArgumentException("capacity must be greater than 0");
		}

		if (roomType == null) {
			throw new IllegalArgumentException("invalid room type");
		}

		Room room = new Room(capacity, isAvailable, roomType);
		library.addRoom(room);

		// add all the room reservations of the time slots from the library's schedule.
		if (library.getLibrarySchedule().getTimeslots() != null) {

			for (Timeslot timeslot : library.getLibrarySchedule().getTimeslots()) {

				addRoomReservations(timeslot, room, library);
			}
		}

		roomRepository.save(room);
		libraryRepository.save(library);

		return room;
	}

	/***
	 * Method to add a room reservation to a specific room
	 * 
	 * @param libraryTimeslot
	 * @param room
	 * @param library
	 */
	public void addRoomReservations(Timeslot libraryTimeslot, Room room, Library library) {
		Date date = libraryTimeslot.getDate();
		Time startHour = libraryTimeslot.getStartTime();
		Time endHour = libraryTimeslot.getEndTime();

		for (int i = startHour.getHours(); i < endHour.getHours(); i++) {
			Client client = new Client("null", "pass", "lee", "shalom avenue", "hihi", true, true);
			clientRepository.save(client);
			RoomReservation roomReservation = new RoomReservation(new Time(i, 0, 0), new Time(i + 1, 0, 0), date, room,
					client);
			library.addRoomReservation(roomReservation);
			roomReservationRepository.save(roomReservation);
			libraryRepository.save(library);
		}
	}

	/***
	 * Method to create a room resevration for a specific client.
	 * 
	 * @param startTime
	 * @param endTime
	 * @param date
	 * @param roomId
	 * @param clientId
	 * @param library
	 */
	@Transactional
	public RoomReservation createRoomReservation(Time startTime, Time endTime, Date date, int roomId, int clientId,
			Library library) {

		Room room = roomRepository.findRoomByRoomId(roomId);
		Client client = clientRepository.findClientByUserId(clientId);

		// for all library hours, we made new reservations that are available, indicated
		// by client being null
		// if the client is not null, that means its already booked so you cannot add a
		// reservation

		if (room == null && client == null) {
			throw new IllegalArgumentException("room reservations must include a valid client and room");
		}

		if (startTime.after(endTime) || startTime.equals(endTime)) {
			throw new IllegalArgumentException("start time must be before end time");
		}

		Date today = new Date(Calendar.getInstance().getTime().getTime());
		if (today.after(date)) {
			throw new IllegalArgumentException("Library time slot cannot be created in the past");
		}

		List<RoomReservation> roomReservations = toList(roomReservationRepository.findAll());

		for (RoomReservation rr : roomReservations) {
			if (rr.getRoom().equals(room)) {
				if (isOverlapping(rr, date, startTime, endTime)) {
					throw new IllegalArgumentException("room reservation is overlapping with an existing reservation");
				}
			}
		}

		RoomReservation roomReservation = new RoomReservation(startTime, endTime, date, room, client);
		library.addRoomReservation(roomReservation);
		libraryRepository.save(library);
		roomReservationRepository.save(roomReservation);
		return roomReservation;
	}

	/***
	 * Method to update a room reservation.
	 * 
	 * @param roomReservationId
	 * @param endTime
	 * @param userId
	 * @param library
	 * @return the updated room reservation
	 */
	@Transactional
	public RoomReservation updateRoomReservation(int roomReservationId, int userId, Library library) {
		Client client = clientRepository.findClientByUserId(userId);
		RoomReservation roomReservation = roomReservationRepository.findRoomReservationByTimeslotId(roomReservationId);

		if (!roomReservation.getClient().getUsername().equals("null")) {
			throw new IllegalArgumentException("This room reservation is already booked");
		} else {
			roomReservation.setClient(client);
			return roomReservation;

		}

	}

	/***
	 * Method to remove a room reservation.
	 * 
	 * @param room    ID
	 * @param User    Id
	 * @param library
	 */
	@Transactional
	public void removeRoomReservation(int roomId, int userId, Library library) {
		RoomReservation roomReservation = null;

		for (RoomReservation roomReservationA : library.getRoomReservations()) {
			if (roomReservationA.getClient().getUserId() == userId
					&& roomReservationA.getRoom().getRoomId() == roomId) {
				roomReservation = roomReservationA;
			}
		}

		if (roomReservation == null) {
			throw new IllegalArgumentException("Cannot find Room Reservation");
		}

		library.getRoomReservations().remove(roomReservation);
		roomReservationRepository.delete(roomReservation);
		libraryRepository.save(library);
	}

	/***
	 * Method to remove a room reservation.
	 * 
	 * @param room ID
	 * @return the reservations of that specific room
	 */
	@Transactional
	public ArrayList<RoomReservation> getRoomReservationsByRoom(int roomId) {
		ArrayList<RoomReservation> roomReservations = new ArrayList<RoomReservation>();
		Library library = getLibrary();
		for (RoomReservation roomReservation : library.getRoomReservations()) {
			if (roomReservation.getRoom().getRoomId() == roomId
					&& roomReservation.getClient().getUsername().equals("null")) {
				// this will only get room reservations that are available (client user name is
				// "null"
				roomReservations.add(roomReservation);
			}
		}
		return roomReservations;

	}

	/***
	 * Method to get reservations made by a client.
	 * 
	 * @param client user name
	 * @return list of reservations made by client
	 */
	@Transactional
	public ArrayList<RoomReservation> getRoomReservationsByClient(String clientUsername) {
		ArrayList<RoomReservation> roomReservations = new ArrayList<RoomReservation>();
		Library library = getLibrary();
		for (RoomReservation roomReservation : library.getRoomReservations()) {
			if (roomReservation.getClient().getUsername().equals(clientUsername)) {
				// this will only get room reservations that are available (client username is
				// "null"
				roomReservations.add(roomReservation);
			}
		}
		return roomReservations;

	}

	/***
	 * Method to create the schedule of an employee
	 * 
	 * @param start    time
	 * @param end      time
	 * @param date
	 * @param library
	 * @param username
	 * @return the new time slot created
	 */
	@Transactional
	public Timeslot createStaffScheduleTimeslot(Time startTime, Time endTime, Date date, Library library,
			String username) {

		Librarian librarian = librarianRepository.findLibrarianByUsername(username);

		if (librarian == null) {
			throw new IllegalArgumentException("librarian does not exist");
		}

		if (startTime.after(endTime) || startTime.equals(endTime)) {
			throw new IllegalArgumentException("start time must be before end time");
		}

		Date today = new Date(Calendar.getInstance().getTime().getTime());
		if (today.after(date)) {
			throw new IllegalArgumentException("Librarian time slot cannot be created in the past");
		}

		for (Timeslot t : librarian.getStaffSchedule().getTimeslots()) {
			if (isOverlapping(t, date, startTime, endTime)) {
				throw new IllegalArgumentException("Librarian time slot cannot overlap existing time slot");
			}

			boolean dayHasHours = false;

			for (Timeslot lt : library.getLibrarySchedule().getTimeslots()) {
				if (lt.getDate().equals(today) || lt.getDate().after(today)) {
					if (lt.getDate().equals(date)
							&& (lt.getStartTime().before(startTime) || lt.getStartTime().equals(startTime))
							&& (lt.getEndTime().after(endTime) || lt.getEndTime().equals(endTime))) {
						throw new IllegalArgumentException(
								"Librarian time slot must be within opening hours of the library");
					}

					else if (lt.getDate().equals(date)) {
						dayHasHours = true;
					}
				}
			}

			if (!dayHasHours) {
				throw new IllegalArgumentException("Librarian time slot must be within opening hours of the library");
			}
		}

		// This is to account for java's built in date.
		int year = date.getYear() - 1900;
		int month = date.getMonth() - 1;
		int day = date.getDate();
		Date date1 = new Date(year, month, day);
		Timeslot timeslot = new Timeslot(startTime, endTime, date1);
		librarian.getStaffSchedule().addTimeslot(timeslot);
		timeslotRepository.save(timeslot);
		scheduleRepository.save(librarian.getStaffSchedule());
		libraryRepository.save(library);

		return timeslot;
	}

	/***
	 * Method to remove a librarian from the system
	 * 
	 * @param library
	 * @param librarian user name
	 */
	@Transactional
	public void removeLibrarian(Library library, String username) {

		Librarian librarian = null;

		for (Librarian l : librarianRepository.findAll()) {
			if (l.getUsername().equals(username)) {
				librarian = l;
			}
		}

		if (librarian == null) {
			throw new IllegalArgumentException("Librarian does not exist");
		} else if (librarian.getIsHeadLibrarian()) {
			throw new IllegalArgumentException("Cannot fire head librarian");
		}

		librarianRepository.delete(librarian);
		library.removeUser(librarian);
		libraryRepository.save(library);
	}

	/***
	 * Method to remove a librarians hours
	 * 
	 * @param time    slot id
	 * @param head    librarian id (Since they are they only one able to perform
	 *                this)
	 * @param library
	 */
	@Transactional
	public void removeStaffScheduleTimeslot(int timeslotId, int headLibrarianId, Library library) {

		Timeslot timeslot = null;

		timeslot = timeslotRepository.findTimeslotByTimeslotId(timeslotId);

		Librarian headLibrarian = librarianRepository.findLibrarianByUserId(headLibrarianId);

		if (headLibrarian.getIsHeadLibrarian() == false) {
			throw new IllegalArgumentException("Librarian that is not a Head Librarian cannot remove timeslot");
		}

		if (timeslot == null) {
			throw new IllegalArgumentException("timeslot does not exist");
		}

		Schedule staffSchedule = headLibrarian.getStaffSchedule();
		staffSchedule.removeTimeslot(timeslot);
		timeslotRepository.delete(timeslot);
		scheduleRepository.save(staffSchedule);
		librarianRepository.save(headLibrarian);
	}

	/***
	 * Method to get a librarian by their Id
	 * 
	 * @param librarian id
	 */
	@Transactional
	public Librarian getLibrarian(int librarianId) {
		Librarian librarian = librarianRepository.findLibrarianByUserId(librarianId);

		if (librarian == null) {
			throw new IllegalArgumentException("Librarian does not exist!");
		}
		return librarian;
	}

	/***
	 * Method to update a librarian.
	 * 
	 * @param aUsername
	 * @param aPassword
	 * @param aFullname
	 * @param isHeadLibrarian
	 * @param Library
	 * @return librarian
	 */
	public Librarian updateLibrarian(String aUsername, String aPassword, String aFullname, String aResidentialAddress,
			String aEmail, boolean isResident, boolean isOnline, Library library) {

		Librarian librarian = null;

		if (aUsername == null || aUsername.trim().length() == 0) {
			throw new IllegalArgumentException("Librarian information cannot be empty!");
		}

		if (aPassword == null || aPassword.trim().length() == 0) {
			throw new IllegalArgumentException("Librarian information cannot be empty!");
		}

		if (aFullname == null || aFullname.trim().length() == 0) {
			throw new IllegalArgumentException("Librarian information cannot be empty!");
		}

		for (User u : library.getUsers()) {
			if (u.getUsername().equals(aUsername)) {
				librarian = (Librarian) u;
			}
		}

		librarian.setFullname(aFullname);
		librarian.setPassword(aPassword);

		librarianRepository.save(librarian);
		libraryRepository.save(library);

		return librarian;
	}

	/***
	 * Method to create a title reservation
	 * 
	 * @param return  date
	 * @param is      checked out
	 * @param title   name
	 * @param user    name
	 * @param Library
	 * @return the title reservation
	 */
	@Transactional
	public TitleReservation createTitleReservation(Date returnDate, boolean isCheckedOut, String titleName,
			String username, Library library) {

		Client client = null;
		Title title = null;

		for (User u : library.getUsers()) {
			if (u instanceof Client && u.getUsername().equals(username)) {
				client = (Client) u;
			}
		}

		for (Title t : library.getTitles()) {
			if (t.getIsAvailable() && t.getName().equals(titleName)) {
				title = t;
			}
		}
		for (Title t : library.getTitles()) {
			if (t.getName().equals(titleName) && t.getTitleType() == TitleType.Newspaper) {
				throw new IllegalArgumentException("Cannot reserve a newspaper!");
			}
			if (t.getName().equals(titleName) && t.getTitleType() == TitleType.Archives) {
				throw new IllegalArgumentException("Cannot reserve an archive!");
			}
		}

		if (client == null) {
			throw new IllegalArgumentException("Client and Title must exist!");
		}

		if (title == null) {
			throw new IllegalArgumentException("Client and Title must exist!");
		}

		TitleReservation titleReservation = new TitleReservation(returnDate, isCheckedOut, title, client);
		library.addTitleReservation(titleReservation);
		title.setIsAvailable(false);

		titleRepository.save(title);
		titleReservationRepository.save(titleReservation);
		libraryRepository.save(library);

		return titleReservation;
	}

	/***
	 * Method to update a title reservation. If the the title is not yet checked
	 * out, it sets it to true.
	 * 
	 * @param titleReservation
	 * @param library
	 * @return the title reservation
	 */
	@Transactional
	public TitleReservation updateTitleReservation(TitleReservation titleReservation, Library library) {
		if (titleReservation.getIsCheckedOut() == true) {
			throw new IllegalArgumentException("Title is already checked out!");
		}

		if (titleReservation.getClient() == null) {
			throw new IllegalArgumentException("Client is null!");
		}
		if (titleReservation.getTitle() == null) {
			throw new IllegalArgumentException("Title is null!");
		}

		titleReservation.setIsCheckedOut(true);

		titleReservationRepository.save(titleReservation);
		libraryRepository.save(library);

		return titleReservation;
	}

	/***
	 * Method to update the fields of a title reservation
	 * 
	 * @param return  date
	 * @param is      checked out
	 * @param title   name
	 * @param user    name
	 * @param Library
	 * @return the title reservation
	 */
	@Transactional
	public TitleReservation updateTitleReservationParameters(Date returnDate, boolean isCheckedOut, String titleName,
			String username, Library library) {
		Client client = null;
		Title title = null;

		for (User u : library.getUsers()) {
			if (u instanceof Client && u.getUsername().equals(username)) {
				client = (Client) u;
			}
		}

		for (Title t : library.getTitles()) {
			if (t.getIsAvailable() && t.getName().equals(titleName)) {
				title = t;
			}
		}

		for (Title t : library.getTitles()) {
			if (t.getIsAvailable() && t.getName().equals(titleName) && t.getTitleType() == TitleType.Newspaper) {
				throw new IllegalArgumentException("Cannot reserve a newspaper!");
			}
			if (t.getIsAvailable() && t.getName().equals(titleName) && t.getTitleType() == TitleType.Archives) {
				throw new IllegalArgumentException("Cannot reserve an archive!");
			}
		}

		if (client == null) {
			throw new IllegalArgumentException("Client does not exist!");
		}

		if (title == null) {
			throw new IllegalArgumentException("Title is not available!");
		}

		TitleReservation titleReservation = new TitleReservation(returnDate, isCheckedOut, title, client);
		library.addTitleReservation(titleReservation);
		title.setIsAvailable(false);

		titleRepository.save(title);
		titleReservationRepository.save(titleReservation);
		libraryRepository.save(library);

		return titleReservation;

	}

	/***
	 * Method to get a title reservation by title Id
	 * 
	 * @param title ID
	 * @return the title reservation
	 */
	@Transactional
	public TitleReservation getTitleReservationByTitleId(int titleId) {
		List<TitleReservation> titleReservations = toList(titleReservationRepository.findAll());
		TitleReservation thisTitleReservation = null;
		for (TitleReservation tr : titleReservations) {
			if (tr.getTitle().getTitleId() == titleId) {
				thisTitleReservation = tr;
			}
		}

		return thisTitleReservation;
	}

	/***
	 * Method to get title reservations by client and title name
	 * 
	 * @param title name
	 * @param user  name
	 * @return the list of title reservations
	 */
	@Transactional
	public TitleReservation getTitleReservationByTitleNameAndClient(String titleName, String username) {
		List<TitleReservation> titleReservations = toList(titleReservationRepository.findAll());
		TitleReservation thisTitleReservation = null;
		for (TitleReservation tr : titleReservations) {
			if (tr.getTitle().getName().equals(titleName) && tr.getClient().getUsername().equals(username)) {
				return tr;
			}
		}
		return thisTitleReservation;
	}

	/***
	 * Method to get all reservations made by a user.
	 * 
	 * @param user name
	 * @return the list of title reservations
	 */
	@Transactional
	public List<TitleReservation> getAllTitleReservationsByUsername(String username) {
		List<TitleReservation> allReservations = toList(titleReservationRepository.findAll());
		List<TitleReservation> myReservations = new ArrayList<TitleReservation>();
		for (TitleReservation tr : allReservations) {
			if (tr.getClient().getUsername().equals(username)) {
				myReservations.add(tr);
			}
		}

		return myReservations;
	}

	/***
	 * Method to get all title reservations.
	 * 
	 * @return the list of title reservations.
	 */
	@Transactional
	public List<TitleReservation> getAllTitleReservations() {
		List<TitleReservation> titleReservations = toList(titleReservationRepository.findAll());
		return titleReservations;
	}

	/***
	 * Method to remove a specific title reservation
	 * 
	 * @param reservation Id
	 * @param library
	 */
	@Transactional
	public void removeTitleReservation(int titleReservationId, Library library) {

		List<TitleReservation> titleReservations = toList(titleReservationRepository.findAll());
		TitleReservation thisTitleReservation = null;
		for (TitleReservation tr : titleReservations) {
			if (tr.getTitle().getTitleId() == titleReservationId) {
				thisTitleReservation = tr;
			}
		}
		library.removeTitleReservation(thisTitleReservation);
		titleReservationRepository.delete(thisTitleReservation);
		libraryRepository.save(library);

	}

	/***
	 * Method to remove a title and all its copies from the system.
	 * 
	 * @param title   ID
	 * @param Library
	 */
	@Transactional
	public void removeTitle(Library library, int titleId) {
		Title title = null;

		for (Title titleA : library.getTitles()) {
			if (titleA.getTitleId() == titleId) {
				title = titleA;
			}
		}

		if (title == null) {
			throw new IllegalArgumentException("Title does not exist! Please provide an existing title Id");
		}

		if (library.getTitleReservations().size() > 0) {
			List<TitleReservation> tempList = new ArrayList<>();
			for (TitleReservation t : library.getTitleReservations()) {
				tempList.add(t);
			}

			for (TitleReservation titleReservation : tempList) {
				if (titleReservation.getTitle().getTitleId() == title.getTitleId()) {
					titleReservationRepository.delete(titleReservation);
					library.removeTitleReservation(titleReservation);

				}

			}
		}

		titleRepository.delete(title);
		library.removeTitle(title);
		libraryRepository.save(library);
	}

	/***
	 * Method to check if time slots are overlapping
	 * 
	 * @param time  slot
	 * @param date  of new time slot
	 * @param start time of new time slot
	 * @param end   time of new time slot
	 * @return if it is overlapping or not
	 */
	@Transactional
	public boolean isOverlapping(Timeslot existingTimeslot, Date newDate, Time newStart, Time newEnd) {
		Date date = existingTimeslot.getDate();

		// Is it on the same day?
		// We again do the same subtraction to be consistent throughout.
		if (date.getYear() == newDate.getYear() - 1900 && date.getMonth() == newDate.getMonth() - 1
				&& date.getDate() == newDate.getDate()) {
			Time startTime = existingTimeslot.getStartTime();
			Time endTime = existingTimeslot.getEndTime();

			if (newStart.before(startTime) && newEnd.after(startTime)) {
				return true;
			}

			if (newStart.after(startTime) && newEnd.after(endTime)) {
				return true;
			}

			if (newStart.before(startTime) && newEnd.after(endTime)) {
				return true;
			}

			if (newStart.after(startTime) && newEnd.before(endTime)) {
				return true;
			}

			if (newStart.equals(startTime) && newEnd.equals(endTime)) {
				return true;
			}

			if (newStart.equals(startTime) && newEnd.after(endTime)) {
				return true;
			}

			if (newStart.equals(startTime) && newEnd.before(endTime)) {
				return true;
			}

			if (newStart.after(startTime) && newEnd.equals(endTime)) {
				return true;
			}

			if (newStart.before(startTime) && newEnd.equals(endTime)) {
				return true;
			}
		}

		return false;
	}

	/***
	 * Method to log in a client
	 * 
	 * @param user     name
	 * @param password
	 * @return client
	 */
	@Transactional
	public Client loginClient(String username, String password) {

		Library library = getLibrary();
		Client client = null;

		for (User u : library.getUsers()) {
			if (u instanceof Client && u.getUsername().equals(username) && u.getPassword().equals(password)) {
				client = (Client) u;
			}
		}

		if (client == null) {
			throw new IllegalArgumentException("username and password incorrect");
		}

		return client;
	}

	/***
	 * Method to log in a librarian
	 * 
	 * @param user     name
	 * @param password
	 * @return librarian
	 */
	@Transactional
	public Librarian loginLibrarian(String username, String password) {

		Library library = getLibrary();
		Librarian librarian = null;

		for (User u : library.getUsers()) {
			if (u instanceof Librarian && u.getUsername().equals(username) && u.getPassword().equals(password)) {
				librarian = (Librarian) u;
			}
		}

		if (librarian == null) {
			throw new IllegalArgumentException("username and password incorrect");
		}

		return librarian;
	}

	/***
	 * Method to get a speicifc time slot by ID
	 * 
	 * @param timeslotId
	 * @return timeslot
	 */
	@Transactional
	public Timeslot getTimeslot(int timeslotId) {
		Timeslot timeSlot = timeslotRepository.findTimeslotByTimeslotId(timeslotId);

		if (timeSlot == null) {
			throw new IllegalArgumentException("Timeslot does not exist!");
		}
		return timeSlot;
	}

	/***
	 * Method to update an existing client.
	 * 
	 * @param aUsername
	 * @param aPassword
	 * @param aFullname
	 * @param aResidentialAddress
	 * @param aEmail
	 * @param aIsOnline
	 * @param library
	 * @return client
	 */
	@Transactional
	public Client updateClient(String aUsername, String aPassword, String aFullname, String aResidentialAddress,
			String aEmail, boolean isResident, boolean isOnline, Library library) {

		Client client = null;

		if (aUsername == null || aUsername.trim().length() == 0) {
			throw new IllegalArgumentException("Client information cannot be empty!");
		}

		if (aPassword == null || aPassword.trim().length() == 0) {
			throw new IllegalArgumentException("Client information cannot be empty!");
		}

		if (aFullname == null || aFullname.trim().length() == 0) {
			throw new IllegalArgumentException("Client information cannot be empty!");
		}

		for (User u : library.getUsers()) {
			if (u.getUsername().equals(aUsername)) {
				client = (Client) u;
			}
		}

		client.setEmail(aEmail);
		client.setFullname(aFullname);
		client.setPassword(aPassword);
		client.setResidentialAddress(aResidentialAddress);

		clientRepository.save(client);
		libraryRepository.save(library);

		return client;
	}

	/***
	 * The following 3 methods are used to easily update a specific field that a
	 * client would like to change. in their account.
	 */

	@Transactional
	public Client updateClientPassword(String aUsername, String aPassword, Library library) {

		Client client = null;

		if (aPassword == null || aPassword.trim().length() == 0) {
			throw new IllegalArgumentException("Client information cannot be empty!");
		}

		for (User u : library.getUsers()) {
			if (u.getUsername().equals(aUsername)) {
				client = (Client) u;
			}
		}

		client.setPassword(aPassword);

		clientRepository.save(client);
		libraryRepository.save(library);

		return client;
	}

	@Transactional
	public Client updateClientEmail(String aUsername, String aEmail, Library library) {

		Client client = null;

		if (aEmail == null || aEmail.trim().length() == 0) {
			throw new IllegalArgumentException("Client information cannot be empty!");
		}

		for (User u : library.getUsers()) {
			if (u.getUsername().equals(aUsername)) {
				client = (Client) u;
			}
		}

		client.setEmail(aEmail);

		clientRepository.save(client);
		libraryRepository.save(library);

		return client;
	}

	@Transactional
	public Client updateClientResAddress(String aUsername, String aResidentialAddress, Library library) {

		Client client = null;

		if (aResidentialAddress == null || aResidentialAddress.trim().length() == 0) {
			throw new IllegalArgumentException("Client information cannot be empty!");
		}

		for (User u : library.getUsers()) {
			if (u.getUsername().equals(aUsername)) {
				client = (Client) u;
			}
		}

		client.setResidentialAddress(aResidentialAddress);

		clientRepository.save(client);
		libraryRepository.save(library);

		return client;
	}

	/***
	 * Method to remove a client from the library
	 * 
	 * @param aUsername
	 * @param aPassword
	 * @param aFullname
	 * @param aResidentialAddress
	 * @param aEmail
	 * @param aIsOnline
	 * @param library
	 * @return client
	 */
	@Transactional
	public void removeClient(String username, Library library) {

		Client client = null;

		for (User u : library.getUsers()) {
			if (u.getUsername().equals(username)) {
				client = (Client) u;
			}
		}

		if (client == null) {
			throw new IllegalArgumentException("Client does not exist!");
		}

		if (library.getTitleReservations().size() > 0) {
			List<TitleReservation> tempList = new ArrayList<>();
			for (TitleReservation t : library.getTitleReservations()) {
				tempList.add(t);
			}
			for (TitleReservation titleReservation : tempList) {
				if (titleReservation.getClient().getUsername().equals(client.getUsername())) {
					library.removeTitleReservation(titleReservation);
					titleReservationRepository.delete(titleReservation);

				}
			}
		}
		if (library.getRoomReservations().size() > 0) {
			List<RoomReservation> roomTemp = new ArrayList<>();
			for (RoomReservation r : library.getRoomReservations()) {
				roomTemp.add(r);
			}
			for (RoomReservation rr : roomTemp) {
				if (rr.getClient().getUsername().equals(client.getUsername())) {
					library.removeRoomReservation(rr);
					roomReservationRepository.delete(rr);
				}
			}
		}

		library.removeUser(client);
		clientRepository.delete(client);
		libraryRepository.save(library);

	}

	/***
	 * Method to get a room reservation by timeslot Id
	 * 
	 * @param ID
	 * @return that specific room reservation
	 */
	@Transactional
	public RoomReservation getRoomReservation(int id) {
		RoomReservation rr = roomReservationRepository.findRoomReservationByTimeslotId(id);
		return rr;
	}

}