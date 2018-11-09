package hu.bme.dipterv.service.mapper;

import hu.bme.dipterv.domain.*;
import hu.bme.dipterv.service.dto.MusicianDTO;

import org.mapstruct.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Mapper for the entity Musician and its DTO MusicianDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MusicianMapper extends EntityMapper<MusicianDTO, Musician> {


    @Mapping(target = "tracks", ignore = true)
    @Mapping(target = "bands", ignore = true)
    Musician toEntity(MusicianDTO musicianDTO);

    default Musician fromId(Long id) {
        if (id == null) {
            return null;
        }
        Musician musician = new Musician();
        musician.setId(id);
        return musician;
    }
}
