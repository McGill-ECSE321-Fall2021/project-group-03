package ca.mcgill.ecse321.librarymanagement.service;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;

import ca.mcgill.ecse321.librarymanagement.dao.ClientRepository;
import ca.mcgill.ecse321.librarymanagement.dao.TitleRepository;
import ca.mcgill.ecse321.librarymanagement.model.Title;
import ca.mcgill.ecse321.librarymanagement.model.Title.TitleType;

@ExtendWith(MockitoExtension.class)
public class TestLibraryManagementService {
	
	@Mock
	private TitleRepository titleRepository;
	
	@Mock
	private ClientRepository clientRepository;
	
	@InjectMocks
	private LibraryManagementService service;
	
	private static final int TITLE_KEY = 123;
	private static final int USER_KEY = 1234;
	
	// Title tests
	
	@BeforeEach
	public void setMockOutput() {
		lenient().when(titleRepository.findTitleByTitleId(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
	        if(invocation.getArgument(0).equals(TITLE_KEY)) {
	        	String name = "moby dick";
	    		String description = "whale eats guy";
	    		String genre = "adventure";
	    		TitleType titleType = TitleType.Book;
	    		Title title = new Title(name, description, genre, true, titleType);
	            title.setTitleId(TITLE_KEY);
	            return title;
	        } else {
	            return null;
	        }
	    });
		
		lenient().when(clientRepository.findById(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
	        if(invocation.getArgument(0).equals(TITLE_KEY)) {
	        	String name = "moby dick";
	    		String description = "whale eats guy";
	    		String genre = "adventure";
	    		TitleType titleType = TitleType.Book;
	    		Title title = new Title(name, description, genre, true, titleType);
	            title.setTitleId(TITLE_KEY);
	            return title;
	        } else {
	            return null;
	        }
	    });
	}
	
	// SAM
	
	@Transactional
	public Title createTitle(String name, String description, String genre, boolean isAvailable, TitleType titleType) {
		
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("Title name cannot be empty!");
		}
		
		if (description == null || description.trim().length() == 0) {
			throw new IllegalArgumentException("Title description cannot be empty!");
		}
		
		if (genre == null || description.trim().length() == 0) {
			throw new IllegalArgumentException("Title genre cannot be empty!");
		}
		
		if (isAvailable == false) {
			throw new IllegalArgumentException("Title availability cannot be false when first created!");
		}
		
		if (titleType == null) {
			throw new IllegalArgumentException("Title type cannot be null!");
		}
		
		Title title = new Title(name, description, genre, true, titleType);
		
		titleRepository.save(title);
		
		return title;
		
	}
	
	@Transactional
	public Title getTitle(String name, String description, String genre, boolean isAvailable, TitleType titleType) {
		
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("Title name cannot be empty!");
		}
		
		if (description == null || description.trim().length() == 0) {
			throw new IllegalArgumentException("Title description cannot be empty!");
		}
		
		if (genre == null || description.trim().length() == 0) {
			throw new IllegalArgumentException("Title genre cannot be empty!");
		}
		
		if (isAvailable == false) {
			throw new IllegalArgumentException("Title availability cannot be false when first created!");
		}
		
		if (titleType == null) {
			throw new IllegalArgumentException("Title type cannot be null!");
		}
		
		Title title = titleRepository.findTitleByTitleId(TITLE_KEY);
				
		return title;
	}
	
	// TARA

}
