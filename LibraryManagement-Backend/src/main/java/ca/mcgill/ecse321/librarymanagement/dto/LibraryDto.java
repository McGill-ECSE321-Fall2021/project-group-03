package ca.mcgill.ecse321.librarymanagement.dto;

import java.util.List;

import ca.mcgill.ecse321.librarymanagement.model.LibrarySchedule;
import ca.mcgill.ecse321.librarymanagement.model.Room;
import ca.mcgill.ecse321.librarymanagement.model.Title;
import ca.mcgill.ecse321.librarymanagement.model.User;

//For the library, seemingly you need to make a shllow copy of all the lists
	//because the whole point is to prevent someone from modifying the model
	//so you cannot give access 

public class LibraryDto {

	private int libraryId;
	private List<Room> rooms;
	private List<Title> titles;
	private LibrarySchedule librarySchedule;
	private List<User> users;

	public LibraryDto() {
	}

	public LibraryDto(int libraryId, List<Room> rooms, List<Title> titles, LibrarySchedule librarySchedule, List<User> users) {
		this.libraryId = libraryId;
		this.rooms = rooms;
		this.titles = titles;
		this.librarySchedule = librarySchedule;
		this.users = users;
	}

	public int getLibraryId() {
		return libraryId;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public List<Title> getTitles() {
		return titles;
	}

	public LibrarySchedule getLibrarySchedule() {
		return librarySchedule;
	}

	public List<User> getUsers() {
		return users;
	}

}
