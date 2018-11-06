package hu.bme.dipterv.web.rest;

import com.codahale.metrics.annotation.Timed;
import hu.bme.dipterv.service.MusicianService;
import hu.bme.dipterv.web.rest.errors.BadRequestAlertException;
import hu.bme.dipterv.web.rest.util.HeaderUtil;
import hu.bme.dipterv.service.dto.MusicianDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Musician.
 */
@RestController
@RequestMapping("/api")
public class MusicianResource {

    private final Logger log = LoggerFactory.getLogger(MusicianResource.class);

    private static final String ENTITY_NAME = "musician";

    private final MusicianService musicianService;

    public MusicianResource(MusicianService musicianService) {
        this.musicianService = musicianService;
    }

    /**
     * POST  /musicians : Create a new musician.
     *
     * @param musicianDTO the musicianDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new musicianDTO, or with status 400 (Bad Request) if the musician has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/musicians")
    @Timed
    public ResponseEntity<MusicianDTO> createMusician(@RequestBody MusicianDTO musicianDTO) throws URISyntaxException {
        log.debug("REST request to save Musician : {}", musicianDTO);
        if (musicianDTO.getId() != null) {
            throw new BadRequestAlertException("A new musician cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MusicianDTO result = musicianService.save(musicianDTO);
        return ResponseEntity.created(new URI("/api/musicians/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /musicians : Updates an existing musician.
     *
     * @param musicianDTO the musicianDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated musicianDTO,
     * or with status 400 (Bad Request) if the musicianDTO is not valid,
     * or with status 500 (Internal Server Error) if the musicianDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/musicians")
    @Timed
    public ResponseEntity<MusicianDTO> updateMusician(@RequestBody MusicianDTO musicianDTO) throws URISyntaxException {
        log.debug("REST request to update Musician : {}", musicianDTO);
        if (musicianDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MusicianDTO result = musicianService.save(musicianDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, musicianDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /musicians : get all the musicians.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of musicians in body
     */
    @GetMapping("/musicians")
    @Timed
    public List<MusicianDTO> getAllMusicians() {
        log.debug("REST request to get all Musicians");
        return musicianService.findAll();
    }

    /**
     * GET  /musicians/:id : get the "id" musician.
     *
     * @param id the id of the musicianDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the musicianDTO, or with status 404 (Not Found)
     */
    @GetMapping("/musicians/{id}")
    @Timed
    public ResponseEntity<MusicianDTO> getMusician(@PathVariable Long id) {
        log.debug("REST request to get Musician : {}", id);
        Optional<MusicianDTO> musicianDTO = musicianService.findOne(id);
        return ResponseUtil.wrapOrNotFound(musicianDTO);
    }

    /**
     * DELETE  /musicians/:id : delete the "id" musician.
     *
     * @param id the id of the musicianDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/musicians/{id}")
    @Timed
    public ResponseEntity<Void> deleteMusician(@PathVariable Long id) {
        log.debug("REST request to delete Musician : {}", id);
        musicianService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
