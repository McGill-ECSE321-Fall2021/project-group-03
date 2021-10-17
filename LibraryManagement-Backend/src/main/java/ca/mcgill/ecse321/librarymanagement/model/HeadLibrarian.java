/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarymanagement.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

// line 51 "model.ump"
// line 144 "model.ump"

@Entity
@DiscriminatorValue("HeadLibrarian")
public class HeadLibrarian extends Librarian
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------
	
  protected HeadLibrarian() {}

  public HeadLibrarian(String aUsername, String aPassword, String aEmailaddress, String aFullName, String aResAddress, boolean aIsResident, Library aLibrary, StaffSchedule aStaffSchedule)
  {
    super(aUsername, aPassword, aEmailaddress, aFullName, aResAddress, aIsResident, aLibrary, aStaffSchedule);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }

}