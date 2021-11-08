package ca.mcgill.ecse321.librarymanagement.controller;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.librarymanagement.dto.BookDto;
import ca.mcgill.ecse321.librarymanagement.dto.HeadLibrarianDto;
import ca.mcgill.ecse321.librarymanagement.dto.LibrarianDto;
import ca.mcgill.ecse321.librarymanagement.dto.MovieDto;
import ca.mcgill.ecse321.librarymanagement.dto.MusicAlbumDto;
import ca.mcgill.ecse321.librarymanagement.dto.NewspaperDto;
import ca.mcgill.ecse321.librarymanagement.dto.UserDto;
import ca.mcgill.ecse321.librarymanagement.model.Book;
import ca.mcgill.ecse321.librarymanagement.model.HeadLibrarian;
import ca.mcgill.ecse321.librarymanagement.model.Librarian;
import ca.mcgill.ecse321.librarymanagement.model.Library;
import ca.mcgill.ecse321.librarymanagement.model.LibrarySchedule;
import ca.mcgill.ecse321.librarymanagement.model.Movie;
import ca.mcgill.ecse321.librarymanagement.model.MusicAlbum;
import ca.mcgill.ecse321.librarymanagement.model.Newspaper;
import ca.mcgill.ecse321.librarymanagement.model.StaffSchedule;
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
	 * 
	 * 
	 * TARA
	 * 
	 * 
	 * 
	 */
	
	@GetMapping(value = { "/books", "/books/" })
	public List<BookDto> getAllBooks() {
		return service.getAllBooks().stream().map(b -> convertToDto(b)).collect(Collectors.toList());
	}
	
	//how to deal with multiple parameters???
	//@PostMapping(value = { "/books/{releaseDate}/{image}/{name}/{author}/{synopsis}/{genre}", "/books/{releaseDate}/{image}/{name}/{author}/{synopsis}/{genre}/" })
	@PostMapping(value = { "/books/{name}", "/books/{name}/" })
	public BookDto createBook(@PathVariable("name") String name, @RequestParam String image, 
			@RequestParam String author, @RequestParam String synopsis, @RequestParam String genre) throws IllegalArgumentException {
		// name = django,releaseDate,
		Date date = new Date(1,1,1);
		Book book = service.createBook(date, image, name, author, synopsis, genre);
		return convertToDto(book);
	}
	
	private BookDto convertToDto(Book b) {
		if (b == null) {
			throw new IllegalArgumentException("There is no such Book!");
		}
		BookDto bookDto = new BookDto(b.getReleaseDate(), b.getImage(), b.getName(), b.getAuthor(), b.getSynopsis(), b.getGenre());
		return bookDto;
		

	}
	
	@GetMapping(value = { "/movies", "/movies/" })
	public List<MovieDto> getAllMovies() {
		return service.getAllMovies().stream().map(m-> convertToDto(m)).collect(Collectors.toList());
	}
	
	//how to deal with multiple parameters???
	//@PostMapping(value = { "/books/{releaseDate}/{image}/{name}/{author}/{synopsis}/{genre}", "/books/{releaseDate}/{image}/{name}/{author}/{synopsis}/{genre}/" })
	@PostMapping(value = { "/movies/{name}", "/movies/{name}/" })
	public MovieDto createMovie(@PathVariable("name") String name) throws IllegalArgumentException {
		Date date = new Date(1,1,1);
		Movie movie = service.createMovie(date, "image", name, "a", "b", "c", 10);
		return convertToDto(movie);
	}
	
	private MovieDto convertToDto(Movie m) {
		if (m == null) {
			throw new IllegalArgumentException("There is no such Movie!");
		}
		MovieDto movieDto = new MovieDto(m.getReleaseDate(), m.getImage(), m.getName(), m.getDirector(), m.getGenre(), m.getSynopsis(), m.getDuration());
		return movieDto;
		

	}
	
	@GetMapping(value = { "/newspapers", "/newspapers/" })
	public List<NewspaperDto> getAllNewspapers() {
		return service.getAllNewspapers().stream().map(n-> convertToDto(n)).collect(Collectors.toList());
	}
	
	@PostMapping(value = { "/newspapers/{name}", "/newspapers/{name}/" })
	public NewspaperDto createNewspaper(@PathVariable("name") String name) throws IllegalArgumentException {
		Date date = new Date(1,1,1);
		Newspaper newspaper = service.createNewspaper(date, "image", name, "a");
		return convertToDto(newspaper);
	}
	
	private NewspaperDto convertToDto(Newspaper n) {
		if (n == null) {
			throw new IllegalArgumentException("There is no such Newspaper!");
		}
		NewspaperDto newspaperDto = new NewspaperDto(n.getReleaseDate(), n.getImage(), n.getName(), n.getHeadline());
		return newspaperDto;
	}
	
	@GetMapping(value = { "/musicalbums", "/musicalbums/" })
	public List<MusicAlbumDto> getAllMusicAlbums() {
		return service.getAllMusicAlbums().stream().map(m-> convertToDto(m)).collect(Collectors.toList());
	}
	
	@PostMapping(value = { "/musicalbums/{name}", "/musicalbums/{name}/" })
	public MusicAlbumDto createMusicAlbum(@PathVariable("name") String name) throws IllegalArgumentException {
		Date date = new Date(1,1,1);
		MusicAlbum musicAlbum = service.createMusicAlbum(date, "image", name, "artist", 10, "genre");
		return convertToDto(musicAlbum);
	}
	
	private MusicAlbumDto convertToDto(MusicAlbum m) {
		if (m == null) {
			throw new IllegalArgumentException("There is no such Music Album!");
		}
		MusicAlbumDto musicAlbumDto = new MusicAlbumDto(m.getReleaseDate(), m.getImage(), m.getName(), m.getArtist(), m.getTrackList(), m.getDuration(), m.getGenre());
		return musicAlbumDto;
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
	
	//user
	
	@GetMapping(value = { "/users", "/users/" })
	public List<UserDto> getAllUsers() {
		return service.getAllUsers().stream().map(u -> convertToDto(u)).collect(Collectors.toList());
	}
	
	@PostMapping(value = { "/users/{name}", "/users/{name}/" })
	public UserDto createUser(@PathVariable("name") String name) throws IllegalArgumentException {
		String username = "username";
		String password = "password";
		String email = "email";
		String fullName = "name";
		String resAddress = "resAddress";
		boolean isResident = true;
		LibrarySchedule librarySchedule = new LibrarySchedule();
		Library library = new Library(librarySchedule);
		
		User user = new User(username, password, email, fullName, resAddress, isResident, library);
		return convertToDto(user);
	}
	
	private UserDto convertToDto(User u) {
		if (u == null) {
			throw new IllegalArgumentException("There is no such Book!");
		}
		UserDto userDto = new UserDto(u.getUsername(), u.getPassword(), u.getEmailaddress(), u.getFullName(), u.getResAddress(), u.getIsResident(), u.getLibrary());
		return userDto;
	}
	
	//librarian
	
	@GetMapping(value = { "/librarians", "/librarians/" })
	public List<UserDto> getAllLibrarians() {
		return service.getAllUsers().stream().map(u -> convertToDto(u)).collect(Collectors.toList());
	}
	
	@PostMapping(value = { "/librarians/{name}", "/librarians/{name}/" })
	public LibrarianDto createLibrarian(@PathVariable("name") String name) throws IllegalArgumentException {
		String username = "username";
		String password = "password";
		String email = "email";
		String fullName = "name";
		String resAddress = "resAddress";
		boolean isResident = true;
		LibrarySchedule librarySchedule = new LibrarySchedule();
		Library library = new Library(librarySchedule);
		StaffSchedule staffSchedule = new StaffSchedule();
		
		Librarian librarian = new Librarian(username, password, email, fullName, resAddress, isResident, library, staffSchedule);
		return convertToDto(librarian);
	}
	
	private LibrarianDto convertToDto(Librarian l) {
		if (l == null) {
			throw new IllegalArgumentException("There is no such Book!");
		}
		LibrarianDto librarianDto = new LibrarianDto(l.getUsername(), l.getPassword(), l.getEmailaddress(), l.getFullName(), l.getResAddress(), l.getIsResident(), l.getLibrary(), l.getStaffSchedule());
		return librarianDto;
	}
	
	// head librarian
	
		@GetMapping(value = { "/headLibrarians", "/headLibrarians/" })
		public List<UserDto> getAllHeadLibrarians() {
			return service.getAllUsers().stream().map(u -> convertToDto(u)).collect(Collectors.toList());
		}
		
		@PostMapping(value = { "/headLibrarians/{name}", "/headLibrarians/{name}/" })
		public HeadLibrarianDto createHeadLibrarian(@PathVariable("name") String name) throws IllegalArgumentException {
			String username = "username";
			String password = "password";
			String email = "email";
			String fullName = "name";
			String resAddress = "resAddress";
			boolean isResident = true;
			LibrarySchedule librarySchedule = new LibrarySchedule();
			Library library = new Library(librarySchedule);
			StaffSchedule staffSchedule = new StaffSchedule();
			
			HeadLibrarian headLibrarian = new HeadLibrarian(username, password, email, fullName, resAddress, isResident, library, staffSchedule);
			return convertToDto(headLibrarian);
		}
		
		private HeadLibrarianDto convertToDto(HeadLibrarian hl) {
			if (hl == null) {
				throw new IllegalArgumentException("There is no such Book!");
			}
			HeadLibrarianDto headLibrarianDto = new HeadLibrarianDto(hl.getUsername(), hl.getPassword(), hl.getEmailaddress(), hl.getFullName(), hl.getResAddress(), hl.getIsResident(), hl.getLibrary(), hl.getStaffSchedule());
			return headLibrarianDto;
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