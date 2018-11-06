package hu.bme.dipterv.service;

import hu.bme.dipterv.service.dto.BandDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Band.
 */
public interface BandService {

    /**
     * Save a band.
     *
     * @param bandDTO the entity to save
     * @return the persisted entity
     */
    BandDTO save(BandDTO bandDTO);

    /**
     * Get all the bands.
     *
     * @return the list of entities
     */
    List<BandDTO> findAll();

    /**
     * Get all the Band with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<BandDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" band.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<BandDTO> findOne(Long id);

    /**
     * Delete the "id" band.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
