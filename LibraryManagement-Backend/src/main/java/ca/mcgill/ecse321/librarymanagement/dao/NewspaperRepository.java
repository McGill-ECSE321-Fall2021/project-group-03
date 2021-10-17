package ca.mcgill.ecse321.librarymanagement.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarymanagement.model.Newspaper;

public interface NewspaperRepository extends CrudRepository<Newspaper, Integer>{
	
	Newspaper findNewspaperByTitleId(int titleId);

}
