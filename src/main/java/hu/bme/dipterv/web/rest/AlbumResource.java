package hu.bme.dipterv.web.rest;

import com.codahale.metrics.annotation.Timed;
import hu.bme.dipterv.service.AlbumService;
import hu.bme.dipterv.web.rest.errors.BadRequestAlertException;
import hu.bme.dipterv.web.rest.util.HeaderUtil;
import hu.bme.dipterv.service.dto.AlbumDTO;
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
 * REST controller for managing Album.
 */
@RestController
@RequestMapping("/api")
public class AlbumResource {

    private final Logger log = LoggerFactory.getLogger(AlbumResource.class);

    private static final String ENTITY_NAME = "album";

    private final AlbumService albumService;

    public AlbumResource(AlbumService albumService) {
        this.albumService = albumService;
    }

    /**
     * POST  /albums : Create a new album.
     *
     * @param albumDTO the albumDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new albumDTO, or with status 400 (Bad Request) if the album has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/albums")
    @Timed
    public ResponseEntity<AlbumDTO> createAlbum(@RequestBody AlbumDTO albumDTO) throws URISyntaxException {
        log.debug("REST request to save Album : {}", albumDTO);
        if (albumDTO.getId() != null) {
            throw new BadRequestAlertException("A new album cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AlbumDTO result = albumService.save(albumDTO);
        return ResponseEntity.created(new URI("/api/albums/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /albums : Updates an existing album.
     *
     * @param albumDTO the albumDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated albumDTO,
     * or with status 400 (Bad Request) if the albumDTO is not valid,
     * or with status 500 (Internal Server Error) if the albumDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/albums")
    @Timed
    public ResponseEntity<AlbumDTO> updateAlbum(@RequestBody AlbumDTO albumDTO) throws URISyntaxException {
        log.debug("REST request to update Album : {}", albumDTO);
        if (albumDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AlbumDTO result = albumService.save(albumDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, albumDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /albums : get all the albums.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of albums in body
     */
    @GetMapping("/albums")
    @Timed
    public List<AlbumDTO> getAllAlbums() {
        log.debug("REST request to get all Albums");
        return albumService.findAll();
    }

    /**
     * GET  /albums/:id : get the "id" album.
     *
     * @param id the id of the albumDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the albumDTO, or with status 404 (Not Found)
     */
    @GetMapping("/albums/{id}")
    @Timed
    public ResponseEntity<AlbumDTO> getAlbum(@PathVariable Long id) {
        log.debug("REST request to get Album : {}", id);
        Optional<AlbumDTO> albumDTO = albumService.findOne(id);
        return ResponseUtil.wrapOrNotFound(albumDTO);
    }

    /**
     * DELETE  /albums/:id : delete the "id" album.
     *
     * @param id the id of the albumDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/albums/{id}")
    @Timed
    public ResponseEntity<Void> deleteAlbum(@PathVariable Long id) {
        log.debug("REST request to delete Album : {}", id);
        albumService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
