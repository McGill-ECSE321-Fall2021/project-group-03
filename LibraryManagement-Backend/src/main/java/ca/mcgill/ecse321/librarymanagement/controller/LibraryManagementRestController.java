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
import ca.mcgill.ecse321.librarymanagement.dto.LibraryDto;
import ca.mcgill.ecse321.librarymanagement.dto.MovieDto;

import ca.mcgill.ecse321.librarymanagement.dto.RoomDto;
import ca.mcgill.ecse321.librarymanagement.dto.RoomScheduleDto;
import ca.mcgill.ecse321.librarymanagement.dto.StaffScheduleDto;
import ca.mcgill.ecse321.librarymanagement.dto.MusicAlbumDto;
import ca.mcgill.ecse321.librarymanagement.dto.NewspaperDto;

import ca.mcgill.ecse321.librarymanagement.dto.UserDto;
import ca.mcgill.ecse321.librarymanagement.model.Book;
import ca.mcgill.ecse321.librarymanagement.model.HeadLibrarian;
import ca.mcgill.ecse321.librarymanagement.model.Librarian;
import ca.mcgill.ecse321.librarymanagement.model.Library;
import ca.mcgill.ecse321.librarymanagement.model.LibrarySchedule;
import ca.mcgill.ecse321.librarymanagement.model.Movie;

