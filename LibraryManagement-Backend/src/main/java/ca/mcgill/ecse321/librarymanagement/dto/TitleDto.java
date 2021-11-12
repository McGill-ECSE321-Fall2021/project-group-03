package ca.mcgill.ecse321.librarymanagement.dto;

import ca.mcgill.ecse321.librarymanagement.model.Title.TitleType;

public class TitleDto {

	  private int titleId;
	  private String description;
	  private String genre;
	  private boolean isAvailable;
	  private TitleType titleType;
	  
	public TitleDto() {
		
	}

	public TitleDto(int titleId, String description, String genre, boolean isAvailable, TitleType titleType) {
		this.titleId = titleId;
		this.description = description;
		this.genre = genre;
		this.isAvailable = isAvailable;
		this.titleType = titleType;
	}


	public int getTitleId() {
		return titleId;
	}


	public String getDescription() {
		return description;
	}


	public String getGenre() {
		return genre;
	}

	public boolean isAvailable() {
		return isAvailable;
	}


	public TitleType getTitleType() {
		return titleType;
	}


	
	
	

	
	
}