package ca.mcgill.ecse321.librarymanagement.dto;

public class LibrarianDto extends UserDto {

	private int userId;
	private String username;
	private String password;
	private String fullName;
	private boolean isHeadLibrarian;
	
	public LibrarianDto(int userId, String username, String password, String fullName, boolean isHeadLibrarian) {
		
		this.username = username;
		this.password = password;
		this.userId = userId;
		this.fullName = fullName;
		this.isHeadLibrarian = isHeadLibrarian;
		
	}
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	public String getFullName() {
		return fullName;
	}

	public boolean isHeadLibrarian() {
		return isHeadLibrarian;
	}


	public int getUserId() {
		return userId;
	}

}

