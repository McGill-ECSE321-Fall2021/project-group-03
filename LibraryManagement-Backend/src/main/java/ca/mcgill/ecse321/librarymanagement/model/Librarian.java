/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarymanagement.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

// line 45 "model.ump"
// line 138 "model.ump"

@Entity
@DiscriminatorValue("Librarian")
public class Librarian extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Librarian Associations
	  @ManyToOne(targetEntity = StaffSchedule.class)
  private StaffSchedule staffSchedule;

  //------------------------
  // CONSTRUCTOR
  //------------------------
  
  protected Librarian() {}

  public Librarian(String aUsername, String aPassword, String aEmailaddress, String aFullName, String aResAddress, boolean aIsResident, int aUserId, Library aLibrary, StaffSchedule aStaffSchedule)
  {
    super(aUsername, aPassword, aEmailaddress, aFullName, aResAddress, aIsResident, aUserId, aLibrary);
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