package ds.pirate.backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ds.pirate.backend.entity.alarm;

public interface AlarmRepository extends JpaRepository<alarm, Long>{
    
    alarm findByArticleId(Long articleId);

}
