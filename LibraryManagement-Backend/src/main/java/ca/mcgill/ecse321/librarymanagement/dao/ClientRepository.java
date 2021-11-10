package ca.mcgill.ecse321.librarymanagement.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarymanagement.model.Client;

public interface ClientRepository extends CrudRepository<Client, Integer>{
	
	Client findClientByUserId(int userId);
	
	Client findClientByUsername(String username);

}
