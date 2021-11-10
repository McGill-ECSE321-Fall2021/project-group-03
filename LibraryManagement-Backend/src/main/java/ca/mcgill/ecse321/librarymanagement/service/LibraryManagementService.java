package ca.mcgill.ecse321.librarymanagement.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.librarymanagement.dao.LibraryRepository;
import ca.mcgill.ecse321.librarymanagement.dao.TitleRepository;
import ca.mcgill.ecse321.librarymanagement.dto.LibraryDto;
import ca.mcgill.ecse321.librarymanagement.model.Library;
import ca.mcgill.ecse321.librarymanagement.model.Title;
import ca.mcgill.ecse321.librarymanagement.model.Title.TitleType;

@Service
public class LibraryManagementService {

	@Autowired
	private TitleRepository titleRepository;
	
	@Autowired
	private LibraryRepository libraryRepository;

	@Transactional
	public Title createTitle(String aName, String aDescription, String aGenre, boolean aIsAvailable,
			TitleType aTitleType, Library aLibrary) {
		Title title = new Title(aName, aDescription, aGenre, aIsAvailable, aTitleType);
		aLibrary.addTitle(title);
		titleRepository.save(title);
		libraryRepository.save(aLibrary);
		return title;
	}

	@Transactional
	public Title getTitle(int titleId) {
		Title title = titleRepository.findTitleByTitleId(titleId);
		return title;
	}

	public List<Title> getAllTitles() {
		return toList(titleRepository.findAll());
	}

	// Method to convert to a list
	private <T> List<T> toList(Iterable<T> iterable) {
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}

	public Library getLibrary() {
		return  toList(libraryRepository.findAll()).get(0);
	}
	
	public void createLibrary() {
		Library library = new Library();
		libraryRepository.save(library);
	}

}
