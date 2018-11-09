package hu.bme.dipterv.service.mapper;

import hu.bme.dipterv.domain.*;
import hu.bme.dipterv.service.dto.BandDTO;

import org.mapstruct.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Mapper for the entity Band and its DTO BandDTO.
 */
@Mapper(componentModel = "spring", uses = {MusicianMapper.class})
public interface BandMapper extends EntityMapper<BandDTO, Band> {


    @Mapping(target = "albums", ignore = true)
    @Mapping(target = "tracks", ignore = true)
    Band toEntity(BandDTO bandDTO);

    default Band fromId(Long id) {
        if (id == null) {
            return null;
        }
        Band band = new Band();
        band.setId(id);
        return band;
    }
}
