package ca.mcgill.ecse321.librarymanagement.dto;

import java.util.List;

import ca.mcgill.ecse321.librarymanagement.model.Library;
import ca.mcgill.ecse321.librarymanagement.model.Room;
import ca.mcgill.ecse321.librarymanagement.model.Schedule;
import ca.mcgill.ecse321.librarymanagement.model.Title;
import ca.mcgill.ecse321.librarymanagement.model.User;

public class LibraryDto {

	private int libraryId;
	private List<Room> rooms;
	private List<Title> titles;
	private Schedule librarySchedule;
	private List<User> users;

	public LibraryDto(Library library) {
		this.rooms = library.getRooms();
		this.titles = library.getTitles();
		this.librarySchedule = library.getLibrarySchedule();
		this.users = library.getUsers();
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

	public Schedule getLibrarySchedule() {
		return librarySchedule;
	}

	public List<User> getUsers() {
		return users;
	}

}