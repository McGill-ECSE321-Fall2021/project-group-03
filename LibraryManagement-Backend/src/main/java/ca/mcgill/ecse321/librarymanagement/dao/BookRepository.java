package ca.mcgill.ecse321.librarymanagement.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarymanagement.model.Book;

public interface BookRepository extends CrudRepository<Book, String>{
	
	Book findBookById(String id);

}
