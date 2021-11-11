package ca.mcgill.ecse321.librarymanagement.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.librarymanagement.dao.ClientRepository;
import ca.mcgill.ecse321.librarymanagement.dao.LibrarianRepository;
import ca.mcgill.ecse321.librarymanagement.dao.LibraryRepository;
import ca.mcgill.ecse321.librarymanagement.dao.ScheduleRepository;
import ca.mcgill.ecse321.librarymanagement.dao.TimeslotRepository;
import ca.mcgill.ecse321.librarymanagement.dao.TitleRepository;
import ca.mcgill.ecse321.librarymanagement.dto.TitleDto;
import ca.mcgill.ecse321.librarymanagement.model.Client;
import ca.mcgill.ecse321.librarymanagement.model.Librarian;
import ca.mcgill.ecse321.librarymanagement.model.Library;
import ca.mcgill.ecse321.librarymanagement.model.Schedule;
import ca.mcgill.ecse321.librarymanagement.model.Timeslot;
import ca.mcgill.ecse321.librarymanagement.model.Title;
import ca.mcgill.ecse321.librarymanagement.model.Title.TitleType;

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
	
	@Transactional
	public Title createTitle(String aName, String aDescription, String aGenre, boolean aIsAvailable,
			TitleType aTitleType, Library aLibrary) {
		Title title = new Title(aName, aDescription, aGenre, aIsAvailable, aTitleType);
		aLibrary.addTitle(title);
		titleRepository.save(title);
		libraryRepository.save(aLibrary);
		return title;
	}

	@Transactional
	public Title getTitle(int titleId) {
		Title title = titleRepository.findTitleByTitleId(titleId);
		return title;
	}

	public List<Title> getAllTitles() {
		return toList(titleRepository.findAll());
	}

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

	public Client createClient(String aUsername, String aPassword, String aFullname, String aResidentialAddress, String aEmail, boolean aIsResident, boolean aIsOnline, Library library) {
		
		Client client = new Client(aUsername, aPassword, aFullname, aResidentialAddress, aEmail, aIsResident, aIsOnline);
		library.addUser(client);
		clientRepository.save(client);
		libraryRepository.save(library);
		
		return client;
	}

	public List<Client> getAllClients() {
		return toList(clientRepository.findAll());
	}

	public Librarian createLibrarian(String username, String password, String fullName, boolean isHeadLibrarian, Library library) {
		Librarian librarian = new Librarian(username, password, fullName, isHeadLibrarian);
		library.addUser(librarian);
		librarianRepository.save(librarian);
		libraryRepository.save(library);
		return librarian;
	}

	public Timeslot createTimeslot(Time startTime, Time endTime, Date date, Schedule librarySchedule, Library library) {
		Timeslot timeslot = new Timeslot(startTime, endTime, date);
		librarySchedule.addTimeslot(timeslot);
		timeslotRepository.save(timeslot);
		scheduleRepository.save(librarySchedule);
		libraryRepository.save(library);
		
		return timeslot;
	}

	public List<Timeslot> getAllLibraryTimeslots() {
		List<Timeslot> timeslots = toList(timeslotRepository.findAll());
		
		// filter through only the library timeslots
		
		Library library = getLibrary();
		
		for (Timeslot t : timeslots) {
			
			// if the timeslot is not part of the library schedule remove it
			if (!library.getLibrarySchedule().getTimeslots().contains(t)) {
				timeslots.remove(t);
			}
		}
		
		return timeslots;
	}

}