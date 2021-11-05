package ca.mcgill.ecse321.librarymanagement.controller;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.librarymanagement.dto.BookDto;
import ca.mcgill.ecse321.librarymanagement.model.Book;
import ca.mcgill.ecse321.librarymanagement.service.LibraryManagementService;

//Should imports be the same as example??

@CrossOrigin(origins = "*")
@RestController
public class LibraryManagementRestController {

	@Autowired
	private LibraryManagementService service;
	
	@GetMapping(value = { "/books", "/books/" })
	public List<BookDto> getAllBooks() {
		return service.getAllBooks().stream().map(b -> convertToDto(b)).collect(Collectors.toList());
	}
	
	//how to deal with multiple parameters???
	@PostMapping(value = { "/books/{name}", "/books/{name}/" })
	public BookDto createBook(@PathVariable("name") String name) throws IllegalArgumentException {
		Date date = new Date(1,1,1);
		Book book = service.createBook(date, name, "a", "b", "c", "d");
		return convertToDto(book);
	}
	
	private BookDto convertToDto(Book b) {
		if (b == null) {
			throw new IllegalArgumentException("There is no such Book!");
		}
		BookDto bookDto = new BookDto(b.getReleaseDate(), b.getImage(), b.getName(), b.getAuthor(), b.getSynopsis(), b.getGenre());
		return bookDto;
		

	}
}
