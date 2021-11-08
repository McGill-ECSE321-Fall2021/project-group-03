package ca.mcgill.ecse321.librarymanagement.dto;

import java.sql.Date;

import ca.mcgill.ecse321.librarymanagement.model.MusicAlbum;

public class MusicAlbumDto {

	private String artist;
	private String trackList;
	private int duration;
	private String genre;

	private Date releaseDate;
	private String image;
	private String name;
	
	public MusicAlbumDto() {
		
	}
	
	public MusicAlbumDto(String aName) {
		this.name = aName;
	}
	
	public MusicAlbumDto(Date aReleaseDate, String aImage, String aName, String aArtist, String aTracklist, int aDuration, String aGenre) {
		this.releaseDate=aReleaseDate;
		this.image=aImage;
		this.name=aName;
		this.artist=aArtist;
		this.trackList=aTracklist;
		this.duration=aDuration;
		this.genre=aGenre;
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
	
	public String getArtist() {
		return this.artist;
	}
	
	public String getTracklist() {
		return this.trackList;
	}
	
	public int getDuration() {
		return this.duration;
	}
}
