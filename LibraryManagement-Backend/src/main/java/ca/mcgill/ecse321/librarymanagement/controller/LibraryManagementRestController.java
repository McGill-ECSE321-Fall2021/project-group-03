package ca.mcgill.ecse321.librarymanagement.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.librarymanagement.dto.ClientDto;
import ca.mcgill.ecse321.librarymanagement.dto.LibrarianDto;
import ca.mcgill.ecse321.librarymanagement.dto.RoomDto;
import ca.mcgill.ecse321.librarymanagement.dto.RoomReservationDto;
import ca.mcgill.ecse321.librarymanagement.dto.TimeslotDto;
import ca.mcgill.ecse321.librarymanagement.dto.TitleDto;
import ca.mcgill.ecse321.librarymanagement.dto.TitleReservationDto;
import ca.mcgill.ecse321.librarymanagement.dto.UserDto;
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
import ca.mcgill.ecse321.librarymanagement.service.LibraryManagementService;

//Should imports be the same as example??

@CrossOrigin(origins = "*")
@RestController
public class LibraryManagementRestController {

	@Autowired
	private LibraryManagementService service;

	/*
	 * 
	 * Titles
	 * 
	 */

	@GetMapping(value = { "/titles/get", "/titles/get/" })
	public List<TitleDto> getAllTitles() {
		return service.getAllTitles().stream().map(b -> convertToDto(b)).collect(Collectors.toList());
	}

	@GetMapping(value = { "/titles/get/{titleId}", "/titles/get/{titleId}/" })
	public TitleDto getTitle(@PathVariable("titleId") String titleId) {
		Title title = service.getTitle(Integer.parseInt(titleId));
		return convertToDto(title);
	}

	@PostMapping(value = { "/titles/create/{name}", "/titles/create/{name}/" })
	public TitleDto createTitle(@PathVariable("name") String name, @RequestParam String description,
			@RequestParam String genre, @RequestParam String isAvailable, @RequestParam String titleType)
			throws IllegalArgumentException {

		Library library = getLibrary();

		Title title = service.createTitle(name, description, genre, Boolean.parseBoolean(isAvailable),
				parseTitleType("Movie"), library);

		return convertToDto(title);
	}

	@PostMapping(value = { "/titles/reserve/{titleName}", "/titles/reserve/{titleName}/" })
	public TitleDto reserveTitle(@PathVariable("titleName") String titleName, @RequestParam String clientUsername)
			throws IllegalArgumentException {

		Library library = getLibrary();

		// get todays date and add two weeks to it
		Date date = new Date(Calendar.getInstance().getTime().getTime());
		Date returnDate = sqlDatePlusDays(date);

		TitleReservation titleReservation = service.createTitleReservation(returnDate, false, titleName, clientUsername,
				library);

		return convertToDto(titleReservation);

	}

	@PostMapping(value = { "/titles/checkout/{name}", "/titles/checkout/{name}/" })
	public TitleDto checkoutTitle(@PathVariable("name") String titleName, @RequestParam String clientUsername)
			throws IllegalArgumentException {
		Library library = getLibrary();

		Date date = new Date(Calendar.getInstance().getTime().getTime());
		Date returnDate = sqlDatePlusDays(date);

		TitleReservation titleReservation = service.createTitleReservation(returnDate, true, titleName, clientUsername, library);

		return convertToDto(titleReservation.getTitle());

	}

	@PostMapping(value = { "/titles/remove/{titleId}", "/titles/remove/{titleId}/" })
	public void removeTitle(@PathVariable("titleId") String titleId) throws IllegalArgumentException {

		Library library = getLibrary();
		Title title = null;

		for (Title titleA : library.getTitles()) {
			if (titleA.getTitleId() == Integer.parseInt(titleId)) {
				title = titleA;
			}
		}

		if (title == null) {
			throw new IllegalArgumentException("This title does not exist");
		} else if (title.getIsAvailable() == false) {
			throw new IllegalArgumentException("This title is not available to be deleted right now");
		}

		service.deleteTitle(library, title);
	}

	public TitleDto convertToDto(Title title) {
		TitleDto titleDto = new TitleDto(title.getTitleId(), title.getDescription(), title.getGenre(),
				title.getIsAvailable(), title.getTitleType());
		return titleDto;
	}

