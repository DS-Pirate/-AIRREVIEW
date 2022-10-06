package ds.pirate.backend.admin.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import ds.pirate.backend.entity.ArticlesList;
import ds.pirate.backend.repository.ArticleRepository.getEmbedInformation;

@Repository
public interface AdminArticleRepository  extends JpaRepository<ArticlesList, Long>, QuerydslPredicateExecutor<ArticlesList> {
    
    @Query("select att from ArticlesList att where aUser =:userid")
    Optional<List<ArticlesList>> getArticlesListByUserid(Long userid);

    @Query("SELECT att.regDate as regdate, att.atitle as title, att.context as context, au.airName as author, (select count(fav) from likeUnlikeList as fav where fav.aid=:article) as favcount, ((select AVG(articleRate) FROM acomments ct WHERE articles_aid=:article and articleRate>0)) as avgrate, img.fileName as imageList "
    +
    "FROM ArticlesList att left join airUser au on att.aUser = au.userid left join ImagesList img on att.aid = img.articles " +
    "WHERE aid=:article")
    Page<getEmbedInformation> getEmbedInfoByAid(Long article, Pageable pageable);
}
