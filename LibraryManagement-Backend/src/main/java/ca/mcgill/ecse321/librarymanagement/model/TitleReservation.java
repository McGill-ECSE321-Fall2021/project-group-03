package ca.mcgill.ecse321.librarymanagement.model;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

// line 58 "model.ump"
// line 142 "model.ump"
@Entity
public class TitleReservation
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TitleReservation Attributes
  private Date returnDate;
  private boolean isCheckedOut;

  //TitleReservation Associations
  
  @OneToOne (targetEntity = Title.class)
  private Title title;
  
  @ManyToOne (targetEntity = Client.class)
  private Client client;
  
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private int titleReservationId;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  protected TitleReservation() {}
  
  public TitleReservation(Date aReturnDate, boolean aIsCheckedOut, Title aTitle, Client aClient)
  {
    returnDate = aReturnDate;
    isCheckedOut = aIsCheckedOut;
    if (!setTitle(aTitle))
    {
      throw new RuntimeException("Unable to create TitleReservation due to aTitle. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (!setClient(aClient))
    {
      throw new RuntimeException("Unable to create TitleReservation due to aClient. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  
  public int getTitleReservationId() {
	  return this.titleReservationId;
  }
  public void setTitleReservationId(int titleReservationId) {
	  this.titleReservationId= titleReservationId;
  }

  public boolean setReturnDate(Date aReturnDate)
  {
    boolean wasSet = false;
    returnDate = aReturnDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsCheckedOut(boolean aIsCheckedOut)
  {
    boolean wasSet = false;
    isCheckedOut = aIsCheckedOut;
    wasSet = true;
    return wasSet;
  }

  public Date getReturnDate()
  {
    return returnDate;
  }

  public boolean getIsCheckedOut()
  {
    return isCheckedOut;
  }
  /* Code from template association_GetOne */
  public Title getTitle()
  {
    return title;
  }
  /* Code from template association_GetOne */
  public Client getClient()
  {
    return client;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setTitle(Title aNewTitle)
  {
    boolean wasSet = false;
    if (aNewTitle != null)
    {
      title = aNewTitle;
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setClient(Client aNewClient)
  {
    boolean wasSet = false;
    if (aNewClient != null)
    {
      client = aNewClient;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete()
  {
    title = null;
    client = null;
  }


  public String toString()
  {
    return super.toString() + "["+
            "isCheckedOut" + ":" + getIsCheckedOut()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "returnDate" + "=" + (getReturnDate() != null ? !getReturnDate().equals(this)  ? getReturnDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "title = "+(getTitle()!=null?Integer.toHexString(System.identityHashCode(getTitle())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "client = "+(getClient()!=null?Integer.toHexString(System.identityHashCode(getClient())):"null");
  }
}