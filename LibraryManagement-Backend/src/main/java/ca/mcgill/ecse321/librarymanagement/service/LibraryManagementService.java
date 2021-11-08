package ca.mcgill.ecse321.librarymanagement.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import ca.mcgill.ecse321.librarymanagement.dto.LibraryDto;
import ca.mcgill.ecse321.librarymanagement.dto.RoomScheduleDto;
import ca.mcgill.ecse321.librarymanagement.dto.StaffScheduleDto;
import ca.mcgill.ecse321.librarymanagement.model.Book;
import ca.mcgill.ecse321.librarymanagement.model.HeadLibrarian;
import ca.mcgill.ecse321.librarymanagement.model.Librarian;
import ca.mcgill.ecse321.librarymanagement.model.Library;
import ca.mcgill.ecse321.librarymanagement.model.LibrarySchedule;
import ca.mcgill.ecse321.librarymanagement.model.StaffSchedule;
import ca.mcgill.ecse321.librarymanagement.model.User;
import ca.mcgill.ecse321.librarymanagement.model.Movie;
import ca.mcgill.ecse321.librarymanagement.model.RoomSchedule;

import ca.mcgill.ecse321.librarymanagement.model.Room;
import ca.mcgill.ecse321.librarymanagement.model.RoomSchedule;
import ca.mcgill.ecse321.librarymanagement.model.MusicAlbum;
import ca.mcgill.ecse321.librarymanagement.model.Newspaper;


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
	
	@Transactional
	public Newspaper createNewspaper(Date aReleaseDate, String aImage, String aName, String aHeadline) {
		Newspaper newspaper = new Newspaper(aReleaseDate, aImage, aName, aHeadline);
		newspaperRepository.save(newspaper);
		return newspaper;
	}
	
	@Transactional
	public Newspaper getNewspaper(int titleId ) {
		Newspaper newspaper = newspaperRepository.findNewspaperByTitleId(titleId);
		return newspaper;
	}
	
	public List<Newspaper> getAllNewspapers(){
		return toList(newspaperRepository.findAll());
	}
	
	@Transactional
	public MusicAlbum createMusicAlbum(Date aReleaseDate, String aImage, String aName, String aArtist, int aDuration, String aGenre) {
		MusicAlbum musicAlbum = new MusicAlbum(aReleaseDate, aImage, aName, aArtist, aDuration, aGenre);
		musicAlbumRepository.save(musicAlbum);
		return musicAlbum;
	}
	
	@Transactional
	public MusicAlbum getMusicAlbum(int titleId) {
		MusicAlbum musicAlbum = musicAlbumRepository.findMusicAlbumByTitleId(titleId);
		return musicAlbum;
	}
	
	public List<MusicAlbum> getAllMusicAlbums(){
		return toList(musicAlbumRepository.findAll());
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
	
	@Transactional
	public RoomSchedule createRoomSchedule() {
		RoomSchedule roomSchedule = new RoomSchedule();
		return roomSchedule;
	}
	
	@Transactional
	public RoomSchedule getRoomSchedule(int scheduleId) {
		RoomSchedule roomSchedule = roomScheduleRepository.findRoomScheduleByScheduleId(scheduleId);
		return roomSchedule;
	}
	
	public List<RoomSchedule> getAllRoomSchedules() {
		return toList(roomScheduleRepository.findAll());
	}
	
	@Transactional
	public LibrarySchedule createLibrarySchedule() {
		LibrarySchedule librarySchedule = new LibrarySchedule();
		return librarySchedule;
	}
	
	@Transactional
	public LibrarySchedule getLibrarySchedule(int scheduleId) {
		LibrarySchedule librarySchedule = libraryScheduleRepository.findLibraryScheduleByScheduleId(scheduleId);
		return librarySchedule;
	}
	
	public List<LibrarySchedule> getAllLibrarySchedules() {
		return toList(libraryScheduleRepository.findAll());
	}
	
	@Transactional
	public StaffSchedule createStaffSchedule() {
		StaffSchedule staffSchedule = new StaffSchedule();
		return staffSchedule;
	}
	
	@Transactional
	public StaffSchedule getStaffSchedule(int scheduleId) {
		StaffSchedule staffSchedule = staffScheduleRepository.findStaffScheduleByScheduleId(scheduleId);
		return staffSchedule;
	}
	
	public List<StaffSchedule> getAllStaffSchedules() {
		return toList(staffScheduleRepository.findAll());
	}
	
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
	public User createUser(String username, String password, String email, String fullName, String resAddress, boolean isResident, LibraryDto libraryDto) {
		Library library = libraryRepository.findLibraryByLibraryId(libraryDto.getLibraryId());
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
	public Librarian createLibrarian(String username, String password, String email, String fullName, String resAddress, boolean isResident, LibraryDto libraryDto, StaffScheduleDto staffScheduleDto) {
		Library library = libraryRepository.findLibraryByLibraryId(libraryDto.getLibraryId());
		StaffSchedule staffSchedule = staffScheduleRepository.findStaffScheduleByScheduleId(staffScheduleDto.getScheduleId());
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
	public HeadLibrarian createHeadLibrarian(String username, String password, String email, String fullName, String resAddress, boolean isResident, LibraryDto libraryDto, StaffScheduleDto staffScheduleDto) {
		Library library = libraryRepository.findLibraryByLibraryId(libraryDto.getLibraryId());
		StaffSchedule staffSchedule = staffScheduleRepository.findStaffScheduleByScheduleId(staffScheduleDto.getScheduleId());
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
	
	@Transactional
	public Room createRoom (RoomScheduleDto aRoomSchedule, LibraryDto aLibrary) {
		RoomSchedule roomSchedule = roomScheduleRepository.findRoomScheduleByScheduleId(aRoomSchedule.getScheduleId());
		Library library = libraryRepository.findLibraryByLibraryId(aLibrary.getLibraryId());
		Room room = new Room(roomSchedule, library);
		roomRepository.save(room);
		return room;
	}
	
	@Transactional
	public Room getRoom(int roomId ) {
		Room room = roomRepository.findRoomByRoomId(roomId);
		return room;
	}
	
	@Transactional
	public List<Room> getAllRooms() {
		return toList(roomRepository.findAll());
	}
	
}