	/*
	 * 
	 * Client
	 * 
	 */

	@GetMapping(value = { "/clients/get", "/clients/get/" })
	public List<ClientDto> getAllClients() {
		return service.getAllClients().stream().map(b -> convertToDto(b)).collect(Collectors.toList());
	}

	@PostMapping(value = { "/clients/create/{username}", "/clients/create/{username}/" })
	public ClientDto createClient(@PathVariable("username") String username, @RequestParam String password,
			@RequestParam String fullName, @RequestParam String residentialAddress, @RequestParam String email,
			@RequestParam String isResident, @RequestParam String isOnline) throws IllegalArgumentException {

		Library library = getLibrary();

		Client client = service.createClient(username, password, fullName, residentialAddress, email,
				Boolean.parseBoolean(isResident), Boolean.parseBoolean(isOnline), library);

		return convertToDto(client);
	}

	public ClientDto convertToDto(Client client) {
		ClientDto clientDto = new ClientDto(client.getUserId(), client.getUsername(), client.getPassword(),
				client.getResidentialAddress(), client.getEmail(), client.getIsResident(), client.getIsOnline());
		return clientDto;
	}

	/*
	 * 
	 * Librarian
	 * 
	 */

	@GetMapping(value = { "/librarians/get", "/librarians/get/" })
	public List<TitleDto> getAllLibrarians() {
		return service.getAllTitles().stream().map(b -> convertToDto(b)).collect(Collectors.toList());
	}

	@PostMapping(value = { "/librarians/create/{username}", "/librarians/create/{username}/" })
	public LibrarianDto createLibrarian(@PathVariable("username") String username, @RequestParam String password,
			@RequestParam String fullName, @RequestParam boolean isHeadLibrarian) throws IllegalArgumentException {

		Library library = getLibrary();

		Librarian librarian = service.createLibrarian(username, password, fullName, isHeadLibrarian, library);

		return convertToDto(librarian);
	}

	@PostMapping(value = { "/librarians/remove/{userId}", "/librarians/remove/{userId}/" })
	public void removeLibrarian(@PathVariable("userId") String userId) throws IllegalArgumentException {

		Library library = getLibrary();
		Librarian librarian = null;

		// find librarian
		for (User user : library.getUsers()) {
			if (user.getUserId() == Integer.parseInt(userId) && user instanceof Librarian) {
				librarian = (Librarian) user;
			}
		}

		if (librarian == null) {
			throw new IllegalArgumentException("librarian does not exist");
		} else if (librarian.getIsHeadLibrarian()) {
			throw new IllegalArgumentException("cannot fire head librarian");
		}

		service.deleteLibrarian(library, librarian);
	}

	public LibrarianDto convertToDto(Librarian librarian) {
		LibrarianDto librarianDto = new LibrarianDto(librarian.getUserId(), librarian.getUsername(),
				librarian.getPassword(), librarian.getFullname(), librarian.getIsHeadLibrarian());
		return librarianDto;
	}

	/**
	 * 
	 * 
	 * Login account
	 * 
	 */

	@GetMapping(value = { "/clients/login", "/clients/login/" })
	public ClientDto loginClient(@RequestParam String username, @RequestParam String password) {
		return convertToDto(service.loginClient(username, password));
	}

	@GetMapping(value = { "/librarians/login", "/librarians/login/" })
	public LibrarianDto loginLibrarian(@RequestParam String username, @RequestParam String password) {
		return convertToDto(service.loginLibrarian(username, password));
	}

	/*
	 * 
	 * Library
	 * 
	 */

	@GetMapping(value = { "/library/get", "/library/get/" })
	public Library getLibrary() {
		return service.getLibrary();
	}


