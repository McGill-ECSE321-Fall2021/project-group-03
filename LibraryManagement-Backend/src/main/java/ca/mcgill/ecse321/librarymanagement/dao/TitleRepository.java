package ca.mcgill.ecse321.librarymanagement.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarymanagement.model.Title;

public interface TitleRepository extends CrudRepository<Title, Integer>{
	
	public Title findTitleByTitleId(int titleId);

}
