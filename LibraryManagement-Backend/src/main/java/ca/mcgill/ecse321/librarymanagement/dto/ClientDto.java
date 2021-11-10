package ca.mcgill.ecse321.librarymanagement.dto;

public class ClientDto {

	private String residentialAddress;
	private String email;
	private boolean isResident;
	private boolean isOnline;
	private int userId;
	private String username;
	private String password;
	
	public ClientDto(int userId, String username, String password, String residentialAddress, String email, boolean isResident, boolean isOnline) {
		this.username = username;
		this.password = password;
		this.userId = userId;
		this.residentialAddress = residentialAddress;
		this.email = email;
		this.isResident = isResident;
		this.isOnline = isOnline;
	}

	public String getResidentialAddress() {
		return residentialAddress;
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
