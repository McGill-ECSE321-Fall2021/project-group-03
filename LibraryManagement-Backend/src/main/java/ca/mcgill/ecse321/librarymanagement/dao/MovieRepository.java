package ca.mcgill.ecse321.librarymanagement.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarymanagement.model.Movie;

public interface MovieRepository extends CrudRepository<Movie, Integer>{
	
	Movie findMovieByTitleId(int titleId);

}
