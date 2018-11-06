package hu.bme.dipterv.service;

import hu.bme.dipterv.service.dto.MusicianDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Musician.
 */
public interface MusicianService {

    /**
     * Save a musician.
     *
     * @param musicianDTO the entity to save
     * @return the persisted entity
     */
    MusicianDTO save(MusicianDTO musicianDTO);

    /**
     * Get all the musicians.
     *
     * @return the list of entities
     */
    List<MusicianDTO> findAll();


    /**
     * Get the "id" musician.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<MusicianDTO> findOne(Long id);

    /**
     * Delete the "id" musician.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
