package ca.mcgill.ecse321.librarymanagement.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

// line 48 "model.ump"
// line 118 "model.ump"

@Entity
@DiscriminatorValue("Librarian")
public class Librarian extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Librarian Attributes
  private boolean isHeadLibrarian;

  //Librarian Associations
  @OneToOne (cascade = CascadeType.ALL)
  private Schedule staffSchedule;

  //------------------------
  // CONSTRUCTOR
  //------------------------
  
  protected Librarian() {}

  public Librarian(String aUsername, String aPassword, String aFullname, boolean aIsHeadLibrarian)
  {
    super(aUsername, aPassword, aFullname);
    isHeadLibrarian = aIsHeadLibrarian;
    staffSchedule = new Schedule();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setIsHeadLibrarian(boolean aIsHeadLibrarian)
  {
    boolean wasSet = false;
    isHeadLibrarian = aIsHeadLibrarian;
    wasSet = true;
    return wasSet;
  }

  public boolean getIsHeadLibrarian()
  {
    return isHeadLibrarian;
  }
  /* Code from template association_GetOne */
  public Schedule getStaffSchedule()
  {
    return staffSchedule;
  }

  public boolean hasStaffSchedule()
  {
    boolean has = staffSchedule != null;
    return has;
  }
  /* Code from template association_SetUnidirectionalOptionalOne */
  public boolean setStaffSchedule(Schedule aNewStaffSchedule)
  {
    boolean wasSet = false;
    staffSchedule = aNewStaffSchedule;
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    staffSchedule = null;
  }


  public String toString()
  {
    return super.toString() + "["+
            "isHeadLibrarian" + ":" + getIsHeadLibrarian()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "staffSchedule = "+(getStaffSchedule()!=null?Integer.toHexString(System.identityHashCode(getStaffSchedule())):"null");
  }
}