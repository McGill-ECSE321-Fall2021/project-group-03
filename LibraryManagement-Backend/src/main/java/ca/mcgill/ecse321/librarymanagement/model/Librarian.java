/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


package ca.mcgill.ecse321.librarymanagement.model;

import javax.persistence.Entity;

// line 38 "model.ump"
// line 136 "model.ump"

public class Librarian extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Librarian Associations
  private StaffSchedule staffSchedule;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Librarian(String aUsername, String aPassword, String aEmailaddress, String aFullName, String aResAddress, int aUserId, Library aLibrary, StaffSchedule aStaffSchedule)
  {
    super(aUsername, aPassword, aEmailaddress, aFullName, aResAddress, aUserId, aLibrary);
    boolean didAddStaffSchedule = setStaffSchedule(aStaffSchedule);
    if (!didAddStaffSchedule)
    {
      throw new RuntimeException("Unable to create librarian due to staffSchedule. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public StaffSchedule getStaffSchedule()
  {
    return staffSchedule;
  }
  /* Code from template association_SetOneToMany */
  public boolean setStaffSchedule(StaffSchedule aStaffSchedule)
  {
    boolean wasSet = false;
    if (aStaffSchedule == null)
    {
      return wasSet;
    }

    StaffSchedule existingStaffSchedule = staffSchedule;
    staffSchedule = aStaffSchedule;
    if (existingStaffSchedule != null && !existingStaffSchedule.equals(aStaffSchedule))
    {
      existingStaffSchedule.removeLibrarian(this);
    }
    staffSchedule.addLibrarian(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    StaffSchedule placeholderStaffSchedule = staffSchedule;
    this.staffSchedule = null;
    if(placeholderStaffSchedule != null)
    {
      placeholderStaffSchedule.removeLibrarian(this);
    }
    super.delete();
  }

}