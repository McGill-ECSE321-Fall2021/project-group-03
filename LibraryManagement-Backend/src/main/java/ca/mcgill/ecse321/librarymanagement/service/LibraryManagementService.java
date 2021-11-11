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

	@Transactional
	public Title createTitle(String name, String description, String genre, boolean isAvailable, TitleType titleType,
			Library library) {

		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("Title cannot be empty!");
		}

		if (description == null || description.trim().length() == 0) {
			throw new IllegalArgumentException("Title cannot be empty!");
		}

		if (genre == null || description.trim().length() == 0) {
			throw new IllegalArgumentException("Title cannot be empty!");
		}

		if (titleType == null) {
			throw new IllegalArgumentException("Title cannot be empty!");
		}

		Title title = new Title(name, description, genre, isAvailable, titleType);
		library.addTitle(title);
		titleRepository.save(title);
		libraryRepository.save(library);
		return title;
	}

	@Transactional
	public Title getTitle(int titleId) {
		Title title = titleRepository.findTitleByTitleId(titleId);
		if (title == null) {
			throw new IllegalArgumentException("Title does not exist!");
		}
		return title;
	}

	public List<Title> getAllTitles() {
		return toList(titleRepository.findAll());
	}
	
//	public List<Title> getTitle(int titleId) {
//		return titleRepository.findById(titleId);
//	}

	// Method to convert to a list
	private <T> List<T> toList(Iterable<T> iterable) {
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}

	public Library getLibrary() {
		try {
			Library library = toList(libraryRepository.findAll()).get(0);
		}

		catch (Exception e) {
			Library library = new Library();
			libraryRepository.save(library);
			return library;

		}

		return toList(libraryRepository.findAll()).get(0);

	}

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

		Client client = new Client(aUsername, aPassword, aFullname, aResidentialAddress, aEmail, aIsResident,
				aIsOnline);
		library.addUser(client);
		clientRepository.save(client);
		libraryRepository.save(library);

		return client;
	}
	
