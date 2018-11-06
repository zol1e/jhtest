package hu.bme.dipterv.repository;

import hu.bme.dipterv.domain.Track;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Track entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TrackRepository extends JpaRepository<Track, Long> {

    @Query(value = "select distinct track from Track track left join fetch track.performers",
        countQuery = "select count(distinct track) from Track track")
    Page<Track> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct track from Track track left join fetch track.performers")
    List<Track> findAllWithEagerRelationships();

    @Query("select track from Track track left join fetch track.performers where track.id =:id")
    Optional<Track> findOneWithEagerRelationships(@Param("id") Long id);

}
