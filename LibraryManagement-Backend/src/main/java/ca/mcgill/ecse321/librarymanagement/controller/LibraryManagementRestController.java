package ca.mcgill.ecse321.librarymanagement.controller;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.librarymanagement.dto.TitleDto;
import ca.mcgill.ecse321.librarymanagement.model.Library;
import ca.mcgill.ecse321.librarymanagement.model.Title;
import ca.mcgill.ecse321.librarymanagement.model.Title.TitleType;
import ca.mcgill.ecse321.librarymanagement.service.LibraryManagementService;


//Should imports be the same as example??

@CrossOrigin(origins = "*")
@RestController
public class LibraryManagementRestController {

	@Autowired
	private LibraryManagementService service;
	
	@GetMapping(value = { "/titles", "/titles/" })
	public List<TitleDto> getAllBooks() {
		return service.getAllTitles().stream().map(b -> convertToDto(b)).collect(Collectors.toList());
	}
	
	@PostMapping(value = { "/titles/{name}", "/titles/{name}/" })
	public TitleDto createBook(@PathVariable("name") String name , @RequestParam String description, @RequestParam String genre, @RequestParam String isAvailable, @RequestParam String titleType)
			throws IllegalArgumentException {
		
		Library library = getLibrary();

		Title title = service.createTitle(name, description, genre, Boolean.parseBoolean(isAvailable), parseTitleType("Movie"), library);
		
		return convertToDto(title);
	}
	
	public TitleDto convertToDto(Title title) {
		TitleDto titleDto = new TitleDto (title.getTitleId(), title.getDescription(), title.getGenre(), title.getIsAvailable(), title.getTitleType());
		return titleDto;
	}
	
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
	
	@GetMapping(value = { "/library", "/library/" })
	public Library getLibrary() {
		return service.getLibrary();
	}
	
	public void createLibrary() {
		service.createLibrary();
	}
	


}

