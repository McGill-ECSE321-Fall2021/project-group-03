package ca.mcgill.ecse321.librarymanagement.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.librarymanagement.model.Book;


public interface BookRepository extends CrudRepository<Book, Integer>{
	
	Book findBookByTitleId(int titleId);

}
