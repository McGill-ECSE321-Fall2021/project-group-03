/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.librarymanagement.model;

import java.util.*;
import java.sql.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
@DiscriminatorValue("MusicAlbum")
public class MusicAlbum extends Title
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MusicAlbum Attributes
  private String artist;
  @OneToOne(targetEntity = MusicAlbum.class)
  private String trackList;
  private int duration;
  private String genre;

  //------------------------
  // CONSTRUCTOR
  //------------------------
  
  protected MusicAlbum() {}

  public MusicAlbum(Date aReleaseDate, String aImage, String aName, String aArtist, int aDuration, String aGenre)
  {
    super(aReleaseDate, aImage, aName);
    artist = aArtist;
    duration = aDuration;
    genre = aGenre;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setArtist(String aArtist)
  {
    boolean wasSet = false;
    artist = aArtist;
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

  public boolean setGenre(String aGenre)
  {
    boolean wasSet = false;
    genre = aGenre;
    wasSet = true;
    return wasSet;
  }

  public String getArtist()
  {
    return artist;
  }
  
  public String getTrackList()
  {
    return this.trackList;
  }

  public boolean hasTrackList()
  {
    return trackList.length() > 0;
  }

  public int indexOfTrackList(String aTrackList)
  {
    int index = trackList.indexOf(aTrackList);
    return index;
  }

  public int getDuration()
  {
    return duration;
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
            "artist" + ":" + getArtist()+ "," +
            "duration" + ":" + getDuration()+ "," +
            "genre" + ":" + getGenre()+ "]";
  }
}