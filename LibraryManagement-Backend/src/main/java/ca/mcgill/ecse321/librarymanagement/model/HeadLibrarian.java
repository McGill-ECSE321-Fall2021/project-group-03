/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


package ca.mcgill.ecse321.librarymanagement.model;

import javax.persistence.Entity;

// line 44 "model.ump"
// line 141 "model.ump"

public class HeadLibrarian extends Librarian
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public HeadLibrarian(String aUsername, String aPassword, String aEmailaddress, String aFullName, String aResAddress, int aUserId, Library aLibrary, StaffSchedule aStaffSchedule)
  {
    super(aUsername, aPassword, aEmailaddress, aFullName, aResAddress, aUserId, aLibrary, aStaffSchedule);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }

}