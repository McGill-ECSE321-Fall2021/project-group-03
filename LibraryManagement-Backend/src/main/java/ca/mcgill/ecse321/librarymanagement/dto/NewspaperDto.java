package ca.mcgill.ecse321.librarymanagement.dto;

import java.sql.Date;

public class NewspaperDto {
	
	private String headline;
	private Date releaseDate;
	private String image;
	private String name;
	
	public NewspaperDto() {
		
	}
	
	public NewspaperDto(String aName) {
		this.name=aName;
	}
	
	public NewspaperDto(Date aReleaseDate, String aImage, String aName, String aHeadline) {
		this.releaseDate=aReleaseDate;
		this.image=aImage;
		this.name=aName;
		this.headline=aHeadline;
	}
	
	public Date ReleaseDate() {
		return this.releaseDate;
	}
	
	public String getImage() {
		return this.image;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getHeadline() {
		return this.headline;
	}

}
