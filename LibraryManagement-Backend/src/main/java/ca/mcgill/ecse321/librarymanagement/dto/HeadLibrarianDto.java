package ca.mcgill.ecse321.librarymanagement.dto;

import ca.mcgill.ecse321.librarymanagement.model.Library;
import ca.mcgill.ecse321.librarymanagement.model.StaffSchedule;

public class HeadLibrarianDto {

	private String username;
	private String password;
	private String emailaddress;
	private String fullName;
	private String resAddress;
	private boolean isResident;
	private int userId;
	private Library library;
	private StaffSchedule staffSchedule;

	public HeadLibrarianDto() {
	}

	public HeadLibrarianDto(String username, String password, String email, String fullName, String resAddress,
			boolean isResident, Library library, StaffSchedule staffSchedule) {
		this.username = username;
		this.password = password;
		this.emailaddress = email;
		this.fullName = fullName;
		this.resAddress = resAddress;
		this.isResident = isResident;
		this.library = library;
		this.staffSchedule = staffSchedule;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmailaddress() {
		return emailaddress;
	}

	public String getFullName() {
		return fullName;
	}

	public String getResAddress() {
		return resAddress;
	}

	public boolean getIsResident() {
		return isResident;
	}

	public int getUserId() {
		return userId;
	}

	public Library getLibrary() {
		return library;
	}

	public StaffSchedule getStaffSchedule() {
		return staffSchedule;
	}

}
