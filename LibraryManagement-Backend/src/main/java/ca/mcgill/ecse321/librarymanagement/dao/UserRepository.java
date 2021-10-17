package ca.mcgill.ecse321.librarymanagement.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarymanagement.model.User;

public interface UserRepository extends CrudRepository<User, Integer>{
	
	User findUserByUserId(int userId); 

}
