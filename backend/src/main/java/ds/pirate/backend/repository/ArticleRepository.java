package ds.pirate.backend.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import ds.pirate.backend.dto.ArticleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.stereotype.Repository;

import ds.pirate.backend.entity.ArticlesList;
import ds.pirate.backend.entity.airUser;


@Repository
public interface ArticleRepository extends JpaRepository<ArticlesList,String> {
    @EntityGraph(attributePaths = { "tags", "images", "cgroup" }, type = EntityGraphType.LOAD)
    @Query(
        value = "select air from ArticlesList air "
      )
    Page<ArticlesList> getArticleList(Pageable pageable);

    ArticlesList getByAid(Long aid);

    @Query("select a from ArticlesList a")
    List<ArticlesList> getList();
      //Optioanl 사용할 이유가 없어보임(딱히 null값으로 나올만한게 없어보이고, 필요하면 사용 예정)

    @Query("select aUser from ArticlesList a where a.aid=:aid")
    Long getArticleAuthorIdByAid(Long aid);
    

    @Query("SELECT a FROM ArticlesList a WHERE a_user =:userid and aid=:aid ")
    Optional<ArticlesList> getArticleByAidAndUserId(Long aid, Long userid);

    ArticlesList findByAid(Long aid);

    @Query("SELECT u.airName ,a.aid, a.atitle, a.context, a.regDate, a.opend " +
            "FROM ArticlesList a left join  airUser u " +
            "on u.userid = a.aUser "+
            "ORDER BY a.aid DESC")
    List<Object[]> getListAndAuthor();

    @Query("SELECT a FROM ArticlesList a WHERE a_user=:userid ")
    List<ArticlesList> getListbyuserId(Long userid);
}
