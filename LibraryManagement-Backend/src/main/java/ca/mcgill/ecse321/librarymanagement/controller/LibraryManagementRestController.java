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

import ca.mcgill.ecse321.librarymanagement.dao.LibraryRepository;
import ca.mcgill.ecse321.librarymanagement.dto.ClientDto;
import ca.mcgill.ecse321.librarymanagement.dto.LibrarianDto;
import ca.mcgill.ecse321.librarymanagement.dto.RoomDto;
import ca.mcgill.ecse321.librarymanagement.dto.RoomReservationDto;
import ca.mcgill.ecse321.librarymanagement.dto.ScheduleDto;
import ca.mcgill.ecse321.librarymanagement.dto.TimeslotDto;
import ca.mcgill.ecse321.librarymanagement.dto.TitleDto;
import ca.mcgill.ecse321.librarymanagement.model.Client;
import ca.mcgill.ecse321.librarymanagement.model.Librarian;
import ca.mcgill.ecse321.librarymanagement.model.Library;
import ca.mcgill.ecse321.librarymanagement.model.Room;
import ca.mcgill.ecse321.librarymanagement.model.RoomReservation;
import ca.mcgill.ecse321.librarymanagement.model.Schedule;
import ca.mcgill.ecse321.librarymanagement.model.Timeslot;
import ca.mcgill.ecse321.librarymanagement.model.Title;
import ca.mcgill.ecse321.librarymanagement.model.Room.RoomType;
import ca.mcgill.ecse321.librarymanagement.model.Title.TitleType;
import ca.mcgill.ecse321.librarymanagement.model.TitleReservation;
import ca.mcgill.ecse321.librarymanagement.model.User;
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

	@GetMapping(value = { "/titles", "/titles/" })
	public List<TitleDto> getAllTitles() {
		return service.getAllTitles().stream().map(b -> convertToDto(b)).collect(Collectors.toList());
	}

	@PostMapping(value = { "/titles/{name}", "/titles/{name}/" })
	public TitleDto createTitle(@PathVariable("name") String name, @RequestParam String description,
			@RequestParam String genre, @RequestParam String isAvailable, @RequestParam String titleType)
			throws IllegalArgumentException {

		Library library = getLibrary();

		Title title = service.createTitle(name, description, genre, Boolean.parseBoolean(isAvailable),
				parseTitleType("Movie"), library);

		return convertToDto(title);
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

	@GetMapping(value = { "/clients", "/clients/" })
	public List<ClientDto> getAllClients() {
		return service.getAllClients().stream().map(b -> convertToDto(b)).collect(Collectors.toList());
	}

	@PostMapping(value = { "/clients/{username}", "/clients/{username}/" })
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

	@GetMapping(value = { "/librarians", "/librarians/" })
	public List<TitleDto> getAllLibrarians() {
		return service.getAllTitles().stream().map(b -> convertToDto(b)).collect(Collectors.toList());
	}

	@PostMapping(value = { "/librarians/{username}", "/librarians/{username}/" })
	public LibrarianDto createLibrarian(@PathVariable("username") String username, @RequestParam String password,
			@RequestParam String fullName, @RequestParam boolean isHeadLibrarian) throws IllegalArgumentException {

		Library library = getLibrary();

		Librarian librarian = service.createLibrarian(username, password, fullName, isHeadLibrarian, library);

		return convertToDto(librarian);
	}
	
	@PostMapping(value = { "/removeLibrarians/{userId}", "/removeLibrarians/{userId}/" })
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
			throw new IllegalArgumentException ("librarian does not exist");
		}
		else if (librarian.getIsHeadLibrarian()) {
			throw new IllegalArgumentException ("cannot fire head librarian");
		}
		
		service.deleteLibrarian(library, librarian);
		
	}

	public LibrarianDto convertToDto(Librarian librarian) {
		LibrarianDto librarianDto = new LibrarianDto(librarian.getUserId(), librarian.getUsername(),
				librarian.getPassword(), librarian.getFullname(), librarian.getIsHeadLibrarian());
		return librarianDto;
	}

	/*
	 * 
	 * Library
	 * 
	 */

	@GetMapping(value = { "/library", "/library/" })
	public Library getLibrary() {
		return service.getLibrary();
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
		}
		else {
			return RoomType.Event;
		}
	}

	/*
	 * 
	 * Vassy
	 * 
	 */

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

	public TimeslotDto convertToDto(Timeslot t) {
		TimeslotDto timeslotDto = new TimeslotDto(t.getStartTime(), t.getEndTime(), t.getDate(), t.getTimeSlotId());
		return timeslotDto;
	}

	// room

	@GetMapping(value = { "/rooms", "/rooms/" })
	public List<RoomDto> getRooms() {
		return service.getAllRooms().stream().map(b -> convertToDto(b)).collect(Collectors.toList());
	}

	@PostMapping(value = { "/rooms/", "/rooms" })
	public RoomDto createRoom(@RequestParam int capacity, @RequestParam boolean isAvailable,
			@RequestParam String roomType) throws IllegalArgumentException {

		Library library = getLibrary();

		Room room = service.createRoom(capacity, isAvailable, parseRoomType(roomType), library);

		return convertToDto(room);
	}

	RoomDto convertToDto(Room r) {
		RoomDto roomDto = new RoomDto(r.getRoomId(), r.getCapacity(), r.getIsAvailable(), r.getRoomType());
		return roomDto;
	}

	// room reservation for a room
	
	@GetMapping(value = { "/roomReservations/{roomId}", "/roomReservations/{roomId}" })
	public List<RoomReservationDto> getRoomReservations(@PathVariable String roomId) {
		return service.getAllRoomReservations(Integer.parseInt(roomId)).stream().map(b -> convertToDto(b))
				.collect(Collectors.toList());
	}

	@PostMapping(value = { "/roomReservation/", "/roomReservation/" })
	public RoomReservationDto createRoomReservation(@RequestParam String userId, @RequestParam String roomId,
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

		RoomReservation roomReservation = service.createRoomReservation(startTime, endTime, date, room, client,
				library);

		return convertToDto(roomReservation);
	}

	public RoomReservationDto convertToDto(RoomReservation rr) {
		RoomDto roomDto = convertToDto(rr.getRoom());
		ClientDto clientDto = convertToDto(rr.getClient());

		RoomReservationDto roomReservationDto = new RoomReservationDto(roomDto, clientDto);
		return roomReservationDto;
	}

	// view/ edit staff schedule

	@GetMapping(value = { "/staffSchedule/{librarianId}", "/staffSchedule/{librarianId}/" })
	public List<TimeslotDto> getAllTimeSlotsInStaffSchedule(@PathVariable String librarianId) {
		return service.getAllLibrarianTimeslots(Integer.parseInt(librarianId)).stream().map(b -> convertToDto(b))
				.collect(Collectors.toList());
	}

	@PostMapping(value = { "/staffSchedule/{librarianId}", "/staffSchedule/{librarianId}/" })
	public TimeslotDto createStaffScheduleTimeslot(@PathVariable String librarianId, @RequestParam String startHour, String startMin,
			@RequestParam String endHour, @RequestParam String endMin, @RequestParam String year,
			@RequestParam String month, @RequestParam String day) throws IllegalArgumentException {

		Library library = getLibrary();
		Librarian librarian = null;

		// find user
		for (User user : library.getUsers()){
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
		
		for (Timeslot t : librarian.getStaffSchedule().getTimeslots()) {
			if (isOverlapping(t, date, endTime, startTime)) {
				throw new IllegalArgumentException("overlapping timeslot in current staff schedule");
			}
		}

		Timeslot timeslot = service.createStaffScheduleTimeslot(startTime, endTime, date, library, librarian);

		return convertToDto(timeslot);
	}
	
	@PostMapping(value = { "/staffSchedule/remove/{librarianId}", "/staffSchedule/remove/{librarianId}/" })
	public TimeslotDto removeStaffScheduleTimeslot(@PathVariable String librarianId, @RequestParam String startHour, String startMin,
			@RequestParam String endHour, @RequestParam String endMin, @RequestParam String year,
			@RequestParam String month, @RequestParam String day) throws IllegalArgumentException {

		Library library = getLibrary();
		Librarian librarian = null;
		Timeslot timeslot = null;

		// find user
		for (User user : library.getUsers()){
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
	 * Adam
	 * 
	 */
	
	@PostMapping(value = { "/titles/reserve/{name}", "/titles/reserve/{name}/" })
	public TitleDto reserveTitle(@PathVariable("name") String titleName, @RequestParam String clientUsername)
			throws IllegalArgumentException {

		Library library = getLibrary();
		
		User user = null;
		Title title = null;
		
		for (Title titleA : library.getTitles()) {
			if (titleA.getName().equals(titleName) && titleA.getIsAvailable() == true) {		
				title = titleA;
			}
		}
		
		for (User userA : library.getUsers()) {
			if (userA.getUsername().equals(clientUsername)) {
				user = userA;
			}
		}
		
		if (user != null && title != null) {
			//get todays date and add two weeks to it
			Date date = new Date(Calendar.getInstance().getTime().getTime());
			Date returnDate = sqlDatePlusDays(date);	
			

			service.createTitleReservation(returnDate, false, title, (Client) user, library);
			
			return convertToDto(title);
		}
		
		throw new IllegalArgumentException("This title is not available to reserve");
	}
	
	private Date sqlDatePlusDays(Date date) {
	    return Date.valueOf(date.toLocalDate().plusDays(14));
	}
	
	@PostMapping(value = { "/titles/checkout/{name}", "/titles/checkout/{name}/" })
	public TitleDto checkoutTitle(@PathVariable("name") String titleName, @RequestParam String clientUsername)
			throws IllegalArgumentException {
		Library library = getLibrary();
		
		User user = null;
		Title title = null;
		
		for (Title titleA : library.getTitles()) {
			if (titleA.getName().equals(titleName)) {		
				title = titleA;
			}
		}
		
		for (User userA : library.getUsers()) {
			if (userA.getUsername().equals(clientUsername)) {
				user = userA;
			}
		}
		
		if (title == null) {
			throw new IllegalArgumentException("This title does not exist");
		}
		
		if (user == null) {
			throw new IllegalArgumentException("This user does not exist");
		}
		
		// !! WHAT IS THERE ARE TWO OF THE SAME BOOKS AND THEY ARE BOTH RESERVED
		//if the user and title were found
		if (user != null && title != null) {
			System.out.println("==============a");
			
			//if title is available
			if (title.getIsAvailable() == true) {
				System.out.println("==============b");
				title.setIsAvailable(false);
				
				Date date = new Date(Calendar.getInstance().getTime().getTime());
				Date returnDate = sqlDatePlusDays(date);	
				
				service.createTitleReservation(returnDate, true, title, (Client) user, library);
				
				return convertToDto(title);
				
			//if title was not available
			} else {
				System.out.println("==============c");
				for (TitleReservation titleReservation : library.getTitleReservations()) {
					
					//if there is a titleReservation for the title
					if (titleReservation.getTitle().equals(title)) {
						System.out.println("==============d");
						
						//if the titleReservation was created by the user
						if (titleReservation.getClient().getUsername().equals(user.getUsername())) {
							System.out.println("==============e");							
							service.updateTitleReservation(titleReservation, library);
							return convertToDto(title);
							
						//if the titleReservation was not created by the user
						} else {
							System.out.println("==============f");
							throw new IllegalArgumentException("This title is reserved by someone else");
						}
					}
				}
			}
		}
		
		throw new IllegalArgumentException("adawdwadw");
	}
	
	@PostMapping(value = { "/titles/remove/{titleId}", "/titles/remove/{titleId}/" })
	public void removeTitle(@PathVariable("name") String titleId) throws IllegalArgumentException {
		
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

	/*
	 * 
	 * Tara
	 * 
	 */

	/*
	 * 
	 * Avi
	 * 
	 */

	/*
	 * 
	 * Liam
	 * 
	 */

}
