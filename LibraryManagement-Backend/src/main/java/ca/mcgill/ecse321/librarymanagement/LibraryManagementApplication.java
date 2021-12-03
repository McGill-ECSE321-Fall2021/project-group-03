package ca.mcgill.ecse321.librarymanagement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.librarymanagement.controller.LibraryManagementRestController;
import ca.mcgill.ecse321.librarymanagement.dto.TitleDto;
import ca.mcgill.ecse321.librarymanagement.model.Library;
import ca.mcgill.ecse321.librarymanagement.model.Title;

@RestController
@SpringBootApplication
public class LibraryManagementApplication {
	
	@Autowired
	private static  LibraryManagementRestController controller;
	
	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementApplication.class, args);
		
	}

	// greeting
	@RequestMapping("/")
	public String greeting(){		
		return "Hello world this is a test!";
	}
	
	@RequestMapping(value = { "/titles/create/{titleInfo}", "/titles/create/{titleInfo}/" })
	public TitleDto createTitle(@PathVariable("titleInfo") String titleInfo)
			throws IllegalArgumentException {
		
		String[] titleInfoList = titleInfo.split("-");
		String name = titleInfoList[0];
		String description = titleInfoList[1];
		String genre = titleInfoList[2];
		String titleType = titleInfoList[3];

		return controller.createTitle(name, description, genre, "true", titleType);
	}
	
	@RequestMapping("/titles/get")
	public List<TitleDto> getTitles(){
		return controller.getAllTitles();
	}

}
