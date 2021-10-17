package ca.mcgill.ecse321.librarymanagement.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.librarymanagement.model.MusicAlbum;

public interface MusicAlbumRepository extends CrudRepository<MusicAlbum, Integer>{
	
	MusicAlbum findMusicAlbumByTitleId(int titleId);

}
