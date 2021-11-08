package ca.mcgill.ecse321.librarymanagement.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.librarymanagement.dao.BookRepository;
import ca.mcgill.ecse321.librarymanagement.dao.HeadLibrarianRepository;
import ca.mcgill.ecse321.librarymanagement.dao.LibrarianRepository;
import ca.mcgill.ecse321.librarymanagement.dao.LibraryRepository;
import ca.mcgill.ecse321.librarymanagement.dao.LibraryScheduleRepository;
import ca.mcgill.ecse321.librarymanagement.dao.MovieRepository;
import ca.mcgill.ecse321.librarymanagement.dao.MusicAlbumRepository;
import ca.mcgill.ecse321.librarymanagement.dao.NewspaperRepository;
import ca.mcgill.ecse321.librarymanagement.dao.RoomRepository;
import ca.mcgill.ecse321.librarymanagement.dao.RoomScheduleRepository;
import ca.mcgill.ecse321.librarymanagement.dao.StaffScheduleRepository;
import ca.mcgill.ecse321.librarymanagement.dao.TimeSlotRepository;
import ca.mcgill.ecse321.librarymanagement.dao.UserRepository;
import ca.mcgill.ecse321.librarymanagement.model.Book;
import ca.mcgill.ecse321.librarymanagement.model.HeadLibrarian;
import ca.mcgill.ecse321.librarymanagement.model.Librarian;
import ca.mcgill.ecse321.librarymanagement.model.Library;
import ca.mcgill.ecse321.librarymanagement.model.LibrarySchedule;
import ca.mcgill.ecse321.librarymanagement.model.StaffSchedule;
import ca.mcgill.ecse321.librarymanagement.model.User;
import ca.mcgill.ecse321.librarymanagement.model.Movie;

@Service
public class LibraryManagementService {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private MusicAlbumRepository musicAlbumRepository;
	
	@Autowired
	private NewspaperRepository newspaperRepository;
	
	@Autowired
	private LibraryScheduleRepository libraryScheduleRepository;
	
	@Autowired
	private RoomScheduleRepository roomScheduleRepository;
	
	@Autowired
	private StaffScheduleRepository staffScheduleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private HeadLibrarianRepository headLibrarianRepository;
	
	@Autowired
	private LibrarianRepository librarianRepository;
	
	@Autowired
	private TimeSlotRepository timeSlotRepository;
	
	@Autowired
	private LibraryRepository libraryRepository;
	
	@Autowired
	private RoomRepository roomRepository;
	
	//Starting with head librarian just as an example.
	
	/*
	 * 
	 * 
	 * 
	 * TARA
	 * 
	 * 
	 * 
	 */
	
	@Transactional
	public Book createBook(Date aReleaseDate, String aImage, String aName, String aAuthor, String aSynopsis, String aGenre ) {
		Book book = new Book(aReleaseDate, aImage, aName, aAuthor, aSynopsis, aGenre);
		bookRepository.save(book);
		return book;
	}
	
	@Transactional
	public Book getBook(int titleId ) {
		Book book = bookRepository.findBookByTitleId(titleId);
		return book;
	}
	
	public List<Book> getAllBooks() {
		return toList(bookRepository.findAll());
	}
	
	@Transactional
	public Movie createMovie(Date aReleaseDate, String aImage, String aName, String aDirector, String aGenre, String aSynopsis, int aDuration ) {
		Movie movie = new Movie(aReleaseDate, aImage, aName, aDirector, aGenre, aSynopsis, aDuration);
		movieRepository.save(movie);
		return movie;
	}
	
	@Transactional
	public Movie getMovie(int titleId ) {
		Movie movie = movieRepository.findMovieByTitleId(titleId);
		return movie;
	}
	
	public List<Movie> getAllMovies() {
		return toList(movieRepository.findAll());
	}
	
	// Method to convert to a list
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
	
	/*
	 * 
	 * 
	 * 
	 * ADAM
	 * 
	 * 
	 * 
	 */
	
	/*
	 * 
	 * 
	 * 
	 * SAM
	 * 
	 * 
	 * 
	 */
	
	
	// users
	
	@Transactional
	public User createUser(String username, String password, String email, String fullName, String resAddress, boolean isResident, Library library) {
		User user = new User(username, password, email, fullName, resAddress, isResident, library);
		userRepository.save(user);
		return user;
	}
	
	@Transactional
	public User getUser(int userId ) {
		User user = userRepository.findUserByUserId(userId);
		return user;
	}
	
	public List<User> getAllUsers() {
		return toList(userRepository.findAll());
	}
	
	// librarians
	
	@Transactional
	public Librarian createLibrarian(String username, String password, String email, String fullName, String resAddress, boolean isResident, Library library, StaffSchedule staffSchedule) {
		Librarian librarian = new Librarian(username, password, email, fullName, resAddress, isResident, library, staffSchedule);
		librarianRepository.save(librarian);
		return librarian;
	}
	
	@Transactional
	public Librarian getLibrarian(int userId) {
		Librarian librarian = librarianRepository.findLibrarianByUserId(userId);
		return librarian;
	}
	
	public List<Librarian> getAllLibrarians() {
		return toList(librarianRepository.findAll());
	}
	
	// head librarian
	
	@Transactional
	public HeadLibrarian createHeadLibrarian(String username, String password, String email, String fullName, String resAddress, boolean isResident, Library library, StaffSchedule staffSchedule) {
		HeadLibrarian headLibrarian = new HeadLibrarian(username, password, email, fullName, resAddress, isResident, library, staffSchedule);
		headLibrarianRepository.save(headLibrarian);
		return headLibrarian;
	}
	
	@Transactional
	public Librarian getHeadLibrarian(int userId) {
		Librarian headLibrarian = headLibrarianRepository.findHeadLibrarianByUserId(userId);
		return headLibrarian;
	}
	
	public List<HeadLibrarian> getAllHeadLibrarians() {
		return toList(headLibrarianRepository.findAll());
	}
	
	/*
	 * 
	 * 
	 * 
	 * AVI
	 * 
	 * 
	 * 
	 */
	
	public Library createLibrary() {
		LibrarySchedule librarySchedule = new LibrarySchedule();
		Library library = new Library(librarySchedule);
		libraryRepository.save(library);
		return library;
	}
	

	@Transactional
	public Library getLibrary (int libraryId) {
		Library library = libraryRepository.findLibraryByLibraryId(libraryId);
		return library;
	}
	
	@Transactional
	public List<Library> getAllLibraries() {
		return toList(libraryRepository.findAll());
	}
	
	/*
	 * 
	 * 
	 * 
	 * LIAM
	 * 
	 * 
	 * 
	 */
	
}
