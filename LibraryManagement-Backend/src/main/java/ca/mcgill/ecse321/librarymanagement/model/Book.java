/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/
package ca.mcgill.ecse321.librarymanagement.model;


import java.sql.Date;

// line 74 "model.ump"
// line 160 "model.ump"
public class Book extends Title
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Book Attributes
  private String author;
  private String synopsis;
  private String genre;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Book(Date aReleaseDate, String aImage, String aName, int aTitleId, String aAuthor, String aSynopsis, String aGenre)
  {
    super(aReleaseDate, aImage, aName, aTitleId);
    author = aAuthor;
    synopsis = aSynopsis;
    genre = aGenre;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setAuthor(String aAuthor)
  {
    boolean wasSet = false;
    author = aAuthor;
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

  public boolean setGenre(String aGenre)
  {
    boolean wasSet = false;
    genre = aGenre;
    wasSet = true;
    return wasSet;
  }

  public String getAuthor()
  {
    return author;
  }

  public String getSynopsis()
  {
    return synopsis;
  }

  public String getGenre()
  {
    return genre;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "author" + ":" + getAuthor()+ "," +
            "synopsis" + ":" + getSynopsis()+ "," +
            "genre" + ":" + getGenre()+ "]";
  }
}