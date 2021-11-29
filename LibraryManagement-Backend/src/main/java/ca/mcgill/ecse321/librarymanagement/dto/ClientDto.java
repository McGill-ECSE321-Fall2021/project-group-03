package ca.mcgill.ecse321.librarymanagement.dto;

public class ClientDto extends UserDto {

	private String residentialAddress;
	private String email;
	private boolean isResident;
	private boolean isOnline;
	private int userId;
	private String username;
	private String password;
	private String fullName;
	
	public ClientDto(String username, String fullName, String password, String residentialAddress, String email, boolean isResident, boolean isOnline) {
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.residentialAddress = residentialAddress;
		this.email = email;
		this.isResident = isResident;
		this.isOnline = isOnline;
	}

	public String getResidentialAddress() {
		return residentialAddress;
	}
	  
	public String getfullName() {
		return fullName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public boolean isOnline() {
		return isOnline;
	}
	
	public boolean isResident() {
		return isResident;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}

}