//	@Transactional
//	public Title createTitle(String name, String description, String genre, boolean isAvailable, TitleType titleType,
//			Library library) {
//
//		if (name == null || name.trim().length() == 0) {
//			throw new IllegalArgumentException("Title cannot be empty!");
//		}
//
//		if (description == null || description.trim().length() == 0) {
//			throw new IllegalArgumentException("Title cannot be empty!");
//		}
//
//		if (genre == null || description.trim().length() == 0) {
//			throw new IllegalArgumentException("Title cannot be empty!");
//		}
//
//		if (titleType == null) {
//			throw new IllegalArgumentException("Title cannot be empty!");
//		}
//
//		Title title = new Title(name, description, genre, isAvailable, titleType);
//		library.addTitle(title);
//		titleRepository.save(title);
//		libraryRepository.save(library);
//		return title;
//	}

	public List<Client> getAllClients() {
		return toList(clientRepository.findAll());
	}
	
	@Transactional
	public Client getClient(int clientId) {
		Client client = clientRepository.findClientByUserId(clientId);
		
		if (client == null) {
			throw new IllegalArgumentException("Client does not exist!");
		}
		return client;
	}

	public Librarian createLibrarian(String username, String password, String fullName, boolean isHeadLibrarian,
			Library library) {

		Librarian headLibrarian = null;

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

	public Timeslot createLibraryTimeslot(Time startTime, Time endTime, Date date, Schedule librarySchedule,
			Library library) {
		
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
		
		Timeslot timeslot = new Timeslot(startTime, endTime, date);
		librarySchedule.addTimeslot(timeslot);
		timeslotRepository.save(timeslot);
		scheduleRepository.save(librarySchedule);
		libraryRepository.save(library);

		return timeslot;
	}

	public List<Timeslot> getAllLibraryTimeslots() {

		Library library = getLibrary();

		return library.getLibrarySchedule().getTimeslots();
	}

	public List<Timeslot> getAllLibrarianTimeslots(int librarianId) {
		// filter through only the library timeslots	
		Librarian librarian = librarianRepository.findLibrarianByUserId(librarianId);	
		
		return librarian.getStaffSchedule().getTimeslots();
	}

	public List<Room> getAllRooms() {
		return toList(roomRepository.findAll());
	}
	
	@Transactional
	public Room getRoom(int roomId) {
		Room room = roomRepository.findRoomByRoomId(roomId);
		
		if (room == null) {
			throw new IllegalArgumentException("Room does not exist!");
		}
		return room;
	}

	public List<RoomReservation> getAllRoomReservations(int roomId) {
		List<RoomReservation> roomReservations = toList(roomReservationRepository.findAll());
		List<RoomReservation> thisRoomReservations = null;
		for (RoomReservation rr : roomReservations) {
			if (rr.getRoom().getRoomId() == roomId) {
				thisRoomReservations.add(rr);
			}
		}

		return thisRoomReservations;
	}

	public Room createRoom(int capacity, boolean isAvailable, RoomType roomType, Library library) {
		Room room = new Room(capacity, isAvailable, roomType);
		library.addRoom(room);
		roomRepository.save(room);
		libraryRepository.save(library);
		return room;
	}

	public RoomReservation createRoomReservation(Time startTime, Time endTime, Date date, Room room, Client client,
			Library library) {
		RoomReservation roomReservation = new RoomReservation(startTime, endTime, date, room, client);
		library.addRoomReservation(roomReservation);
		libraryRepository.save(library);
		roomReservationRepository.save(roomReservation);
		return null;
	}

	public Timeslot createStaffScheduleTimeslot(Time startTime, Time endTime, Date date, Library library,
			Librarian librarian) {
		Timeslot timeslot = new Timeslot(startTime, endTime, date);
		librarian.getStaffSchedule().addTimeslot(timeslot);
		timeslotRepository.save(timeslot);
		scheduleRepository.save(librarian.getStaffSchedule());
		libraryRepository.save(library);

		return timeslot;
	}

	public void deleteLibrarian(Library library, Librarian librarian) {
		librarianRepository.delete(librarian);
		library.removeUser(librarian);
		libraryRepository.save(library);
	}

	public void removeStaffScheduleTimeslot(Timeslot timeslot, Librarian librarian) {
		Schedule staffSchedule = librarian.getStaffSchedule();
		staffSchedule.removeTimeslot(timeslot);
		timeslotRepository.delete(timeslot);
		scheduleRepository.save(staffSchedule);
		librarianRepository.save(librarian);
	}
	
	@Transactional
	public Librarian getLibrarian(int librarianId) {
		Librarian librarian = librarianRepository.findLibrarianByUserId(librarianId);
		
		if (librarian == null) {
			throw new IllegalArgumentException("Librarian does not exist!");
		}
		return librarian;
	}

	public TitleReservation createTitleReservation(Date returnDate, boolean aBoolean, Title title, Client client,
			Library library) {

		TitleReservation titleReservation = new TitleReservation(returnDate, aBoolean, title, client);
		library.addTitleReservation(titleReservation);
		title.setIsAvailable(false);

		titleRepository.save(title);
		titleReservationRepository.save(titleReservation);
		libraryRepository.save(library);

		return titleReservation;
	}

	// sets titleReservation IsCheckedOut=true
	public TitleReservation updateTitleReservation(TitleReservation titleReservation, Library library) {
		titleReservation.setIsCheckedOut(true);

		titleReservationRepository.save(titleReservation);
		libraryRepository.save(library);

		return titleReservation;
	}

	public void deleteTitle(Library library, Title title) {
		titleRepository.delete(title);
		library.removeTitle(title);
		libraryRepository.save(library);
	}

	public boolean isOverlapping(Timeslot existingTimeslot, Date newDate, Time newStart, Time newEnd) {
		Date date = existingTimeslot.getDate();

		// is it on the same day?
		if (date.getYear() == newDate.getYear() && date.getMonth() == newDate.getMonth()
				&& date.getDay() == newDate.getDate()) {
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

}