package ca.mcgill.ecse321.librarymanagement.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarymanagement.model.Library;

public interface LibraryRepository extends CrudRepository<Library, Integer>{
	
	Library findLibraryByLibraryId(int libraryId);

}
