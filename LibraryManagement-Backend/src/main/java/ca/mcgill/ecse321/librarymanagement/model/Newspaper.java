/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.librarymanagement.model;

import java.sql.Date;

// line 89 "model.ump"
// line 171 "model.ump"
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

  public Newspaper(Date aReleaseDate, String aImage, String aName, String aTitleID, String aHeadline)
  {
    super(aReleaseDate, aImage, aName, aTitleID);
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