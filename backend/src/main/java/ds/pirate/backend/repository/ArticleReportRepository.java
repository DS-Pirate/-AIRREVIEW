package ds.pirate.backend.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ds.pirate.backend.entity.reportList;

@Repository
public interface ArticleReportRepository extends JpaRepository<reportList, Long> {
    List<reportList> findByReid(Long reid);

    @Query("SELECT re FROM reportList re WHERE userid_userid=:userid and articles_aid=:articles")
    Optional<reportList> checkReportLogByUserIdAndArticleId(Long userid, Long articles);

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
    Page<getMySettingReportList> getSettingReportListByUserIdWithPageable(Long userid, Pageable pageable);

    public interface getMySettingReportList {
        Long getReid();

        Byte[] getContext();

        Long getAid();

        LocalDateTime getRegDate();
    }
}
