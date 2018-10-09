package hu.bme.dipterv.repository;

import hu.bme.dipterv.domain.Article;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Article entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("select article from Article article where article.writer.login = ?#{principal.username}")
    List<Article> findByWriterIsCurrentUser();

}