	// library time slot

//	@GetMapping(value = { "/libraryTimeslots", "/libraryTimeslots/" })
//	public List<TimeslotDto> getAllLibraryTimeslots() {
//		return service.getAllLibraryTimeslots().stream().map(b -> convertToDto(b)).collect(Collectors.toList());
//	}
//
//	@PostMapping(value = { "/libraryTimeslot", "/libraryTimeslot/" })
//	public TimeslotDto createLibraryTimeslot(@RequestParam String startHour, String startMin,
//			@RequestParam String endHour, @RequestParam String endMin, @RequestParam String year,
//			@RequestParam String month, @RequestParam String day) throws IllegalArgumentException {
//
//		Library library = getLibrary();
//
//		Schedule librarySchedule = library.getLibrarySchedule();
//
//		Time startTime = new Time(Integer.parseInt(startHour), Integer.parseInt(startMin), 0);
//
//		Time endTime = new Time(Integer.parseInt(endHour), Integer.parseInt(endMin), 0);
//
//		Date date = new Date(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
//
//		Timeslot timeslot = service.createTimeslot(startTime, endTime, date, librarySchedule, library);
//
//		return convertToDto(timeslot);
//	}

	/*
	 * 
	 * Timeslots
	 * 
	 */

	public TimeslotDto convertToDto(Timeslot t) {
		TimeslotDto timeslotDto = new TimeslotDto(t.getStartTime(), t.getEndTime(), t.getDate(), t.getTimeSlotId());
		return timeslotDto;
	}
	
	@GetMapping(value = { "/timeslots/get/{timeslotId}", "/timeslots/get/{timeslotId}/" })
	public TimeslotDto getTimeslot(@PathVariable("timeslotId") String timeslotId) {
		Timeslot timeslot = service.getTimeslot(Integer.parseInt(timeslotId));
		return convertToDto(timeslot);
	}

	/*
	 * 
	 * Rooms
	 * 
	 */

	@GetMapping(value = { "/rooms/get", "/rooms/get/" })
	public List<RoomDto> getRooms() {
		return service.getAllRooms().stream().map(b -> convertToDto(b)).collect(Collectors.toList());
	}

	@PostMapping(value = { "/rooms/create/{capacity}" })
	public RoomDto createRoom(@PathVariable("capacity") int capacity, @RequestParam boolean isAvailable,
			@RequestParam String roomType) throws IllegalArgumentException {

		Library library = getLibrary();

		Room room = service.createRoom(capacity, isAvailable, parseRoomType(roomType), library);

		return convertToDto(room);
	}

	RoomDto convertToDto(Room r) {
		RoomDto roomDto = new RoomDto(r.getRoomId(), r.getCapacity(), r.getIsAvailable(), r.getRoomType());
		return roomDto;
	}

	/*
	 * 
	 * Room Reservations
	 * 
	 */

	// room reservation for a room
	@GetMapping(value = { "/roomReservations/get/{roomId}", "/roomReservations/get/{roomId}/" })
	public List<RoomReservationDto> getRoomReservations(@PathVariable("roomId") String roomId) {
		return service.getAllRoomReservations(Integer.parseInt(roomId)).stream().map(b -> convertToDto(b))
				.collect(Collectors.toList());
	}

	@PostMapping(value = { "/roomReservations/create/{roomId}" })
	public RoomReservationDto createRoomReservation(@PathVariable("roomId") String roomId, @RequestParam String userId,
			@RequestParam String startHour, String startMin, @RequestParam String endHour, @RequestParam String endMin,
			@RequestParam String year, @RequestParam String month, @RequestParam String day)
			throws IllegalArgumentException {

		Library library = getLibrary();

		// find client
		Client client = null;
		for (User u : library.getUsers()) {
			if (u.getUserId() == Integer.parseInt(userId) && u instanceof Client) {
				client = (Client) u;
			}
		}

		if (client == null) {
			throw new IllegalArgumentException("client does not exist");
		}

		// find room
		Room room = null;
		for (Room r : library.getRooms()) {
			if (r.getRoomId() == Integer.parseInt(roomId)) {
				room = r;
			}
		}

		if (room == null) {
			throw new IllegalArgumentException("room does not exist");
		}

		// Schedule roomSchedule = room
		Time startTime = new Time(Integer.parseInt(startHour), Integer.parseInt(startMin), 0);
		Time endTime = new Time(Integer.parseInt(endHour), Integer.parseInt(endMin), 0);
		Date date = new Date(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));

		for (RoomReservation rr : library.getRoomReservations()) {
			if (isOverlapping(rr, date, startTime, endTime)) {
				throw new IllegalArgumentException("overlapping room reservations");
			}
		}

		RoomReservation roomReservation = service.createRoomReservation(startTime, endTime, date,
				Integer.parseInt(roomId), Integer.parseInt(userId), library);

