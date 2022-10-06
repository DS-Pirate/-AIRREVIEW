package ds.pirate.backend.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ds.pirate.backend.entity.QuestionsList;
import ds.pirate.backend.entity.airUser;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionsList, Long> {

  @Query("SELECT que FROM QuestionsList que WHERE userid=:userid")
  List<QuestionsList> getQuestionList(airUser userid);

  @Query("SELECT que FROM QuestionsList que WHERE userid_userid=:userid")
  Page<QuestionsList> getQuestionListWithPageable(Long userid, Pageable pageable);
}
