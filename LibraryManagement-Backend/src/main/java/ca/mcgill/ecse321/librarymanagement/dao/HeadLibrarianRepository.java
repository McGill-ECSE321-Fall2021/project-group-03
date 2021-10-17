package ca.mcgill.ecse321.librarymanagement.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarymanagement.model.HeadLibrarian;
import ca.mcgill.ecse321.librarymanagement.model.User;

public interface HeadLibrarianRepository extends CrudRepository<HeadLibrarian, Integer>{
	
	HeadLibrarian findHeadLibrarianByUserId(int userId); 

}
