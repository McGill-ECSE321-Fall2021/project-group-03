package ca.mcgill.ecse321.librarymanagement.model;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
 
  //------------------------
  // CONSTRUCTOR
  //------------------------
  
  protected User() {}

  public User(String aUsername, String aPassword, String aFullname)
  {
    username = aUsername;
    password = aPassword;
    fullname = aFullname;
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


  public String toString()
  {
    return super.toString() + "["+
            "userId" + ":" + getUserId()+ "," +
            "username" + ":" + getUsername()+ "," +
            "password" + ":" + getPassword()+ "," +
            "fullname" + ":" + getFullname()+ "]" + System.getProperties().getProperty("line.separator");
  }
}
