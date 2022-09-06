package ds.pirate.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ds.pirate.backend.entity.QuestionsList;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionsList, Long> {
  
}
