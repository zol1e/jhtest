package hu.bme.dipterv.service.impl;

import hu.bme.dipterv.service.MusicianService;
import hu.bme.dipterv.domain.Musician;
import hu.bme.dipterv.repository.MusicianRepository;
import hu.bme.dipterv.service.dto.MusicianDTO;
import hu.bme.dipterv.service.mapper.MusicianMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Musician.
 */
@Service
@Transactional
public class MusicianServiceImpl implements MusicianService {

    private final Logger log = LoggerFactory.getLogger(MusicianServiceImpl.class);

    private final MusicianRepository musicianRepository;

    private final MusicianMapper musicianMapper;

    public MusicianServiceImpl(MusicianRepository musicianRepository, MusicianMapper musicianMapper) {
        this.musicianRepository = musicianRepository;
        this.musicianMapper = musicianMapper;
    }

    /**
     * Save a musician.
     *
     * @param musicianDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MusicianDTO save(MusicianDTO musicianDTO) {
        log.debug("Request to save Musician : {}", musicianDTO);
        Musician musician = musicianMapper.toEntity(musicianDTO);
        musician = musicianRepository.save(musician);
        return musicianMapper.toDto(musician);
    }

    /**
     * Get all the musicians.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<MusicianDTO> findAll() {
        log.debug("Request to get all Musicians");
        return musicianRepository.findAll().stream()
            .map(musicianMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one musician by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MusicianDTO> findOne(Long id) {
        log.debug("Request to get Musician : {}", id);
        return musicianRepository.findById(id)
            .map(musicianMapper::toDto);
    }

    /**
     * Delete the musician by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Musician : {}", id);
        musicianRepository.deleteById(id);
    }
}
