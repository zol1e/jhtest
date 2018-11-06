package hu.bme.dipterv.service.mapper;

import hu.bme.dipterv.domain.*;
import hu.bme.dipterv.service.dto.TrackDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Track and its DTO TrackDTO.
 */
@Mapper(componentModel = "spring", uses = {BandMapper.class, MusicianMapper.class})
public interface TrackMapper extends EntityMapper<TrackDTO, Track> {

    @Mapping(source = "band.id", target = "bandId")
    TrackDTO toDto(Track track);

    @Mapping(source = "bandId", target = "band")
    Track toEntity(TrackDTO trackDTO);

    default Track fromId(Long id) {
        if (id == null) {
            return null;
        }
        Track track = new Track();
        track.setId(id);
        return track;
    }
}