		return convertToDto(roomReservation);
	}

	public RoomReservationDto convertToDto(RoomReservation rr) {
		RoomDto roomDto = convertToDto(rr.getRoom());
		ClientDto clientDto = convertToDto(rr.getClient());

		RoomReservationDto roomReservationDto = new RoomReservationDto(roomDto, clientDto);
		return roomReservationDto;
	}

	/*
	 * 
	 * staffSchedules
	 * 
	 */

	@GetMapping(value = { "/staffSchedules/get/{librarianId}", "/staffSchedules/get/{librarianId}/" })
	public List<TimeslotDto> getAllTimeSlotsInStaffSchedule(@PathVariable("librarianId") String librarianId) {
		return service.getAllLibrarianTimeslots(Integer.parseInt(librarianId)).stream().map(b -> convertToDto(b))
				.collect(Collectors.toList());
	}

	@PostMapping(value = { "/staffSchedules/create/{librarianId}", "/staffSchedules/create/{librarianId}/" })
	public TimeslotDto createStaffScheduleTimeslot(@PathVariable("librarianId") String librarianId,
			@RequestParam String startHour, String startMin, @RequestParam String endHour, @RequestParam String endMin,
			@RequestParam String year, @RequestParam String month, @RequestParam String day)
			throws IllegalArgumentException {

		Library library = getLibrary();
		Librarian librarian = null;

		Time startTime = new Time(Integer.parseInt(startHour), Integer.parseInt(startMin), 0);
		Time endTime = new Time(Integer.parseInt(endHour), Integer.parseInt(endMin), 0);
		Date date = new Date(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));

		Timeslot timeslot = service.createStaffScheduleTimeslot(startTime, endTime, date, library,
				Integer.parseInt(librarianId));

		return convertToDto(timeslot);
	}

	@PostMapping(value = { "/staffSchedules/remove/{librarianId}", "/staffSchedules/remove/{librarianId}/" })
	public void removeStaffScheduleTimeslot(@PathVariable("librarianId") String librarianId,
			@RequestParam String startHour, String startMin, @RequestParam String endHour, @RequestParam String endMin,
			@RequestParam String year, @RequestParam String month, @RequestParam String day)
			throws IllegalArgumentException {

		Library library = getLibrary();
		Librarian librarian = null;
		Timeslot timeslot = null;

		// find user
		for (User user : library.getUsers()) {
			if (user.getUserId() == Integer.parseInt(librarianId) && user instanceof Librarian) {
				librarian = (Librarian) user;
			}
		}

		if (librarian == null) {
			throw new IllegalArgumentException("librarian does not exist");
		}

		Time startTime = new Time(Integer.parseInt(startHour), Integer.parseInt(startMin), 0);
		Time endTime = new Time(Integer.parseInt(endHour), Integer.parseInt(endMin), 0);
		Date date = new Date(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));

		// find timeslot
		for (Timeslot t : librarian.getStaffSchedule().getTimeslots()) {
			if (t.getDate().equals(date) && t.getStartTime().equals(startTime) && t.getEndTime().equals(endTime)) {
				timeslot = t;
			}
		}

		if (timeslot == null) {
			throw new IllegalArgumentException("time slot does not exist");
		}

		service.removeStaffScheduleTimeslot(timeslot, librarian);
		return convertToDto(timeslot);
	}

	/*
	 * 
	 * Helper methods
	 * 
	 */

	public TitleType parseTitleType(String titleType) {
		TitleType type = null;

		if (titleType.equals("MusicAlbum")) {
			type = TitleType.MusicAlbum;
		} else if (titleType.equals("Archives")) {
			type = TitleType.Archives;
		} else if (titleType.equals("Book")) {
			type = TitleType.Book;
		} else if (titleType.equals("Newspaper")) {
			type = TitleType.Newspaper;
		} else if (titleType.equals("Movie")) {
			type = TitleType.Movie;
		}
		return type;
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

	public RoomType parseRoomType(String roomType) {
		if (roomType.equals("Study")) {
			return RoomType.Study;
		} else {
			return RoomType.Event;
		}
	}

	private Date sqlDatePlusDays(Date date) {
		return Date.valueOf(date.toLocalDate().plusDays(14));
	}

}
