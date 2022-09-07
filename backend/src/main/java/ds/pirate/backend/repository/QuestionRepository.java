package ds.pirate.backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ds.pirate.backend.entity.QuestionsList;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionsList, Long> {
  
}
