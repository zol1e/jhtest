package hu.bme.dipterv.web.rest;

import com.codahale.metrics.annotation.Timed;
import hu.bme.dipterv.service.BandService;
import hu.bme.dipterv.web.rest.errors.BadRequestAlertException;
import hu.bme.dipterv.web.rest.util.HeaderUtil;
import hu.bme.dipterv.service.dto.BandDTO;
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
 * REST controller for managing Band.
 */
@RestController
@RequestMapping("/api")
public class BandResource {

    private final Logger log = LoggerFactory.getLogger(BandResource.class);

    private static final String ENTITY_NAME = "band";

    private final BandService bandService;

    public BandResource(BandService bandService) {
        this.bandService = bandService;
    }

    /**
     * POST  /bands : Create a new band.
     *
     * @param bandDTO the bandDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new bandDTO, or with status 400 (Bad Request) if the band has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/bands")
    @Timed
    public ResponseEntity<BandDTO> createBand(@RequestBody BandDTO bandDTO) throws URISyntaxException {
        log.debug("REST request to save Band : {}", bandDTO);
        if (bandDTO.getId() != null) {
            throw new BadRequestAlertException("A new band cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BandDTO result = bandService.save(bandDTO);
        return ResponseEntity.created(new URI("/api/bands/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /bands : Updates an existing band.
     *
     * @param bandDTO the bandDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated bandDTO,
     * or with status 400 (Bad Request) if the bandDTO is not valid,
     * or with status 500 (Internal Server Error) if the bandDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/bands")
    @Timed
    public ResponseEntity<BandDTO> updateBand(@RequestBody BandDTO bandDTO) throws URISyntaxException {
        log.debug("REST request to update Band : {}", bandDTO);
        if (bandDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BandDTO result = bandService.save(bandDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, bandDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /bands : get all the bands.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of bands in body
     */
    @GetMapping("/bands")
    @Timed
    public List<BandDTO> getAllBands(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all Bands");
        return bandService.findAll();
    }

    /**
     * GET  /bands/:id : get the "id" band.
     *
     * @param id the id of the bandDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the bandDTO, or with status 404 (Not Found)
     */
    @GetMapping("/bands/{id}")
    @Timed
    public ResponseEntity<BandDTO> getBand(@PathVariable Long id) {
        log.debug("REST request to get Band : {}", id);
        Optional<BandDTO> bandDTO = bandService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bandDTO);
    }

    /**
     * DELETE  /bands/:id : delete the "id" band.
     *
     * @param id the id of the bandDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/bands/{id}")
    @Timed
    public ResponseEntity<Void> deleteBand(@PathVariable Long id) {
        log.debug("REST request to delete Band : {}", id);
        bandService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
