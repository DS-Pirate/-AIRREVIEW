package ds.pirate.backend.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ds.pirate.backend.entity.ArticlesList;

@Repository
public interface ArticleRepository extends JpaRepository<ArticlesList, String> {

        @EntityGraph(attributePaths = { "tags", "images", "cgroup" }, type = EntityGraphType.LOAD)
        @Query(value = "select air from ArticlesList air ")
        Page<ArticlesList> getArticleList(Pageable pageable);

        ArticlesList getByAid(Long aid);

        @Query("select a from ArticlesList a")
        List<ArticlesList> getList();
        // Optioanl 사용할 이유가 없어보임(딱히 null값으로 나올만한게 없어보이고, 필요하면 사용 예정)

        @Query("select aUser from ArticlesList a where a.aid=:aid")
        Long getArticleAuthorIdByAid(Long aid);

        @Query("SELECT a FROM ArticlesList a WHERE a_user =:userid and aid=:aid ")
        Optional<ArticlesList> getArticleByAidAndUserId(Long aid, Long userid);

        ArticlesList findByAid(Long aid);

        @Query("SELECT u.airName as airName, a.aid as aid, a.atitle as atitle, a.context as context, a.regDate as regDate, "
                        +
                        "a.opend as opend, avg(c.articleRate) as articleRate, COUNT(l.favid) as likeCount " +
                        "FROM ArticlesList a left join  airUser u on u.userid = a.aUser " +
                        "left join HashTags h on h.articles = a.aid " +
                        "left join acomments c on c.articles = a.aid " +
                        "left join likeUnlikeList l on l.aid = a.aid " +
                        "where a.opend = 1L " +
                        "group by a.aid")
        Optional<List<getEmbedCardsInformation>> getListAndAuthor(Sort sort);

        @Query("SELECT u.airName as airName, a.aid as aid, a.atitle as atitle, a.context as context, a.regDate as regDate, "
                        +
                        "a.opend as opend, avg(c.articleRate) as articleRate, COUNT(l.favid) as likeCount " +
                        "FROM ArticlesList a left join  airUser u on u.userid = a.aUser " +
                        "left join HashTags h on h.articles = a.aid " +
                        "left join acomments c on c.articles = a.aid " +
                        "left join likeUnlikeList l on l.aid = a.aid " +
                        "left join subscribList s on s.targetId = a.aUser " +
                        "where s.userid.userid=:userid and a.opend = 1L " +
                        "group by a.aid ")
        Optional<List<getEmbedCardsInformation>> getCardsListBySub(Long userid, Sort sort);

        @Query("SELECT a FROM ArticlesList a WHERE a_user=:userid ")
        List<ArticlesList> getListbyuserId(Long userid);

        @Query("SELECT u.airName as airName, a.aid as aid, a.atitle as atitle, a.context as context, a.regDate as regDate, "
                        +
                        "a.opend as opend, avg(c.articleRate) as articleRate, COUNT(l.favid) as likeCount " +
                        "FROM ArticlesList a left join  airUser u on u.userid = a.aUser " +
                        "left join HashTags h on h.articles = a.aid " +
                        "left join acomments c on c.articles = a.aid " +
                        "left join likeUnlikeList l on l.aid = a.aid " +
                        "where a.opend = 1L AND (u.airName LIKE CONCAT('%',:search,'%') Or " +
                        "a.atitle LIKE CONCAT('%',:search,'%') Or " +
                        "h.hashTagName LIKE CONCAT('%',:search,'%')) " +
                        "group by a.aid")

        Optional<List<getEmbedCardsInformation>> getListAndAuthorByAuthorOrAtitle(String search, Sort sort);

        @Query("SELECT u.airName as airName, a.aid as aid, a.atitle as atitle, a.context as context, a.regDate as regDate, "
                        +
                        "a.opend as opend, avg(c.articleRate) as articleRate, COUNT(l.favid) as likeCount " +
                        "FROM ArticlesList a left join  airUser u on u.userid = a.aUser " +
                        "left join HashTags h on h.articles = a.aid " +
                        "left join acomments c on c.articles = a.aid " +
                        "left join likeUnlikeList l on l.aid = a.aid " +
                        "where a.opend = 1L " +
                        "group by a.aid")
        Optional<Page<getEmbedCardsInformation>> getListAndAuthor2(Pageable pageable);

        @Query("SELECT aid " +
                        "FROM ArticlesList " +
                        "where opend = 1L " +
                        "group by aid")
        Page<ArticlesList> getListAndAuthorPage(Pageable pageable);

