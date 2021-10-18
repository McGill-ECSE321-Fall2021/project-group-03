/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarymanagement.model;


import java.sql.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.springframework.stereotype.Component;


@Entity
@DiscriminatorValue("Newspaper")
public class Newspaper extends Title
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Newspaper Attributes
  private String headline;

  //------------------------
  // CONSTRUCTOR
  //------------------------
  
  protected Newspaper() {}

  public Newspaper(Date aReleaseDate, String aImage, String aName, String aHeadline)
  {
    super(aReleaseDate, aImage, aName);
    headline = aHeadline;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setHeadline(String aHeadline)
  {
    boolean wasSet = false;
    headline = aHeadline;
    wasSet = true;
    return wasSet;
  }

  public String getHeadline()
  {
    return headline;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "headline" + ":" + getHeadline()+ "]";
  }
}