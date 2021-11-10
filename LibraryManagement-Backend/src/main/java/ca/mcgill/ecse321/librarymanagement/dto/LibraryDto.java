package ca.mcgill.ecse321.librarymanagement.dto;

import java.util.List;

import ca.mcgill.ecse321.librarymanagement.model.Library;
import ca.mcgill.ecse321.librarymanagement.model.Room;
import ca.mcgill.ecse321.librarymanagement.model.ScheduleDto;
import ca.mcgill.ecse321.librarymanagement.model.Title;
import ca.mcgill.ecse321.librarymanagement.model.User;

public class LibraryDto {

	private int libraryId;
	private List<RoomDto> rooms;
	private List<TitleDto> titles;
	private ScheduleDto librarySchedule;
	private List<User> users;

	public LibraryDto(List<RoomDto> rooms, List<TitleDto> titles,  ScheduleDto librarySchedule, List<UserDto> users) {
		this.rooms = rooms;
		this.titles = titles;
		this.librarySchedule = librarySchedule;
		this.users = users;
	}

	public int getLibraryId() {
		return libraryId;
	}

	public List<RoomDto> getRooms() {
		return rooms;
	}

	public List<TitleDto> getTitles() {
		return titles;
	}

	public ScheduleDto getLibrarySchedule() {
		return librarySchedule;
	}

	public List<UserDto> getUsers() {
		return users;
	}

}