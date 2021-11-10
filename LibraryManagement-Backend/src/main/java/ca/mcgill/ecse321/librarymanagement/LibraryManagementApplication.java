package ca.mcgill.ecse321.librarymanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.librarymanagement.dao.LibraryRepository;
import ca.mcgill.ecse321.librarymanagement.model.Library;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@SpringBootApplication
public class LibraryManagementApplication {
	
	
	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementApplication.class, args);
	}

	// greeting
	@RequestMapping("/")
	public String greeting(){
		return "Hello world!";
	}

}
