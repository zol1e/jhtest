package hu.bme.dipterv.service.mapper;

import hu.bme.dipterv.domain.*;
import hu.bme.dipterv.service.dto.ArticleDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Article and its DTO ArticleDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface ArticleMapper extends EntityMapper<ArticleDTO, Article> {

    @Mapping(source = "writer.id", target = "writerId")
    ArticleDTO toDto(Article article);

    @Mapping(source = "writerId", target = "writer")
    Article toEntity(ArticleDTO articleDTO);

    default Article fromId(Long id) {
        if (id == null) {
            return null;
        }
        Article article = new Article();
        article.setId(id);
        return article;
    }
}
