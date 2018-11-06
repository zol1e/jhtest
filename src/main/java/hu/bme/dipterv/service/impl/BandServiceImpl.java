package hu.bme.dipterv.service.impl;

import hu.bme.dipterv.service.BandService;
import hu.bme.dipterv.domain.Band;
import hu.bme.dipterv.repository.BandRepository;
import hu.bme.dipterv.service.dto.BandDTO;
import hu.bme.dipterv.service.mapper.BandMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Band.
 */
@Service
@Transactional
public class BandServiceImpl implements BandService {

    private final Logger log = LoggerFactory.getLogger(BandServiceImpl.class);

    private final BandRepository bandRepository;

    private final BandMapper bandMapper;

    public BandServiceImpl(BandRepository bandRepository, BandMapper bandMapper) {
        this.bandRepository = bandRepository;
        this.bandMapper = bandMapper;
    }

    /**
     * Save a band.
     *
     * @param bandDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public BandDTO save(BandDTO bandDTO) {
        log.debug("Request to save Band : {}", bandDTO);
        Band band = bandMapper.toEntity(bandDTO);
        band = bandRepository.save(band);
        return bandMapper.toDto(band);
    }

    /**
     * Get all the bands.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<BandDTO> findAll() {
        log.debug("Request to get all Bands");
        return bandRepository.findAllWithEagerRelationships().stream()
            .map(bandMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the Band with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<BandDTO> findAllWithEagerRelationships(Pageable pageable) {
        return bandRepository.findAllWithEagerRelationships(pageable).map(bandMapper::toDto);
    }
    

    /**
     * Get one band by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BandDTO> findOne(Long id) {
        log.debug("Request to get Band : {}", id);
        return bandRepository.findOneWithEagerRelationships(id)
            .map(bandMapper::toDto);
    }

    /**
     * Delete the band by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Band : {}", id);
        bandRepository.deleteById(id);
    }
}
