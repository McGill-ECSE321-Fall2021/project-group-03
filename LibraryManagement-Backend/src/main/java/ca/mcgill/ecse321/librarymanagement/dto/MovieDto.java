package ca.mcgill.ecse321.librarymanagement.dto;

import java.sql.Date;

public class MovieDto {

	private String director;
	private String genre;
	private String synopsis;
	private int duration;
	private Date releaseDate;
	private String image;
	private String name;

	public MovieDto() {

	}

	public MovieDto(String aName) {
		this.name = aName;
	}

	public MovieDto(Date aReleaseDate, String aImage, String aName, String aDirector, String aGenre, String aSynopsis, int aDuration) {
		this.releaseDate = aReleaseDate;
		this.image = aImage;
		this.director = aDirector;
		this.synopsis = aSynopsis;
		this.name = aName;
		this.genre = aGenre;
		
	}

	public String getDirector() {
		return this.director;
	}
	
	public int getDuration() {
		return this.duration;
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
