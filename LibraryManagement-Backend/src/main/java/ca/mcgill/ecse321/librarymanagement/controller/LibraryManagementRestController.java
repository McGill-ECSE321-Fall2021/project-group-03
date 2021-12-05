package ca.mcgill.ecse321.librarymanagement.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
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

	/***
	 * @param Title Id of a specific title
	 * @return A specific title by title Id
	 */
	@GetMapping(value = { "/titles/get/{titleId}", "/titles/get/{titleId}/" })
	public TitleDto getTitle(@PathVariable("titleId") String titleId) {
		Title title = service.getTitle(Integer.parseInt(titleId));
		return convertToDto(title);
	}

	/***
	 * @param All the required fields of a title
	 * @return the created title as a DTO
	 */
	@PostMapping(value = { "/titles/create/{name}", "/titles/create/{name}/" })
	public TitleDto createTitle(@PathVariable("name") String name, @RequestParam String description,
			@RequestParam String genre, @RequestParam String isAvailable, @RequestParam String titleType)
			throws IllegalArgumentException {

		Library library = getLibrary();

		Title title = service.createTitle(name, description, genre, Boolean.parseBoolean(isAvailable),
				parseTitleType(titleType), library);

		return convertToDto(title);
	}

	/***
	 * @param All the required fields of a title
	 * @return returns the updated title as a DTO 
	 */
	@PostMapping(value = { "/titles/update/{name}", "/titles/update/{name}/" })
	public TitleDto updateTitle(@PathVariable("name") String name, @RequestParam String description,
			@RequestParam String genre, @RequestParam String titleType)
			throws IllegalArgumentException {

		Library library = getLibrary();

		Title title = service.updateTitle(name, description, genre,
				parseTitleType(titleType), library);

		return convertToDto(title);
	}

	/***
	 * @param Name of the title and client who wants to make reservation
	 * @return returns the title reservation as a DTO 
	 */
	@PostMapping(value = { "/titles/reserve/{titleName}", "/titles/reserve/{titleName}/" })
	public TitleReservationDto reserveTitle(@PathVariable("titleName") String titleName, @RequestParam String clientUsername)
			throws IllegalArgumentException {

		Library library = getLibrary();

		// get todays date and add two weeks to it
		Date date = new Date(Calendar.getInstance().getTime().getTime());
		Date returnDate = sqlDatePlusDays(date);

		TitleReservation titleReservation = service.createTitleReservation(returnDate, false, titleName, clientUsername,
				library);

		return convertToDto(titleReservation);
	}
	
	@PostMapping(value = { "/titles/return/{titleName}", "/titles/return/{titleName}/" })
	public void returnTitle(@PathVariable("titleName") String titleName, @RequestParam String clientUsername)
			throws IllegalArgumentException {
		Library library = getLibrary();

		
		for (TitleReservation titleReservation : library.getTitleReservations()) {
			if (titleReservation.getTitle().getName().equals(titleName) && titleReservation.getClient().getUsername().equals(clientUsername)) {
				service.removeTitleReservation(titleReservation.getTitleReservationId(), library);
			}
		}
	}

	/***
	 * @param title reservation
	 * @return title reservation DTO
	 */
	public TitleReservationDto convertToDto(TitleReservation tr) {
		return new TitleReservationDto(tr.getReturnDate(), tr.getIsCheckedOut(), convertToDto(tr.getTitle()), convertToDto(tr.getClient()), tr.getTitleReservationId());
	}

	/***
	 * @param Title name and the client who reserved it
	 * @return title reservation's title DTO
	 */
	@PostMapping(value = { "/titles/checkout/{name}", "/titles/checkout/{name}/" })
	public TitleDto checkoutTitle(@PathVariable("name") String titleName, @RequestParam String clientUsername)
			throws IllegalArgumentException {
		Library library = getLibrary();


		TitleReservation titleReservation = service.getTitleReservationByTitleNameAndClient(titleName, clientUsername);

		service.updateTitleReservation(titleReservation, library);

		return convertToDto(titleReservation.getTitle());
	}

	/***
	 * this method will remove a title from the list of titles of the library using the title id
	 * @param title Id
	 */
	@PostMapping(value = { "/titles/remove/{titleId}", "/titles/remove/{titleId}/" })
	public void removeTitle(@PathVariable("titleId") String titleId) throws IllegalArgumentException {

		Library library = getLibrary();

		service.removeTitle(library, Integer.parseInt(titleId));
	}

	/***
	 * @param title
	 * @return title DTO
	 */
	public TitleDto convertToDto(Title title) {
		TitleDto titleDto = new TitleDto(title.getName(), title.getTitleId(), title.getDescription(), title.getGenre(),
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

	/***
	 * @param client Id
	 * @return client DTO 
	 */
	@GetMapping(value = { "/clients/get/{clientId}", "/clients/get/{clientId}/" })
	public ClientDto getClient(@PathVariable("clientId") String clientId) {
		Client client = service.getClient(Integer.parseInt(clientId));
		return convertToDto(client);
	}

	/***
	 * @param All fields required for a client
	 * @return client DTO 
	 */
	@PostMapping(value = { "/clients/create/{username}", "/clients/create/{username}/" })
	public ClientDto createClient(@PathVariable("username") String username, @RequestParam String password,
			@RequestParam String fullName, @RequestParam String residentialAddress, @RequestParam String email,
			@RequestParam String isResident, @RequestParam String isOnline) throws IllegalArgumentException {

		Library library = getLibrary();

		Client client = service.createClient(username, password, fullName, residentialAddress, email,
				Boolean.parseBoolean(isResident), Boolean.parseBoolean(isOnline), library);

		return convertToDto(client);
	}

	/***
	 * @param All fields required for a client
	 * @return updated client DTO 
	 */
	@PostMapping(value = { "/clients/update/{username}", "/clients/update/{username}/" })
	public ClientDto updateClient(@PathVariable("username") String username, @RequestParam String password,
			@RequestParam String fullName, @RequestParam String residentialAddress, @RequestParam String email,
			@RequestParam String isResident, @RequestParam String isOnline) throws IllegalArgumentException {

		Library library = getLibrary();

		Client client = service.updateClient(username, password, fullName, residentialAddress, email,
				Boolean.parseBoolean(isResident), Boolean.parseBoolean(isOnline), library);

		return convertToDto(client);
	}
	
	@PostMapping(value = { "/clients/update/password/{username}", "/clients/update/{username}/" })
	public ClientDto updateClientPassword(@PathVariable("username") String username, @RequestParam String password) throws IllegalArgumentException {

		Library library = getLibrary();

		Client client = service.updateClientPassword(username, password, library);

		return convertToDto(client);
	}
	
	@PostMapping(value = { "/clients/update/email/{username}", "/clients/update/{username}/" })
	public ClientDto updateClientEmail(@PathVariable("username") String username, @RequestParam String email) throws IllegalArgumentException {

		Library library = getLibrary();

		Client client = service.updateClientEmail(username, email, library);

		return convertToDto(client);
	}
	
	@PostMapping(value = { "/clients/update/address/{username}", "/clients/update/{username}/" })
	public ClientDto updateClientResAddress(@PathVariable("username") String username, @RequestParam String residentialAddress) throws IllegalArgumentException {

		Library library = getLibrary();

		Client client = service.updateClientResAddress(username, residentialAddress, library);

		return convertToDto(client);
	}

	/***
	 * this method will remove the user from the list of clients of the library which will no longer let the client login unless
	 * they create a new account
	 * @param Client's username
	 */
	@PostMapping(value = { "/clients/remove/{username}", "/clients/remove/{username}/" })
	public void removeClient(@PathVariable("username") String username) throws IllegalArgumentException {
		Library library = getLibrary();

		service.removeClient(username, library);
	}
	
	@GetMapping(value = { "/clients/get/reservations/{username}", "/clients/get/reservations/{username}/" })
	public List<TitleReservationDto> getTitleReservations(@PathVariable("username") String username) {
		List<TitleReservation> reservations = service.getAllTitleReservationsByUsername(username);
		return reservations.stream().map(b -> convertToDto(b)).collect(Collectors.toList());

	}

	/***
	 * @param client
	 * @return client as a DTO
	 */
	public ClientDto convertToDto(Client client) {
		ClientDto clientDto = new ClientDto(client.getUserId(), client.getUsername(), client.getFullname(), client.getPassword(),
				client.getResidentialAddress(), client.getEmail(), client.getIsResident(), client.getIsOnline());
		return clientDto;
	}

	/*
	 * 
	 * Librarian
	 * 
	 */

	/***
	 * @return a list of librarians from the library
	 */
	@GetMapping(value = { "/librarians/get", "/librarians/get/" })
	public List<LibrarianDto> getAllLibrarians() {
		return service.getAllLibrarians().stream().map(b -> convertToDto(b)).collect(Collectors.toList());
	}

	/***
	 * @param librarian Id
	 * @return librarian DTO
	 */
	@GetMapping(value = { "/librarians/get/{librarianId}", "/librarians/get/{librarianId}/" })
	public LibrarianDto getLibrarian(@PathVariable("librarianId") String librarianId) {
		Librarian librarian = service.getLibrarian(Integer.parseInt(librarianId));
		return convertToDto(librarian);
	}

	/***
	 * @param All fields needed to create a librarian.
	 * @return Librarian as a DTO
	 */
	@PostMapping(value = { "/librarians/create/{username}", "/librarians/create/{username}/" })
	public LibrarianDto createLibrarian(@PathVariable("username") String username, @RequestParam String password,
			@RequestParam String fullName, @RequestParam boolean isHeadLibrarian) throws IllegalArgumentException {
		Library library = getLibrary();

		Librarian librarian = service.createLibrarian(username, password, fullName, isHeadLibrarian, library);

		return convertToDto(librarian);
	}

	/***
	 * @param All fields required for a librarian
	 * @return updated librarian DTO
	 */
	@PostMapping(value = { "/librarians/update/{username}", "/librarians/update/{username}/" })
	public LibrarianDto updateLibrarian(@PathVariable("username") String username, @RequestParam String password,
			@RequestParam String fullName, @RequestParam String residentialAddress, @RequestParam String email,
			@RequestParam String isResident, @RequestParam String isOnline) throws IllegalArgumentException {
		Library library = getLibrary();

		Librarian librarian = service.updateLibrarian(username, password, fullName, residentialAddress, email,
				Boolean.parseBoolean(isResident), Boolean.parseBoolean(isOnline), library);

		return convertToDto(librarian);
	}

	@PostMapping(value = { "/librarians/remove/{username}", "/librarians/remove/{username}/" })
	public void removeLibrarian(@PathVariable("username") String username) throws IllegalArgumentException {
		Library library = getLibrary();

		service.removeLibrarian(library, username);
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

	@GetMapping(value = { "/clients/login/{username}", "/clients/login/{username}/" })
	public ClientDto loginClient(@PathVariable("username") String username, @RequestParam String password) {
		return convertToDto(service.loginClient(username, password));
	}

	@GetMapping(value = { "/librarians/login/{username}", "/librarians/login/{username}/" })
	public LibrarianDto loginLibrarian(@PathVariable("username") String username, @RequestParam String password) {
		return convertToDto(service.loginLibrarian(username, password));
	}

	/*
	 * 
	 * Library
	 * 
	 */

	
	/***
	 * 
	 * This method is used to get the library itself which contains all of the elements of the system such as titles, rooms, etc.
	 * 
	 * @return the library class
	 */
	@GetMapping(value = { "/library/get", "/library/get/" })
	public Library getLibrary() {
		return service.getLibrary();
	}

	/***
	 * deletes the instance of the library class, essentially reseting the database and removing all data inside of it
	 */
	@PostMapping(value = { "/library/remove", "/library/remove/" })
	public void removeLibrary() {
		Library library = getLibrary();
		service.removeLibrary(library);
	}
	


	/*
	 * 
	 * Library Timeslots
	 * 
	 */

	/***
	 * 
	 * @return a list of TimeSlot DTOs
	 */
	@GetMapping(value = { "/libraryTimeslots/get", "/libraryTimeslots/get/" })
	public List<TimeslotDto> getAllLibraryTimeslots() {
		return service.getAllLibraryTimeslots().stream().map(b -> convertToDto(b)).collect(Collectors.toList());
	}

	/***
	 * 
	 * @param startHour
	 * @param startMin
	 * @param endHour
	 * @param endMin
	 * @param year
	 * @param month
	 * @param day
	 * @return the created time slot with information passed through the parameters
	 * @throws IllegalArgumentException when there is a violation found in the service class
	 */
	@PostMapping(value = { "/libraryTimeslots/create/{startHour}", "/libraryTimeslots/create/{startHour}/" })
	public TimeslotDto createLibraryTimeslot(@PathVariable("startHour") String startHour, String startMin,
			@RequestParam String endHour, @RequestParam String endMin, @RequestParam String year,
			@RequestParam String month, @RequestParam String day) throws IllegalArgumentException {

		Library library = getLibrary();

		Schedule librarySchedule = library.getLibrarySchedule();

		Time startTime = new Time(Integer.parseInt(startHour), Integer.parseInt(startMin), 0);
		Time endTime = new Time(Integer.parseInt(endHour), Integer.parseInt(endMin), 0);
		Date date = new Date(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));

		Timeslot timeslot = service.createLibraryTimeslot(startTime, endTime, date, librarySchedule, library);

		return convertToDto(timeslot);
	}

	/***
	 * 
	 * this method will remove a timeslot from the library opening hours
	 * 
	 * @param timeslotId - the auto generate id given to every timeslot in the database
	 * @throws IllegalArgumentException when trying to remove a timeslot that does not exist
	 */
	@PostMapping(value = { "/librarySchedule/remove/{timeslotId}", "/librarySchedule/remove/{timeslotId}/" })
	public void removeLibraryTimeslot(@PathVariable("timeslotId") String timeslotId)
			throws IllegalArgumentException {

		Library library = getLibrary();

		// find timeslot
		for (Timeslot timeslot : library.getLibrarySchedule().getTimeslots()) {
			if (timeslot.getTimeSlotId() == Integer.parseInt(timeslotId)) {
				service.removeLibraryScheduleTimeslot(Integer.parseInt(timeslotId));
			}
		}
	}

	/*
	 * 
	 * Timeslots
	 * 
	 */

	/***
	 * 
	 * @param Timeslot
	 * @return Timeslot DTO
	 */
	public TimeslotDto convertToDto(Timeslot t) {
		TimeslotDto timeslotDto = new TimeslotDto(t.getStartTime(), t.getEndTime(), t.getDate(), t.getTimeSlotId());
		return timeslotDto;
	}

	/***
	 * 
	 * @param timeslotId - auto generate id given to every timeslot in the database
	 * @return the timeslot DTO associated to the specific timeslot id
	 */
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

	/***
	 * 
	 * @return a list of all the rooms in the library
 	 */
	@GetMapping(value = { "/rooms/get", "/rooms/get/" })
	public List<RoomDto> getRooms() {
		return service.getAllRooms().stream().map(b -> convertToDto(b)).collect(Collectors.toList());
	}

	/***
	 * Given a roomId, which is auto-generated by the database, the method returns the respective 
	 * Room DTO associated to the id passed in
	 * @param roomId
	 * @return room DTO
	 */
	@GetMapping(value = { "/rooms/get/{roomId}", "/rooms/get/{roomId}/" })
	public RoomDto getRoom(@PathVariable("roomId") String roomId) {
		Room room = service.getRoom(Integer.parseInt(roomId));
		return convertToDto(room);
	}

	
	/***
	 * 
	 * This method will create a new room with attributes associated to the parameters below
	 * 
	 * @param capacity
	 * @param isAvailable
	 * @param roomType
	 * @return the created room using information passed in through the parameters
	 * @throws IllegalArgumentException when a room with such information cannot be created in the service class
	 */

	@PostMapping(value = { "/rooms/create/{capacity}", "/rooms/create/{capacity}/" })
	public RoomDto createRoom(@PathVariable("capacity") int capacity, @RequestParam boolean isAvailable,
			@RequestParam String roomType) throws IllegalArgumentException {

		Library library = getLibrary();

		Room room = service.createRoom(capacity, isAvailable, parseRoomType(roomType), library);

		return convertToDto(room);
	}

	/***
	 * 
	 * @param Room instance
	 * @return Room DTO
	 */
	RoomDto convertToDto(Room r) {
		RoomDto roomDto = new RoomDto(r.getRoomId(), r.getCapacity(), r.getIsAvailable(), r.getRoomType());
		return roomDto;
	}

	/***
	 * 
	 * @param roomId
	 * @return list of all the room reservations associated to the room with the passed in room id
	 */
	@GetMapping(value = { "/rooms/getRoomReservationByRoom/{roomId}", "/rooms/getRoomReservationByRoom/{roomId}/" })
	public List<RoomReservationDto> getRoomReservationsByRoom(@PathVariable("roomId") String roomId) {
		ArrayList<RoomReservation> roomReservations = service.getRoomReservationsByRoom(Integer.parseInt(roomId));
		return convertToDto(roomReservations);
	}

	/***
	 * 
	 * @param clientUsername
	 * @return list of all the current room reservations associated to the account with the given username
	 */
	@GetMapping(value = { "/rooms/getRoomReservationByClient/{clientUsername}",
			"/rooms/getRoomReservationByRoom/{clientUsername}/" })
	public List<RoomReservationDto> getRoomReservationsByClient(@PathVariable("clientUsername") String clientUsername) {
		ArrayList<RoomReservation> roomReservations = service.getRoomReservationsByClient(clientUsername);
		return convertToDto(roomReservations);
	}

	/***
	 * 
	 * @param list of RoomReservations instances
	 * @return a list of RoomReservation DTOs
	 */
	ArrayList<RoomReservationDto> convertToDto(ArrayList<RoomReservation> roomReservations) {
		ArrayList<RoomReservationDto> roomReservationDtos = new ArrayList<RoomReservationDto>();
		for (RoomReservation roomReservation : roomReservations) {
			roomReservationDtos.add(convertToDto(roomReservation));
		}
		return roomReservationDtos;
	}
	
	
	
	

	/*
	 * 
	 * Room Reservations
	 * 
	 */
	
	
	/***
	 * @param roomId
	 * @return all room reservations in the form of DTO associated to the room with the given roomId
	 */
	@GetMapping(value = { "/roomReservations/get/{roomId}", "/roomReservations/get/{roomId}/" })
	public List<RoomReservationDto> getRoomReservations(@PathVariable("roomId") String roomId) {
		return service.getAllRoomReservations(Integer.parseInt(roomId)).stream().map(b -> convertToDto(b))
				.collect(Collectors.toList());
	}

	/***
	 * 
	 * @param roomId
	 * @param userId
	 * @param startHour
	 * @param startMin
	 * @param endHour
	 * @param endMin
	 * @param year
	 * @param month
	 * @param day
	 * @return the created Room Reservation DTO using the information passed in
	 * @throws IllegalArgumentException
	 */
	@PostMapping(value = { "/roomReservations/create/{roomId}" })
	public RoomReservationDto createRoomReservation(@PathVariable("roomId") String roomId, @RequestParam String userId,
			@RequestParam String startHour, String startMin, @RequestParam String endHour, @RequestParam String endMin,
			@RequestParam String year, @RequestParam String month, @RequestParam String day)
			throws IllegalArgumentException {

		Library library = getLibrary();

		Time startTime = new Time(Integer.parseInt(startHour), Integer.parseInt(startMin), 0);
		Time endTime = new Time(Integer.parseInt(endHour), Integer.parseInt(endMin), 0);
		Date date = new Date(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));

		RoomReservation roomReservation = service.createRoomReservation(startTime, endTime, date,
				Integer.parseInt(roomId), Integer.parseInt(userId), library);

		return convertToDto(roomReservation);
	}

	/***
	 * 
	 * This method will set the client to a room reservation which essentially books the client to the room for the given time slot
	 * @param roomReservationId associated to the id of the room reservation that was created
	 * @param userId
	 * @return updated Room Reservation DTO which will book the room for the given user
	 * @throws IllegalArgumentException
	 */
	@PostMapping(value = { "/roomReservations/update/{roomReservationId}" })
	public RoomReservationDto updateRoomReservation(@PathVariable("roomReservationId") int roomReservationId, @RequestParam  int userId)
			throws IllegalArgumentException {

		Library library = getLibrary();

		RoomReservation roomReservation = service.updateRoomReservation(roomReservationId, userId, library);

		return convertToDto(roomReservation);
	}

	/***
	 * 
	 * This method will remover the reservation which will generally be used in the front-end to delete reservations that have already 
	 * passed so that a given client does not have to see previously booked rooms
	 * @param roomId of the associated room you would like to remove
	 * @param userId of the user associated to the reservation
	 * @throws IllegalArgumentException
	 */
	@PostMapping(value = { "/roomReservations/remove/{roomId}", "/roomReservations/remove/{timeslotId}/" })
	public void removeRoomReservation(@PathVariable("roomId") String roomId, @RequestParam String userId)
			throws IllegalArgumentException {
		Library library = getLibrary();
		
		service.removeRoomReservation(Integer.parseInt(roomId), Integer.parseInt(userId), library);

	}

	/***
	 * 
	 * @param Room Reservation instance
	 * @return Room Reservation DTO using the information from the Room Reservation instance
	 */
	public RoomReservationDto convertToDto(RoomReservation rr) {
		RoomDto roomDto = convertToDto(rr.getRoom());
		ClientDto clientDto = convertToDto(rr.getClient());

		RoomReservationDto roomReservationDto = new RoomReservationDto(roomDto, clientDto, rr.getTimeSlotId(), rr.getStartTime(), rr.getEndTime(), rr.getDate());
		return roomReservationDto;
	}

	/*
	 * 
	 * Staff Schedules
	 * 
	 */

	/***
	 * 
	 * @param librarianUsername
	 * @return list of time slot for a given librarian using their respective username
	 */
	@GetMapping(value = { "/staffSchedules/get/{librarianUsername}", "/staffSchedules/get/{librarianUsername}/" })
	public List<TimeslotDto> getAllTimeSlotsInStaffSchedule(@PathVariable("librarianUsername") String librarianUsername) {
		return service.getAllLibrarianTimeslots(librarianUsername).stream().map(b -> convertToDto(b))
				.collect(Collectors.toList());
	}

	/***
	 * 
	 * @param librarianUsername
	 * @param startHour
	 * @param startMin
	 * @param endHour
	 * @param endMin
	 * @param year
	 * @param month
	 * @param day
	 * @return Timeslot DTO using the information passed through the params of the method
	 * @throws IllegalArgumentException
	 */
	@PostMapping(value = { "/staffSchedules/create/{librarianUsername}",
			"/staffSchedules/create/{librarianUsername}/" })
	public TimeslotDto createStaffScheduleTimeslot(@PathVariable("librarianUsername") String librarianUsername,
			@RequestParam String startHour, String startMin, @RequestParam String endHour, @RequestParam String endMin,
			@RequestParam String year, @RequestParam String month, @RequestParam String day)
			throws IllegalArgumentException {

		Library library = getLibrary();
		Librarian librarian = null;

		Time startTime = new Time(Integer.parseInt(startHour), Integer.parseInt(startMin), 0);
		Time endTime = new Time(Integer.parseInt(endHour), Integer.parseInt(endMin), 0);
		Date date = new Date(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));

		Timeslot timeslot = service.createStaffScheduleTimeslot(startTime, endTime, date, library, librarianUsername);

		return convertToDto(timeslot);
	}

	/***
	 * This method removes the staff schedule time slot for a given librarian
	 * @param timeslotId
	 * @param headLibrarianId
	 * @throws IllegalArgumentException
	 */
	@PostMapping(value = { "/staffSchedules/remove/{timeslotId}", "/staffSchedules/remove/{timeslotId}/" })
	public void removeStaffScheduleTimeslot(@PathVariable("timeslotId") String timeslotId, String headLibrarianId)
			throws IllegalArgumentException {
		
		Library library = getLibrary();

		service.removeStaffScheduleTimeslot(Integer.parseInt(timeslotId), Integer.parseInt(headLibrarianId), library);
	}

	/*
	 * 
	 * Helper methods
	 * 
	 */

	/***
	 * This method is used to parse requests from the front-end and translate a title type string to
	 * a title type variable of type TitleType
	 * @param titleType
	 * @return
	 */
	public TitleType parseTitleType(String titleType) {
		TitleType type = null;

		if (titleType.equals("MusicAlbum")) {
			type = TitleType.MusicAlbum;
		} else if (titleType.equals("Archive")) {
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

	/***
	 * This method is used to parse requests from the front-end and translate a room type string to
	 * a room type variable of type RoomType
	 * 
	 * @param roomType name as a string
	 * @return the room type as a Type
	 */
	public RoomType parseRoomType(String roomType) {
		if (roomType.equals("Study")) {
			return RoomType.Study;
		} else {
			return RoomType.Event;
		}
	}

	/***
	 * This method is used to determine when a title is to be returned following a reservation of the title
	 * @param Date
	 * @return the date object in 2 weeks time
	 */
	private Date sqlDatePlusDays(Date date) {
		return Date.valueOf(date.toLocalDate().plusDays(14));
	}

}
