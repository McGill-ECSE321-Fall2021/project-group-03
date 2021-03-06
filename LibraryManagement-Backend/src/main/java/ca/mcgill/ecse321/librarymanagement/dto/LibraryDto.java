package ca.mcgill.ecse321.librarymanagement.dto;

import java.util.List;

public class LibraryDto {

	private int libraryId;
	private List<RoomDto> rooms;
	private List<TitleDto> titles;
	private ScheduleDto librarySchedule;
	private List<UserDto> users;

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