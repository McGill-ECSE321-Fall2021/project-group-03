package ca.mcgill.ecse321.librarymanagement.dto;

import java.sql.Date;

public class BookDto {

	private String author;
	private String synopsis;
	private String genre;
	
	private Date releaseDate;
	private String image;
	private String name;
	
	public BookDto() {
		
	}
	
	public BookDto(String aName) {
		this.name = aName;
		}
	
	public BookDto(Date aReleaseDate, String aImage, String aName, String aAuthor, String aSynopsis, String aGenre) {
		this.releaseDate = aReleaseDate;
		this.image = aImage;
		this.author= aAuthor;
		this.synopsis = aSynopsis;
		this.name = aName;
		this.genre = aGenre;
		
	}

	public String getAuthor() {
		return this.author;
	}
	
	public String getSynopsis() {
		return this.synopsis;
	}
	
	public String getGenre() {
		return this.genre;
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
	
}