import ca.mcgill.ecse321.librarymanagement.model.StaffSchedule;
import ca.mcgill.ecse321.librarymanagement.model.Title;
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
	public BookDto createBook(@PathVariable("name") String name, @RequestParam String date, @RequestParam String image, @RequestParam String author, @RequestParam String synopsis, @RequestParam String genre) throws IllegalArgumentException {

		String[] dateArr = date.split("-");
		int year = Integer.parseInt(dateArr[0]);
		int month = Integer.parseInt(dateArr[1]);
		int day = Integer.parseInt(dateArr[2]);
		Date dateObj = new Date(year,month,day);
		Book book = service.createBook(dateObj, image, name, author, synopsis, genre);
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
	public MovieDto createMovie(@PathVariable("name") String name, @RequestParam String date, @RequestParam String image, @RequestParam String director, @RequestParam String genre, @RequestParam String synopsis, @RequestParam int duration) throws IllegalArgumentException {
		String[] dateArr = date.split("-");
		int year = Integer.parseInt(dateArr[0]);
		int month = Integer.parseInt(dateArr[1]);
		int day = Integer.parseInt(dateArr[2]);
		Date dateObj = new Date(year,month,day);
		Movie movie = service.createMovie(dateObj, image, name, director, genre, synopsis, duration);
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
	public NewspaperDto createNewspaper(@PathVariable("name") String name, @RequestParam String date, @RequestParam String image, @RequestParam String headline) throws IllegalArgumentException {
		//date format YYYY-MM-DD
		String[] dateArr = date.split("-");
		int year = Integer.parseInt(dateArr[0]);
		int month = Integer.parseInt(dateArr[1]);
		int day = Integer.parseInt(dateArr[2]);
		Date dateObj = new Date(year, month, day);
		Newspaper newspaper = service.createNewspaper(dateObj, image , name, headline);
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
	public MusicAlbumDto createMusicAlbum(@PathVariable("name") String name, @RequestParam String date, @RequestParam String image, @RequestParam String artist, @RequestParam int duration, @RequestParam String genre) throws IllegalArgumentException {
		String[] dateArr = date.split("-");
		int year = Integer.parseInt(dateArr[0]);
		int month = Integer.parseInt(dateArr[1]);
		int day = Integer.parseInt(dateArr[2]);
		Date dateObj = new Date(year,month,day);
		MusicAlbum musicAlbum = service.createMusicAlbum(dateObj, image, name, artist, 10, genre);
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
	
	//use these values to get the staffSchedules from service
		@GetMapping(value = { "/roomSchedules", "/roomSchedules/" })
		public List<RoomScheduleDto> getAllRoomSchedules() {
			return service.getAllRoomSchedules().stream().map(b -> convertToDto(b)).collect(Collectors.toList());
		}
		
		@PostMapping(value = { "/roomSchedules/", "/roomSchedules"  })
		public RoomScheduleDto createRoomSchedule() throws IllegalArgumentException {
			RoomSchedule roomSchedule = new RoomSchedule();
			return convertToDto(roomSchedule);
		}
		
		//used to copy a given RoomSchedule object to be used as a DTO
		private RoomScheduleDto convertToDto(RoomSchedule b) {
			if (b == null) {
				throw new IllegalArgumentException("There is no such Room Schedule!");
			}
			RoomScheduleDto roomScheduleDto = new RoomScheduleDto();
			return roomScheduleDto;
		}
		
		//use these values to get the staffSchedules from service
		@GetMapping(value = { "/librarySchedules", "/librarySchedules/" })
		public List<LibraryScheduleDto> getAllLibrarySchedules() {
			return service.getAllLibrarySchedules().stream().map(b -> convertToDto(b)).collect(Collectors.toList());
		}
		
		@PostMapping(value = { "/librarySchedule/", "/librarySchedule" })
		public LibraryScheduleDto createLibrarySchedule() throws IllegalArgumentException {
			LibrarySchedule librarySchedule = new LibrarySchedule();
			return convertToDto(librarySchedule);
		}
		
		//used to copy a given LibrarySchedule object to be used as a DTO
		private LibraryScheduleDto convertToDto(LibrarySchedule b) {
			if (b == null) {
				throw new IllegalArgumentException("There is no such Library Schedule!");
			}
			LibraryScheduleDto libraryScheduleDto = new LibraryScheduleDto();
			return libraryScheduleDto;
		}
		
		//use these values to get the staffSchedules from service
		@GetMapping(value = { "/staffSchedules", "/staffSchedules/" })
		public List<StaffScheduleDto> getAlltaffSchedules() {
			return service.getAllStaffSchedules().stream().map(b -> convertToDto(b)).collect(Collectors.toList());
		}
		
		@PostMapping(value = { "/staffSchedules/", "/staffSchedules" })
		public StaffScheduleDto createStaffSchedule() throws IllegalArgumentException {
			StaffSchedule staffSchedule = new StaffSchedule();
			return convertToDto(staffSchedule);
		}
		
		//used to copy a given StaffSchedule object to be used as a DTO
		private StaffScheduleDto convertToDto(StaffSchedule b) {
			if (b == null) {
				throw new IllegalArgumentException("There is no such Library Schedule!");
			}
			StaffScheduleDto staffScheduleDto = new StaffScheduleDto();
			return staffScheduleDto;
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
	
	//user
	
	@GetMapping(value = { "/users", "/users/" })
	public List<UserDto> getAllUsers() {
		return service.getAllUsers().stream().map(u -> convertToDto(u)).collect(Collectors.toList());
	}
	
	@PostMapping(value = { "/users/{username}", "/users/{username}/" })
	public UserDto createUser(@PathVariable("username") String username, @RequestParam String password, @RequestParam String email, @RequestParam String fullName, @RequestParam String resAddress, @RequestParam boolean isResident) throws IllegalArgumentException {
		LibrarySchedule librarySchedule = new LibrarySchedule();
		Library library = new Library(librarySchedule);
		User user = new User(username, password, email, fullName, resAddress, isResident, library);
		return convertToDto(user);
	}
	
	private UserDto convertToDto(User u) {
		if (u == null) {
			throw new IllegalArgumentException("There is no such User!");
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
	public LibrarianDto createLibrarian(@PathVariable("name") String name, @RequestParam String password, String email, String fullName, String resAddress, boolean isResident) throws IllegalArgumentException {
		String username = "username";
		LibrarySchedule librarySchedule = new LibrarySchedule();
		Library library = new Library(librarySchedule);
		StaffSchedule staffSchedule = new StaffSchedule();
		
		Librarian librarian = new Librarian(username, password, email, fullName, resAddress, isResident, library, staffSchedule);
		return convertToDto(librarian);
	}
	
	private LibrarianDto convertToDto(Librarian l) {
		if (l == null) {
			throw new IllegalArgumentException("There is no such Librarian!");
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
		public HeadLibrarianDto createHeadLibrarian(@PathVariable("username") String username, @RequestParam String password, @RequestParam String email, @RequestParam String fullName, @RequestParam String resAddress, @RequestParam boolean isResident) throws IllegalArgumentException {
			LibrarySchedule librarySchedule = new LibrarySchedule();
			Library library = new Library(librarySchedule);
			StaffSchedule staffSchedule = new StaffSchedule();
			
			HeadLibrarian headLibrarian = new HeadLibrarian(username, password, email, fullName, resAddress, isResident, library, staffSchedule);
			return convertToDto(headLibrarian);
		}
		
		private HeadLibrarianDto convertToDto(HeadLibrarian hl) {
			if (hl == null) {

				throw new IllegalArgumentException("There is no such HeadLibrarian!");

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
		
		
		@GetMapping(value = { "/library", "/library/" })     //in singular because we will only have one library????
		public List<LibraryDto> getAllLibraries() {
			return service.getAllLibraries().stream().map(u -> convertToDto(u)).collect(Collectors.toList());
		}
		
		//ARE YOU SUPPOSED TO BE ABLE TO ADD USERS, ROOMS, ETC?????
		//I used ID to be the identifier for library, but isn't it supposed to be autogenerated - unsure what else to use...
		@PostMapping(value = { "/library/{ID}", "/librarians/{ID}/" })
		public LibraryDto createLibrary(@PathVariable("ID") int ID) throws IllegalArgumentException {
			LibrarySchedule librarySchedule = new LibrarySchedule();
			Library library = new Library(librarySchedule);
			library.setLibraryId(ID);
			return convertToDto(library);
		}
		
		private LibraryDto convertToDto(Library library) {
			if (library == null) {
				throw new IllegalArgumentException("There is no such Library!");
			} else {
				LibraryDto libraryDto = new LibraryDto (library.getLibraryId(), library.getRooms(), library.getTitles(), library.getLibrarySchedule(), library.getUsers());
				return libraryDto;
			}
		}
		//DO WE NEED TO MAKE DEEP COPIES OF ANYTHING OR JUST DOING THIS IS FINE???


	/*
	 * 
	 * 
	 * 
	 * LIAM
	 * 
	 * 
	 * 
	 */
	
		@GetMapping(value = { "/rooms", "/rooms/" })
		public List<RoomDto> getAllRooms() {
			return service.getAllRooms().stream().map(r -> convertToDto(r)).collect(Collectors.toList());
		}
	
		//The name needs to be verified. 
		@PostMapping(value = { "/rooms", "/rooms/" })
		public RoomDto createRoom(@PathVariable("") String name) {
			
			Room room = service.createRoom(null, null);
			return convertToDto(room);
		}

		private RoomDto convertToDto(Room r) {
			if (r == null) {
				throw new IllegalArgumentException("There is no such Room!");
	}
			RoomDto roomDto = new RoomDto(r.getRoomSchedule(), r.getLibrary());
			return roomDto;
}
	
	
}