package ca.mcgill.ecse321.librarymanagement.dto;

import javax.persistence.ManyToOne;

import ca.mcgill.ecse321.librarymanagement.model.Library;

public class UserDto {
	
	  private String username;
	  private String password;
	  private String emailaddress;
	  private String fullName;
	  private String resAddress;
	  private boolean isResident;
	  private int userId;
	  private Library library;
	  
	  public UserDto() {}
	  
	  public UserDto(String username, String password, String email, String fullName, String resAddress, boolean isResident, Library library) {
		    this.username = username;
		    this.password = password;
		    this.emailaddress = email;
		    this.fullName = fullName;
		    this.resAddress = resAddress;
		    this.isResident = isResident;
		    this.library = library;
	  }

	  public String getUsername()
	  {
	    return username;
	  }

	  public String getPassword()
	  {
	    return password;
	  }

	  public String getEmailaddress()
	  {
	    return emailaddress;
	  }

	  public String getFullName()
	  {
	    return fullName;
	  }

	  public String getResAddress()
	  {
	    return resAddress;
	  }

	  public boolean getIsResident()
	  {
	    return isResident;
	  }

	  public int getUserId()
	  {
	    return userId;
	  }

	  public Library getLibrary()
	  {
	    return library;
	  }

}
