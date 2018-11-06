package hu.bme.dipterv.repository;

import hu.bme.dipterv.domain.Musician;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Musician entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MusicianRepository extends JpaRepository<Musician, Long> {

}
