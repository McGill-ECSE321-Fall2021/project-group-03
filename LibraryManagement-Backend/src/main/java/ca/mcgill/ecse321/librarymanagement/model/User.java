/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.librarymanagement.model;

// line 29 "model.ump"
// line 131 "model.ump"
public class User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  private String username;
  private String password;
  private String emailaddress;
  private String fullName;
  private String resAddress;
  private int userId;

  //User Associations
  private Library library;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User(String aUsername, String aPassword, String aEmailaddress, String aFullName, String aResAddress, int aUserId, Library aLibrary)
  {
    username = aUsername;
    password = aPassword;
    emailaddress = aEmailaddress;
    fullName = aFullName;
    resAddress = aResAddress;
    userId = aUserId;
    boolean didAddLibrary = setLibrary(aLibrary);
    if (!didAddLibrary)
    {
      throw new RuntimeException("Unable to create user due to library. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setUsername(String aUsername)
  {
    boolean wasSet = false;
    username = aUsername;
    wasSet = true;
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public boolean setEmailaddress(String aEmailaddress)
  {
    boolean wasSet = false;
    emailaddress = aEmailaddress;
    wasSet = true;
    return wasSet;
  }

  public boolean setFullName(String aFullName)
  {
    boolean wasSet = false;
    fullName = aFullName;
    wasSet = true;
    return wasSet;
  }

  public boolean setResAddress(String aResAddress)
  {
    boolean wasSet = false;
    resAddress = aResAddress;
    wasSet = true;
    return wasSet;
  }

  public boolean setUserId(int aUserId)
  {
    boolean wasSet = false;
    userId = aUserId;
    wasSet = true;
    return wasSet;
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

  public int getUserId()
  {
    return userId;
  }
  /* Code from template association_GetOne */
  public Library getLibrary()
  {
    return library;
  }
  /* Code from template association_SetOneToMany */
  public boolean setLibrary(Library aLibrary)
  {
    boolean wasSet = false;
    if (aLibrary == null)
    {
      return wasSet;
    }

    Library existingLibrary = library;
    library = aLibrary;
    if (existingLibrary != null && !existingLibrary.equals(aLibrary))
    {
      existingLibrary.removeUser(this);
    }
    library.addUser(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Library placeholderLibrary = library;
    this.library = null;
    if(placeholderLibrary != null)
    {
      placeholderLibrary.removeUser(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "username" + ":" + getUsername()+ "," +
            "password" + ":" + getPassword()+ "," +
            "emailaddress" + ":" + getEmailaddress()+ "," +
            "fullName" + ":" + getFullName()+ "," +
            "resAddress" + ":" + getResAddress()+ "," +
            "userId" + ":" + getUserId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "library = "+(getLibrary()!=null?Integer.toHexString(System.identityHashCode(getLibrary())):"null");
  }
}