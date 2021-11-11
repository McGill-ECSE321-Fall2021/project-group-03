package ca.mcgill.ecse321.librarymanagement.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/



// line 18 "model.ump"
// line 100 "model.ump"
@Entity
@Table(name="pseudoTitle")
public class Title
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum TitleType { Book, Movie, MusicAlbum, Newspaper, Archives }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Title Attributes
  private String name;


  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private int titleId;
  private String description;
  private String genre;
  
  //@Column(nullable = true) 
  private boolean isAvailable;
  private TitleType titleType;

  //Title Associations
 

  //------------------------
  // CONSTRUCTOR
  //------------------------
  
  protected Title() {}

  public Title(String aName, String aDescription, String aGenre, boolean aIsAvailable, TitleType aTitleType)
  {
    name = aName;
    description = aDescription;
    genre = aGenre;
    isAvailable = aIsAvailable;
    titleType = aTitleType;
    
  }

  //------------------------
  // INTERFACE
  //------------------------

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

  public boolean setDescription(String aDescription)
  {
    boolean wasSet = false;
    description = aDescription;
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

  public boolean setIsAvailable(boolean aIsAvailable)
  {
    boolean wasSet = false;
    isAvailable = aIsAvailable;
    wasSet = true;
    return wasSet;
  }

  public boolean setTitleType(TitleType aTitleType)
  {
    boolean wasSet = false;
    titleType = aTitleType;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public int getTitleId()
  {
    return titleId;
  }

  public String getDescription()
  {
    return description;
  }

  public String getGenre()
  {
    return genre;
  }

  public boolean getIsAvailable()
  {
    return isAvailable;
  }

  public TitleType getTitleType()
  {
    return titleType;
  }
  /* Code from template association_GetOne */
  public Library getLibrary()
  {
    return library;
  }
  /* Code from template association_SetOneToMany */
  public boolean setLibrary(Library aLibrary)
  {
    boolean wasSet = false;
    if (aLibrary == null)
    {
      return wasSet;
    }

    Library existingLibrary = library;
    library = aLibrary;
    if (existingLibrary != null && !existingLibrary.equals(aLibrary))
    {
      existingLibrary.removeTitle(this);
    }
    library.addTitle(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Library placeholderLibrary = library;
    this.library = null;
    if(placeholderLibrary != null)
    {
      placeholderLibrary.removeTitle(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "titleId" + ":" + getTitleId()+ "," +
            "description" + ":" + getDescription()+ "," +
            "genre" + ":" + getGenre()+ "," +
            "isAvailable" + ":" + getIsAvailable()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "titleType" + "=" + (getTitleType() != null ? !getTitleType().equals(this)  ? getTitleType().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "library = "+(getLibrary()!=null?Integer.toHexString(System.identityHashCode(getLibrary())):"null");
  }
}