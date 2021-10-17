/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarymanagement.model;



// line 51 "model.ump"
// line 144 "model.ump"
public class HeadLibrarian extends Librarian
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public HeadLibrarian(String aUsername, String aPassword, String aEmailaddress, String aFullName, String aResAddress, boolean aIsResident, int aUserId, Library aLibrary, StaffSchedule aStaffSchedule)
  {
    super(aUsername, aPassword, aEmailaddress, aFullName, aResAddress, aIsResident, aUserId, aLibrary, aStaffSchedule);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }

}