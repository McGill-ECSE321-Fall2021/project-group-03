package ca.mcgill.ecse321.librarymanagement.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/



// line 38 "model.ump"
// line 110 "model.ump"
@Entity
@DiscriminatorValue("Client")
public class Client extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Client Attributes
  private String residentialAddress;
  private String email;
  private boolean isResident;
  private boolean isOnline;

  //------------------------
  // CONSTRUCTOR
  //------------------------
  
  public Client(int aUserId, String aUsername, String aPassword, String aFullname, Library aLibrary, String aResidentialAddress, String aEmail, boolean aIsResident, boolean aIsOnline)
  {
    super(aUserId, aUsername, aPassword, aFullname, aLibrary);
    residentialAddress = aResidentialAddress;
    email = aEmail;
    isResident = aIsResident;
    isOnline = aIsOnline;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setResidentialAddress(String aResidentialAddress)
  {
    boolean wasSet = false;
    residentialAddress = aResidentialAddress;
    wasSet = true;
    return wasSet;
  }

  public boolean setEmail(String aEmail)
  {
    boolean wasSet = false;
    email = aEmail;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsResident(boolean aIsResident)
  {
    boolean wasSet = false;
    isResident = aIsResident;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsOnline(boolean aIsOnline)
  {
    boolean wasSet = false;
    isOnline = aIsOnline;
    wasSet = true;
    return wasSet;
  }

  public String getResidentialAddress()
  {
    return residentialAddress;
  }

  public String getEmail()
  {
    return email;
  }

  public boolean getIsResident()
  {
    return isResident;
  }

  public boolean getIsOnline()
  {
    return isOnline;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "residentialAddress" + ":" + getResidentialAddress()+ "," +
            "email" + ":" + getEmail()+ "," +
            "isResident" + ":" + getIsResident()+ "," +
            "isOnline" + ":" + getIsOnline()+ "]";
  }
}