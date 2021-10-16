/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.librarymanagement.model;

import java.util.*;
import java.sql.Date;

// line 81 "model.ump"
// line 166 "model.ump"
public class MusicAlbum extends Title
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MusicAlbum Attributes
  private String artist;
  private List<String> trackList;
  private int duration;
  private String genre;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MusicAlbum(Date aReleaseDate, String aImage, String aName, String aTitleID, String aArtist, int aDuration, String aGenre)
  {
    super(aReleaseDate, aImage, aName, aTitleID);
    artist = aArtist;
    trackList = new ArrayList<String>();
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
  /* Code from template attribute_SetMany */
  public boolean addTrackList(String aTrackList)
  {
    boolean wasAdded = false;
    wasAdded = trackList.add(aTrackList);
    return wasAdded;
  }

  public boolean removeTrackList(String aTrackList)
  {
    boolean wasRemoved = false;
    wasRemoved = trackList.remove(aTrackList);
    return wasRemoved;
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
  /* Code from template attribute_GetMany */
  public String getTrackList(int index)
  {
    String aTrackList = trackList.get(index);
    return aTrackList;
  }

  public String[] getTrackList()
  {
    String[] newTrackList = trackList.toArray(new String[trackList.size()]);
    return newTrackList;
  }

  public int numberOfTrackList()
  {
    int number = trackList.size();
    return number;
  }

  public boolean hasTrackList()
  {
    boolean has = trackList.size() > 0;
    return has;
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