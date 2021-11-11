package ca.mcgill.ecse321.librarymanagement.controller;
import java.sql.Date;
import java.sql.Time;
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
import ca.mcgill.ecse321.librarymanagement.dto.ScheduleDto;
import ca.mcgill.ecse321.librarymanagement.dto.TimeslotDto;
import ca.mcgill.ecse321.librarymanagement.dto.TitleDto;
import ca.mcgill.ecse321.librarymanagement.model.Client;
import ca.mcgill.ecse321.librarymanagement.model.Librarian;
import ca.mcgill.ecse321.librarymanagement.model.Library;
import ca.mcgill.ecse321.librarymanagement.model.Schedule;
import ca.mcgill.ecse321.librarymanagement.model.Timeslot;
import ca.mcgill.ecse321.librarymanagement.model.Title;
import ca.mcgill.ecse321.librarymanagement.model.Title.TitleType;
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
	 * */
	
	@GetMapping(value = { "/titles", "/titles/" })
	public List<TitleDto> getAllTitles() {
		return service.getAllTitles().stream().map(b -> convertToDto(b)).collect(Collectors.toList());
	}
	
	@PostMapping(value = { "/titles/{name}", "/titles/{name}/" })
	public TitleDto createTitle(@PathVariable("name") String name , @RequestParam String description, @RequestParam String genre, @RequestParam String isAvailable, @RequestParam String titleType)
			throws IllegalArgumentException {
		
		Library library = getLibrary();

		Title title = service.createTitle(name, description, genre, Boolean.parseBoolean(isAvailable), parseTitleType("Movie"), library);
		
		return convertToDto(title);
	}
	
	public TitleDto convertToDto(Title title) {
		TitleDto titleDto = new TitleDto (title.getTitleId(), title.getDescription(), title.getGenre(), title.getIsAvailable(), title.getTitleType());
		return titleDto;
	}
	
	/*
	 * 
	 * Client
	 * 
	 * */
	
	@GetMapping(value = { "/clients", "/clients/" })
	public List<ClientDto> getAllClients() {
		return service.getAllClients().stream().map(b -> convertToDto(b)).collect(Collectors.toList());
	}
	
	@PostMapping(value = { "/clients/{username}", "/clients/{username}/" })
	public ClientDto createClient(@PathVariable("username") String username , @RequestParam String password, @RequestParam String fullName, @RequestParam String residentialAddress, @RequestParam String email, @RequestParam String isResident, @RequestParam String isOnline)
			throws IllegalArgumentException {
		
		Library library = getLibrary();
		
		Client client = service.createClient(username, password, fullName, residentialAddress, email, Boolean.parseBoolean(isResident), Boolean.parseBoolean(isOnline), library);
		
		
		return convertToDto(client);
	}
	
	public ClientDto convertToDto(Client client) {
		ClientDto clientDto = new ClientDto(client.getUserId(), client.getUsername(), client.getPassword(), client.getResidentialAddress(), client.getEmail(), client.getIsResident(), client.getIsOnline());
		return clientDto;
	}
	
	/*
	 * 
	 * Librarian
	 * 
	 * */
	
	@GetMapping(value = { "/librarians", "/librarians/" })
	public List<TitleDto> getAllLibrarians() {
		return service.getAllTitles().stream().map(b -> convertToDto(b)).collect(Collectors.toList());
	}
	
	@PostMapping(value = { "/librarians/{username}", "/librarians/{username}/" })
	public LibrarianDto createLibrarian(@PathVariable("username") String username , @RequestParam String password, @RequestParam String fullName, @RequestParam boolean isHeadLibrarian)
			throws IllegalArgumentException {
		
		Library library = getLibrary();
		
		Librarian librarian = service.createLibrarian(username, password, fullName, isHeadLibrarian, library);
		
		
		return convertToDto(librarian);
	}
	
	public LibrarianDto convertToDto(Librarian librarian) {
		LibrarianDto librarianDto = new LibrarianDto(librarian.getUserId(), librarian.getUsername(), librarian.getPassword(), librarian.getFullname(), librarian.getIsHeadLibrarian());
		return librarianDto;
	}
	
	
	/*
	 * 
	 * Library
	 * 
	 * */
	
	@GetMapping(value = { "/library", "/library/" })
	public Library getLibrary() {
		
		return service.getLibrary();
		
	}
	
	/*
	 * 
	 * Helper methods
	 * 
	 * */
	
	public TitleType parseTitleType(String titleType) {
		TitleType type = null;
		
		if ( titleType.equals("MusicAlbum") ) {
			type = TitleType.MusicAlbum;
		} 
		else if (titleType.equals("Archives")) {
			type = TitleType.Archives;
		}
		else if ( titleType.equals("Book") ) {
			type = TitleType.Book;
		}
		else if ( titleType.equals("Newspaper") ) {
			type = TitleType.Newspaper;
		}
		else if ( titleType.equals("Movie") ) {
			type = TitleType.Movie;
		}
		return type;
	}

	/*
	 * 
	 * Schedule
	 * 
	 * */

	@GetMapping(value = { "/schedules", "/schedules/" })
	public List<ScheduleDto> getAllSchedules() {
		return service.getAllSchedules().stream().map(b -> convertToDto(b)).collect(Collectors.toList());
	}
	
	public ScheduleDto convertToDto(Schedule schedule) {
		ScheduleDto scheduleDto = new LibrarianDto(librarian.getUserId(), librarian.getUsername(), librarian.getPassword(), librarian.getFullname(), librarian.getIsHeadLibrarian());
		return librarianDto;
	}
	
	
	/*
	 * 
	 * Vassy
	 * 
	 * */
	
	
	// library timeslot
	
	@GetMapping(value = { "/libraryTimeslots", "/libraryTimeslots/" })
	public List<TimeslotDto> getAllLibraryTimeslots() {
		return service.getAllLibraryTimeslots().stream().map(b -> convertToDto(b)).collect(Collectors.toList());
	}
	
	@PostMapping(value = { "/libraryTimeslot", "/libraryTimeslot/" })
	public TimeslotDto createLibraryTimeslot(@RequestParam String startHour, String startMin, @RequestParam String endHour, @RequestParam String endMin, @RequestParam String year, @RequestParam String month, @RequestParam String day)
			throws IllegalArgumentException {
		
		Library library = getLibrary();
		
		Schedule librarySchedule = library.getLibrarySchedule();
		
		Time startTime = new Time(Integer.parseInt(startHour), Integer.parseInt(startMin), 0);
		
		Time endTime = new Time(Integer.parseInt(endHour), Integer.parseInt(endMin), 0);
		
		Date date = new Date(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));

		
		Timeslot timeslot = service.createTimeslot(startTime, endTime, date, librarySchedule, library);
		
		
		return convertToDto(timeslot);
	}
	
	public TimeslotDto convertToDto(Timeslot t) {
		TimeslotDto timeslotDto = new TimeslotDto(t.getStartTime(), t.getEndTime(), t.getDate(), t.getTimeSlotId());
		return timeslotDto;
	}
	
	
	// room reservation
	

	
	/*
	 * 
	 * Adam
	 * 
	 * */
	
	
	/*
	 * 
	 * Tara
	 * 
	 * */
	
	
	
	/*
	 * 
	 * Avi
	 * 
	 * */
	
	/*
	 * 
	 * Liam
	 * 
	 * */
	
}