        @Query("SELECT u.airName as airName, a.aid as aid, a.atitle as atitle, a.context as context, a.regDate as regDate, "
                        +
                        "a.opend as opend, avg(c.articleRate) as articleRate, COUNT(l.favid) as likeCount " +
                        "FROM ArticlesList a left join  airUser u on u.userid = a.aUser " +
                        "left join HashTags h on h.articles = a.aid " +
                        "left join acomments c on c.articles = a.aid " +
                        "left join likeUnlikeList l on l.aid = a.aid " +
                        "left join subscribList s on s.targetId = a.aUser " +
                        "where s.userid.userid=:userid and a.opend = 1L " +
                        "group by a.aid ")
        Optional<List<getEmbedCardsInformation>> getCardsListBySub2(Long userid, Sort sort);

        // "ORDER BY a.aid DESC"
        @Query("update ArticlesList a set a.opencount = a.opencount+1 where a.aid=:aid")
        @Modifying
        void updateOpencount(Long aid);

        @Query("SELECT att.regDate as regdate, att.atitle as title, att.context as context, au.airName as author, (select count(fav) from likeUnlikeList as fav where fav.aid=:article) as favcount, ((select AVG(articleRate) FROM acomments ct WHERE articles_aid=:article and articleRate>0)) as avgrate "
                        +
                        "FROM ArticlesList att left join airUser au on att.aUser = au.userid " +
                        "WHERE aid=:article")
        Optional<getEmbedInformation> getEmbedInfoByAid(Long article);

        @Query("SELECT a FROM ArticlesList a WHERE a_user=:userid ")
        Optional<List<ArticlesList>> getListbyuserId2(Long userid);

        @Query(value = "select at.aid as aid, at.atitle as atitle, " +
                        "(select count(ar.aid) from articles_list as ar left join like_unlike_list as li on ar.aid = li.aid where  at.aid = ar.aid) as favCount, "
                        +
                        "(select count(ar.aid) from articles_list as ar left join save_list as sav on ar.aid = sav.aid where at.aid = ar.aid) as saveCount, "
                        +
                        "at.opencount as openCount, at.regdate as regDate " +
                        "from articles_list as at " +
                        "where at.a_user=:userid", countQuery = "select at.aid as aid, at.atitle as atitle, " +
                                        "(select count(ar.aid) from articles_list as ar left join like_unlike_list as li on ar.aid = li.aid where  at.aid = ar.aid) as favCount, "
                                        +
                                        "(select count(ar.aid) from articles_list as ar left join save_list as sav on ar.aid = sav.aid where at.aid = ar.aid) as saveCount, "
                                        +
                                        "at.opencount as openCount, at.regdate as regDate " +
                                        "from articles_list as at " +
                                        "where at.a_user=:userid", nativeQuery = true)
        Page<getMyChannelArticleList> getArticleListByUserIdWithPageable(Long userid, Pageable pageable);

        public interface getEmbedInformation {
                LocalDateTime getRegdate();

                String getTitle();

                byte[] getContext();

                String getAuthor();

                Long getFavcount();

                Double getAvgrate();
        }

        public interface getEmbedCardsInformation {
                String getAirName();

                Long getAid();

                String getAtitle();

                byte[] getContext();

                LocalDateTime getRegDate();

                boolean getOpend();

                Integer getArticleRate();

                Long getLikeCount();
        }

        public interface getMyChannelArticleList {
                Long getAid();

                String getAtitle();

                Integer getFavCount();

                Integer getSaveCount();

                Integer getOpenCount();

                LocalDateTime getRegDate();
        }

        @Query(value = "select at.aid as aid, at.atitle as atitle, " +
                        "(select count(ar.aid) from articles_list as ar left join like_unlike_list as li on ar.aid = li.aid where  at.aid = ar.aid) as favCount, "
                        +
                        "(select count(ar.aid) from articles_list as ar left join save_list as sav on ar.aid = sav.aid where at.aid = ar.aid) as saveCount, "
                        +
                        "at.opencount as openCount, at.regdate as regDate " +
                        "from articles_list as at " +
                        "where at.a_user=:userid", countQuery = "select at.aid as aid, at.atitle as atitle, " +
                                        "(select count(ar.aid) from articles_list as ar left join like_unlike_list as li on ar.aid = li.aid where  at.aid = ar.aid) as favCount, "
                                        +
                                        "(select count(ar.aid) from articles_list as ar left join save_list as sav on ar.aid = sav.aid where at.aid = ar.aid) as saveCount, "
                                        +
                                        "at.opencount as openCount, at.regdate as regDate " +
                                        "from articles_list as at " +
                                        "where at.a_user=:userid", nativeQuery = true)
        Page<getMySettingArticleList> getSettingArticleListByUserIdWithPageable(Long userid, Pageable pageable);

        public interface getMySettingArticleList {
                Long getAid();

                String getAtitle();

                Byte[] getContext();

                Integer getFavCount();

                Integer getSaveCount();

                Integer getOpenCount();

                LocalDateTime getRegDate();
        }

}
