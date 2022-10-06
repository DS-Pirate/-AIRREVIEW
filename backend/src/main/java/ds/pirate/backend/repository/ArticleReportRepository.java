package ds.pirate.backend.repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ds.pirate.backend.entity.airUser;
import ds.pirate.backend.entity.reportList;

@Repository
public interface ArticleReportRepository extends JpaRepository<reportList, Long> {
    List<reportList> findByReid(Long reid);

    @Query("SELECT re FROM reportList re WHERE userid_userid=:userid and articles_aid=:articles")
    Optional<reportList> checkReportLogByUserIdAndArticleId(Long userid, Long articles);

    List<reportList> findByUserid(airUser userid);

    @Query("SELECT re FROM reportList re WHERE userid_userid=:userid")
    Page<reportList> findByUseridWithPageable(Long userid, Pageable pageable);
}
