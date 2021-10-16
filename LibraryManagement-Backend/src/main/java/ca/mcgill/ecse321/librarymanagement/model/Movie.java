/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.librarymanagement.model;

import java.sql.Date;

// line 73 "model.ump"
// line 161 "model.ump"
public class Movie extends Title
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Movie Attributes
  private String director;
  private String genre;
  private String synopsis;
  private int duration;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Movie(Date aReleaseDate, String aImage, String aName, String aTitleID, String aDirector, String aGenre, String aSynopsis, int aDuration)
  {
    super(aReleaseDate, aImage, aName, aTitleID);
    director = aDirector;
    genre = aGenre;
    synopsis = aSynopsis;
    duration = aDuration;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDirector(String aDirector)
  {
    boolean wasSet = false;
    director = aDirector;
    wasSet = true;
    return wasSet;
  }

  public boolean setGenre(String aGenre)
  {
    boolean wasSet = false;
    genre = aGenre;
    wasSet = true;
    return wasSet;
  }

  public boolean setSynopsis(String aSynopsis)
  {
    boolean wasSet = false;
    synopsis = aSynopsis;
    wasSet = true;
    return wasSet;
  }

  public boolean setDuration(int aDuration)
  {
    boolean wasSet = false;
    duration = aDuration;
    wasSet = true;
    return wasSet;
  }

  public String getDirector()
  {
    return director;
  }

  public String getGenre()
  {
    return genre;
  }

  public String getSynopsis()
  {
    return synopsis;
  }

  public int getDuration()
  {
    return duration;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "director" + ":" + getDirector()+ "," +
            "genre" + ":" + getGenre()+ "," +
            "synopsis" + ":" + getSynopsis()+ "," +
            "duration" + ":" + getDuration()+ "]";
  }
}