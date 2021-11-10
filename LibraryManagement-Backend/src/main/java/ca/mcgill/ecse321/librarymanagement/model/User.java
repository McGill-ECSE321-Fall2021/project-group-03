package ca.mcgill.ecse321.librarymanagement.model;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/



// line 30 "model.ump"
// line 105 "model.ump"

@Entity
@Table(name="pseudoUser")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "User_Type")
public abstract class User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private int userId;
  
  private String username;
  private String password;
  private String fullname;

  //User Associations
  @ManyToOne (targetEntity = Library.class)
  private Library library;

  //------------------------
  // CONSTRUCTOR
  //------------------------
  
  protected User() {}

  public User(int aUserId, String aUsername, String aPassword, String aFullname, Library aLibrary)
  {
    userId = aUserId;
    username = aUsername;
    password = aPassword;
    fullname = aFullname;
    boolean didAddLibrary = setLibrary(aLibrary);
    if (!didAddLibrary)
    {
      throw new RuntimeException("Unable to create user due to library. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setUserId(int aUserId)
  {
    boolean wasSet = false;
    userId = aUserId;
    wasSet = true;
    return wasSet;
  }

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

  public boolean setFullname(String aFullname)
  {
    boolean wasSet = false;
    fullname = aFullname;
    wasSet = true;
    return wasSet;
  }

  public int getUserId()
  {
    return userId;
  }

  public String getUsername()
  {
    return username;
  }

  public String getPassword()
  {
    return password;
  }

  public String getFullname()
  {
    return fullname;
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
            "userId" + ":" + getUserId()+ "," +
            "username" + ":" + getUsername()+ "," +
            "password" + ":" + getPassword()+ "," +
            "fullname" + ":" + getFullname()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "library = "+(getLibrary()!=null?Integer.toHexString(System.identityHashCode(getLibrary())):"null");
  }
}