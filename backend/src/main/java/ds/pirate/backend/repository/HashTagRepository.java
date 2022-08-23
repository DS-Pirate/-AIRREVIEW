package ds.pirate.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ds.pirate.backend.entity.HashTags;
@Repository
public interface HashTagRepository extends JpaRepository<HashTags, Long>{
    
}
