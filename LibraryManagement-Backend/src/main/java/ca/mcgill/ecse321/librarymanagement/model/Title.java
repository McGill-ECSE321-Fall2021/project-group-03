/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarymanagement.model;


import java.sql.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Title_Type")
public abstract class Title
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Title Attributes
  private Date releaseDate;
  private String image;
  private String name;
  
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private int titleId;

  //------------------------
  // CONSTRUCTOR
  //------------------------
  
  protected Title() {}

  public Title(Date aReleaseDate, String aImage, String aName)
  {
    releaseDate = aReleaseDate;
    image = aImage;
    name = aName;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setReleaseDate(Date aReleaseDate)
  {
    boolean wasSet = false;
    releaseDate = aReleaseDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setImage(String aImage)
  {
    boolean wasSet = false;
    image = aImage;
    wasSet = true;
    return wasSet;
  }

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setTitleId(int aTitleId)
  {
    boolean wasSet = false;
    titleId = aTitleId;
    wasSet = true;
    return wasSet;
  }

  public Date getReleaseDate()
  {
    return releaseDate;
  }

  public String getImage()
  {
    return image;
  }

  public String getName()
  {
    return name;
  }

  public int getTitleId()
  {
    return titleId;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "image" + ":" + getImage()+ "," +
            "name" + ":" + getName()+ "," +
            "titleId" + ":" + getTitleId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "releaseDate" + "=" + (getReleaseDate() != null ? !getReleaseDate().equals(this)  ? getReleaseDate().toString().replaceAll("  ","    ") : "this" : "null");
  }
}