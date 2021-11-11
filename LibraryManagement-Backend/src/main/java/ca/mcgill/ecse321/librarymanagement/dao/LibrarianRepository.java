package ca.mcgill.ecse321.librarymanagement.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarymanagement.model.Librarian;

public interface LibrarianRepository extends CrudRepository<Librarian, Integer>{
	
	Librarian findLibrarianByUserId(int userId); 
	
	Librarian findLibrarianByUsername(String username);

}
