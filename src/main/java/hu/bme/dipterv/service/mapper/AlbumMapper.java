package hu.bme.dipterv.service.mapper;

import hu.bme.dipterv.domain.*;
import hu.bme.dipterv.service.dto.AlbumDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Album and its DTO AlbumDTO.
 */
@Mapper(componentModel = "spring", uses = {BandMapper.class})
public interface AlbumMapper extends EntityMapper<AlbumDTO, Album> {

    @Mapping(source = "band.id", target = "bandId")
    AlbumDTO toDto(Album album);

    @Mapping(source = "bandId", target = "band")
    Album toEntity(AlbumDTO albumDTO);

    default Album fromId(Long id) {
        if (id == null) {
            return null;
        }
        Album album = new Album();
        album.setId(id);
        return album;
    }
}
