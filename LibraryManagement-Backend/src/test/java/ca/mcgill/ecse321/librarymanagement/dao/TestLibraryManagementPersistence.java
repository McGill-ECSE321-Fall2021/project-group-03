package ca.mcgill.ecse321.librarymanagement.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.librarymanagement.model.Book;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestLibraryManagementPersistence {
	
	@Autowired
	private BookRepository bookRepository;
	
	@AfterEach
	public void clearDatabase() {
		// delete any db used in the tests
		bookRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadBook() {
		String bookId = "adamsBook";
	}
}
